package uz.example.touristguider.settings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import uz.example.touristguider.R;
import uz.example.touristguider.databinding.FragmentEditProfileBinding;

public class EditProfileFragment extends Fragment {

    FragmentEditProfileBinding binding;

    public EditProfileFragment() {
    }

    public static EditProfileFragment newInstance() {
        return new EditProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
// hamma ma'lumotlarni firebase firestorega saqlash kerak

        // saveProfile tugmasini bosganda barcha ma'lumotlarni olib, Firestorega saqlash
        binding.updateProfile.setOnClickListener(v -> {
            String name = binding.editName.getText().toString().trim();
            String surname = binding.editSurname.getText().toString().trim();
            String email = binding.editEmail.getText().toString().trim();
            String country = binding.editCountry.getText().toString().trim();
            String phoneNumber = binding.editPhone.getText().toString().trim();

            // Ma'lumotlarni tekshirish, misol uchun ism va familiyani tekshirish:
            if (name.isEmpty()) {
                // Ism kiritilmagan
                Toast.makeText(getContext(), "Iltimos, ismingizni kiriting", Toast.LENGTH_SHORT).show();
                return;
            }
            if (surname.isEmpty()) {
                // Familiya kiritilmagan
                Toast.makeText(getContext(), "Iltimos, familiyangizni kiriting", Toast.LENGTH_SHORT).show();
                return;
            }

            // Ma'lumotlarni Firestorega saqlash
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            String currentUserUid;
            currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid(); // "currentUserUid" - foydalanuvchi identifikatori
            DocumentReference userRef = db.collection("users").document(currentUserUid); // currentUserUid - foydalanuvchi identifikatori

            Map<String, Object> userData = new HashMap<>();
            userData.put("name", name);
            userData.put("surname", surname);
            userData.put("email", email);
            userData.put("country", country);
            userData.put("phoneNumber", phoneNumber);

            // Ma'lumotlarni Firestorega qo'shish
            userRef.set(userData)
                    .addOnSuccessListener(aVoid -> {
                        // Ma'lumotlar muvaffaqiyatli saqlandi
                        Toast.makeText(getContext(), "Ma'lumotlar muvaffaqiyatli saqlandi", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        // Saqlashda xatolik yuz berdi
                        Toast.makeText(getContext(), "Xatolik: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });


        binding.editProfilePhoto.setOnClickListener(v -> {
            // Open gallery
        });

        return root;
    }
}