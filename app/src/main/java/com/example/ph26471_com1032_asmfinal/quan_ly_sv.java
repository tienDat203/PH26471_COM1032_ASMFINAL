package com.example.ph26471_com1032_asmfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ph26471_com1032_asmfinal.ADAPTER.ADAPTER_SV;
import com.example.ph26471_com1032_asmfinal.DAO.DAO_SV;

import java.util.List;

public class quan_ly_sv extends AppCompatActivity {
    DAO_SV dao_sv;
    ADAPTER_SV adapter_sv;
    ListView lv_list;
    ImageView img_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_sv);

        dao_sv= new DAO_SV(this);
        dao_sv.open();

        adapter_sv= new ADAPTER_SV(dao_sv.selectALL(), dao_sv);

        lv_list= findViewById(R.id.lv_list_sv);
        lv_list.setAdapter(adapter_sv);

        img_add=findViewById(R.id.btn_add_sv);
        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter_sv.showdialogAdd(quan_ly_sv.this);
            }
        });
    }
}