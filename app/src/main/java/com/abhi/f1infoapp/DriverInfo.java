package com.abhi.f1infoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class DriverInfo extends AppCompatActivity {


    ImageButton imageButton;
    ArrayList<Note> notes;
    ImageView img;
    TextView txt;
    TextView desc;
    TextView pred;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_info);

        imageButton = findViewById(R.id.img_add);
        Intent intent = getIntent();
        int position = intent.getIntExtra("position",0);
        int id = intent.getIntExtra("id",0);

        Note note = new Note(id,"");

       // boolean isInserted = new DriverPredictionHandler(DriverInfo.this).create(note);
        /*DriverPredictionHandler driverPredictionHandler = new DriverPredictionHandler(DriverInfo.this);
        Note noteRead = driverPredictionHandler.readSingleNote(position);
        String prediction = noteRead.getPrediction();
        if(prediction == ""){

        }*/

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) DriverInfo.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewInput = inflater.inflate(R.layout.note_input, null, false);

                EditText edtDescription = viewInput.findViewById(R.id.edt_description);


                new AlertDialog.Builder(DriverInfo.this)
                        .setView(viewInput)
                        .setTitle("Add prediction")
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String prediction = edtDescription.getText().toString();

                                Intent intent = getIntent();
                                int position = intent.getIntExtra("position",0);

                                Note note = new Note(id,prediction);

                                boolean isInserted = new DriverPredictionHandler(DriverInfo.this).update(note);

                                if(isInserted){
                                    Toast.makeText(DriverInfo.this,"Note Saved", Toast.LENGTH_SHORT).show();
                                    desc = findViewById(R.id.Prediction);
                                    desc.setText(prediction);

                                }
                                else
                                {
                                    Toast.makeText(DriverInfo.this, "Unable to save the note", Toast.LENGTH_SHORT).show();

                                }
                                dialogInterface.cancel();



                            }
                        }).show();
            }
        });

        img = findViewById(R.id.imgCircled);
        txt = findViewById(R.id.Driver);
        desc = findViewById(R.id.DriverDes);
        pred = findViewById(R.id.Prediction);
        //Intent intent = getIntent();
        img.setImageResource(intent.getIntExtra("image",R.drawable.ic_launcher_foreground));
        txt.setText(intent.getStringExtra("name"));
        desc.setText(intent.getStringExtra("description"));

        //int position = intent.getIntExtra("position",0);
        position = intent.getIntExtra("position",0);
        Note noteRead = new DriverPredictionHandler(DriverInfo.this).readSingleNote(position);
        String strPrediction = noteRead.getPrediction();
        pred.setText(noteRead.getPrediction());

    }

    public ArrayList<Note> readNotes(){
        ArrayList<Note> notes = new DriverPredictionHandler(this).readNotes();

        return notes;
    }
    public void loadNotes(){
        desc = findViewById(R.id.DriverDes);
        Intent intent = getIntent();
        notes = readNotes();
        desc.setText(intent.getStringExtra("description") + "\n" +notes.get(0).getPrediction() );
    }
    public void readSingleNote(Integer position){

    }


}
