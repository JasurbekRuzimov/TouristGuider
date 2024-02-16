package uz.example.touristguider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import uz.example.touristguider.databinding.FragmentSettingsBinding;
import uz.example.touristguider.register.SignIn;

public class SettingsFragment extends Fragment {

    FragmentSettingsBinding binding;


    public SettingsFragment() {
        // Required empty public constructor
    }


    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.backTv.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });

        binding.editAccountTv.setOnClickListener(v -> {
          //  startActivity(new Intent(requireContext(), EditAccount.class));
        });

        binding.languageTv.setOnClickListener(v -> showLanguageDialog());

        binding.nightModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
               setNightMode(true);
            } else {
               setNightMode(false);
            }
        });

        binding.invateTv.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=uz.example.touristguider");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        });

        binding.helpTv.setOnClickListener(v -> {
            String tg_url = "https://t.me/Haytboyev_Sarvar";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(android.net.Uri.parse(tg_url));
            startActivity(intent);
        });

        binding.logout.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setMessage(R.string.accountdan_chiqib_ketmoqchimisiz)
                    .setPositiveButton("Yes", (dialog, id) -> {
                        ProgressDialog progressDialog = new ProgressDialog(requireContext());
                        progressDialog.setMessage(getString(R.string.chiqish_amalga_oshmoqda));
                        progressDialog.show();
                        FirebaseAuth.getInstance().signOut();
                        progressDialog.dismiss();
                        startActivity(new Intent(requireContext(), SignIn.class));
                        requireActivity().finish();
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        return view;
    }

    private void setNightMode(boolean b) {
        if (b) {
            requireActivity().setTheme(R.style.Theme_TouristGuiderDark);
        } else {
            requireActivity().setTheme(R.style.Theme_TouristGuider);
        }
    }

    private void showLanguageDialog() {
        String[] languages = {"Uzbek", "English", "Russian"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Tilni tanlang")
                .setItems(languages, (dialog, which) -> {
                    String selectedLanguage = languages[which];
                    // Do something with the selected language
                    // For example, exchange a display lang
                     Toast.makeText(getActivity(), "Selected language: " + selectedLanguage, Toast.LENGTH_SHORT).show();
                });
        builder.create().show();
    }
}