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
import com.example.srikanthgovindan.zeliot.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import model.Ability;
import model.PokeResult;
import model.Type;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit.IPokemon;
import retrofit.ItemClickListener;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonTypeAdapter extends RecyclerView.Adapter<PokemonTypeAdapter.PokeViewHolder>{

    public PokemonTypeAdapter(List<Type> detailList) {
        this.detailList = detailList;
    }

    private List<Type> detailList;

    @NonNull
    @Override
    public PokemonTypeAdapter.PokeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_fragment_item, parent, false);
        return new PokemonTypeAdapter.PokeViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull PokemonTypeAdapter.PokeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        Log.d("Pokemon size", "" + detailList.size());
        return detailList.size();
    }

    public class PokeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView pokemText;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        ItemClickListener itemClickListener;

        public PokeViewHolder(View itemView) {
            super(itemView);
            pokemText = itemView.findViewById(R.id.tvType);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.clickItem(view, getAdapterPosition());
        }
    }
}
