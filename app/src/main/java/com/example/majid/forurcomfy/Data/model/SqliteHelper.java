package com.example.majid.forurcomfy.Data.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yesol on 2018-05-04.
 */

public class SqliteHelper extends SQLiteOpenHelper {

    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SHOPPING_CART(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " itemId TEXT, quantity INTEGER,  itemName TEXT," +
                " category TEXT, description TEXT, price INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(int _id, String itemId, int quantity, String itemName, String category, String description, int price) {
        SQLiteDatabase db = getReadableDatabase();
        int q = 0;
        Cursor c = db.rawQuery("SELECT quantity FROM SHOPPING_CART WHERE itemName = '"+ itemName +"';", null);

        int count = c.getCount();
        c.moveToFirst();
        if(count > 0) q = c.getInt(0);

        c.close();

        Log.d("DB: ", "count is "+count);

        db = getWritableDatabase();
        if(count > 0) {
//            q = c.getInt(1);
            q += 1;
            db.execSQL("UPDATE SHOPPING_CART SET quantity='"+q+"' WHERE itemId='" + itemId +"'");
        }else {
            db.execSQL("INSERT INTO SHOPPING_CART VALUES(null,'"+ itemId + "','" + quantity + "','" +
                    itemName + "','" + category + "','" + description + "','" + price + "');");
        }


//        try{
//            db.execSQL("INSERT INTO SHOPPING_CART VALUES(null,'"+ itemId + "','" + quantity + "','" +
//                    itemName + "','" + category + "','" + description + "','" + price + "');");
//        }
//        catch (SQLiteConstraintException e) {
//            quantity += 1;
//            db.execSQL("UPDATE SHOPPING_CART SET quantity='"+quantity+"' WHERE itemId='" + itemId +"'");
//        }

        db.close();
    }

    public void clear() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + "SHOPPING_CART");
        db.close();
    }

    public void delete(String _id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM SHOPPING_CART WHERE _id='" + _id + "';" );
        db.close();
    }

    public int getTotalPrice() {
        int total = 0;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT price*quantity FROM SHOPPING_CART;", null);
        while (c.moveToNext()) {
            total += c.getInt(0);
        }
        Log.d("TOTAL PRICE", "total price is " + total);
        db.close();
        return total;
    }

    public JSONObject getJSonResult() {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";
        JSONObject jsonObject = new JSONObject();
        Cursor cursor = db.rawQuery("SELECT * FROM SHOPPING_CART", null);
        while(cursor.moveToNext()){
            result += "{\"_id:\" \"" + cursor.getString(0) + "\", \"itemId\": \"" + cursor.getString(1) + "\", \"itemName\": \""
                    + cursor.getString(2) + "\" ,\"category\": \"" + cursor.getString(3) + "\", \"description\": \""
                    + cursor.getString(4) + "\",\"price\": \"" + cursor.getString(5) + "\"}";
            try {
                jsonObject = new JSONObject(result);
                Log.d("SqliteHelper", jsonObject.toString());
            } catch (JSONException e) {
                Log.e("SqliteHelper", "could not parse JSON \"" + result + "\"");
            }
        }
        return jsonObject;
    }

    public String getResult() {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";
        JSONObject jsonObject = new JSONObject();
        Cursor cursor = db.rawQuery("SELECT * FROM SHOPPING_CART", null);
        while(cursor.moveToNext()){

            return "FoodMenu{" +
                    "_id='" + cursor.getString(0) + '\'' +
                    ", itemId=" + cursor.getString(1) +
                    ", itemName='" + cursor.getString(2) + '\'' +
                    ", category='" + cursor.getString(3) + '\'' +
                    ", description='" + cursor.getString(4) + '\'' +
                    ", sortPosition=" + cursor.getString(5) +
                    ", price=" + cursor.getString(6) +
                    ", image='" + cursor.getString(7) + '\'' +
                    '}';
        }
        return result;
    }

}
