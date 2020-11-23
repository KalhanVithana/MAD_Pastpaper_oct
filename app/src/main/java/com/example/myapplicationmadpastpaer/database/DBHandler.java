package com.example.myapplicationmadpastpaer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DBHandler extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "FeedReader.db";

        public DBHandler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
            db.execSQL(SQL_CREATE_ENTRIES1);
            db.execSQL(SQL_CREATE_ENTRIES2);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }


        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + DatabaseMaster.Users.TABLE_NAME + " (" +
                        DatabaseMaster.Users._ID + " INTEGER PRIMARY KEY," +
                        DatabaseMaster.Users.COLUMN_1 + " TEXT," +
                        DatabaseMaster.Users.COLUMN_2 + " TEXT," +
                        DatabaseMaster.Users.COLUMN_3 + " TEXT)";




        private static final String SQL_CREATE_ENTRIES1 =
                "CREATE TABLE " + DatabaseMaster.game.TABLE_NAME + " (" +
                        DatabaseMaster.game._ID + " INTEGER PRIMARY KEY," +
                        DatabaseMaster.game.COLUMN_1 + " TEXT," +
                        DatabaseMaster.game.COLUMN_2 + " TEXT)";


        private static final String SQL_CREATE_ENTRIES2 =
                "CREATE TABLE " + DatabaseMaster.comments.TABLE_NAME + " (" +
                        DatabaseMaster.comments._ID + " INTEGER PRIMARY KEY," +
                        DatabaseMaster.comments.COLUMN_1 + " TEXT," +
                        DatabaseMaster.comments.COLUMN_2 + " TEXT," +
                        DatabaseMaster.comments.COLUMN_3 + " TEXT)";




        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + DatabaseMaster.Users.TABLE_NAME;





        public long registerUSer(String name,String password){


            // Gets the data repository in write mode
            SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(DatabaseMaster.Users.COLUMN_1, name);
            values.put(DatabaseMaster.Users.COLUMN_2, password);

// Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(DatabaseMaster.Users.TABLE_NAME, null, values);


            return  newRowId;
        }



        public List login(String req){


            SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
            String[] projection = {
                    BaseColumns._ID,
                    DatabaseMaster.Users.COLUMN_1,
                    DatabaseMaster.Users.COLUMN_2
            };

// Filter results WHERE "title" = 'My Title'


// How you want the results sorted in the resulting Cursor
            String sortOrder =
                    DatabaseMaster.Users.COLUMN_1 + " ASC";

            Cursor cursor = db.query(
                    DatabaseMaster.Users.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    null,              // The columns for the WHERE clause
                    null,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );



            List validuser = new ArrayList<>();
            List validuse2 = new ArrayList<>();
            while(cursor.moveToNext()) {
                 String user = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_1));
                 String pass = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_2));
                        validuser.add(user);
                validuse2.add(pass);
            }
            cursor.close();

            //Log.i("","readall"+validuser);

            if(req == "admin"){

                return validuser;
            }
            else if( req == "pass"){

                return validuse2;
            }
            else {

                return  null;
            }


        }


        public  boolean addgame(String gamename,String gameyear){

            SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(DatabaseMaster.game.COLUMN_1, gamename);
            values.put(DatabaseMaster.game.COLUMN_2, gameyear);

// Insert the new row, returning the primary key value of the new row
            long id=  db.insert(DatabaseMaster.game.TABLE_NAME, null, values);


          if(id > 0){

              return  true;


          }
          else {

              return false;
          }

        }






    public ArrayList viewgame(){


        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                DatabaseMaster.game.COLUMN_1,
                DatabaseMaster.game.COLUMN_2
        };

// Filter results WHERE "title" = 'My Title'


// How you want the results sorted in the resulting Cursor
        String sortOrder =
                DatabaseMaster.game.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                DatabaseMaster.game.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );



        ArrayList validuser = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.game.COLUMN_1));
            String pass = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.game.COLUMN_2));
            validuser.add(user);
            validuser.add(pass);
        }
        cursor.close();

        //Log.i("","readall"+validuser);

            return  validuser;


    }


    public List loginlist(){





        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                DatabaseMaster.Users.COLUMN_1,


        };

// Filter results WHERE "title" = 'My Title'
        String selection = DatabaseMaster.Users.COLUMN_1 + " = ?";
        String[] selectionArgs = { "My Title" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                DatabaseMaster.Users.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                DatabaseMaster.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );



        List userlist = new ArrayList<>();
        List userlist2 = new ArrayList<>();
        while(cursor.moveToNext()) {
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseMaster.Users._ID));

            String itemId2 = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_1));


            userlist.add(itemId);
            userlist2.add(itemId2);
        }
        cursor.close();


        return userlist2;
    }

}

