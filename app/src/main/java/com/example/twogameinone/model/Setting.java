package com.example.twogameinone.model;

import java.io.Serializable;

public class Setting implements Serializable {
    private int mLength = 3;
    private String mGameName = "";
    private ColorBackground mColorBackground = ColorBackground.White;

    public Setting(int mLength, String mGameName) {
        this.mLength = mLength;
        this.mGameName = mGameName;
    }

    public int getmLength() {
        return mLength;
    }

    public void setmLength(int mLength) throws Exception {
        if (mGameName.equals("Four")){
            if (mLength>=5){
                this.mLength = mLength;
            } else throw new Exception("length is lower 5");
        }
        this.mLength = mLength;
    }

    public String getmGameName() {
        return mGameName;
    }

    public void setmGameName(String mGameName) {
        this.mGameName = mGameName;
    }

    public ColorBackground getmColorBackground() {
        return mColorBackground;
    }

    public void setmColorBackground(ColorBackground mColorBackground) {
        this.mColorBackground = mColorBackground;
    }
}
