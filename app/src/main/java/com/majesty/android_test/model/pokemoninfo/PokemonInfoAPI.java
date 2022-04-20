package com.majesty.android_test.model.pokemoninfo;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.majesty.android_test.database.Converters;

import java.util.List;
import java.util.SplittableRandom;

@RequiresApi(api = Build.VERSION_CODES.N)
@Entity(tableName = "PokemonInfo")
public class PokemonInfoAPI {
    @Ignore
    public PokemonInfoAPI() {
    }

    @Ignore
    public final int maxHP = 300;
    @Ignore
    public final int maxATK = 300;
    @Ignore
    public final int maxDEF = 300;
    @Ignore
    public final int maxSPD = 300;
    @Ignore
    public final int maxEXP = 1000;

    @PrimaryKey
    @NonNull
    public String name;

    @TypeConverters(Converters.class)
    @SerializedName("types")
    public List<TypesResponse> types;

    @SerializedName("height")
    @Ignore
    public Integer height;
    public String heightFormatted;

    @SerializedName("weight")
    @Ignore
    public Integer weight;
    public String weightFormatted;

    public List<TypesResponse> getTypes() {
        return types;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    @Ignore
    public Integer hp = new SplittableRandom().nextInt(100, maxHP);
    @Ignore
    public Integer atk = new SplittableRandom().nextInt(100, maxATK);
    @Ignore
    public Integer def = new SplittableRandom().nextInt(100, maxDEF);
    @Ignore
    public Integer spd = new SplittableRandom().nextInt(100, maxSPD);
    @Ignore
    public Integer exp = new SplittableRandom().nextInt(300, maxEXP);

    public Float hpFormatted;
    public Float atkFormatted;
    public Float defFormatted;
    public Float spdFormatted;
    public Float expFormatted;

    public String hpString = hp + "/" + maxHP;
    public String atkString = atk + "/" + maxATK;
    public String defString = def + "/" + maxDEF;
    public String spdString = spd + "/" + maxSPD;
    public String expString = exp + "/" + maxEXP;

    public PokemonInfoAPI(@NonNull String name, List<TypesResponse> types, String heightFormatted, String weightFormatted,
                          Float hpFormatted, Float atkFormatted, Float defFormatted, Float spdFormatted, Float expFormatted,
                          String hpString, String atkString, String defString, String spdString, String expString) {
        this.name = name;
        this.types = types;
        this.heightFormatted = heightFormatted;
        this.weightFormatted = weightFormatted;
        this.hpFormatted = hpFormatted;
        this.atkFormatted = atkFormatted;
        this.defFormatted = defFormatted;
        this.spdFormatted = spdFormatted;
        this.expFormatted = expFormatted;
        this.hpString = hpString;
        this.atkString = atkString;
        this.defString = defString;
        this.spdString = spdString;
        this.expString = expString;
    }
}
