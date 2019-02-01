package retrofit;

import model.Ability;
import model.PokePojo;
import model.Type;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IPokemon {
    @GET("pokemon")
    Call<PokePojo> getPokemonList();

    @GET("pokemon/{id}")
    Call<Ability> getPokemonById(@Path("id") String id);
}
