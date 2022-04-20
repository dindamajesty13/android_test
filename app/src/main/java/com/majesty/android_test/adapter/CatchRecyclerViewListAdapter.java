package com.majesty.android_test.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.florent37.glidepalette.BitmapPalette;
import com.github.florent37.glidepalette.GlidePalette;
import com.majesty.android_test.R;
import com.majesty.android_test.databinding.PokemonItemBinding;
import com.majesty.android_test.model.pokemoncatch.PokemonCatch;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CatchRecyclerViewListAdapter extends RecyclerView.Adapter<CatchRecyclerViewListAdapter.RecyclerViewViewHolder> {

    private final Context context;
    private List<PokemonCatch> pokemonCatch = new ArrayList<>();

    public CatchRecyclerViewListAdapter(List<PokemonCatch> pokemonCatch, Context context) {
        this.pokemonCatch = pokemonCatch;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokemonItemBinding pokemonItemBinding = PokemonItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new RecyclerViewViewHolder(pokemonItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder holder, int position) {
        PokemonCatch item = pokemonCatch.get(position);

        String namePoke = item.getName();
        String imagePoke = item.getUrl();

        holder.pokemonItemBinding.namePoke.setText(namePoke);
        Glide.with(context).load(imagePoke).placeholder(R.drawable.placeholder).error(R.drawable.error).into(holder.pokemonItemBinding.imagePoke);

        Glide.with(context).load(imagePoke)
                .listener(
                        GlidePalette.with(imagePoke).use(BitmapPalette.Profile.MUTED_LIGHT)
                                .intoCallBack(palette -> {
                                    if (palette != null && palette.getDominantSwatch() != null) {
                                        int rgbHexCode = palette.getDominantSwatch().getRgb();
                                        holder.pokemonItemBinding.cardView.setCardBackgroundColor(rgbHexCode);
                                    }
                                }).crossfade(true))
                .into(holder.pokemonItemBinding.imagePoke);
    }

    @Override
    public int getItemCount() {
        return pokemonCatch.size();
    }

    public static class RecyclerViewViewHolder extends RecyclerView.ViewHolder {

        private final PokemonItemBinding pokemonItemBinding;

        public RecyclerViewViewHolder(PokemonItemBinding pokemonItemBinding) {
            super(pokemonItemBinding.getRoot());
            this.pokemonItemBinding = pokemonItemBinding;
        }
    }
}
