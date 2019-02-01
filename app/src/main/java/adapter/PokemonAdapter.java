package adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.srikanthgovindan.PokemonDetailFragment;
import com.example.srikanthgovindan.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import model.PokeResult;
import retrofit.ItemClickListener;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokeViewHolder> {
    public static final String IMAGE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";

    public PokemonAdapter(List<PokeResult> pokemonPojoList) {
        this.pokemonPojoList = pokemonPojoList;
    }

    private List<PokeResult> pokemonPojoList;

    @NonNull
    @Override
    public PokemonAdapter.PokeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_list_item, parent, false);
        return new PokeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.PokeViewHolder holder, int position) {

        Picasso.get().load(IMAGE_URL + (position + 1) + ".png").into(holder.pokeImageView);
        Log.d("img url", "" + IMAGE_URL + (position + 1) + ".png");

        holder.pokemText.setText(pokemonPojoList.get(position).getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void clickItem(View view, int position) {
                Fragment fragment = new PokemonDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", pokemonPojoList.get(position).getName());
                bundle.putString("position", String.valueOf(position));
                fragment.setArguments(bundle);
                ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.listItem, fragment).addToBackStack(null).commit();
                Toast.makeText(view.getContext(), "Clicked at " + position, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.d("Pokemon size", "" + pokemonPojoList.size());
        return pokemonPojoList.size();
    }

    public class PokeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView pokeImageView;
        private TextView pokemText;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        ItemClickListener itemClickListener;

        public PokeViewHolder(View itemView) {
            super(itemView);
            pokeImageView = itemView.findViewById(R.id.pokemon_image);
            pokemText = itemView.findViewById(R.id.txt_pokemon_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.clickItem(view, getAdapterPosition());
        }
    }
}
