package com.example.root.imc;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by root on 25/04/18.
 */


@Entity
public class Aluno {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nome;
    public double peso;
    public double altura;
    public double imc;
    public Integer idade;
    public String classificacao;
    public Integer sexo;

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", peso=" + peso +
                ", altura=" + altura +
                ", imc=" + imc +
                ", idade=" + idade +
                ", classificacao='" + classificacao + '\'' +
                ", sexo=" + sexo +
                '}';
    }
}
