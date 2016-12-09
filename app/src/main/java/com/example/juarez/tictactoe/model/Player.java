package com.example.juarez.tictactoe.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Juarez on 08/12/2016.
 */

public class Player implements Parcelable {

    private int id;
    private String nome;
    private double matricula;

    public Player() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getMatricula() {
        return matricula;
    }

    public void setMatricula(double matricula) {
        this.matricula = matricula;
    }

    public boolean isPlayer(int id){
        return this.id == id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nome);
        dest.writeDouble(this.matricula);
    }

    private Player(Parcel in) {
        this.id = in.readInt();
        this.nome = in.readString();
        this.matricula = in.readDouble();
    }

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel source) {
            return new Player(source);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

}
