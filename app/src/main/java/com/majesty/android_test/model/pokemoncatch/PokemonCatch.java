package com.majesty.android_test.model.pokemoncatch;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "PokemonCatch")
public class PokemonCatch {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("url")
    public String url;

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public PokemonCatch(int id, String name, String url){
        this.id = id;
        this.name = name;
        this.url = url;
    }

    @Ignore
    public PokemonCatch(String name, String url){
        this.name = name;
        this.url = url;
    }
}
