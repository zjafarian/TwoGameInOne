package com.example.twogameinone.model;

import java.io.Serializable;

public class Setting implements Serializable {
    private int mRow=5;
    private int mColumn=5;
    private String mGameName = sStringNameGameTicTacToe;
    private ColorBackground mColorBackground = ColorBackground.White;
    private static String sStringNameGameTicTacToe ="TicTacToe";
    private static String sStringNameGameFourInRow = "ForInRow";
    private String sexUser="girl";

    public Setting(String mGameName) {
        this.mGameName = mGameName;
        if (this.mGameName.equals(sStringNameGameTicTacToe)){
            mRow =3;
            mColumn=3;
        }
    }

    public String getSexUser() {
        return sexUser;
    }

    public void setSexUser(String sexUser) {
        this.sexUser = sexUser;
    }

    public int getRow() {
        return mRow;
    }

    public void setRow(int row) {
        mRow = row;
    }

    public int getColumn() {
        return mColumn;
    }

    public void setColumn(int column) {
        mColumn = column;
    }

    public String getGameName() {
        return mGameName;
    }

    public void setGameName(String gameName) {
        mGameName = gameName;
    }

    public ColorBackground getColorBackground() {
        return mColorBackground;
    }

    public void setColorBackground(ColorBackground colorBackground) {
        mColorBackground = colorBackground;
    }
}
