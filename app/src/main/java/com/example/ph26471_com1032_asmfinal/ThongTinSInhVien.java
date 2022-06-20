package com.example.ph26471_com1032_asmfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;

public class ThongTinSInhVien extends AppCompatActivity {

    WebView wv_ttsv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_sinh_vien);
        wv_ttsv=findViewById(R.id.wv_ttsv);
        wv_ttsv.getSettings().setJavaScriptEnabled(true);
        wv_ttsv.loadUrl("file:///android_asset/inforSV.html");
    }
}