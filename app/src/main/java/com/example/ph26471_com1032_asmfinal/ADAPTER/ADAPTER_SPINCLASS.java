package com.example.ph26471_com1032_asmfinal.ADAPTER;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ph26471_com1032_asmfinal.DAO.DAO_CLASS;
import com.example.ph26471_com1032_asmfinal.DTO.DTO_CLASS;
import com.example.ph26471_com1032_asmfinal.R;

import java.util.ArrayList;

public class ADAPTER_SPINCLASS extends BaseAdapter {
    ArrayList<DTO_CLASS> list;

    public ADAPTER_SPINCLASS(ArrayList<DTO_CLASS> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        DTO_CLASS dto_class= list.get(i);
        return dto_class;
    }

    @Override
    public long getItemId(int i) {
        DTO_CLASS dto_class= list.get(i);
        return dto_class.getId_class();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        // khởi tạo cho itemView
        if(view == null){
            row = View.inflate(viewGroup.getContext(), R.layout.spiner, null);
        }else
            row = view;

        //--- lấy thông tin bản ghi dữ liệu
        final DTO_CLASS dto_class = list.get(i);
        final int index = i;

        // ánh xạ các view vào biến
        TextView tv_id= row.findViewById(R.id.tv_id_class_spiner);
        TextView tv_namelop = row.findViewById(R.id.tv_tenlop_spiner);

        //----------- set text
        tv_id.setText("ID: "+ dto_class.getId_class()+"");
        tv_namelop.setText("Lớp: "+dto_class.getTenlop());

        return  row;
    }
}
