package com.example.lenovo.clothesorganizer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "myDB";
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_TABLE = "myThing";

        // поля таблицы
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_MINTEMPR = "minTempr";
        public static final String COLUMN_MAXTEMPR = "maxTempr";
        public static final String COLUMN_COMMENT = "comment";
        public static final String COLUMN_PATH = "pathToImage";

        // запрос на создание базы данных
        private static final String DATABASE_CREATE = "create table "
                + DATABASE_TABLE + "(" + COLUMN_ID
                + " integer primary key autoincrement, " + COLUMN_NAME
                + " text not null, " + COLUMN_TYPE
                + " text not null, "
                + COLUMN_MINTEMPR + " text not null, " + COLUMN_MAXTEMPR + " text not null, "
                + COLUMN_COMMENT + " text not null, " + COLUMN_PATH + " text not null" +");";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(DBHelper.class.getName(), "Upgrading database from version "
                    + oldVersion + " to " + newVersion
                    + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS myThing");
            onCreate(db);
        }

}