package com.dgr.topquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Button mPlayButton;
    private EditText mNameEditText;
    private User mUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // appel au dossier res, fichier activity_main
        setContentView(R.layout.activity_main);

        // obtenir les widgets dans Activity
        mNameEditText = findViewById(R.id.main_edittext_name);
        mPlayButton = findViewById(R.id.main_button_play);

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
                // The user just clicked
                // génerer un lien/échanger entre les activities pour aller et revenir entre les deux
                Intent game = new Intent(MainActivity.this, GameActivity.class);
                startActivity(game);
            }
        });
    }
}