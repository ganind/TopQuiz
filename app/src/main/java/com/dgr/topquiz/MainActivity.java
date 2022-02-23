package com.dgr.topquiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mPlayButton;
    private EditText mNameEditText;
    private TextView mWelcomeText;
    private TextView mScoreText;
    private User mUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // appel au dossier res, fichier activity_main
        setContentView(R.layout.activity_main);

        // obtenir les widgets dans Activity
        mNameEditText = findViewById(R.id.main_edittext_name);
        mPlayButton = findViewById(R.id.main_button_play);
        mWelcomeText = findViewById(R.id.main_textview_greeting);
        mScoreText = findViewById(R.id.main_lastScore_text);

        // empeche l'utilisation du buton si aucun texte a été saisi
        mPlayButton.setEnabled(false);

        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                // This is where we'll check the user input
                // si pas d'input, le buton est disabled
                mPlayButton.setEnabled(!s.toString().isEmpty());
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // récuperation du texte saisi
                mUser.setmFirstName(mNameEditText.getText().toString());
                // Stockage de la saisie dans le fichier SharedPreferences(Android)
                getSharedPreferences(Constantes.SHARED_PREF_USER_INFO, MODE_PRIVATE)
                        .edit()
                        .putString(Constantes.SHARED_PREF_USER_INFO_NAME, mUser.getmFirstName())
                        .apply();
                // The user just clicked
                // génerer un lien/échange entre les activities pour aller et revenir entre les deux
                Intent game = new Intent(MainActivity.this, GameActivity.class);
                // enregistrement / appel dans l'activité
                startActivityForResult(game, Constantes.GAME_ACTIVITY_REQUEST_CODE);
            }
        });

        // récuperer les SharedPreferences. Si aucune préference valeur par défaut est null
        SharedPreferences lPreferences = getSharedPreferences(Constantes.SHARED_PREF_USER_INFO, MODE_PRIVATE);
        String lFirstName = lPreferences.getString(Constantes.SHARED_PREF_USER_INFO_NAME, null);

        if (lFirstName != null) {
            // Affiche infos user existant
            int lScore = lPreferences.getInt(Constantes.SHARED_PREF_USER_LAST_SCORE, 0);
            mUser.setmFirstName(lFirstName);
            mWelcomeText.setText(getString(R.string.welcome_back, lFirstName));
            mNameEditText.setText(mUser.getmFirstName());
            mNameEditText.setSelection(mUser.getmFirstName().length());
            mScoreText.setText(getString(R.string.last_score, lScore));
        }
        Log.d("********TOPQUIZ********", "onCreate");
    }

    // récuperation du résultat (ok/failed/autre) de l'activité
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constantes.GAME_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                int mScore = data.getIntExtra(Constantes.BUNDLE_EXTRA_SCORE, 0);
                Toast.makeText(this, "Score = " + mScore, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("********TOPQUIZ********", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("********TOPQUIZ********", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("********TOPQUIZ********", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("********TOPQUIZ********", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("********TOPQUIZ********", "onDestroy");
    }
}