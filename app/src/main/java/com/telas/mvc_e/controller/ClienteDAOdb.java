package com.telas.mvc_e.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.telas.mvc_e.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAOdb extends SQLiteOpenHelper {

    public ClienteDAOdb(@Nullable Context context){
        super(context, "cliente.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table cliente(" +
                                        "    id integer primary key autoincrement," +
                                        "    nome text," +
                                        "    telefone text," +
                                        "    email text," +
                                        "    dataNasc text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS TABLE_USERS");
        onCreate(sqLiteDatabase);
    }

    public  List<Cliente> todos(){
        List<Cliente> consulta = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        try{
            Cursor cursor = db.rawQuery("Select * from cliente",null);

            if (cursor.moveToFirst()){
                do {
                    Cliente cl = new Cliente(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4));
                    consulta.add(cl);
                }while (cursor.moveToNext());
                cursor.close();
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            db.close();
        }

        return consulta;
    }

    public void salvar(Cliente cliente){
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues valores = new ContentValues();
            valores.put("nome", cliente.getNome());
            valores.put("telefone", cliente.getTelefone());
            valores.put("email", cliente.getEmail());
            valores.put("dataNasc", cliente.getDataNasc());

            long id = db.insert("cliente", null, valores);
            cliente.setId((int) id);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            db.close();
        }

    }

    public void remover(Cliente cliente){
        SQLiteDatabase db = getWritableDatabase();

        String condicao[] = new String[]{String.valueOf(cliente.getId())};

        db.delete("cliente","id=?",condicao);
        db.close();

    }


}
