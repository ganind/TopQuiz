package com.dgr.topquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    // Modèles
    private QuestionBank myQuestions;
    private Question mCurrentQuestion;
    private TextView mLibelleQuestion;

    // Widgets/Views
    private Button mButtonChoix1;
    private Button mButtonChoix2;
    private Button mButtonChoix3;
    private Button mButtonChoix4;

    // Attributs
    private int mScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // liaison entre classe java et composant graphique
        mLibelleQuestion = findViewById(R.id.game_activity_textview_question);
        mButtonChoix1 = findViewById(R.id.game_activity_button_1);
        mButtonChoix2 = findViewById(R.id.game_activity_button_2);
        mButtonChoix3 = findViewById(R.id.game_activity_button_3);
        mButtonChoix4 = findViewById(R.id.game_activity_button_4);

        // Ajouter les listeners
        mButtonChoix1.setOnClickListener(this);
        mButtonChoix2.setOnClickListener(this);
        mButtonChoix3.setOnClickListener(this);
        mButtonChoix4.setOnClickListener(this);

        // init les modèles
        myQuestions = initQuestionBank();

        // recupère la question suivante
        hasNextQuestion();
    }

    /**
     * Change le texte des Widgtes avec les infos de la question
     * @param question
     */

    private void displayQuestion(Question question) {
        // Set the text for the question text view and the four buttons
        mLibelleQuestion.setText(question.getQuestion());
        mButtonChoix1.setText(question.getChoix().get(0));
        mButtonChoix2.setText(question.getChoix().get(1));
        mButtonChoix3.setText(question.getChoix().get(2));
        mButtonChoix4.setText(question.getChoix().get(3));
    }

    private QuestionBank initQuestionBank() {
        Question question1 = new Question(
                "Who is the creator of Android?",
                Arrays.asList(
                        "Andy Rubin",
                        "Steve Wozniak",
                        "Jake Wharton",
                        "Paul Smith"),
                0);
        Question question2 = new Question(
                "When did the first man land on the moon?",
                Arrays.asList(
                        "1958",
                        "1962",
                        "1967",
                        "1969"),
                3);
        Question question3 = new Question(
                "What is the house number of The Simpsons?",
                Arrays.asList(
                        "42",
                        "101",
                        "666",
                        "742"),
                3);

        return new QuestionBank(Arrays.asList(question1, question2, question3));
    }

    @Override
    public void onClick(View view) {
        int lChoixIndex = -1;
        if (view == mButtonChoix1)
            lChoixIndex = 0;
        else if (view == mButtonChoix2)
            lChoixIndex = 1;
        else if (view == mButtonChoix3)
            lChoixIndex = 2;
        else
            lChoixIndex = 3;

        if(lChoixIndex == mCurrentQuestion.getIndexReponse()) {
            // Bonne réponse !
            Toast.makeText(this, R.string.toast_message_success, Toast.LENGTH_SHORT).show();
            mScore++;
        } else {
            // Mauvaise réponse !
            Toast.makeText(this, R.string.toast_message_failed, Toast.LENGTH_SHORT).show();
        }

        if (!hasNextQuestion()) {
            // Plus de question
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.dialog_wellDone)
                    .setMessage(getString(R.string.dialog_score, mScore))
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).create().show();
        }
    }

    /**
     * Renvoie true si une question
     * @return
     */
    private boolean hasNextQuestion() {
        mCurrentQuestion = myQuestions.getNextQuestion();
        if (mCurrentQuestion == null)
            return false;

        displayQuestion(mCurrentQuestion);
        return true;
    }
}