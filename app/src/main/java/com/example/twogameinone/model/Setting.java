package com.example.twogameinone.model;

import java.io.Serializable;

public class Setting implements Serializable {
    private int mRow = 7;
    private int mColumn = 6;
    private String mGameName = "";
    private ColorBackground mColorBackground = ColorBackground.White;
    private static String sStringNameGameTicTacToe ="TicTacToe";
    private static String sStringNameGameFourInRow = "ForInRow";

    public Setting(String mGameName) {
        this.mGameName = mGameName;
        if (this.mGameName.equals(sStringNameGameTicTacToe)){
            mRow =3;
            mColumn=3;
        }
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
