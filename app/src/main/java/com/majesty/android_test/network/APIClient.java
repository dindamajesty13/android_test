package com.majesty.android_test.network;

import com.majesty.android_test.model.pokemoninfo.PokemonInfoAPI;
import com.majesty.android_test.model.pokemonlist.PokemonListAPI;

import io.reactivex.rxjava3.core.Observable;

public class APIClient {

    private final APIService apiService;

    public APIClient(APIService apiService) {
        this.apiService = apiService;
    }

    public Observable<PokemonListAPI> observableFetchPokemonList(int offset) {
        return apiService.fetchPokemonList(offset);
    }

    public Observable<PokemonInfoAPI> observableFetchPokemonInfo(String name) {
        return apiService.fetchPokemonInfo(name);
    }
}
