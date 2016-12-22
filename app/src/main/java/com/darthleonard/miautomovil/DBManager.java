package com.darthleonard.miautomovil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Darth on 18/12/2016.
 */
public class DBManager extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "miautomovil.db";

    private static final String SQL_CREAR = "CREATE TABLE registros (_id INTEGER PRIMARY KEY AUTOINCREMENT, fecha TEXT, precio TEXT, litros TEXT, km TEXT)";

    public static final int MAXIMO_REGISTROS = 10;

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREAR);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){ }

    public void insertaRegistro(Registro registro) {
        int[] aux = cuentaRegistros();
        if(aux[0] >= MAXIMO_REGISTROS)
            eliminaRegistro(aux[1]);

        SQLiteDatabase db = getWritableDatabase();
        if(db != null) {

            ContentValues campos = new ContentValues();
            campos.put("fecha", registro.getFecha());
            campos.put("precio", registro.getPrecio());
            campos.put("litros", registro.getLitros());
            campos.put("km", registro.getKilometros());
            db.insert("registros",null,campos);
            db.close();
        }
    }

    public void eliminaRegistro(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("registros", "_id="+id, null);
        db.close();
    }

    public ArrayList<Registro> recuperarRegistros() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Registro> registros = new ArrayList<>();
        String[] campos = {"_id","fecha","precio","litros","km"};
        Cursor c = db.query("registros",campos,null,null,null,null,null,null);

        if(c.moveToFirst()) {
            do {
                registros.add(
                        new Registro(
                                c.getInt(0),
                                c.getString(1),
                                c.getString(2),
                                c.getString(3),
                                c.getString(4)
                        )
                );
            }while (c.moveToNext());
        }

        c.close();
        db.close();
        return  registros;
    }

    private int[] cuentaRegistros() {
        SQLiteDatabase db = getReadableDatabase();
        String[] campos = {"_id","fecha"};
        Cursor c = db.query("registros", campos, null, null, null, null, null, null);

        int[] res = {0,0};
        if(c.moveToFirst()) {
            int[] aux = {0, c.getInt(0)};
            do
                aux[0]++;
            while (c.moveToNext());
            res = aux;
        }

        c.close();
        db.close();
        return res;
    }

}
