package edu.handong.android.connect4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class NewGame_Settings extends AppCompatActivity {
    public int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* implementation of localization
        Locale locale = new Locale("fr");
        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        ****************************************/
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_new_game_settings);
        SoundEffect clickSound=new SoundEffect(this);

        ToggleButton multiplayer = findViewById(R.id.multiplayer_toggleButton);
        ToggleButton timer = findViewById(R.id.elapsed_time_toggleButton2);
        TextView txtGuest = findViewById(R.id.txtGuest_name);
        EditText guest = findViewById(R.id.edtGuest);
        Spinner pieces = findViewById(R.id.ballShape_spinner);



        multiplayer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (multiplayer.isChecked()) {
                    txtGuest.setVisibility(View.VISIBLE);
                    guest.setVisibility(View.VISIBLE);
                    timer.setEnabled(false);
                    timer.setChecked(false);
                } else {
                    txtGuest.setVisibility(View.INVISIBLE);
                    guest.setVisibility(View.INVISIBLE);
                    timer.setEnabled(true);
                }

            }
        });

        //Go back to home page
        ImageButton home_Button = findViewById(R.id.newgamehome);
        home_Button.setOnClickListener(view -> {
            clickSound.playSound();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);}
        );
        //Advance to the actual game activity
        ImageButton game_Button = findViewById(R.id.launchgame);
        game_Button.setOnClickListener(view -> {
            clickSound.playSound();
            String name2 = guest.getText().toString();
            String piece = pieces.getSelectedItem().toString();
            Intent intent = new Intent(this, Connect4GameActivity.class);
            intent.putExtra("Player2Name",name2);
            intent.putExtra("ModelPiece",piece);
            if(multiplayer.isChecked())
                intent.putExtra("Mode",true);
            else
                intent.putExtra("Mode",false);
            if(timer.isChecked())
                intent.putExtra("Timer",true);
            else
                intent.putExtra("Timer",false);

            startActivity(intent);}
        );
    }

    @Override
    public void onBackPressed() {
        counter++;
        if(counter==3) {
            Toast.makeText(this, R.string.alert_back, Toast.LENGTH_LONG).show();
            counter=0;
        }
    }
}