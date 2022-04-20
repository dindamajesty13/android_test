package com.majesty.android_test.database;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.majesty.android_test.model.pokemoncatch.PokemonCatch;
import com.majesty.android_test.model.pokemoninfo.PokemonInfoAPI;
import com.majesty.android_test.model.pokemonlist.ResultsResponse;

@RequiresApi(api = Build.VERSION_CODES.N)
@Database(entities = {ResultsResponse.class, PokemonInfoAPI.class, PokemonCatch.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "PokemonDatabase")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract PokemonListDAO pokemonListDAO();

    public abstract PokemonInfoDAO pokemonInfoDAO();

    public abstract PokemonCatchDAO pokemonCatchDAO();
}
