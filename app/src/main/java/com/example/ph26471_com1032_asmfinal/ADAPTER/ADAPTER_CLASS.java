package com.example.ph26471_com1032_asmfinal.ADAPTER;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ph26471_com1032_asmfinal.DAO.DAO_CLASS;
import com.example.ph26471_com1032_asmfinal.DTO.DTO_CLASS;
import com.example.ph26471_com1032_asmfinal.R;

import java.util.ArrayList;

public class ADAPTER_CLASS extends BaseAdapter {
    ArrayList<DTO_CLASS> list;
    DAO_CLASS dao_class;

    public ADAPTER_CLASS(ArrayList<DTO_CLASS> list, DAO_CLASS dao_class) {
        this.list = list;
        this.dao_class = dao_class ;
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
        if(view==null){
            row= View.inflate(viewGroup.getContext(), R.layout.row_list_lop,null);
        }else{
            row=view;
        }
        final DTO_CLASS dto_class= list.get(i);
        int index=i;

        TextView tv_id_class= row.findViewById(R.id.tv_id_class);
        TextView tv_tenlop= row.findViewById(R.id.tv_ten_class);
        ImageView img_delete= row.findViewById(R.id.btn_delete);
        ImageView img_edit= row.findViewById(R.id.btn_edit);

        tv_id_class.setText("ID: "+dto_class.getId_class()+"");
        tv_tenlop.setText("Tên Lớp: "+dto_class.getTenlop());

        img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialogUpdate(viewGroup.getContext(), index,dto_class);
            }
        });

        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(viewGroup.getContext());
                builder.setTitle("Xóa Nhóm?");
                builder.setMessage("Bạn thực sự muốn xóa lớp: "+dto_class.getTenlop());
                builder.setPositiveButton("Đồng ý xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int kq= dao_class.deleteClass(dto_class);
                        if(kq>0){
                            list.remove(index);
                            notifyDataSetChanged();
                            Toast.makeText(viewGroup.getContext(), "Xóa Thành Công ", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(viewGroup.getContext(), "Xóa Không Thành Công ", Toast.LENGTH_SHORT).show();
                        }
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("Không xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });



        return row;
    }
    public void showdialogAdd(Context context){
        Dialog dialog= new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.them_lop);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.custom_dialog);
        dialog.show();

        EditText ed_tenlop=dialog.findViewById(R.id.ed_tenlop);
        Button btn_save= dialog.findViewById(R.id.btn_save_lop);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DTO_CLASS dto_class= new DTO_CLASS();
                dto_class.setTenlop(ed_tenlop.getText().toString());
                long res= dao_class.insertClass(dto_class);
                if(res>0){
                    list.clear();
                    list.addAll(dao_class.selectALL());
                    notifyDataSetChanged();
                    Toast.makeText(context,"Thêm Lớp Thành Công",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context,"Thêm Lớp Không Thành Công",Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        Button btn_exit= dialog.findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    public void showdialogUpdate(Context context, int index, DTO_CLASS dto_class){
        Dialog dialog= new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.sua_lop);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.custom_dialog);
        dialog.show();

        EditText ed_tenlop=dialog.findViewById(R.id.ed_tenlop_up);
        ed_tenlop.setText(dto_class.getTenlop());

        Button btn_save= dialog.findViewById(R.id.btn_save_lop_up);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dto_class.setTenlop(ed_tenlop.getText().toString());
                int res= dao_class.updateClass(dto_class);
                if(res>0){
                    list.set(index,dto_class);
                    notifyDataSetChanged();
                    Toast.makeText(context,"Sửa Lớp Thành Công",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context,"Sửa Lớp Không Thành Công",Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        Button btn_exit= dialog.findViewById(R.id.btn_exit_up);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }




}
