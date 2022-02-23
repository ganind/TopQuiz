package com.dgr.topquiz;

import java.util.Collections;
import java.util.List;

public class QuestionBank {

    private List<Question> mQuestionList;
    private int mNextQuestionIndex = 0;

    public QuestionBank(List<Question> questionList) {
        // Shuffle the question list before storing it
        Collections.shuffle(questionList);
        mQuestionList = questionList;
    }

    /**
     * Renvoie la question suivante, null si plus de question
     * @return question suivante ou null
     */
    public Question getNextQuestion() {
        // Loop over the questions and return a new one at each call
        if(mNextQuestionIndex >= mQuestionList.size())
            return null;

        // compteur de questions
        return mQuestionList.get(mNextQuestionIndex++);
    }

    /**
     * renvoie l'index de la question courante
     * @return index
     */
    public int getNextQuestionIndex() {
        return mNextQuestionIndex;
    }

    public void setNextQuestionIndex(int nextQuestionIndex) {
        mNextQuestionIndex = nextQuestionIndex;
    }
}
