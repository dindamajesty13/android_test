package com.majesty.android_test.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.majesty.android_test.model.pokemoncatch.PokemonCatch;
import com.majesty.android_test.model.pokemonlist.ResultsResponse;
import com.majesty.android_test.repository.CatchRepository;
import com.majesty.android_test.repository.MainRepository;

import java.util.List;

public class CatchViewModel extends ViewModel {

    private CatchRepository catchRepository;
    private final List<PokemonCatch> pokemonList;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CatchViewModel (Application application) {
        catchRepository = new CatchRepository(application);
        pokemonList = catchRepository.getPokemonCatch();
    }

    public List<PokemonCatch> getPokemonList() { return pokemonList; }

}
