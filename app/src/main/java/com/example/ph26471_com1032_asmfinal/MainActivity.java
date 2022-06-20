package com.example.ph26471_com1032_asmfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_login;
    EditText ed_user;
    EditText ed_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_login = findViewById(R.id.btn_login);
        ed_user = findViewById(R.id.ed_user);
        ed_pass = findViewById(R.id.ed_pass);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!check_user() || !check_pass()){
                    return;
                }
                Intent intent = new Intent(getBaseContext(), trang_chu.class);
                startActivity(intent);
            }
        });
    }

    private boolean check_user(){
        String check_user = ed_user.getText().toString();
        if(check_user.isEmpty()){
            ed_user.setError("Username không được để trống");
            return  false;
        }else{
            ed_user.setError(null);
            return true;
        }
    }

    private boolean check_pass(){
        String check_pass = ed_pass.getText().toString();
        if(check_pass.isEmpty()){
            ed_pass.setError("Password không được để trống");
            return  false;
        }else{
            ed_pass.setError(null);
            return true;
        }
    }
}