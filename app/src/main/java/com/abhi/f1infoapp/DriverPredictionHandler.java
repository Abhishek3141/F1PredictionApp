package com.abhi.f1infoapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class DriverPredictionHandler extends DatabaseHelper{
    public DriverPredictionHandler(Context context){
        super(context);

    }

    public boolean create(Note note){
        ContentValues values = new ContentValues();

        values.put("id",note.getId());
        values.put("prediction",note.getPrediction());
        SQLiteDatabase db = this.getWritableDatabase();

        boolean isSuccessful = db.insert("Note",null,values) > 0;
        db.close();
        return isSuccessful;
    }
    public ArrayList<Note> readNotes() {
        ArrayList<Note> notes = new ArrayList<>();

        String sqlQuery = "SELECT * FROM Note ORDER BY id ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        if (cursor.moveToFirst()) {
            do {

                @SuppressLint("Range") int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                @SuppressLint("Range") String prediction = cursor.getString(cursor.getColumnIndex("prediction"));

                Note note = new Note(id,prediction);
                note.setId(id);
                notes.add(note);

            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }
        return notes;
    }

    public Note readSingleNote(int id) {
        Note note = null;
        String sqlQuery = "SELECT * FROM Note WHERE id=" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        if (cursor.moveToFirst()) {
            @SuppressLint("Range") int noteId = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            @SuppressLint("Range") String prediction = cursor.getString(cursor.getColumnIndex("prediction"));

            note = new Note(noteId,prediction);
            note.setId(noteId);

        }
        cursor.close();
        db.close();
        return note;

    }

    public boolean update(Note note){

        ContentValues values = new ContentValues();
        values.put("prediction", note.getPrediction());

        SQLiteDatabase db = this.getWritableDatabase();
        boolean isSuccessfull = db.update("Note",values,"id='"+note.getId()+"'",null) > 0;
        db.close();
        return isSuccessfull;

    }
    public boolean delete(int id){
        boolean isDeleted;
        SQLiteDatabase db = this.getWritableDatabase();
        isDeleted = db.delete("Note","id='"+id+"'",null) > 0;
        db.close();
        return isDeleted;

    }

}
