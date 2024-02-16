package uz.example.touristguider.bottomnavfragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

import uz.example.touristguider.R;
import uz.example.touristguider.SettingsFragment;
import uz.example.touristguider.databinding.FragmentAccountBinding;
import uz.example.touristguider.settings.EditProfileFragment;

public class AccountFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private FragmentAccountBinding binding;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private StorageReference storageReference;

    public AccountFragment() {
        // Required empty public constructor
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

        // Initialize Firebase Auth and Storage
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

        binding.changeProfilePhoto.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        });

        if (auth.getCurrentUser() != null) {
            binding.userEmail.setText(auth.getCurrentUser().getEmail());
            binding.userPassword.setText("**********");
        } else {
            Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show();
        }

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            // Update the profile photo using the selected image URI
            updateProfilePhoto(selectedImageUri);
        }
    }
    private void updateProfilePhoto(Uri imageUri) {
        Glide.with(this).load(imageUri).into(binding.userImage);
        String imageName = auth.getCurrentUser().getUid();
        StorageReference imageRef = storageReference.child(imageName);
        imageRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        String downloadUrl = uri.toString();
                        showToast("Profile photo updated");
                    }).addOnFailureListener(e -> showToast("Failed to retrieve download URL"));
                })
                .addOnFailureListener(e -> showToast("Failed to upload image"));
    }
    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

}
