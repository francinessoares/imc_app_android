package com.example.root.imc;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    static Object tranca = new Object();
    private Handler handlerThreadPrincipal;
    private Executor executorThreadDoBanco;

    private static BancoDeDados banco;
    private BancoDeDados base;
    private Aluno aluno;
    private List<Aluno> itens;
    private List<Map<String, String>> listaAtual;

    static BancoDeDados bancoDeDados(Context context) {
        if (banco == null)
            banco = Room.databaseBuilder(context,
                    BancoDeDados.class, "base-1").build();
        return banco;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handlerThreadPrincipal = new Handler(Looper.getMainLooper());
        executorThreadDoBanco = Executors.newSingleThreadExecutor();

        base = bancoDeDados(getApplicationContext());

        AlunoDao dao = base.itemDao();

        rodarNaThreadDoBanco(
                new Runnable() {
                    @Override
                    public void run() {
                        itens = base.itemDao().listar();
                        rodarNaThreadPrincipal(new Runnable() {
                            @Override
                            public void run() {
                                preencherConteudo();
                            }
                        });
                    }
                });

        Button cadastrarAluno = (Button) findViewById(R.id.cadastrarAluno);
        cadastrarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCadastroActivity();
            }
        });
    }

    public void openCadastroActivity() {
        Intent CadastroActivity = new Intent(this, CadastroActivity.class);
        startActivity(CadastroActivity);
    }

    private void preencherConteudo() {

        DecimalFormat df = new DecimalFormat("0.##");

        TableLayout tableLayout = findViewById(R.id.tabelaAluno);

        TableRow header = new TableRow(this);
        preencheHeader(header);
        header.setBackgroundColor(Color.parseColor("#FF3F51B5"));
        header.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        tableLayout.addView(header);

        if (itens != null) {
            for(Aluno a : itens){
                TableRow row = new TableRow(this);

                TextView nome = new TextView(this);
                setEstilo(nome);
                nome.setText(a.nome);
                row.addView(nome);

                TextView imc = new TextView(this);
                setEstilo(imc);
                imc.setText(df.format(a.imc));
                row.addView(imc);

                TextView cla = new TextView(this);
                setEstilo(cla);
                cla.setText(a.classificacao.length() > 10 ? a.classificacao.substring(0,10) : a.classificacao);
                row.addView(cla);

                tableLayout.addView(row);
            }
        }
    }

    public void setEstiloHeader(TextView tv) {
        setEstilo(tv);
        tv.setTextColor(Color.WHITE);
    }

    public void setEstilo(TextView tv){
        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(16);
        tv.setPadding(5, 5, 5, 10);
    }

    public void  preencheHeader(TableRow header){
        TextView coluna = new TextView(this);
        setEstiloHeader(coluna);
        coluna.setText("Nome");
        header.addView(coluna);

        coluna = new TextView(this);
        setEstiloHeader(coluna);
        coluna.setText("IMC");
        header.addView(coluna);

        coluna = new TextView(this);
        setEstiloHeader(coluna);
        coluna.setText("Classificação");
        header.addView(coluna);

    }

    void rodarNaThreadPrincipal(Runnable acao) {
        handlerThreadPrincipal.post(acao);
    }

    void rodarNaThreadDoBanco(Runnable acao) {
        executorThreadDoBanco.execute(acao);
    }

}
