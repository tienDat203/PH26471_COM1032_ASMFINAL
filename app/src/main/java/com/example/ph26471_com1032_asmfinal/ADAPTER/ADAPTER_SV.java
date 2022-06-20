package com.example.ph26471_com1032_asmfinal.ADAPTER;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ph26471_com1032_asmfinal.DAO.DAO_CLASS;
import com.example.ph26471_com1032_asmfinal.DAO.DAO_SV;
import com.example.ph26471_com1032_asmfinal.DTO.DTO_CLASS;
import com.example.ph26471_com1032_asmfinal.DTO.DTO_SV;
import com.example.ph26471_com1032_asmfinal.R;

import java.util.ArrayList;

public class ADAPTER_SV extends BaseAdapter {
    ArrayList<DTO_SV> list;
    DAO_SV dao_sv;

    public ADAPTER_SV(ArrayList<DTO_SV> list, DAO_SV dao_sv) {
        this.list = list;
        this.dao_sv = dao_sv;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        DTO_SV dto_sv = list.get(i);
        return dto_sv;
    }

    @Override
    public long getItemId(int i) {
        DTO_SV dto_sv = list.get(i);
        return dto_sv.getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)  {
        View row;
        if (view == null) {
            row = View.inflate(viewGroup.getContext(), R.layout.row_list_sv, null);
        } else {
            row = view;
        }
        //Lấy thông tin
        final DTO_SV dto_sv = list.get(i);
        int index = i;
        // Ánh xạ
        TextView tv_hoten = row.findViewById(R.id.tv_hoten_list);
        TextView tv_sodt = row.findViewById(R.id.tv_sodt_list);
        TextView tv_text_sodt=row.findViewById(R.id.tv_sodt);
        tv_text_sodt.setText("Số Điện Thoại: ");
        tv_hoten.setText("Họ Tên: "+dto_sv.getHoten());
        tv_sodt.setText(dto_sv.getSodt());

        // Gọi điện
        tv_sodt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = viewGroup.getContext();
                String sodt= tv_sodt.getText().toString();
                Intent intent= new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+sodt));
                context.startActivity(intent);
            }
        });

        ImageView btn_update = row.findViewById(R.id.btn_edit_sv);
        ImageView btn_delete = row.findViewById(R.id.btn_delete_sv);

        //Giữ tên để hiển thị chi tiết
        tv_hoten.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(viewGroup.getContext());
//                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(viewGroup.getContext());
                builder.setTitle("Thông Tin Sinh Viên");
                builder.setIcon(R.drawable.avt);

                DTO_SV new_dto_sv = dao_sv.hienthichitiet(dto_sv.getId());
                builder.setMessage( new_dto_sv.toString () );
                AlertDialog dialog = builder.create();
                dialog.show();

                return false;
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // hiển thị dialog hỏi
                AlertDialog.Builder builder = new AlertDialog.Builder(viewGroup.getContext());
                builder.setTitle("Xóa Sinh Viên ?");
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setMessage("Bạn muốn xóa : " + dto_sv.getHoten());
                builder.setPositiveButton("Đồng ý xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // gọi lệnh xóa dòng
                        int kq = dao_sv.delete(dto_sv);
                        if (kq > 0) {
                            list.remove(dto_sv);
                            notifyDataSetChanged();
                            Toast.makeText(viewGroup.getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(viewGroup.getContext(), "Xóa Không Thành Công", Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();
                        }
                    }
                });
                builder.setNegativeButton("Không Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialogUpdate(dto_sv, index, viewGroup.getContext());
            }
        });

        return row;
    }

    public void showdialogUpdate(DTO_SV dto_sv, int index, Context context) {
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.sua_sv);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.custom_dialog);
        dialog.show();

        EditText ed_hoten = dialog.findViewById(R.id.ed_hoten_up);
        EditText ed_nganh = dialog.findViewById(R.id.ed_nganh_up);
        EditText ed_email = dialog.findViewById(R.id.ed_email_up);
        EditText ed_sodt = dialog.findViewById(R.id.ed_sodt_up);

        ed_hoten.setText(dto_sv.getHoten());
        ed_nganh.setText(dto_sv.getNganh());
        ed_email.setText(dto_sv.getEmail());
        ed_sodt.setText(dto_sv.getSodt());


        //Viết Spiner
        final Spinner spin_class = dialog.findViewById(R.id.spin_class);
        DAO_CLASS dao_class = new DAO_CLASS(context);
        dao_class.open();
        ArrayList<DTO_CLASS> list_class = dao_class.selectALL();
        ADAPTER_SPINCLASS adapter_spinclass = new ADAPTER_SPINCLASS(list_class);
        spin_class.setAdapter(adapter_spinclass);
        for (int j = 0; j < list.size(); j++) {
            DTO_CLASS tmp = list_class.get(j);
            if (tmp.getId_class() == dto_sv.getId_class()) {
                spin_class.setSelection(j);
                spin_class.setSelected(true);
            }
        }

        Button btn_saveUp = dialog.findViewById(R.id.btn_save_sv_up);
        btn_saveUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dto_sv.setHoten(ed_hoten.getText().toString());
                dto_sv.setNganh(ed_nganh.getText().toString());
                dto_sv.setEmail(ed_email.getText().toString());
                dto_sv.setSodt(ed_sodt.getText().toString());

                DTO_CLASS dto_class = (DTO_CLASS) spin_class.getSelectedItem();
                dto_sv.setId_class(dto_class.getId_class());

                int res = dao_sv.update(dto_sv);
                if (res > 0) {
                    list.set(index, dto_sv);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Cập Nhập Thành Công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Cập Nhập Không Thành Công", Toast.LENGTH_SHORT).show();
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

    public void showdialogAdd(Context context) {
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.them_sv);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.custom_dialog);
        dialog.show();

        EditText ed_hoten = dialog.findViewById(R.id.ed_hoten);
        EditText ed_nganh = dialog.findViewById(R.id.ed_nganh);
        EditText ed_email = dialog.findViewById(R.id.ed_email);
        EditText ed_sodt = dialog.findViewById(R.id.ed_sodt);

        //Viết Spiner
        final Spinner spin_class = dialog.findViewById(R.id.spin_class);
        DAO_CLASS dao_class = new DAO_CLASS(context);
        dao_class.open();
        ADAPTER_SPINCLASS adtSpin = new ADAPTER_SPINCLASS(dao_class.selectALL());
        spin_class.setAdapter(adtSpin);


        Button btn_saveUp = dialog.findViewById(R.id.btn_save_sv);
        btn_saveUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DTO_SV dto_sv = new DTO_SV();

                dto_sv.setHoten(ed_hoten.getText().toString());
                dto_sv.setNganh(ed_nganh.getText().toString());
                dto_sv.setEmail(ed_email.getText().toString());
                dto_sv.setSodt(ed_sodt.getText().toString());

                DTO_CLASS dto_class = (DTO_CLASS) spin_class.getSelectedItem();
                dto_sv.setId_class(dto_class.getId_class());

                long res = dao_sv.insert(dto_sv);
                if (res > 0) {
                    list.clear();
                    notifyDataSetChanged();
                    list.addAll(dao_sv.selectALL());
                    Toast.makeText(context, "Thêm Mới Thành Công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Thêm Mới Không Thành Công", Toast.LENGTH_SHORT).show();
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

}

