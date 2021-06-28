package com.example.root.imc;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Aluno.class}, version = 2, exportSchema = false)
public abstract class BancoDeDados extends RoomDatabase {
    public abstract AlunoDao itemDao();
}
