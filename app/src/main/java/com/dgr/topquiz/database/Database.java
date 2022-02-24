package com.dgr.topquiz.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dgr.topquiz.Question;
import com.dgr.topquiz.QuestionBank;

import java.util.List;

public class Database {

    public Database(@NonNull Context context) {
        DatabaseManager.initializeInstance(new DatabaseHelper(context));
    }

    /**
     * Enregistre dans la table Question, la liste des questions dans la QuestionBank
     * @param dataRow
     * @return
     */
    public boolean insertQuestionBank(QuestionBank dataRow) {
        QuestionDAO dao = new QuestionDAO();
        for (Question q : dataRow.getQuestionList())
            dao.insertRow(q);

        Log.d("********TOPQUIZ********", "La Bank a été sauvegardé");
        return true;
    }

    /**
     * Lit les questions de la table Question et renvoie cette liste dans une QUestionBank
     * @return
     */
    public QuestionBank getQuestionBank() {
        QuestionDAO dao = new QuestionDAO();
        List<Question>lQuestions = dao.readQuestions();
        return new QuestionBank(lQuestions);
    }
}
