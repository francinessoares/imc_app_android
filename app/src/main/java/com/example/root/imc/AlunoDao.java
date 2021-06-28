package com.example.root.imc;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by root on 25/04/18.
 */

@Dao
public interface AlunoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long inserir(Aluno item);

    @Query("SELECT * from Aluno")
    List<Aluno> listar();
}
