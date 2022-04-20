package com.majesty.android_test.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.majesty.android_test.model.pokemoncatch.PokemonCatch;

import java.util.List;

@Dao
public interface PokemonCatchDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPokemonCatch(PokemonCatch pokemonCatch);

    @Query("SELECT * FROM PokemonCatch")
    List<PokemonCatch> getPokemonCatch();

    @Query("DELETE FROM PokemonCatch")
    void deleteAll();
}
