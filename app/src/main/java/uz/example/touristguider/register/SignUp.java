package uz.example.touristguider.register;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Objects;

import uz.example.touristguider.databinding.ActivitySignUpBinding;
import uz.example.touristguider.models.UserModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class SignUp extends AppCompatActivity {

    ActivitySignUpBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        progressDialog = new ProgressDialog(this);

        binding.signUpButtonFirebase.setOnClickListener(v -> {

            String email = Objects.requireNonNull(binding.emailIdFairbase.getEditText()).getText().toString();
            if (email.isEmpty()) {
                binding.emailIdFairbase.setError("Enter your email !");
                return;
            }
            String phoneNumber = Objects.requireNonNull(binding.phoneNumberIdFirebase.getEditText()).getText().toString();
            if (phoneNumber.isEmpty()) {
                binding.phoneNumberIdFirebase.setError("Enter a phone number !");
                return;
            }
            String password = Objects.requireNonNull(binding.passwordIdFairbase.getEditText()).getText().toString();
            if (password.isEmpty()) {
                binding.passwordIdFairbase.setError("Enter the password !");
                return;
            }

            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener(authResult -> {
                        startActivity(new Intent(SignUp.this, SignIn.class));
                        progressDialog.cancel();

                        firebaseFirestore.collection("User")
                                .document(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                .set(new UserModel(email, password, phoneNumber));

                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    });
        });

        binding.alreadyHaveAnAccount.setOnClickListener(v -> {
            startActivity(new Intent(SignUp.this, SignIn.class));
            finish();
        });

        binding.alreadyHaveAnAccount1.setOnClickListener(v -> {
            startActivity(new Intent(SignUp.this, SignIn.class));
            finish();
        });


    }
}


