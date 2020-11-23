package com.example.myapplicationmadpastpaer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplicationmadpastpaer.database.DBHandler;

import java.util.ArrayList;
import java.util.List;

public class GameList extends AppCompatActivity {


    ArrayList gamelist;
    ListView game;
    ArrayAdapter adpter;

    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);


        game = findViewById(R.id.gamelit);
    db= new DBHandler(getApplicationContext());
        gamelist = db.viewgame();

        adpter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,gamelist);

        game.setAdapter(adpter);

        game.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String text = game.getItemAtPosition(i).toString();
                Toast.makeText(GameList.this, "Uder"+text, Toast.LENGTH_SHORT).show();
            }
        });

    }
}