package com.dgr.topquiz.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dgr.topquiz.Constantes;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Requête SQL de création de la table
    private static final String CREATE_TABLE_QUESTION =
            "CREATE TABLE IF NOT EXISTS " + QuestionEntry.TABLE_NAME + " ("
                    + QuestionEntry.TABLE_ID + " INTEGER PRIMARY KEY, "
                    + QuestionEntry.TABLE_LIBELLE + " TEXT NOT NULL, "
                    + QuestionEntry.TABLE_PROP1 + " TEXT NOT NULL, "
                    + QuestionEntry.TABLE_PROP2 + " TEXT NOT NULL, "
                    + QuestionEntry.TABLE_PROP3 + " TEXT NOT NULL, "
                    + QuestionEntry.TABLE_PROP4 + " TEXT NOT NULL, "
                    + QuestionEntry.TABLE_INDEX + "INTEGER NOT NULL)";

    // Requête SQL de drop de la table
    private static final String DROP_TABLE_QUESTION =
            "DROP TABLE IF EXISTS " + QuestionEntry.TABLE_NAME;

    DatabaseHelper(Context context) {
        super(context, Constantes.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // db.execSQL(DROP_TABLE_QUESTION);
        db.execSQL(CREATE_TABLE_QUESTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}
}
