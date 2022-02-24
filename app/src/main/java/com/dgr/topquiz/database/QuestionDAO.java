package com.dgr.topquiz.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dgr.topquiz.Constantes;
import com.dgr.topquiz.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionDAO {

    // Ajouter des données
    public boolean insertRow(Question dataRow) {
        // Récupérer une connexion à la BdD
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        // Créer un map des valeurs
        ContentValues values = new ContentValues();
        // values.put(QuestionEntry.TABLE_ID, dataRow.getId());
        values.put(QuestionEntry.TABLE_LIBELLE, dataRow.getQuestion());

        List<String> lChoix = dataRow.getChoix();
        values.put(QuestionEntry.TABLE_PROP1, lChoix.get(0));
        values.put(QuestionEntry.TABLE_PROP2, lChoix.get(1));
        values.put(QuestionEntry.TABLE_PROP3, lChoix.get(2));
        values.put(QuestionEntry.TABLE_PROP4, lChoix.get(3));
        values.put(QuestionEntry.TABLE_INDEX, dataRow.getIndexReponse());

        // Insèrer l’enregistrement et renvoyer la valeur de son ID
        long newRowId = db.insert(QuestionEntry.TABLE_NAME, null, values);

        // Fermer la connexion
        DatabaseManager.getInstance().closeDatabase();

        return newRowId >= 0L;
    }

    // Lire des données
    public List<Question> readQuestions() {

        // Récupérer une connexion à la BdD
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        // Définir une projection pour spécifier les colonnes à lire
        String[] projection = {
                QuestionEntry.TABLE_ID,
                QuestionEntry.TABLE_LIBELLE,
                QuestionEntry.TABLE_PROP1,
                QuestionEntry.TABLE_PROP2,
                QuestionEntry.TABLE_PROP3,
                QuestionEntry.TABLE_PROP4,
                QuestionEntry.TABLE_INDEX,
        };

        // Lancer la requête
        Cursor cursor = db.query(QuestionEntry.TABLE_NAME, // The table to query
                projection, // The array of columns to return (pass null to get all)
                null, // The columns for the WHERE clause
                null, // The values for the WHERE clause
                null, // don't group the rows
                null, // don't filter by row groups
                null // The sort order
        );

        // Lire le résultat
        List<Question> dataRows = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(QuestionEntry.TABLE_ID));
            String libelle = cursor.getString(cursor.getColumnIndexOrThrow(QuestionEntry.TABLE_LIBELLE));
            String prop1 = cursor.getString(cursor.getColumnIndexOrThrow(QuestionEntry.TABLE_PROP1));
            String prop2 = cursor.getString(cursor.getColumnIndexOrThrow(QuestionEntry.TABLE_PROP2));
            String prop3 = cursor.getString(cursor.getColumnIndexOrThrow(QuestionEntry.TABLE_PROP3));
            String prop4 = cursor.getString(cursor.getColumnIndexOrThrow(QuestionEntry.TABLE_PROP4));
            int index = cursor.getInt(cursor.getColumnIndexOrThrow(QuestionEntry.TABLE_INDEX));

            // Ajout sur le DTO
            Question q = new Question(id, libelle, Arrays.asList(prop1, prop2, prop3, prop4), index);
            // Ajout de la liste
            dataRows.add(q);
        }
        cursor.close();

        // Fermer la connexion
        DatabaseManager.getInstance().closeDatabase();

        // Renvoyer le résultat
        return dataRows;
    }
}
