package com.example.twogameinone.model;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    private String mSexUser;
    private char mSymbolUser;
    private char[][] mSituation;
    private int length;
    private String mStringGameName;


    public User(String sexUser, char symbolUser, char[][] situation, int length, String stringGameName) {
        mSexUser = sexUser;
        mSymbolUser = symbolUser;
        mSituation = situation;
        this.length = length;
        mStringGameName = stringGameName;
    }

    public String getStringGameName() {
        return mStringGameName;
    }

    public void setStringGameName(String stringGameName) {
        mStringGameName = stringGameName;
    }

    public String getSexUser() {
        return mSexUser;
    }

    public void setSexUser(String sexUser) {
        mSexUser = sexUser;
    }

    public char getSymbolUser() {
        return mSymbolUser;
    }

    public void setSymbolUser(char symbolUser) {
        mSymbolUser = symbolUser;
    }

    public char[][] getSituation() {
        return mSituation;
    }

    public void setSituation(char[][] situation) {
        mSituation = situation;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean reviewWinner(char[][] situation, char symbolUserTwo, int row, int column) {
        this.mSituation = situation;

        if (checkRow(symbolUserTwo))
            return true;
        else if (checkColumn(symbolUserTwo))
            return true;
        else if (checkDiagonalOne(symbolUserTwo))
            return true;
        else if (checkDiagonalTwo(symbolUserTwo))
            return true;


        return false;
    }

    private boolean checkRow(char symbolUserTwo) {
        boolean check = false;
        int j = 0;
        for (int i = 0; i < mSituation.length; i++) {
            while (j < mSituation.length - 1) {
                if (mSituation[i][0] == mSymbolUser || mSituation[i][0] == symbolUserTwo) {
                    if (mSituation[i][0] == mSituation[i][++j]) {
                        check = true;
                    } else check = false;
                } else break;
            }
            if (check) {
                return check;
            }
            j = 0;
        }
        return false;
    }

    private boolean checkColumn(char symbolUserTwo) {
        boolean check = false;
        int j = 0;
        for (int i = 0; i < mSituation.length; i++) {
            while (j < mSituation.length - 1) {
                if (mSituation[0][i] == mSymbolUser || mSituation[0][i] == symbolUserTwo) {
                    if (mSituation[0][i] == mSituation[++j][i]) {
                        check = true;
                    } else check = false;
                } else break;

            }
            if (check) {
                return check;
            }
            j = 0;
        }
        return false;
    }

    private boolean checkDiagonalOne(char symbolUserTwo) {
        boolean check = false;
        int i = 0;
        while (i < mSituation.length - 1) {
            if (mSituation[0][0] == mSymbolUser || mSituation[0][0] == symbolUserTwo) {
                if (mSituation[0][0] == mSituation[++i][i]) {
                    check = true;
                } else check = false;
            } else break;
        }
        if (check) {
            return check;
        }
        return false;
    }

    private boolean checkDiagonalTwo(char symbolUserTwo) {
        boolean check = false;
        int j = mSituation.length - 1;
        int i = 0;
        while (j >= 0) {
            if (mSituation[0][j] == mSymbolUser || mSituation[0][j] == symbolUserTwo) {
                if (mSituation[0][j] == mSituation[++i][--j]) {
                    check = true;
                } else check = false;
            }else break;

        }
        if (check) {
            return check;
        }
        return false;
    }

}
