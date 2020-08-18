package com.example.twogameinone.model;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    private String mUserName;
    private int mUserId;
    private String mSexUser;
    private String mColorUser;
    private int mScoreUser;
    private char mSymbolUser;
    private char[][] mSituation;
    private int length;

    public User(int length, int mUserId) {
        this.length = length;
        this.mUserId = mUserId;
        mSituation = new char[length][length];
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public int getmUserId() {
        return mUserId;
    }

    public String getmSexUser() {
        return mSexUser;
    }

    public void setmSexUser(String mSexUser) {
        this.mSexUser = mSexUser;
    }

    public String getmColorUser() {
        return mColorUser;
    }

    public void setmColorUser(String mColorUser) {
        this.mColorUser = mColorUser;
    }

    public int getmScoreUser() {
        return mScoreUser;
    }

    public void setmScoreUser(int mScoreUser) {
        this.mScoreUser = mScoreUser;
    }

    public char getmSymbolUser() {
        return mSymbolUser;
    }

    public void setmSymbolUser(char mSymbolUser) {
        this.mSymbolUser = mSymbolUser;
    }

    public char[][] getmSituation() {
        return mSituation;
    }

    public void setmSituation(char[][] mSituation) {
        this.mSituation = mSituation;
    }

    public char[][] selectCell(char[][] mSituation, int row, int column, int mUserId) {
        this.mSituation = mSituation;
        if (mUserId == 0) {
            this.mSituation[row][column]='*';
        } else if (mUserId==1){
            this.mSituation[row][column]='o';
        }
        return mSituation;
    }


}
