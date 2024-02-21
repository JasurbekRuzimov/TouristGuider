package uz.example.touristguider;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CityDetails extends AppCompatActivity {


    TextView city_name, city_history, cityLocation, cityAttraction, cityHours, cityPhone, cityWebsite;
    ImageView detailImage, backBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_details);

        city_name = findViewById(R.id.city_name);
        city_history = findViewById(R.id.city_history);
        detailImage = findViewById(R.id.detailImage);
        cityLocation = findViewById(R.id.city_location);
        cityAttraction = findViewById(R.id.city_attractions);
        cityHours = findViewById(R.id.city_hours);
        cityPhone = findViewById(R.id.city_phone);
        cityWebsite = findViewById(R.id.city_website);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> {
            finish();
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            city_name.setText(bundle.getString("Name"));
            city_history.setText(bundle.getString("History"));
            detailImage.setImageResource(bundle.getInt("Image"));
        }


    }
}