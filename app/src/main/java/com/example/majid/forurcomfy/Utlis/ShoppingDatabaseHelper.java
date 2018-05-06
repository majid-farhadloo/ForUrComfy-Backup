package com.example.majid.forurcomfy.Utlis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.majid.forurcomfy.Common.Current;
import com.example.majid.forurcomfy.ShoppingCart.Shopping;

import java.util.ArrayList;

public class ShoppingDatabaseHelper extends SQLiteOpenHelper {

    private  static final  String TAG = "ShoppingDatabaseHelper";
    protected static ArrayList<Shopping> shoppings;

    public static final String TABLE_NAME = Current.currentUser.getEmail().split("@")[0] + "ShoppingCartTable";
    public static final String COL = "_id";
    public static final String COL1 = "foodname";
    public static final String COL2 = "price";
    public static final String COL3 = "quantity";
    public static final String COL4 = "totalprice";

    public ShoppingDatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" + COL + " INTEGER PRIMARY KEY, " + COL1 + " TEXT, " + COL2 + " TEXT, "  + COL3 + " TEXT, " + COL4 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String foodname, String price, String quantity, String totalprice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, foodname);
        contentValues.put(COL2, price);
        contentValues.put(COL3, quantity);
        contentValues.put(COL4, totalprice);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor viewData(String foodname) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL1 + " = ?", new String[] { foodname });
        if (res.getCount() > 0 ) {
            return res;
        } else {
            return null;
        }
    }

    public ArrayList<Shopping> getAllDatas() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        shoppings = new ArrayList<>();
        if (res.moveToFirst()) {
            while (!res.isAfterLast()) {
                String foodname = res.getString(res.getColumnIndex(COL1));
                String price = "Rs. " + res.getString(res.getColumnIndex(COL2));
                String quantity = "x " + res.getString(res.getColumnIndex(COL3));
                String totalprice = "Rs. " + res.getString(res.getColumnIndex(COL4));

                shoppings.add(new Shopping(foodname, price, quantity, totalprice));
                res.moveToNext();
            }

        }
        return shoppings;
    }

    public void deleteAll(String tableName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ tableName);
    }

    public boolean updateData(String foodname, String price, String quantity, String totalprice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, foodname);
        contentValues.put(COL2, price);
        contentValues.put(COL3, quantity);
        contentValues.put(COL4, totalprice);

        long result = db.update(TABLE_NAME, contentValues,COL1 +" = ?", new String[] {foodname});

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

}
