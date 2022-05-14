package com.abhi.f1infoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements DriverAdapter.MyClickInterface{
    RecyclerView recyclerView;
    ArrayList<Driver> Drivers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recycler_view);
        DriverPredictionHandler driverPredHandler = new DriverPredictionHandler(this);


        ArrayList<Note> notes = driverPredHandler.readNotes();
        //For the first time only setup this up(delete and create). This process is called init.
        if(notes.size() != 21)
        {
            for (int i = 0; i < notes.size(); i++) {

                driverPredHandler.delete(i);
            }

            for (int i = 0; i < 21; i++) {
                Note note = new Note(i, "0");
                driverPredHandler.create(note);
            }
        }

        Drivers = new ArrayList<>();


        Drivers.add(new Driver(0,"Max Verstappen",R.drawable.ver, "F1 World Champion 2021",Integer.parseInt(notes.get(0).getPrediction())));
        Drivers.add(new Driver(1,"Sergio Perez",R.drawable.per, "First mexican to be on pole in a F1 race",Integer.parseInt(notes.get(1).getPrediction())));
        Drivers.add(new Driver(2,"Charles Leclerc",R.drawable.lec, "2022 Championship leader", Integer.parseInt(notes.get(2).getPrediction())));
        Drivers.add(new Driver(3,"Carlos Sainz",R.drawable.sai, "2 podiums in 2022", Integer.parseInt(notes.get(3).getPrediction())));
        Drivers.add(new Driver(4,"Lewis Hamilton",R.drawable.ham,"7 time world champion", Integer.parseInt(notes.get(4).getPrediction())));
        Drivers.add(new Driver(5,"George Russell",R.drawable.rus, "Achieved a podium at Belgium in a Williams", Integer.parseInt(notes.get(5).getPrediction())));
        Drivers.add(new Driver(6,"Valtteri Bottas",R.drawable.bot, "9 time race winner", Integer.parseInt(notes.get(6).getPrediction())));
        Drivers.add(new Driver(7,"Zhou Guanyu",R.drawable.zho, "Achieved points on his F1 debut", Integer.parseInt(notes.get(7).getPrediction())));
        Drivers.add(new Driver(8,"Lando Norris",R.drawable.nor, "Achieved pole during the 2021 Russian GP", Integer.parseInt(notes.get(8).getPrediction())));
        Drivers.add(new Driver(9,"Daniel Ricciardo",R.drawable.ric, "8 time race winner", Integer.parseInt(notes.get(9).getPrediction())));
        Drivers.add(new Driver(10,"Esteban Ocon ",R.drawable.oco, "Won the 2021 Hungarian GP", Integer.parseInt(notes.get(10).getPrediction())));
        Drivers.add(new Driver(11,"Fernando Alonso",R.drawable.alo, "2 time world champion", Integer.parseInt(notes.get(11).getPrediction())));
        Drivers.add(new Driver(12,"Kevin Magnussen",R.drawable.mag, "Scored Haas it's first points since 2020", Integer.parseInt(notes.get(12).getPrediction())));
        Drivers.add(new Driver(13,"Mick Schumacher",R.drawable.sch, "Achieved 11th place uring the 2022 Bahrain GP", Integer.parseInt(notes.get(13).getPrediction())));
        Drivers.add(new Driver(14,"Pierre Gasly",R.drawable.gas,"Won the 2020 Italian GP", Integer.parseInt(notes.get(14).getPrediction())));
        Drivers.add(new Driver(15,"Yuki Tsunoda",R.drawable.tsu, "Achieved points on his debut during the 2021 Bahrain GP", Integer.parseInt(notes.get(15).getPrediction())));
        Drivers.add(new Driver(16,"Lance Stroll",R.drawable.str, "Youngest rookie to be on a F1 podium", Integer.parseInt(notes.get(16).getPrediction())));
        Drivers.add(new Driver(17,"Sebastian Vettel",R.drawable.vet, "4 time world champion", Integer.parseInt(notes.get(17).getPrediction())));
        Drivers.add(new Driver(18,"Alex Albon",R.drawable.alb, "2 podiums as a red bull driver", Integer.parseInt(notes.get(18).getPrediction())));
        Drivers.add(new Driver(19,"Nicholas Latifi",R.drawable.lat, "Achieved points in the 2021 season", Integer.parseInt(notes.get(19).getPrediction())));

        Collections.sort(Drivers, new Comparator<Driver>() {
            @Override
            public int compare(Driver lhs, Driver rhs) {
                Integer pred1 = lhs.getPrediction();
                Integer pred2 = rhs.getPrediction();
                return pred1.compareTo(pred2);

            }
        });


        DriverAdapter DriverAdapter = new DriverAdapter(Drivers,notes,this, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(DriverAdapter);
    }

    @Override
    public void onItemClick(int positionOfTheDriver) {
        Toast.makeText(this, "Clicked "+Drivers.get(positionOfTheDriver).getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,DriverInfo.class);
        intent.putExtra("image",Drivers.get(positionOfTheDriver).getImage());
        intent.putExtra("name",Drivers.get(positionOfTheDriver).getName());
        intent.putExtra("description",Drivers.get(positionOfTheDriver).getDescription());
        intent.putExtra("position",positionOfTheDriver);
        intent.putExtra("id",Drivers.get(positionOfTheDriver).getId());
        startActivity(intent);
    }
    @Override
    public void onRestart(){
        super.onRestart();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }



}