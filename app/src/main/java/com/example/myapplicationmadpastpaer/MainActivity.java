package com.example.myapplicationmadpastpaer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplicationmadpastpaer.database.DBHandler;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String CHANNEL_ID ="hello" ;
    EditText name,pasword;
    Button login,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        name = findViewById(R.id.name1);
        pasword=findViewById(R.id.pasword1);
        register=findViewById(R.id.register);
        login=findViewById(R.id.login1);




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
// Register the channel with the system; you can't change

// or other notification behaviors after this
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Notifactionmessage = name.getText().toString();
                name.setText(Notifactionmessage);


                String notication= "hello" + Notifactionmessage +"welcome";

                // Create an explicit intent for an Activity in your app
                Intent intent = new Intent(getApplicationContext(), Notification.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
                NotificationCompat.Builder builder = new
                        NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("My notification")

                        .setContentText(notication)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
// Set the intent that will fire when the user

                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
// notificationId is a unique int for each notification that you

                notificationManager.notify(0, builder.build());
                DBHandler db= new DBHandler(getApplicationContext());

                long register = db.registerUSer(name.getText().toString(),pasword.getText().toString());


                    Toast.makeText(MainActivity.this, "registerd sucess"+register, Toast.LENGTH_SHORT).show();

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DBHandler db= new DBHandler(getApplicationContext());

                List name1 = db.login("admin");
                List pass1 = db.login("pass");

                String user = name.getText().toString();
                String pass = pasword.getText().toString();

                if(name1.indexOf(user)>=0){

                    if(pass1.get(name1.indexOf(user)).equals(pass)){

                        Toast.makeText(MainActivity.this, "logedsucess", Toast.LENGTH_SHORT).show();


                        String A ="a";
                        String B ="Name";
                        if(user.equals(A) && pass.equals(B) ){
                            Intent i = new Intent(getApplicationContext(),AddGame.class);
                            startActivity(i);
                        }
                        else{

                            Intent i = new Intent(getApplicationContext(),GameList.class);
                            startActivity(i);
                        }







                    }
                    else {

                        Toast.makeText(MainActivity.this, "loginfail", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }


}