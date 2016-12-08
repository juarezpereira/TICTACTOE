package com.example.juarez.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.juarez.tictactoe.model.Player;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameStartActivity extends AppCompatActivity {

    private int[][] mCombination = new int[][]{
            {1,2,3},
            {4,5,6},
            {7,8,9},
            {1,4,7},
            {2,5,8},
            {3,6,9},
            {1,5,9},
            {3,5,7}
    };

    @BindViews({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9})
    List<Button> mButtons;
    @BindView(R.id.tvJogador)
    TextView tvJogador;
    @BindView(R.id.labelPlayer1)
    TextView tvPlayer1;
    @BindView(R.id.labelPlayer2)
    TextView tvPlayer2;
    @BindView(R.id.tvStatus)
    TextView tvStatus;

    private List<Player> mPlayers;
    private Player mPlayerCurrent;
    private Player mPlayerOne;
    private Player mPlayerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);
        ButterKnife.bind(this);

        this.mPlayerOne = getIntent().getExtras().getParcelable(MainActivity.PLAYER_1_EXTRAS);
        this.mPlayerTwo = getIntent().getExtras().getParcelable(MainActivity.PLAYER_2_EXTRAS);
        this.mPlayerCurrent = mPlayerOne;

        this.tvJogador.setText(String.format(getResources().getString(R.string.gameActivity_label_player),mPlayerCurrent.getNome()));
        this.tvPlayer1.setText(String.format(getString(R.string.gameActivity_label_player_one),mPlayerOne.getNome()));
        this.tvPlayer2.setText(String.format(getString(R.string.gameActivity_label_player_two),mPlayerTwo.getNome()));
        this.tvStatus.setText(String.format(getString(R.string.gameActivity_label_status), "Jogo em andamento"));
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9})
    public void onClick(View v){
        Button button = ((Button)v);

        if(mPlayerCurrent.getId() == 1){
            button.setText("X");
            mPlayerCurrent = mPlayerTwo;
            tvJogador.setText(String.format(getResources().getString(R.string.gameActivity_label_player),mPlayerCurrent.getNome()));
        }else{
            button.setText("O");
            mPlayerCurrent = mPlayerOne;
            tvJogador.setText(String.format(getResources().getString(R.string.gameActivity_label_player),mPlayerCurrent.getNome()));
        }

        button.setEnabled(false);
    }

}
