package com.example.myapplicationmadpastpaer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplicationmadpastpaer.database.DBHandler;

import java.util.List;

public class AddGame extends AppCompatActivity {


    EditText gamename,gameyear;
    Button add;
    ArrayAdapter list;
    Spinner spinner;
    Adapter adapter;
    CheckBox checkBox1,checkBox2;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);


        gamename = findViewById(R.id.gamename);
        gameyear=findViewById(R.id.gameyear);
        add=findViewById(R.id.addgame);




        Spinner spinner = (Spinner) findViewById(R.id.spinner14);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner5);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);




        DBHandler db = new DBHandler(getApplicationContext());
        List  list = db.loginlist();

        ArrayAdapter<List> game = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);

        game.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the
        spinner1.setAdapter(game);




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DBHandler db =new DBHandler(getApplicationContext());

                boolean add = db.addgame(gamename.getText().toString(),gameyear.getText().toString());

                if(add){

                    Toast.makeText(AddGame.this, "add"+add, Toast.LENGTH_SHORT).show();
                }

                else {

                    Toast.makeText(AddGame.this, "not add", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}