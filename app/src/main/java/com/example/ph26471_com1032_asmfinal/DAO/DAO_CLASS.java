package com.example.ph26471_com1032_asmfinal.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ph26471_com1032_asmfinal.DTO.DTO_CLASS;
import com.example.ph26471_com1032_asmfinal.SQL.SQL_SV;

import java.util.ArrayList;

public class DAO_CLASS {
    SQLiteDatabase db;
    SQL_SV sql_sv;

    public DAO_CLASS(Context context){
        sql_sv = new SQL_SV(context);
    }

    public void open(){
        db=sql_sv.getWritableDatabase();
    }
    public void close(){
        sql_sv.close();
    }

    public ArrayList<DTO_CLASS> selectALL(){
        ArrayList<DTO_CLASS> listclass= new ArrayList<>();
        String []insert_tb_class= new String[] {"*"};
        Cursor c= db.query("tb_class",insert_tb_class,null,null,null,null,null);
        if(c.moveToFirst()){
            while (!c.isAfterLast()){
                DTO_CLASS dto_class= new DTO_CLASS();
                dto_class.setId_class(c.getInt(0));
                dto_class.setTenlop(c.getString(1));
                listclass.add(dto_class);
                c.moveToNext();
            }
        }
        return listclass;
    }

    public long insertClass(DTO_CLASS dto_class){
        ContentValues contentValues= new ContentValues();
        contentValues.put("NAMECLASS",dto_class.getTenlop());
        long res= db.insert("tb_class",null,contentValues);
        return res;
    }

    public int deleteClass(DTO_CLASS dto_class){
        int res=db.delete("tb_class","ID_CLASS=?",new String[]{dto_class.getId_class()+""});
        return res;
    }

    public int updateClass(DTO_CLASS dto_class){
        ContentValues contentValues= new ContentValues();
        contentValues.put("NAMECLASS",dto_class.getTenlop());
        int res= db.update("tb_class",contentValues,"ID_CLASS=?",new String[]{dto_class.getId_class()+""});
        return res;
    }

}
