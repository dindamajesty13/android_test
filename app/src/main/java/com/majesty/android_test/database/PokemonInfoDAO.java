package com.majesty.android_test.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.majesty.android_test.model.pokemoninfo.PokemonInfoAPI;

@Dao
public interface PokemonInfoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPokemonInfo(PokemonInfoAPI pokemonInfoAPI);

    @Query("SELECT * FROM PokemonInfo WHERE `name` = :name")
    PokemonInfoAPI getPokemonInfo(String name);

    @Query("DELETE FROM PokemonInfo")
    void deleteAll();
}
