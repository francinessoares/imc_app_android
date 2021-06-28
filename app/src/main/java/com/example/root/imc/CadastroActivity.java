package com.example.root.imc;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Fonte: http://www.calculoimc.com.br/
 */
public class CadastroActivity extends AppCompatActivity {

    private static BancoDeDados banco;
    private BancoDeDados base;
    private Aluno aluno;

    static BancoDeDados bancoDeDados(Context context) {
        if (banco == null)
            banco = Room.databaseBuilder(context,
                    BancoDeDados.class, "base-1").build();
        return banco;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        base = bancoDeDados(getApplicationContext());

        Button voltar = (Button) findViewById(R.id.voltar);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        final EditText campoIdade = (EditText) findViewById(R.id.idade);
        final RadioGroup radioGrupo = (RadioGroup) findViewById(R.id.radioGroup);

        campoIdade.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Integer idade = Integer.valueOf(campoIdade.getText().toString());
                    if (idade != null && idade <= 15) {
                        radioGrupo.setVisibility(View.VISIBLE);
                    } else {
                        radioGrupo.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });


        Button botaSalvar = (Button) findViewById(R.id.calcularButton);
        botaSalvar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView campoMensagemUsuario = (TextView) findViewById(R.id.imcTextView);
                        EditText campoAltura = (EditText) findViewById(R.id.altura);
                        EditText campoPeso = (EditText) findViewById(R.id.peso);
                        EditText campoNome = (EditText) findViewById(R.id.nome);

                        int idRadioSelecionado = radioGrupo.getCheckedRadioButtonId();

                        try {
                            Integer idade = Integer.valueOf(campoIdade.getText().toString());
                            Double altura = Double.valueOf(campoAltura.getText().toString());
                            Double peso = Double.valueOf(campoPeso.getText().toString());
                            String nome = campoNome.getText().toString();

                            boolean possuiErroPreenchimento = peso == null || peso <= 0 || altura == null || altura <= 0||
                                    nome == null || nome.isEmpty() || idade == null;

                            if(!possuiErroPreenchimento) {

                                AlunoDao alunoDao = banco.itemDao();
                                aluno = new Aluno();
                                aluno.altura = altura;
                                aluno.peso = peso;
                                aluno.idade = idade;
                                if (idRadioSelecionado == R.id.mascRadio) {
                                    aluno.sexo = 0;
                                } else if (idRadioSelecionado == R.id.femRadio) {
                                    aluno.sexo = 1;
                                }
                                aluno.nome = nome;

                                CalculadoraImc calc = new CalculadoraImc();
                                aluno = calc.calcula(aluno, idRadioSelecionado);

                                insereAluno();

                                campoNome.setText("");
                                campoIdade.setText("");
                                campoAltura.setText("");
                                campoPeso.setText("");
                                radioGrupo.setVisibility(View.INVISIBLE);

                                campoMensagemUsuario.setTextColor(Color.parseColor("#2C812C"));
                                campoMensagemUsuario.setText("Aluno(a) '"+nome+"' inserido(a) com sucesso!");

                            }else{
                                throw new Exception("Todos os campos devem ser preenchidos.");
                            }
                        } catch (Exception e) {
                            campoMensagemUsuario.setTextColor(Color.RED);
                            campoMensagemUsuario.setText("Erro! "+ e.getMessage());
                        }

                    }
                }
        );
    }

    public void openMainActivity() {
        Intent MainActivity = new Intent(this, MainActivity.class);
        startActivity(MainActivity);
    }

    private void insereAluno(){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                base.itemDao().inserir(aluno);
                return null;
            }
        }.execute();
    }




}