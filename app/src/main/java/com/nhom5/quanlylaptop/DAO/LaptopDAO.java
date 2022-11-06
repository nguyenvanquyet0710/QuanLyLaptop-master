package com.nhom5.quanlylaptop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.nhom5.quanlylaptop.Database.QLLaptopDB;
import com.nhom5.quanlylaptop.Entity.Laptop;

import java.util.ArrayList;

public class LaptopDAO {
    QLLaptopDB qlLaptopDB;
    SQLiteDatabase db;
    Context context;
    String TAG = "LaptopDAO_____";

    public LaptopDAO(Context context) {
        this.context = context;
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
    }

    public ArrayList selectLaptop(String[] columns, String selection, String[] selectionArgs, String orderBy) {
        ArrayList<Laptop> listLap = new ArrayList<>();
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        Cursor c = db.query("TB_Laptop", columns, selection, selectionArgs, null, null, orderBy);
        Log.d(TAG, "selectLaptop: Cursor: " + c.toString());

        if (c.getCount() > 0) {
            Log.d(TAG, "selectLaptop: Cursor not null");
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Log.d(TAG, "selectLaptop: Cursor not last");
                String maLaptop = c.getString(0);
                String maHangLap = c.getString(1);
                String maRate = c.getString(2);
                byte[] anhLaptop = c.getBlob(3);
                String tenLaptop = c.getString(4);
                String loaiLaptop = c.getString(4);
                String thongSoKT = c.getString(4);
                float giaTien = 0;
                try {
                    giaTien = Float.parseFloat(c.getString(4));
                } catch (Exception e){
                    e.printStackTrace();
                }
                Laptop newLap = new Laptop(maLaptop, maHangLap, maRate, tenLaptop, loaiLaptop, thongSoKT, giaTien, anhLaptop);
                Log.d(TAG, "selectLaptop: new Laptop: " + newLap.toString());

                listLap.add(newLap);
                c.moveToNext();
            }
            c.close();
        } else {
            Log.d(TAG, "selectLaptop: Cursor null");
        }
        db.close();

        return listLap;
    }

    public void insertLaptop(Laptop laptop) {
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maLaptop", laptop.getMaLaptop());
        values.put("maHangLap", laptop.getMaHangLap());
        values.put("maRate", laptop.getMaRate());
        values.put("anhLaptop", laptop.getAnhLaptop());
        values.put("tenLaptop", laptop.getTenLaptop());
        values.put("loaiLaptop", laptop.getLoaiLaptop());
        values.put("thongSoKT", laptop.getThongSoKT());
        values.put("giaTien", laptop.getGiaTien());
        Log.d(TAG, "insertLaptop: Laptop: " + laptop.toString());
        Log.d(TAG, "insertLaptop: Values: " + values);

        long ketqua = db.insert("TB_Laptop", null, values);
        if (ketqua > 0) {
            Log.d(TAG, "insertLaptop: Thêm thành công");
            Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "insertLaptop: Thêm thất bại");
            Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public void updateLaptop(Laptop laptop) {
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maLaptop", laptop.getMaLaptop());
        values.put("maHangLap", laptop.getMaHangLap());
        values.put("maRate", laptop.getMaRate());
        values.put("anhLaptop", laptop.getAnhLaptop());
        values.put("tenLaptop", laptop.getTenLaptop());
        values.put("loaiLaptop", laptop.getLoaiLaptop());
        values.put("thongSoKT", laptop.getThongSoKT());
        values.put("giaTien", laptop.getGiaTien());
        Log.d(TAG, "updateLaptop: Laptop: " + laptop.toString());
        Log.d(TAG, "updateLaptop: Values: " + values);

        long ketqua = db.update("TB_Laptop", values, "maLaptop=?", new String[]{String.valueOf(laptop.getMaLaptop())});
        if (ketqua > 0) {
            Log.d(TAG, "updateLaptop: Sửa thành công");
            Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "updateLaptop: Sửa thất bại");
            Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public void deleteLaptop(Laptop laptop){
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        Log.d(TAG, "deleteLaptop: Laptop: " + laptop.toString());

        long ketqua = db.delete("TB_Laptop", "maLaptop=?", new String[]{String.valueOf(laptop.getMaLaptop())});
        if (ketqua > 0) {
            Log.d(TAG, "deleteLaptop: Xóa thành công");
            Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "deleteLaptop: Xóa thất bại");
            Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}
