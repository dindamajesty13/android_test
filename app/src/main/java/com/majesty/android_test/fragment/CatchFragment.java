package com.majesty.android_test.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.Glide;
import com.github.florent37.glidepalette.BitmapPalette;
import com.github.florent37.glidepalette.GlidePalette;
import com.majesty.android_test.R;
import com.majesty.android_test.database.AppDatabase;
import com.majesty.android_test.database.PokemonCatchDAO;
import com.majesty.android_test.databinding.FragmentCatchBinding;
import com.majesty.android_test.model.pokemoncatch.PokemonCatch;
import com.majesty.android_test.repository.CatchRepository;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CatchFragment extends Fragment {

    private FragmentCatchBinding fragmentCatchBinding;
    private String imagePoke;
    private final String STATE_IMAGE = "Current Image";
    CatchRepository catchRepository;
    PokemonCatchDAO pokemonCatchDAO;
    private final List<PokemonCatch> pokemonList = new ArrayList<>();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final static MutableLiveData<PokemonCatch> pokemonListLiveData = new MutableLiveData<PokemonCatch>();
    private final static MutableLiveData<Boolean> progressBarLiveData = new MutableLiveData<>();
    private final static MutableLiveData<Boolean> swipeRefreshLayoutLiveData = new MutableLiveData<>();
    private final static MutableLiveData<String> toastLiveData = new MutableLiveData<>();

    public CatchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            imagePoke = requireActivity().getIntent().getExtras().getString(STATE_IMAGE);
        } else {
            imagePoke = savedInstanceState.getString(STATE_IMAGE);
        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentCatchBinding = FragmentCatchBinding.inflate(inflater, container, false);

        return fragmentCatchBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpDataGetFromMainFragment();
        setArrowButton();
        catchPokemon();
    }

    private void catchPokemon() {
        fragmentCatchBinding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int randomNumber = random.nextInt(2);
                if (randomNumber == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Congratulation!");

                    final EditText input = new EditText(getActivity());
                    input.setHint("pokemon name");
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    builder.setView(input);

                    builder.setMessage(R.string.congrats)
                            .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.N)
                                public void onClick(DialogInterface dialog, int id) {
                                    AppDatabase appDatabase = AppDatabase.getInstance(getActivity());
                                    String namePoke = input.getText().toString();

                                    String urlPoke = imagePoke;

                                    PokemonCatch pokemonCatch = new PokemonCatch(namePoke, urlPoke);

                                    appDatabase.pokemonCatchDAO().insertPokemonCatch(pokemonCatch);

                                    progressBarLiveData.setValue(false);
                                    pokemonListLiveData.setValue(pokemonCatch);
                                    swipeRefreshLayoutLiveData.setValue(true);


                                    Toast.makeText(getActivity(), "Saved Success", Toast.LENGTH_SHORT).show();

                                }
                            })
                            .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    // Create the AlertDialog object and return it
                    builder.create();
                    builder.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Sorry")
                            .setMessage(R.string.sorry)
                            .setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    // Create the AlertDialog object and return it
                    builder.create();
                    builder.show();
                }

            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull @NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_IMAGE, imagePoke);
    }


    private void setUpDataGetFromMainFragment() {
        Glide.with(this).load(imagePoke).placeholder(R.drawable.placeholder).into(fragmentCatchBinding.imagePoke);

        Glide.with(this).load(imagePoke)
                .listener(
                        GlidePalette.with(imagePoke).use(BitmapPalette.Profile.MUTED_LIGHT)
                                .intoCallBack(palette -> {
                                    if (palette != null && palette.getDominantSwatch() != null) {
                                        int rgbHexCode = palette.getDominantSwatch().getRgb();
                                        fragmentCatchBinding.cardView.setCardBackgroundColor(rgbHexCode);
                                    }
                                }).crossfade(true))
                .into(fragmentCatchBinding.imagePoke);
    }

    private void setArrowButton() {
        fragmentCatchBinding.arrow.setOnClickListener(view -> {
            requireActivity().finish();
        });
    }


}