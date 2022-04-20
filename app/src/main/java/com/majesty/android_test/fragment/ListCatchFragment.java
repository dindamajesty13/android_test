package com.majesty.android_test.fragment;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.majesty.android_test.R;
import com.majesty.android_test.adapter.CatchRecyclerViewListAdapter;
import com.majesty.android_test.database.AppDatabase;
import com.majesty.android_test.databinding.FragmentListCatchBinding;
import com.majesty.android_test.model.pokemoncatch.PokemonCatch;
import com.majesty.android_test.repository.CatchRepository;
import com.majesty.android_test.viewmodel.CatchViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListCatchFragment extends Fragment {
    private FragmentListCatchBinding fragmentListCatchBinding;

    public ListCatchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentListCatchBinding = FragmentListCatchBinding.inflate(inflater, container, false);
        fragmentListCatchBinding.pokemonList.setHasFixedSize(true);
        setUpRecyclerView();

        return fragmentListCatchBinding.getRoot();
    }

    private void setUpRecyclerView() {
        class GetData extends AsyncTask<Void, Void, List<PokemonCatch>> {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            protected List<PokemonCatch> doInBackground(Void... voids) {
                List<PokemonCatch> pokeList= AppDatabase.getInstance(getActivity()).pokemonCatchDAO().getPokemonCatch();
                return pokeList;

            }

            @Override
            protected void onPostExecute(List<PokemonCatch> pokemonCatch) {
                CatchRecyclerViewListAdapter adapter=new CatchRecyclerViewListAdapter(pokemonCatch, getActivity());
                fragmentListCatchBinding.pokemonList.setAdapter(adapter);
                super.onPostExecute(pokemonCatch);
            }
        }
        GetData gd=new GetData();
        gd.execute();
    }
}