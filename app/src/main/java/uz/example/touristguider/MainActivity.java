package uz.example.touristguider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import uz.example.touristguider.bottomnavfragments.CurrencyExchangeFragment;
import uz.example.touristguider.bottomnavfragments.HomeFragment;
import uz.example.touristguider.bottomnavfragments.LocationFragment;
import uz.example.touristguider.bottomnavfragments.AccountFragment;
import uz.example.touristguider.databinding.ActivityMainBinding;
import uz.example.touristguider.register.SignIn;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uz.example.touristguider.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new HomeFragment());
        fragmentTransaction.commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment;
            if (item.getItemId() == R.id.menuHome) {
                selectedFragment = new HomeFragment();
            } else if (item.getItemId() == R.id.menuMap) {
                selectedFragment = new LocationFragment();
            } else if (item.getItemId() == R.id.menuSave) {
                selectedFragment = new CurrencyExchangeFragment();
            } else {
                selectedFragment = new AccountFragment();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        });

    }
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, SignIn.class));
            finish();
        }
    }

}
