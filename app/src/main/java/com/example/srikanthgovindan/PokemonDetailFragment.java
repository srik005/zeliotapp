package com.example.srikanthgovindan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.srikanthgovindan.R;

import java.util.List;

import adapter.PokemonAdapter;
import adapter.PokemonTypeAdapter;
import model.Ability;
import model.Type;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit.IPokemon;
import retrofit.Retrofitclient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonDetailFragment extends Fragment {
    private RecyclerView typeRecyclerView;
    private String getId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View detailView = inflater.inflate(R.layout.detail_fragment, container, false);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        if (appCompatActivity != null && appCompatActivity.getSupportActionBar() != null) {
            appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        typeRecyclerView = detailView.findViewById(R.id.detailRecyclerView);
        typeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String getId = bundle.getString("position");
            String getName = bundle.getString("name");
            getActivity().setTitle(getName);
            Log.d("GetUrl", "" + getId);
        }

        IPokemon pokemon = Retrofitclient.getmInstance().create(IPokemon.class);
        Call<Ability> abilityCall = pokemon.getPokemonById(getId);
        abilityCall.enqueue(new Callback<Ability>() {
            @Override
            public void onResponse(Call<Ability> call, Response<Ability> response) {
                Log.d("Detail Response", "" + response);
                if (response.body() != null) {
                    List<Type> typeList = response.body().getTypes();
                    Log.d("Type Respoonse", "" + response);
                    PokemonTypeAdapter pokemonTypeAdapter = new PokemonTypeAdapter(typeList);
                    typeRecyclerView.setAdapter(pokemonTypeAdapter);
                }
            }

            @Override
            public void onFailure(Call<Ability> call, Throwable t) {

            }
        });
        return detailView;
    }
}

