package com.example.ph26471_com1032_asmfinal.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.example.ph26471_com1032_asmfinal.DTO.DTO_SV;
import com.example.ph26471_com1032_asmfinal.SQL.SQL_SV;

import java.util.ArrayList;

public class DAO_SV {
    SQLiteDatabase db;
    SQL_SV sql_sv;

    public DAO_SV(Context context){
        sql_sv= new SQL_SV(context);
    }
    public void open(){
        db=sql_sv.getWritableDatabase();
    }
    public void close(){
        sql_sv.close();
    }

    public ArrayList<DTO_SV> selectALL(){
        // tạo biến chứa danh sách
        ArrayList<DTO_SV> list= new ArrayList<>();
        String [] insert_sql= new String[]{"*"};
        // tạo con trỏ đọc dữ liệu
        Cursor c= db.query("tb_sv", insert_sql, null,null,null,null,null);
        if(c.moveToFirst()){
            while (!c.isAfterLast()){
                DTO_SV dto_sv= new DTO_SV();

                dto_sv.setId(c.getInt(0));
                dto_sv.setHoten(c.getString(1));
                dto_sv.setNganh(c.getString(2));
                dto_sv.setEmail(c.getString(3));
                dto_sv.setSodt(c.getString(4));
                dto_sv.setId_class(c.getInt(5));
                list.add(dto_sv);
                c.moveToNext();
            }
        }

        return list;
    }

    public long insert(DTO_SV dto_sv){
        ContentValues contentValues= new ContentValues();

        contentValues.put("NAME",dto_sv.getHoten());
        contentValues.put("NGANH",dto_sv.getNganh());
        contentValues.put("EMAIL",dto_sv.getEmail());
        contentValues.put("PHONE",dto_sv.getSodt());
        contentValues.put("ID_CLASS",dto_sv.getId_class());

        long res= db.insert("tb_sv",null,contentValues);
        return res;
    }

    public int delete(DTO_SV dto_sv){
        int res=db.delete("tb_sv","ID=?",new String[]{dto_sv.getId()+""});
        return res;
    }

    public int update(DTO_SV dto_sv){
        ContentValues contentValues= new ContentValues();

        contentValues.put("NAME",dto_sv.getHoten());
        contentValues.put("NGANH",dto_sv.getNganh());
        contentValues.put("EMAIL",dto_sv.getEmail());
        contentValues.put("PHONE",dto_sv.getSodt());
        contentValues.put("ID_CLASS",dto_sv.getId_class());

        int res= db.update("tb_sv",contentValues,"ID=?",new String[]{dto_sv.getId()+""});
        return res;
    }

    public DTO_SV hienthichitiet(int id){
        String [] agrs= new String[]{id+ ""};

        DTO_SV dto_sv= new DTO_SV();
        String insert_tb_class="SELECT ID, NAME,NGANH, EMAIL,PHONE,tb_class.ID_CLASS,tb_class.NAMECLASS FROM tb_sv INNER JOIN tb_class ON tb_sv.ID_CLASS = tb_class.ID_CLASS WHERE ID = ?";
        Cursor c= db.rawQuery(insert_tb_class,agrs);
        if(c.moveToFirst()){
            dto_sv.setId(c.getInt(0));
            dto_sv.setHoten(c.getString(1));
            dto_sv.setNganh(c.getString(2));
            dto_sv.setEmail(c.getString(3));
            dto_sv.setSodt(c.getString(4));
            dto_sv.setId_class(c.getInt(5));
            dto_sv.setLop(c.getString(6));
        }
        return dto_sv;
    }

}
