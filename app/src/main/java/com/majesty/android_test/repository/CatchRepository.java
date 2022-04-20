package com.majesty.android_test.repository;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.majesty.android_test.MyApplication;
import com.majesty.android_test.database.AppDatabase;
import com.majesty.android_test.database.PokemonCatchDAO;
import com.majesty.android_test.model.pokemoncatch.PokemonCatch;
import com.majesty.android_test.network.APIClient;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class CatchRepository {

    @Inject
    PokemonCatchDAO mpokemonCatchDAO;
    private List<PokemonCatch> pokemonList ;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CatchRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mpokemonCatchDAO = db.pokemonCatchDAO();
        pokemonList = mpokemonCatchDAO.getPokemonCatch();
    }

    public List<PokemonCatch> getPokemonCatch() {
        return pokemonList;
    }


}
