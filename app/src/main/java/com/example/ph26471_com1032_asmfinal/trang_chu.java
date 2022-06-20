package com.example.ph26471_com1032_asmfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class trang_chu extends AppCompatActivity {
    ImageView img_dssv;
    ImageView img_dsclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        // chạy animation
        ConstraintLayout constraintLayout= findViewById(R.id.login_Layout);
        AnimationDrawable animationDrawable= (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1500);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();

        img_dssv=findViewById(R.id.img_quanly);
        img_dssv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1= new Intent(getBaseContext(),quan_ly_sv.class);
                startActivity(intent1);
            }
        });

        img_dsclass= findViewById(R.id.img_danhsach);
        img_dsclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2= new Intent(getBaseContext(),quan_ly_class.class);
                startActivity(intent2);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu_option,menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(R.id.in4_sv==item.getItemId()){
            Intent intent= new Intent(getBaseContext(),ThongTinSInhVien.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}