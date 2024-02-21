package uz.example.touristguider.bottomnavfragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uz.example.touristguider.R;
import uz.example.touristguider.adapters.CityAdapter;
import uz.example.touristguider.databinding.FragmentHomeBinding;
import uz.example.touristguider.models.City;
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView city_list;
    List<City> cityList = new ArrayList<>();
    private CityAdapter cityAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        androidx.appcompat.widget.SearchView searchView;
        searchView = rootView.findViewById(R.id.search);
        searchView.clearFocus();

        city_list = binding.cityList;
        cityList.add(new City("Kalta Minor", "Xorazm", getResources().getDrawable(R.drawable.xiva)));
        cityList.add(new City("Buxoro Arki", "Buxoro", getResources().getDrawable(R.drawable.buxoro)));
        cityList.add(new City("Toshkent Teleminorasi", "Toshkent", getResources().getDrawable(R.drawable.xiva2)));
        cityList.add(new City("Registon maydoni", "Samarqand", getResources().getDrawable(R.drawable.registon)));
        cityList.add(new City("Islom Xo'ja Minorasi", "Xorazm", getResources().getDrawable(R.drawable.xiva)));
        cityList.add(new City("Buxoro Arki", "Buxoro", getResources().getDrawable(R.drawable.buxoro)));
        cityList.add(new City("Toshkent", "Toshkent", getResources().getDrawable(R.drawable.xiva2)));
        cityList.add(new City("Samarqand", "Samarqand", getResources().getDrawable(R.drawable.registon)));
        cityList.add(new City("Kalta Minor", "Xorazm", getResources().getDrawable(R.drawable.xiva)));
        cityList.add(new City("Buxoro Arki", "Buxoro", getResources().getDrawable(R.drawable.buxoro)));
        cityList.add(new City("Toshkent Teleminorasi", "Toshkent", getResources().getDrawable(R.drawable.xiva2)));
        cityList.add(new City("Registon maydoni", "Samarqand", getResources().getDrawable(R.drawable.registon)));
        cityList.add(new City("Islom Xo'ja Minorasi", "Xorazm", getResources().getDrawable(R.drawable.xiva)));
        cityList.add(new City("Buxoro Arki", "Buxoro", getResources().getDrawable(R.drawable.buxoro)));
        cityList.add(new City("Toshkent", "Toshkent", getResources().getDrawable(R.drawable.xiva2)));
        cityList.add(new City("Samarqand", "Samarqand", getResources().getDrawable(R.drawable.registon)));
        cityList.add(new City("Samarqand", "Samarqand", getResources().getDrawable(R.drawable.registon)));
        cityList.add(new City("Samarqand", "Samarqand", getResources().getDrawable(R.drawable.registon)));
        cityList.add(new City("Samarqand", "Samarqand", getResources().getDrawable(R.drawable.registon)));
        cityList.add(new City("Kalta Minor", "Xorazm", getResources().getDrawable(R.drawable.xiva)));
        cityList.add(new City("Buxoro Arki", "Buxoro", getResources().getDrawable(R.drawable.buxoro)));
        cityList.add(new City("Toshkent Teleminorasi", "Toshkent", getResources().getDrawable(R.drawable.xiva2)));
        cityList.add(new City("Registon maydoni", "Samarqand", getResources().getDrawable(R.drawable.registon)));
        cityList.add(new City("Islom Xo'ja Minorasi", "Xorazm", getResources().getDrawable(R.drawable.xiva)));
        cityList.add(new City("Buxoro Arki", "Buxoro", getResources().getDrawable(R.drawable.buxoro)));
        cityList.add(new City("Toshkent", "Toshkent", getResources().getDrawable(R.drawable.xiva2)));
        cityList.add(new City("Samarqand", "Samarqand", getResources().getDrawable(R.drawable.registon)));
        cityList.add(new City("Kalta Minor", "Xorazm", getResources().getDrawable(R.drawable.xiva)));
        cityList.add(new City("Buxoro Arki", "Buxoro", getResources().getDrawable(R.drawable.buxoro)));
        cityList.add(new City("Toshkent Teleminorasi", "Toshkent", getResources().getDrawable(R.drawable.xiva2)));
        cityList.add(new City("Registon maydoni", "Samarqand", getResources().getDrawable(R.drawable.registon)));
        cityList.add(new City("Islom Xo'ja Minorasi", "Xorazm", getResources().getDrawable(R.drawable.xiva)));
        cityList.add(new City("Buxoro Arki", "Buxoro", getResources().getDrawable(R.drawable.buxoro)));
        cityList.add(new City("Toshkent", "Toshkent", getResources().getDrawable(R.drawable.xiva2)));
        cityList.add(new City("Samarqand", "Samarqand", getResources().getDrawable(R.drawable.registon)));
        cityList.add(new City("Samarqand", "Samarqand", getResources().getDrawable(R.drawable.registon)));
        cityList.add(new City("Samarqand", "Samarqand", getResources().getDrawable(R.drawable.registon)));
        cityList.add(new City("Samarqand", "Samarqand", getResources().getDrawable(R.drawable.registon)));

        cityAdapter = new CityAdapter(cityList, getContext());

        city_list.setLayoutManager(new LinearLayoutManager(getContext()));
        city_list.setAdapter(cityAdapter);

        binding.uzbekistan.setOnClickListener(v -> {
            cityAdapter.setSearchList(cityList);
        });
        binding.tashkent.setOnClickListener(v -> {
            List<City> tashkentList = new ArrayList<>();
            for (City city : cityList) {
                if (city.getRegion().equals("Toshkent")) {
                    tashkentList.add(city);
                }
            }
            cityAdapter.setSearchList(tashkentList);
        });
        binding.samarkand.setOnClickListener(v -> {
            List<City> samarqandList = new ArrayList<>();
            for (City city : cityList) {
                if (city.getRegion().equals("Samarqand")) {
                    samarqandList.add(city);
                }
            }
            cityAdapter.setSearchList(samarqandList);
        });
        binding.bukhara.setOnClickListener(v -> {
            List<City> buxoroList = new ArrayList<>();
            for (City city : cityList) {
                if (city.getRegion().equals("Buxoro")) {
                    buxoroList.add(city);
                }
            }
            cityAdapter.setSearchList(buxoroList);
        });
        binding.khorezm.setOnClickListener(v -> {
            List<City> khorezmList = new ArrayList<>();
            for (City city : cityList) {
                if (city.getRegion().equals("Xorazm")) {
                    khorezmList.add(city);
                }
            }
            cityAdapter.setSearchList(khorezmList);
        });
        binding.fergana.setOnClickListener(v -> {
            List<City> ferganaList = new ArrayList<>();
            for (City city : cityList) {
                if (city.getRegion().equals("Farg'ona")) {
                    ferganaList.add(city);
                }
            }
            cityAdapter.setSearchList(ferganaList);
        });
        binding.navoi.setOnClickListener(v -> {
            List<City> navoiList = new ArrayList<>();
            for (City city : cityList) {
                if (city.getRegion().equals("Navoiy")) {
                    navoiList.add(city);
                }
            }
            cityAdapter.setSearchList(navoiList);
        });
        binding.andijan.setOnClickListener(v -> {
            List<City> andijanList = new ArrayList<>();
            for (City city : cityList) {
                if (city.getRegion().equals("Andijon")) {
                    andijanList.add(city);
                }
            }
            cityAdapter.setSearchList(andijanList);
        });
        binding.namangan.setOnClickListener(v -> {
            List<City> namanganList = new ArrayList<>();
            for (City city : cityList) {
                if (city.getRegion().equals("Namangan")) {
                    namanganList.add(city);
                }
            }
            cityAdapter.setSearchList(namanganList);
        });
        binding.qashqadaryo.setOnClickListener(v -> {
            List<City> qashqadaryoList = new ArrayList<>();
            for (City city : cityList) {
                if (city.getRegion().equals("Qashqadaryo")) {
                    qashqadaryoList.add(city);
                }
            }
            cityAdapter.setSearchList(qashqadaryoList);
        });
        binding.surxondaryo.setOnClickListener(v -> {
            List<City> surxondaryoList = new ArrayList<>();
            for (City city : cityList) {
                if (city.getRegion().equals("Surxondaryo")) {
                    surxondaryoList.add(city);
                }
            }
            cityAdapter.setSearchList(surxondaryoList);
        });
        binding.jizzakh.setOnClickListener(v -> {
            List<City> jizzakhList = new ArrayList<>();
            for (City city : cityList) {
                if (city.getRegion().equals("Jizzax")) {
                    jizzakhList.add(city);
                }
            }
            cityAdapter.setSearchList(jizzakhList);
        });
        binding.sirdaryo.setOnClickListener(v -> {
            List<City> sirdaryoList = new ArrayList<>();
            for (City city : cityList) {
                if (city.getRegion().equals("Sirdaryo")) {
                    sirdaryoList.add(city);
                }
            }
            cityAdapter.setSearchList(sirdaryoList);
        });
        binding.karakalpakstan.setOnClickListener(v -> {
            List<City> karakalpakstanList = new ArrayList<>();
            for (City city : cityList) {
                if (city.getRegion().equals("Qoraqalpog'iston Respublikasi")) {
                    karakalpakstanList.add(city);
                }
            }
            cityAdapter.setSearchList(karakalpakstanList);
        });

        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return false;
            }
        });

        return rootView;
    }

    private void searchList(String newText) {
        List<City> searchList = new ArrayList<>();
        for (City city : cityList) {
            if (city.getName().toLowerCase().contains(newText.toLowerCase())) {
                searchList.add(city);
            } else if (city.getRegion().toLowerCase().contains(newText.toLowerCase())) {
                searchList.add(city);
            }
        }
        cityAdapter.setSearchList(searchList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
