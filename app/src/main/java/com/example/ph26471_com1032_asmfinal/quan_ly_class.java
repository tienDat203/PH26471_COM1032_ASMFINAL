package com.example.ph26471_com1032_asmfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ph26471_com1032_asmfinal.ADAPTER.ADAPTER_CLASS;
import com.example.ph26471_com1032_asmfinal.DAO.DAO_CLASS;

public class quan_ly_class extends AppCompatActivity {
    DAO_CLASS dao_class;
    ADAPTER_CLASS adapter_class;
    ListView lv_liss;
    ImageView img_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_class);
        dao_class= new DAO_CLASS(this);
        dao_class.open();

        adapter_class= new ADAPTER_CLASS(dao_class.selectALL(), dao_class);
        lv_liss= findViewById(R.id.lv_list_class);
        lv_liss.setAdapter(adapter_class);

        img_add=findViewById(R.id.btn_add_class);
        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter_class.showdialogAdd(quan_ly_class.this);
            }
        });
    }
}