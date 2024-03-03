package uz.example.touristguider.settings;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import uz.example.touristguider.R;
import uz.example.touristguider.bottomnavfragments.AccountFragment;
import uz.example.touristguider.databinding.FragmentEditProfileBinding;

public class EditProfileFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private FragmentEditProfileBinding binding;
    private FirebaseFirestore db;
    private DocumentReference userRef;
    private Uri selectedImageUri;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private String userId;
    private String[] countries = {
            "", "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan",
            "The Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana",
            "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic", "Chad", "Chile",
            "China", "Colombia", "Comoros", "Congo, Democratic Republic of the", "Congo, Republic of the", "Costa Rica", "Côte d’Ivoire", "Croatia", "Cuba", "Cyprus",
            "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor (Timor-Leste)", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea",
            "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "The Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada",
            "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel",
            "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, North", "Korea, South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia",
            "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta",
            "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia, Federated States of", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique",
            "Myanmar (Burma)", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Macedonia", "Norway", "Oman", "Pakistan",
            "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis",
            "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone",
            "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "Spain", "Sri Lanka", "Sudan", "Sudan, South", "Suriname", "Sweden", "Switzerland",
            "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine",
            "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"
    };

    public EditProfileFragment() {
    }

    public static EditProfileFragment newInstance() {
        return new EditProfileFragment();
    }

    @SuppressLint("UseRequireInsteadOfGet")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        progressBar = root.findViewById(R.id.progressBarEditProfile);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        String currentUserUid = auth.getCurrentUser().getUid();
        userRef = db.collection("users").document(currentUserUid);

        Spinner countrySpinner = root.findViewById(R.id.countrySpinner);
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, countries);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(countryAdapter);

        binding.updateProfile.setOnClickListener(v -> updateProfile());

        binding.editProfilePhoto.setOnClickListener(v -> openImageChooser());

        binding.backTV.setOnClickListener(v -> Objects.requireNonNull(getActivity()).onBackPressed());

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
                        binding.editName.setText(name);
                        binding.editSurname.setText(surname);
                        int spinnerPosition = countryAdapter.getPosition(country);
                        countrySpinner.setSelection(spinnerPosition);
                        binding.editPhone.setText(number);

//                        // Telefon raqamini olish
//                        String phoneNumber = binding.editPhone.getText().toString().trim();
//
//                        if (!TextUtils.isEmpty(phoneNumber)) {
//                            if (phoneNumber.matches("^\\+[0-9]{11}$")) { // Telefon raqamini +998901234567 formatda kiritish
//                                // Telefon raqamini formatga o'tkazish
//                                String formattedPhoneNumber = phoneNumber.replaceFirst("^\\+(\\d{2})(\\d{3})(\\d{2})(\\d{2})(\\d{2})$", "+$1 ($2) $3-$4-$5");
//                                // Formatlangan telefon raqamini qaytarish
//                                return formattedPhoneNumber;
//                            } else {
//                                // Telefon raqamining noto'g'ri formatda kiritilgan xabarni ko'rsatish
//                                Toast.makeText(getContext(), "Please enter the phone number in the correct format", Toast.LENGTH_SHORT).show();
//                                // Bo'sh qaytarish
//                                return "";
//                            }
//                        } else {
//                            // Telefon raqamining kiritilmagan xabarni ko'rsatish
//                            Toast.makeText(getContext(), "Please enter your phone number", Toast.LENGTH_SHORT).show();
//                            // Bo'sh qaytarish
//                            return "";
//                        }


                        progressBar.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(requireContext(), "Failed to retrieve user data", Toast.LENGTH_SHORT).show());

        loadUserImage();

        return root;
    }

    private void loadUserImage() {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference imageRef = storageRef.child("profile_images/" + Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
            Glide.with(requireContext()).load(uri).into(binding.editUserImage);
        }).addOnFailureListener(e -> {
            Toast.makeText(requireContext(), "Failed to load user image", Toast.LENGTH_SHORT).show();
        });
    }

    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void updateProfile() {
        String name = binding.editName.getText().toString().trim();
        String surname = binding.editSurname.getText().toString().trim();
        String country = binding.countrySpinner.getSelectedItem().toString();
        String phoneNumber = binding.editPhone.getText().toString().trim();

        if (name.isEmpty() || surname.isEmpty() || country.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(getContext(), "Please enter your name and surname", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> userData = new HashMap<>();
        userData.put("name", name);
        userData.put("surname", surname);
        userData.put("country", country);
        userData.put("phoneNumber", phoneNumber);

        userRef.set(userData)
                .addOnSuccessListener(aVoid -> {
                    showToast("Profile updated successfully");
                    navigateToAccountFragment();
                })
                .addOnFailureListener(e -> showToast("Failed to update profile"));

        if (selectedImageUri != null) {
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            StorageReference imageRef = storageRef.child("profile_images/" + Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
            imageRef.putFile(selectedImageUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                            String downloadUrl = uri.toString();
                            userData.put("profileImageUrl", downloadUrl);
                            userRef.set(userData)
                                    .addOnSuccessListener(aVoid -> showToast("Profile photo and data updated successfully"))
                                    .addOnFailureListener(e -> showToast("Failed to update profile photo and data"));
                        }).addOnFailureListener(e -> showToast("Failed to retrieve download URL"));
                    })
                    .addOnFailureListener(e -> showToast("Failed to upload image"));
        }
    }

    private void navigateToAccountFragment() {
        AccountFragment accountFragment = new AccountFragment();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, accountFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            Glide.with(this).load(selectedImageUri).into(binding.editUserImage);
        }
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
