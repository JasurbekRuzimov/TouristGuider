package uz.example.touristguider.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uz.example.touristguider.CityDetails;
import uz.example.touristguider.R;
import uz.example.touristguider.models.City;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private List<City> cityList;
    private Context context;

    @SuppressLint("NotifyDataSetChanged")
    public void setSearchList(List<City> dataSearchList) {
        this.cityList = dataSearchList;
        notifyDataSetChanged();
    }

    public CityAdapter(List<City> cityList, Context context) {
        this.cityList = cityList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        City city = cityList.get(position);
        holder.imgMain.setImageDrawable(city.getCityImage());
        holder.nameCity.setText(city.getName());
        holder.nameRegion.setText(city.getRegion());
        holder.imgInfo.setOnClickListener(v -> {
            Intent intent = new Intent(context, CityDetails.class);
            intent.putExtra("Name", city.getName());
            intent.putExtra("History", city.getHistory());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgMain;
        public TextView nameCity;
        public TextView nameRegion;
        public ImageView imgInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMain = itemView.findViewById(R.id.img_main);
            nameCity = itemView.findViewById(R.id.name_city);
            nameRegion = itemView.findViewById(R.id.name_region);
            imgInfo = itemView.findViewById(R.id.city_info);
        }
    }
}