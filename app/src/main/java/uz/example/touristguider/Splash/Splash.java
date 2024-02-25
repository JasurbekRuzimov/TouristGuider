package uz.example.touristguider.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import uz.example.touristguider.MainActivity;
import uz.example.touristguider.R;
import uz.example.touristguider.databinding.ActivitySplashBinding;
import uz.example.touristguider.register.SignIn;

public class Splash extends AppCompatActivity {
ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                startActivity(new Intent(Splash.this, SplashMidd.class));
                finish();
            }, 1500);
    }
}