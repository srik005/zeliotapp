
package com.example.srikanthgovindan;

import android.accounts.NetworkErrorException;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.srikanthgovindan.R;

import java.io.IOException;
import java.util.List;

import adapter.PokemonAdapter;
import model.PokePojo;
import model.PokeResult;
import retrofit.IPokemon;
import retrofit.Retrofitclient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonFragment extends Fragment {
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View pokeListView = inflater.inflate(R.layout.poke_fragment, container, false);
        recyclerView = pokeListView.findViewById(R.id.recyclerView);

        Log.d("inside fragment", "" + "fragment");
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        IPokemon iPokemon = Retrofitclient.getmInstance().create(IPokemon.class);
        Call<PokePojo> pokemonCall = iPokemon.getPokemonList();
        pokemonCall.enqueue(new Callback<PokePojo>() {
            @Override
            public void onResponse(@NonNull Call<PokePojo> call, @NonNull Response<PokePojo> response) {
                if (response.body() != null) {
                    List<PokeResult> pokeResults = response.body().getResults();
                    Log.d("Response", "" + pokeResults);
                    recyclerView.setAdapter(new PokemonAdapter(pokeResults));
                }
            }

            @Override
            public void onFailure(Call<PokePojo> call, Throwable t) {
                if (t instanceof NetworkErrorException) {
                    Toast.makeText(getActivity(), "Network is down", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return pokeListView;
    }
}
