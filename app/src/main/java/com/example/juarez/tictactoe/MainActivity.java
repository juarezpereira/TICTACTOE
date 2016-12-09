package com.example.juarez.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.juarez.tictactoe.model.Player;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String PLAYER_1_EXTRAS = "PLAYER1";
    public static final String PLAYER_2_EXTRAS = "PLAYER2";
    private static final int REQUEST_START_GAME = 1;

    @BindView(R.id.tvMatricula1)
    TextView tvMatricula1;
    @BindView(R.id.tvMatricula2)
    TextView tvMatricula2;
    @BindView(R.id.tvNome1)
    TextView tvNome1;
    @BindView(R.id.tvNome2)
    TextView tvNome2;
    @BindView(R.id.tvStatus)
    TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnIniciar)
    public void onClick(View v){
        Player mPlayerOne = new Player();
        mPlayerOne.setId(1);
        mPlayerOne.setNome(tvNome1.getText().toString());
        mPlayerOne.setMatricula(Double.parseDouble(tvMatricula1.getText().toString()));

        Player mPlayerTwo = new Player();
        mPlayerTwo.setId(2);
        mPlayerTwo.setNome(tvNome2.getText().toString());
        mPlayerTwo.setMatricula(Double.parseDouble(tvMatricula2.getText().toString()));

        Intent intent = new Intent(MainActivity.this, GameStartActivity.class);
        intent.putExtra(PLAYER_1_EXTRAS,mPlayerOne);
        intent.putExtra(PLAYER_2_EXTRAS,mPlayerTwo);

        startActivityForResult(intent,REQUEST_START_GAME);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_START_GAME){
            if(resultCode == RESULT_OK){
                String message = data.getExtras().getString(GameStartActivity.MESSAGE_EXTRAS);
                if(message != null && !message.equals("Empatou!")){
                    this.tvStatus.setText(String.format(getString(R.string.activity_main_label_status_wins),message+" venceu!"));
                    return;
                }
                this.tvStatus.setText(String.format(getString(R.string.activity_main_label_status_wins),message));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
