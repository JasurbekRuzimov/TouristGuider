package uz.example.touristguider.bottomnavfragments;

import static java.lang.Math.round;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import uz.example.touristguider.R;
import uz.example.touristguider.databinding.FragmentCurrencyexchangeBinding;

public class CurrencyExchangeFragment extends Fragment {

    private FragmentCurrencyexchangeBinding binding;
    ProgressBar progressBar;
    WebView currencyWebView;

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentCurrencyexchangeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        progressBar = root.findViewById(R.id.progressBar);
        currencyWebView = root.findViewById(R.id.currencyWebView);
        currencyWebView.getSettings().setJavaScriptEnabled(true);
        currencyWebView.setWebViewClient(new WebViewClient());
        currencyWebView.setWebChromeClient(new WebChromeClient());
        currencyWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }
        });

        currencyWebView.loadUrl("https://www.xe.com/currencyconverter/");

        return root;
    }
}
