package com.dgr.topquiz;

import java.util.ArrayList;
import java.util.List;

/**
 * Modèle pour les Questions (DTO de la table 'Question')
 */
public class Question {

    private int id;
    private String mQuestion;
    private List<String> mChoiceList;
    private int mAnswerIndex;

    public Question(int id, String question, List<String> choiceList, int answerIndex) {
        mQuestion = question;
        mChoiceList = choiceList;
        mAnswerIndex = answerIndex;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public List<String> getChoix() {
        return mChoiceList;
    }

    public int getIndexReponse() {
        return mAnswerIndex;
    }

    public int getId() {
        return id;
    }

    public void setProposition(String prop1, String prop2, String prop3, String prop4) {
        mChoiceList = new ArrayList<>();
        mChoiceList.add(prop1);
        mChoiceList.add(prop2);
        mChoiceList.add(prop3);
        mChoiceList.add(prop4);
    }
}
