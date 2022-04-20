package com.majesty.android_test.di;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Room;

import com.majesty.android_test.database.AppDatabase;
import com.majesty.android_test.database.PokemonCatchDAO;
import com.majesty.android_test.database.PokemonInfoDAO;
import com.majesty.android_test.database.PokemonListDAO;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    private final Application application;

    public DatabaseModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, "PokemonDatabase")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Provides
    @Singleton
    PokemonListDAO providePokemonListDAO(AppDatabase appDatabase) {
        return appDatabase.pokemonListDAO();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Provides
    @Singleton
    PokemonInfoDAO providePokemonInfoDAO(AppDatabase appDatabase) {
        return appDatabase.pokemonInfoDAO();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Provides
    @Singleton
    PokemonCatchDAO providePokemonCatchDAO(AppDatabase appDatabase) {
        return appDatabase.pokemonCatchDAO();
    }
}
