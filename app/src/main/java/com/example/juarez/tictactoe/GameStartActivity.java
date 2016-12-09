package com.example.juarez.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.juarez.tictactoe.model.Player;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameStartActivity extends AppCompatActivity {

    public static final String MESSAGE_EXTRAS = "MESSAGE";

    private int[][] mFinalStates = new int[][]{
            {1,2,3},
            {4,5,6},
            {7,8,9},
            {1,4,7},
            {2,5,8},
            {3,6,9},
            {1,5,9},
            {3,5,7}};

    @BindViews({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9})
    Button[] mButtons;
    @BindView(R.id.tvJogador)
    TextView tvJogador;
    @BindView(R.id.labelPlayer1)
    TextView tvPlayer1;
    @BindView(R.id.labelPlayer2)
    TextView tvPlayer2;
    @BindView(R.id.tvStatus)
    TextView tvStatus;

    private Player mPlayerCurrent;
    private Player mPlayerOne;
    private Player mPlayerTwo;
    private Player mPlayerWins;

    private int mTotalPlays = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);
        ButterKnife.bind(this);

        this.mPlayerWins = new Player();
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

        mTotalPlays++;
        if(mPlayerCurrent.isPlayer(mPlayerOne.getId())){
            button.setText("X");
            isEnd();
        }else{
            button.setText("O");
            isEnd();
        }
        button.setEnabled(false);
        tvJogador.setText(String.format(getResources().getString(R.string.gameActivity_label_player),mPlayerCurrent.getNome()));

    }

    public boolean isEnd(){
        for (int index = 0; index <= 7; index++){
            String valor1 = mButtons[mFinalStates[index][0]-1].getText().toString();
            String valor2 = mButtons[mFinalStates[index][1]-1].getText().toString();
            String valor3 = mButtons[mFinalStates[index][2]-1].getText().toString();

            if((!valor1.equals("")) && (!valor2.equals("")) && (!valor3.equals(""))) {
                if( (valor1.equals(valor2)) && (valor2.equals(valor3)) ){
                    ButterKnife.apply(mButtons, BUTTONS_SETTER_ENABLE, false);

                    mPlayerWins = mPlayerCurrent;
                    tvStatus.setText(String.format(getString(R.string.gameActivity_label_status), mPlayerWins.getNome()+" venceu!"));
                    return true;
                }
            }
        }

        if(mTotalPlays == 9){
            mPlayerWins.setNome("Empatou!");
            tvStatus.setText(String.format(getString(R.string.gameActivity_label_status), mPlayerWins.getNome()));
            return true;
        }

        changePlayer();
        return false;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("Deseja sair?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(MESSAGE_EXTRAS, mPlayerWins.getNome());
                        setResult(RESULT_OK, intent);

                        GameStartActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("Não", null)
                .setNeutralButton("Reiniciar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onRestartGame();
                    }
                }).create().show();
    }

    static final ButterKnife.Setter<Button, Boolean> BUTTONS_SETTER_ENABLE = new ButterKnife.Setter<Button, Boolean>() {
        @Override
        public void set(@NonNull Button view, Boolean value, int index) {
            view.setEnabled(value);
        }
    };

    static final ButterKnife.Action<Button> BUTTONS_CLEAR = new ButterKnife.Action<Button>() {
        @Override
        public void apply(@NonNull Button view, int index) {
            view.setText("");
        }
    };

    public void changePlayer(){
        if(mPlayerCurrent.isPlayer(mPlayerOne.getId())){
            this.mPlayerCurrent = mPlayerTwo;
        }else{
            this.mPlayerCurrent = mPlayerOne;
        }
    }

    public void onRestartGame(){
        ButterKnife.apply(mButtons,BUTTONS_CLEAR);
        ButterKnife.apply(mButtons,BUTTONS_SETTER_ENABLE,true);
        tvStatus.setText(String.format(getString(R.string.gameActivity_label_status), "Jogo em andamento"));
    }

}
