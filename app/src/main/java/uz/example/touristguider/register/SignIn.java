package uz.example.touristguider.register;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import com.google.firebase.auth.FirebaseAuth;

import uz.example.touristguider.MainActivity;
import uz.example.touristguider.databinding.ActivitySignInBinding;

public class SignIn extends AppCompatActivity {
    ActivitySignInBinding binding;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        binding.signInFirebase.setOnClickListener(v -> {
            String email = Objects.requireNonNull(binding.emailFairbase.getEditText()).getText().toString();
            if (email.isEmpty()) {
                binding.emailFairbase.setError("Emailni kiriting !");
                return;
            }
            String password = Objects.requireNonNull(binding.passwordFairbase.getEditText()).getText().toString();
            if (password.isEmpty()) {
                binding.passwordFairbase.setError("Parolni kiriting !");
                return;
            }

            progressDialog.show();
            signInWithEmailAndPassword(email, password);
        });

        binding.ForgotPasswordFirebase.setOnClickListener(v -> {
            String email = Objects.requireNonNull(binding.emailFairbase.getEditText()).getText().toString();
            if (email.isEmpty()) {
                Toast.makeText(SignIn.this, "Iltimos, emailni kiriting!", Toast.LENGTH_SHORT).show();
                return;
            }

            progressDialog.show();
            firebaseAuth.sendPasswordResetEmail(email)
                    .addOnSuccessListener(unused -> {
                        progressDialog.cancel();
                        Toast.makeText(SignIn.this, "Parol tiklash havolasi emailingizga yuborildi!", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        progressDialog.cancel();
                        Toast.makeText(SignIn.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

        binding.GoToSignUpFirebase.setOnClickListener(v -> startActivity(new Intent(SignIn.this, SignUp.class)));
    }

    private void signInWithEmailAndPassword(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    saveLoginState();
                    startActivity(new Intent(SignIn.this, MainActivity.class));
                    finish();
                    progressDialog.cancel();
                })
                .addOnFailureListener(e -> {
                    clearLoginState();
                    progressDialog.cancel();
                    Toast.makeText(SignIn.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void saveLoginState() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
    }

    private void clearLoginState() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();
    }

}

