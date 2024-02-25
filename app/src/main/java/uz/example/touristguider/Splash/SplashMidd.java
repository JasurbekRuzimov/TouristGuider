package uz.example.touristguider.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import uz.example.touristguider.MainActivity;
import uz.example.touristguider.R;
import uz.example.touristguider.register.SignIn;

public class SplashMidd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_midd);
        if (isUserLoggedIn()) {
            startActivity(new Intent(SplashMidd.this, MainActivity.class));
        } else {
            startActivity(new Intent(SplashMidd.this, SignIn.class));
        }
        finish();
    }

    private boolean isUserLoggedIn() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return sharedPreferences.getBoolean("isLoggedIn", false);
    }

}