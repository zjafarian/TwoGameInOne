package com.example.twogameinone.controller;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.twogameinone.R;
import com.example.twogameinone.model.Setting;
import com.example.twogameinone.model.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class FourInARowFragment extends Fragment {
    private int mIntRow;
    private int mIntColumn;
    private User mUserGirl;
    private User mUserBoy;
    private Setting mSetting;
    private Button[][] mButtons;
    private LinearLayout mLayoutButtons;
    private ImageButton mImageButtonSetting;
    private ImageButton mImageButtonActiveUserGirl;
    private ImageButton mImageButtonActiveUserBoy;
    private ImageButton mImageButtonDeactivateGirl;
    private ImageButton mImageButtonDeactivateBoy;
    private int[][] mIdButtons;
    private char[][] mCharsSituation;
    private View mLayoutMain;
    private int mRow = 0;
    private int mColumn = 0;
    private int widthAndHeight = 800;
    public static String sGameNameFourInRow = "FourInRow";
    private int scoreBoy = 0;
    private int scoreGirl = 0;


    public FourInARowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSetting = new Setting("ForInRow");
        mIntRow = mSetting.getRow();
        mIntColumn = mSetting.getColumn();
        mCharsSituation = new char[mIntRow][mIntColumn];
        for (int i = 0; i < mIntRow; i++) {
            for (int j = 0; j < mIntColumn; j++) {
                mCharsSituation[i][j] = ' ';
            }
        }
        mUserGirl = new User("girl", 'r', mCharsSituation, mIntRow, mIntColumn, "TicTacToe");
        mUserBoy = new User("boy", 'b', mCharsSituation, mIntRow, mIntColumn, "TicTocToe");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_four_in_a_row, container, false);
        mButtons = new Button[mIntRow][mIntColumn];
        mIdButtons = new int[mIntRow][mIntColumn];
        setViewId(view);
        createButtons(view, mIntRow, mIntColumn);
        setListener();
        return view;
    }

    private void setViewId(View view) {
        mLayoutButtons = view.findViewById(R.id.layout_buttons);
        mImageButtonSetting = view.findViewById(R.id.image_btn_setting);
        mImageButtonActiveUserGirl = view.findViewById(R.id.image_btn_user_girl_active);
        mImageButtonActiveUserBoy = view.findViewById(R.id.image_btn_user_boy_active);
        mImageButtonDeactivateGirl = view.findViewById(R.id.image_btn_user_girl_deactive);
        mImageButtonDeactivateBoy = view.findViewById(R.id.image_btn_user_boy_deactive);
        mLayoutMain = view.findViewById(R.id.layout_main);
    }

    private void createButtons(@NonNull View view, int intRow, int intColumn) {
        int id = 100;
        int indexRow = 0;
        int indexColumn = 0;
        widthAndHeight = widthAndHeight / mIntRow;
        for (int i = 0; i < mIntRow; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            LinearLayout mLinearLayouts = new LinearLayout(getContext());
            mLinearLayouts.setOrientation(LinearLayout.HORIZONTAL);
            mLinearLayouts.setLayoutParams(params);
            for (int j = 0; j < mIntColumn; j++) {
                mIdButtons[i][j] = id + j;
                mButtons[i][j] = new Button(getContext());
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(widthAndHeight, widthAndHeight);
                mButtons[i][j].setLayoutParams(params1);
                mButtons[i][j].setGravity(Gravity.CENTER);
                mButtons[i][j].setId(Integer.valueOf(mIdButtons[i][j]));
                mLinearLayouts.addView(mButtons[i][j]);
                if (i < mIntRow - 1)
                    mButtons[i][j].setEnabled(false);
            }
            mLayoutButtons.addView(mLinearLayouts);
            id += 100;
        }

    }

    private void outWinner(String mUserSex) {
        if (mUserSex.equals(mUserGirl.getSexUser())) {
            scoreGirl++;
            mUserGirl.setScore(scoreGirl);
        } else {
            scoreBoy++;
            mUserBoy.setScore(scoreBoy);
        }
        String message = mUserSex + "won";
        for (int i = 0; i < mIntRow; i++) {
            for (int j = 0; j < mIntColumn; j++) {
                mButtons[i][j].setEnabled(false);
            }
        }
        Snackbar snackbar = Snackbar.make(mLayoutMain, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void setListener() {
        for (int i = mIntRow - 1; i > 0; i--) {
            final int finalI = i;
            for (int j = 0; j < mIntColumn; j++) {
                final int finalJ = j;
                mButtons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (mImageButtonActiveUserGirl.getVisibility() == View.VISIBLE) {
                            onTogglePlayer(true,finalI,finalJ);
                            mButtons[finalI][finalJ].setPadding(20, 8, 0, 0);
                            Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_red);
                            mButtons[finalI][finalJ].setCompoundDrawablesWithIntrinsicBounds(icon,
                                    null, null, null);
                            mButtons[finalI][finalJ].setEnabled(false);
                            findWinner(finalI, finalJ, true);
                        } else if (mImageButtonActiveUserBoy.getVisibility() == View.VISIBLE) {
                            onTogglePlayer(false,finalI,finalJ);
                            mButtons[finalI][finalJ].setPadding(20, 8, 0, 0);
                            Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_blue);
                            mButtons[finalI][finalJ].setCompoundDrawablesWithIntrinsicBounds(icon,
                                    null, null, null);
                            mButtons[finalI][finalJ].setEnabled(false);
                            findWinner(finalI, finalJ, false);
                        }

                    }
                });
            }
        }
    }

    private void findWinner(int indexI, int indexJ, boolean isGirl) {
        mButtons[indexI - 1][indexJ].setEnabled(true);
        if (isGirl) {
            if (mUserGirl.reviewWinner(sGameNameFourInRow, mCharsSituation, mUserGirl.getSymbolUser())) {
                outWinner("Girl");
                for (int i = 0; i < mIntRow; i++) {
                    for (int j = 0; j < mIntColumn; j++) {
                        mCharsSituation[i][j] = ' ';
                    }
                }
                mUserGirl.setSituation(mCharsSituation);
                mUserBoy.setSituation(mCharsSituation);
            }

        } else {
            if (mUserBoy.reviewWinner(sGameNameFourInRow, mCharsSituation, mUserBoy.getSymbolUser())) {
                outWinner("Boy");
                for (int i = 0; i < mIntRow; i++) {
                    for (int j = 0; j < mIntColumn; j++) {
                        mCharsSituation[i][j] = ' ';
                    }
                }
                mUserGirl.setSituation(mCharsSituation);
                mUserBoy.setSituation(mCharsSituation);
            }
        }
    }

    private void onTogglePlayer(boolean isGirl,int indexI, int indexJ) {
        if (isGirl) {
            mCharsSituation[indexI][indexJ] = 'r';
            mUserGirl.setSituation(mCharsSituation);
            mImageButtonActiveUserGirl.setVisibility(View.GONE);
            mImageButtonDeactivateGirl.setVisibility(View.VISIBLE);
            mImageButtonActiveUserBoy.setVisibility(View.VISIBLE);
            mImageButtonDeactivateBoy.setVisibility(View.GONE);
        } else {
            mCharsSituation[indexI][indexJ] = 'b';
            mUserBoy.setSituation(mCharsSituation);
            mImageButtonActiveUserBoy.setVisibility(View.GONE);
            mImageButtonDeactivateBoy.setVisibility(View.VISIBLE);
            mImageButtonActiveUserGirl.setVisibility(View.VISIBLE);
            mImageButtonDeactivateGirl.setVisibility(View.GONE);
        }
        /*if (mColumn >= 2) {
            mColumn = 0;
            mRow++;
        } else mColumn++;*/
    }

}