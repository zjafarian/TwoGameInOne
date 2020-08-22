package com.example.twogameinone.model;

import com.example.twogameinone.controller.FourInARowFragment;
import com.example.twogameinone.controller.TicTacToeFragment;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    private String mSexUser;
    private char mSymbolUser;
    private char[][] mSituation;
    private int mIntRow;
    private int mIntColumn;
    private String mStringGameName;
    private int score = 0;

    public User(String sexUser, char symbolUser, char[][] situation, int row, int column, String stringGameName) {
        mSexUser = sexUser;
        mSymbolUser = symbolUser;
        this.mIntRow = row;
        this.mIntColumn = column;
        mSituation = new char[row][column];
        mSituation = situation;
        mStringGameName = stringGameName;
    }

    public int getIntRow() {
        return mIntRow;
    }

    public void setIntRow(int intRow) {
        mIntRow = intRow;
    }

    public int getIntColumn() {
        return mIntColumn;
    }

    public void setIntColumn(int intColumn) {
        mIntColumn = intColumn;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public boolean reviewWinner(String gameName, char[][] situation, char symbolUser) {
        this.mSituation = situation;
        if (gameName.equals(TicTacToeFragment.sGameNameTicTacToe)) {
            if (checkRow(symbolUser))
                return true;
            else if (checkColumn(symbolUser))
                return true;
            else if (checkDiagonalOne(symbolUser))
                return true;
            else if (checkDiagonalTwo(symbolUser))
                return true;
        } else if (gameName.equals(FourInARowFragment.sGameNameFourInRow)) {
            if (checkRowFour(symbolUser))
                return true;
            else if (checkColumnFour(symbolUser))
                return true;
            else if (checkDiagonalOneFour(symbolUser))
                return true;
            else if (checkDiagonalTwoFour(symbolUser))
                return true;
        }

        return false;
    }

    private boolean checkRow(char symbolUser) {
        boolean check = false;
        int counter = 0;
        for (int i = 0; i < mIntRow; i++) {
            for (int j = 0; j < mIntColumn; j++) {
                if (mSituation[i][j] == symbolUser)
                    counter++;
            }
            if (counter == mIntRow) {
                check = true;
                return check;
            } else counter = 0;
        }

        return check;
    }

    private boolean checkRowFour(char symbolUser) {
        boolean check = false;
        int counter = 0;
        int j = 0;
        int count = 0;
        for (int i = mIntRow - 1; i >= 0; i--) {
            while (j < mIntColumn - 1) {
                for (int k = j; k < j + 4; k++) {
                    if (mSituation[i][k] == symbolUser) {
                        counter++;
                    }
                }
                if (counter == 4) {
                    check = true;
                    return check;
                } else counter = 0;
                j++;
            }
        }
        return check;
    }

    private boolean checkColumn(char symbolUser) {
        boolean check = false;
        int counter = 0;

        for (int i = 0; i < mIntColumn; i++) {
            for (int j = 0; j < mIntRow; j++) {
                if (mSituation[j][i] == symbolUser)
                    counter++;
            }
            if (counter == mIntRow) {
                check = true;
                return check;
            } else counter = 0;
        }
        return check;
    }

    private boolean checkColumnFour(char symbolUser) {
        boolean check = false;
        int counter = 0;
        int j = mIntRow - 1;
        for (int i = 0; i < mIntColumn; i++) {
            while (j >= 0) {
                for (int k = j; k > j - 4; k--) {
                    if (mSituation[k][i] == symbolUser) {
                        counter++;
                    }
                }
                if (counter == 4) {
                    check = true;
                    return check;
                } else counter = 0;
                j--;
            }
        }
        return check;
    }

    private boolean checkDiagonalOne(char symbolUser) {
        boolean check = false;
        int counter = 0;
        int i = 0;
        while (i < mIntRow - 1) {
            if (mSituation[i][i] == symbolUser) {
                counter++;
            }
            i++;
        }
        if (counter == mIntRow)
            check = true;
        return check;
    }

    private boolean checkDiagonalOneFour(char symbolUser) {
        boolean check = false;
        int counter = 0;
        int index = 1;
        int range = 0;
        int i = mIntRow - 1;
        int j = mIntColumn - 1;
        int lengthRow = i - 4;
        int lengthColumn = j - 4;
        while (i >= lengthRow && j >= lengthColumn) {
            if (range < 4) {
                if (mSituation[i][j] == symbolUser) {
                    counter++;
                }
                i -= 1;
                j -= 1;
                range++;
            }
            if (counter == 4) {
                check = true;
                return check;
            }
            counter = 0;
            range = 0;
            index++;
            i = mIntRow - index;
            j = mIntColumn - index;
        }
        return check;
    }

    private boolean checkDiagonalTwo(char symbolUser) {
        boolean check = false;
        int counter = 0;
        int j = mIntColumn - 1;
        int i = 0;
        while (i < mIntRow - 1) {
            if (mSituation[i][j] == symbolUser)
                counter++;
            i++;
            j--;
        }
        if (counter == mIntRow)
            check = true;
        else counter = 0;
        return check;
    }

    private boolean checkDiagonalTwoFour(char symbolUser) {
        boolean check = false;
        int counter = 0;
        int index = 1;
        int range = 0;
        int i = mIntRow - 1;
        int j = 0;
        int lengthRow = i - 4;
        int lengthColumn = j + 4;
        while (i >= lengthRow && j <= lengthColumn) {
            if (range < 4) {
                if (mSituation[i][j] == symbolUser) {
                    counter++;
                }
                i -= 1;
                j -= 1;
                range++;
            }
            if (counter == 4) {
                check = true;
                return check;
            }
            counter = 0;
            range = 0;
            index++;
            i = mIntRow - index;
            j = mIntColumn + index;
        }

        return check;
    }


}
