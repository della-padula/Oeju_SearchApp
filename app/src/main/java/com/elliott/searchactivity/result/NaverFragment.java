package com.elliott.searchactivity.result;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.elliott.searchactivity.BaseData;
import com.elliott.searchactivity.R;
import com.elliott.searchactivity.databinding.FragmentNaverBinding;

public class NaverFragment extends Fragment {
    private FragmentNaverBinding binding;

    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundleSavedInstance) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_naver, container, false);
        binding.setFragment(this);

        this.loadWebPage();
        return binding.getRoot();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void loadWebPage() {
        String loadBaseURL = "https://search.naver.com/search.naver?where=post&query=";
        binding.webView.setWebViewClient(new WebViewClientClass());
        binding.webView.setWebChromeClient(new WebChromeClient());

        binding.webView.loadUrl(loadBaseURL + BaseData.searchKeyword);
        binding.webView.getSettings().setSupportZoom(true);
        binding.webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.getSettings().setUseWideViewPort(true);
        binding.webView.getSettings().setLoadWithOverviewMode(true);
        binding.webView.getSettings().setDomStorageEnabled(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.loadWebPage();
    }

    private class WebViewClientClass extends WebViewClient {//페이지 이동
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("check URL",url);
            view.loadUrl(url);
            return true;
        }
    }
}
