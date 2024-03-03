package uz.example.touristguider;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import uz.example.touristguider.databinding.ActivityCityDetailsBinding;

public class CityDetails extends AppCompatActivity {

    ActivityCityDetailsBinding binding;
    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        city_name = findViewById(R.id.city_name);
//        city_history = findViewById(R.id.city_history);
//        detailImage = findViewById(R.id.detailImage);
//        cityLocation = findViewById(R.id.city_location);
//        cityAttraction = findViewById(R.id.city_attractions);
//        cityHours = findViewById(R.id.city_hours);
//        cityPhone = findViewById(R.id.city_phone);
//        cityWebsite = findViewById(R.id.city_website);

//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            city_name.setText(bundle.getString("Name"));
//            city_history.setText(bundle.getString("History"));
//        }


        binding.backBtn.setOnClickListener(v -> {
            finish();
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference("regions");
        reference = database.getReference("regions").child("Tashkent");
        reference.child("Name").setValue("Tashkent");
        reference.child("History").setValue("Tashkent is the capital of Uzbekistan. It’s known for its many museums and its mix of modern and Soviet-era architecture. The Amir Timur Museum houses manuscripts, weapons and other relics from the Timurid dynasty. Nearby, the huge State Museum of History of Uzbekistan has centuries-old Buddhist artifacts. The city’s skyline is distinguished by Tashkent Tower, which offers city views from its observation deck.");
        reference.child("Location").setValue("41.2995° N, 69.2401° E");
        reference.child("Attractions").setValue(" Amir Timur Museum\n State Museum of History of Uzbekistan\n Tashkent Tower");
        reference.child("Hours").setValue("Open 24 hours");
        reference.child("Phone").setValue("+998 71 200 00 00");
        reference.child("Website").setValue("https://trvlland.com/wp-content/uploads/2022/09/uzbekistan_tashkent-3.jpg");
        reference.child("Image").setValue(R.drawable.registon);

        reference.child("Name").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                binding.cityName.setText(task.getResult().getValue().toString());
            }
        });

        reference.child("History").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                binding.cityHistory.setText(task.getResult().getValue().toString());
            }
        });

        reference.child("Location").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                binding.cityLocation.setText(task.getResult().getValue().toString());
            }
        });

        reference.child("Attractions").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                binding.cityAttractions.setText(task.getResult().getValue().toString());
            }
        });

        reference.child("Hours").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                binding.cityHours.setText(task.getResult().getValue().toString());
            }
        });

        reference.child("Phone").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                binding.cityPhone.setText(task.getResult().getValue().toString());
            }
        });

        reference.child("Website").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                binding.cityWebsite.setText(task.getResult().getValue().toString());
            }
        });

        reference.child("Image").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                binding.detailImage.setImageResource(Integer.parseInt(task.getResult().getValue().toString()));
            }
        });


    }
}
