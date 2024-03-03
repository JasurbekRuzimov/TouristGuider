package uz.example.touristguider.bottomnavfragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

import uz.example.touristguider.R;
import uz.example.touristguider.settings.SettingsFragment;
import uz.example.touristguider.databinding.FragmentAccountBinding;
import uz.example.touristguider.settings.EditProfileFragment;

public class AccountFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private FragmentAccountBinding binding;
    private FirebaseAuth auth;
    private StorageReference storageReference;
    String userId;
    ProgressBar progressBar;

    public AccountFragment() {
    }

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        auth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("profile_images");

        binding.settings.setOnClickListener(view -> {
            SettingsFragment settingsFragment = new SettingsFragment();
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = ((FragmentManager) fragmentManager).beginTransaction();
            transaction.replace(R.id.fragment_container, settingsFragment);
            transaction.addToBackStack(String.valueOf(true));
            transaction.commit();
        });

        binding.enterData.setOnClickListener(view -> {
            EditProfileFragment editProfileFragment = new EditProfileFragment();
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = ((FragmentManager) fragmentManager).beginTransaction();
            transaction.replace(R.id.fragment_container, editProfileFragment);
            transaction.addToBackStack(String.valueOf(true));
            transaction.commit();
        });

        if (auth.getCurrentUser() != null) {
            binding.userEmail.setText(auth.getCurrentUser().getEmail());
            binding.userPassword.setText("**********");
        } else {
            Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show();
        }

        progressBar = binding.progressBarProfile;
        userId = auth.getCurrentUser().getUid();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("users").document(userId).get()
                .addOnSuccessListener(documentSnapshot -> {
                    progressBar.setVisibility(View.VISIBLE);
                    if (documentSnapshot.exists()) {
                        String name = documentSnapshot.getString("name");
                        String surname = documentSnapshot.getString("surname");
                        String country = documentSnapshot.getString("country");
                        String number = documentSnapshot.getString("phoneNumber");
                        binding.userName.setText(name);
                        binding.userSurname.setText(surname);
                        binding.userCountry.setText(country);
                        binding.userPhone.setText(number);
                        progressBar.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(requireContext(), "Failed to retrieve user data", Toast.LENGTH_SHORT).show());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference userRef = db.collection("users").document(currentUserUid);
        binding.userImage.setOnClickListener(v -> openImageChooser());
        loadUserImage();

        return root;
    }

    private void loadUserImage() {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference imageRef = storageRef.child("profile_images/" + Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
            Glide.with(requireContext()).load(uri).into(binding.userImage);
        }).addOnFailureListener(e -> {
            Toast.makeText(requireContext(), "Failed to load user image", Toast.LENGTH_SHORT).show();
        });
    }

    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            Glide.with(this).load(selectedImageUri).into(binding.userImage);
        }
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

}
