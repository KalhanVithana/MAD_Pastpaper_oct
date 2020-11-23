package com.example.myapplicationmadpastpaer.database;


import android.provider.BaseColumns;

public final class DatabaseMaster {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DatabaseMaster() {
    }

    /* Inner class that defines the table contents */
    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "Users";
        public static final String COLUMN_1 = "username";
        public static final String COLUMN_2 = "password";
        public static final String COLUMN_3 = "usertype";
    }


    /* Inner class that defines the table contents */
    public static class game implements BaseColumns {
        public static final String TABLE_NAME = "game";
        public static final String COLUMN_1 = "gamename";
        public static final String COLUMN_2 = "gameyear";
        public static final String COLUMN_3 = "check";

    }


    public static class comments implements BaseColumns {
        public static final String TABLE_NAME = "comments";
        public static final String COLUMN_1 = "gamename";
        public static final String COLUMN_2 = "gamerating";
        public static final String COLUMN_3 = "gamecomments";
    }
}