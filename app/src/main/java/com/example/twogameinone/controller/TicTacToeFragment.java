package com.example.twogameinone.controller;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.PointerIcon;
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


public class TicTacToeFragment extends Fragment {
    public static final String EXTRA_PUT_SETTING_TIC_TOC_TOE = "com.example.twogameinone.Put_Setting_TicTocToe";
    public static final String EXTRA_IS_TIC_TAC_TOE = "com.example.twogameinone.Is_TicTacToe";
    public static final int EXTRA_TIC_TAC_TOE_REQUEST_CODE = 0;
    public static final String SAVE_USER_GIRL = "save_user_girl";
    public static final String SAVE_USER_BOY = "save_user_boy";
    public static final String CHECK_SETTING_TIC_TAC_TOE = "check_setting_tic_tac_toe";
    public static final String SAVE_SETTING_TIC_TOC_TOE = "save_setting_tic_toc_toe";
    public static final String SAVE_ROW = "save_row";
    public static final String SAVE_COLUMN = "save_column";
    public static final String SAVE_SCORE_BOY = "saveScoreBoy";
    public static final String SAVE_SCORE_GIRL = "saveScoreGirl";
    public static final String SAVE_SETTING = "saveSetting";
    private ImageButton mImageButtonActiveUserGirl;
    private ImageButton mImageButtonActiveUserBoy;
    private ImageButton mImageButtonDeactivateGirl;
    private ImageButton mImageButtonDeactivateBoy;
    private ImageButton mImageButtonSetting;
    private Button[][] mButtons;
    private Button mButtonFinishTic;
    private Button mButtonSatrtTic;
    private User mUserGirl;
    private User mUserBoy;
    private int mRow;
    private int mColumn;
    private boolean mCheckSettingTicTocToe = false;
    public static String sGameNameTicTacToe = "TicTacToe";
    private Setting mSettingTicTocTOe = new Setting(sGameNameTicTacToe);
    private char[][] mCharsSituation;
    private View mLayoutMain;
    private int scoreGirl = 0;
    private int scoreBoy = 0;


    public TicTacToeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRow = mSettingTicTocTOe.getRow();
        mColumn = mSettingTicTocTOe.getColumn();
        mCharsSituation = new char[mRow][mColumn];
        for (int i = 0; i < mRow; i++) {
            for (int j = 0; j < mColumn; j++) {
                mCharsSituation[i][j] = ' ';
            }
        }
        mUserGirl = new User("Girl", '*', mCharsSituation, mRow, mColumn, "TicTacToe");
        mUserBoy = new User("Boy", 'o', mCharsSituation, mRow, mColumn, "TicTocToe");
        if (savedInstanceState != null) {
            mRow = savedInstanceState.getInt(SAVE_ROW);
            mColumn = savedInstanceState.getInt(SAVE_COLUMN);
            mCharsSituation = new char[mRow][mColumn];
            mUserGirl = (User) savedInstanceState.getSerializable(SAVE_USER_GIRL);
            mUserBoy = (User) savedInstanceState.getSerializable(SAVE_USER_BOY);
            scoreGirl = savedInstanceState.getInt(SAVE_SCORE_GIRL);
            scoreBoy = savedInstanceState.getInt(SAVE_SCORE_BOY);
            mUserGirl.setScore(scoreGirl);
            mUserBoy.setScore(scoreBoy);
            mCharsSituation = mUserGirl.getSituation();
            mCheckSettingTicTocToe = savedInstanceState.getBoolean(CHECK_SETTING_TIC_TAC_TOE);
            if (mCheckSettingTicTocToe) {
                mSettingTicTocTOe = (Setting) savedInstanceState.getSerializable(SAVE_SETTING_TIC_TOC_TOE);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
        setViewId(view);
        mButtons = new Button[][]{{view.findViewById(R.id.btn_1_1), view.findViewById(R.id.btn_1_2),
                view.findViewById(R.id.btn_1_3)}, {view.findViewById(R.id.btn_2_1),
                view.findViewById(R.id.btn_2_2), view.findViewById(R.id.btn_2_3)},
                {view.findViewById(R.id.btn_3_1), view.findViewById(R.id.btn_3_2),
                        view.findViewById(R.id.btn_3_3)}};
        if (savedInstanceState != null) {
            changeBackgroundButton();
        }
        if (mCheckSettingTicTocToe) {
            changeColorBackground();
            changeSituationFirstPlayer();
        }


        setListener(view);
        return view;
    }

    private void changeBackgroundButton() {
        for (int i = 0; i < mRow; i++) {
            for (int j = 0; j < mColumn; j++) {
                if (mCharsSituation[i][j] == '*') {
                    mButtons[i][j].setPadding(90, 10, 0, 0);
                    Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_close);
                    mButtons[i][j].setCompoundDrawablesWithIntrinsicBounds(icon,
                            null, null, null);
                } else if (mCharsSituation[i][j] == 'o') {
                    mButtons[i][j].setPadding(90, 10, 0, 0);
                    Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_circle);
                    mButtons[i][j].setCompoundDrawablesWithIntrinsicBounds(icon,
                            null, null, null);
                } else if (mCharsSituation[i][j]==' '){
                    mButtons[i][j].setPadding(90, 10, 0, 0);
                    Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_accent);
                    mButtons[i][j].setCompoundDrawablesWithIntrinsicBounds(icon,
                            null, null, null);

                }
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null)
            return;
        if (requestCode == EXTRA_TIC_TAC_TOE_REQUEST_CODE) {
            mCheckSettingTicTocToe = data.getBooleanExtra(SettingFragment.EXTRA_IS_SETTING, false);
            mSettingTicTocTOe = (Setting) data.getSerializableExtra(SettingFragment.EXTRA_GET_SETTING);
            changeColorBackground();
            changeSituationFirstPlayer();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVE_USER_GIRL, mUserGirl);
        outState.putSerializable(SAVE_USER_BOY, mUserBoy);
        outState.putInt(SAVE_SCORE_BOY, scoreBoy);
        outState.putInt(SAVE_SCORE_GIRL, scoreGirl);
        outState.putBoolean(CHECK_SETTING_TIC_TAC_TOE, mCheckSettingTicTocToe);
        outState.putSerializable(SAVE_SETTING_TIC_TOC_TOE, mSettingTicTocTOe);
        outState.putInt(SAVE_ROW, mRow);
        outState.putInt(SAVE_COLUMN, mColumn);
    }

    private void changeSituationFirstPlayer() {
        if (mSettingTicTocTOe.getSexUser().equals("girl")) {
            mImageButtonActiveUserGirl.setVisibility(View.VISIBLE);
            mImageButtonDeactivateBoy.setVisibility(View.VISIBLE);
            mImageButtonDeactivateGirl.setVisibility(View.GONE);
            mImageButtonActiveUserBoy.setVisibility(View.GONE);
        } else if (mSettingTicTocTOe.getSexUser().equals("boy")) {
            mImageButtonActiveUserBoy.setVisibility(View.VISIBLE);
            mImageButtonDeactivateGirl.setVisibility(View.VISIBLE);
            mImageButtonActiveUserGirl.setVisibility(View.GONE);
            mImageButtonDeactivateBoy.setVisibility(View.GONE);
        }
    }

    private void changeColorBackground() {
        switch (mSettingTicTocTOe.getColorBackground()) {
            case Red:
                mLayoutMain.setBackgroundColor(Color.RED);
                break;
            case Blue:
                mLayoutMain.setBackgroundColor(Color.BLUE);
                break;
            case Green:
                mLayoutMain.setBackgroundColor(Color.GREEN);
                break;
            case White:
                mLayoutMain.setBackgroundColor(Color.WHITE);
                break;
        }
    }

    private void setListener(View view) {
        for (int i = 0; i < mRow; i++) {
            final int finalI = i;
            for (int j = 0; j < mColumn; j++) {
                final int finalJ = j;
                mButtons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mImageButtonActiveUserGirl.getVisibility() == View.VISIBLE) {
                            onTogglePlayer(true, finalI, finalJ);
                            mButtons[finalI][finalJ].setPadding(90, 10, 0, 0);
                            Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_close);
                            mButtons[finalI][finalJ].setCompoundDrawablesWithIntrinsicBounds(icon,
                                    null, null, null);
                            mButtons[finalI][finalJ].setEnabled(false);
                            findWinner(true);
                        } else if (mImageButtonActiveUserBoy.getVisibility() == View.VISIBLE) {
                            onTogglePlayer(false, finalI, finalJ);
                            mButtons[finalI][finalJ].setPadding(90, 10, 0, 0);
                            Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_circle);
                            mButtons[finalI][finalJ].setCompoundDrawablesWithIntrinsicBounds(icon,
                                    null, null, null);
                            mButtons[finalI][finalJ].setEnabled(false);
                            findWinner(false);
                        }
                    }
                });
            }
        }

        mButtonFinishTic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scoreGirl > scoreBoy) {
                    Toast toast = Toast.makeText(getContext(), "Girl is winner", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.show();
                } else if (scoreBoy > scoreGirl) {
                    Toast toast = Toast.makeText(getContext(), "Boy is winner", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getContext(), "Boy and Girl are Equal", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.show();
                }

                for (int i = 0; i < mRow; i++) {
                    for (int j = 0; j < mColumn; j++) {
                        mCharsSituation[i][j] = ' ';
                    }
                }
                scoreGirl = 0;
                scoreGirl = 0;
                mUserGirl.setSituation(mCharsSituation);
                mUserBoy.setSituation(mCharsSituation);
                mUserBoy.setScore(scoreBoy);
                mUserGirl.setScore(scoreGirl);
                changeBackgroundButton();
            }

        });

        mButtonSatrtTic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mRow; i++) {
                    for (int j = 0; j < mColumn; j++) {
                        mCharsSituation[i][j] = ' ';
                        mButtons[i][j].setEnabled(true);
                    }
                }
                mUserGirl.setSituation(mCharsSituation);
                mUserBoy.setSituation(mCharsSituation);
                changeBackgroundButton();
            }
        });

        mImageButtonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                intent.putExtra(EXTRA_PUT_SETTING_TIC_TOC_TOE, mSettingTicTocTOe);
                intent.putExtra(EXTRA_IS_TIC_TAC_TOE, true);
                startActivityForResult(intent, EXTRA_TIC_TAC_TOE_REQUEST_CODE);
            }
        });


    }

    private void findWinner(boolean isGirl) {
        if (isGirl) {
            if (mUserGirl.reviewWinner(sGameNameTicTacToe, mCharsSituation, mUserGirl.getSymbolUser())) {
                outWinner("Girl");
                for (int i = 0; i < mRow; i++) {
                    for (int j = 0; j < mColumn; j++) {
                        mCharsSituation[i][j] = ' ';
                    }
                }
                mUserGirl.setSituation(mCharsSituation);
                mUserBoy.setSituation(mCharsSituation);
            }

        } else {
            if (mUserBoy.reviewWinner(sGameNameTicTacToe, mCharsSituation, mUserBoy.getSymbolUser())) {
                outWinner("Boy");
                for (int i = 0; i < mRow; i++) {
                    for (int j = 0; j < mColumn; j++) {
                        mCharsSituation[i][j] = ' ';
                    }
                }
                mUserGirl.setSituation(mCharsSituation);
                mUserBoy.setSituation(mCharsSituation);
            }
        }
    }

    private void onTogglePlayer(boolean isGirl, int indexI, int indexJ) {
        if (isGirl) {
            mCharsSituation[indexI][indexJ] = '*';
            mUserGirl.setSituation(mCharsSituation);
            mUserBoy.setSituation(mCharsSituation);
            mImageButtonActiveUserGirl.setVisibility(View.GONE);
            mImageButtonDeactivateGirl.setVisibility(View.VISIBLE);
            mImageButtonActiveUserBoy.setVisibility(View.VISIBLE);
            mImageButtonDeactivateBoy.setVisibility(View.GONE);
        } else {
            mCharsSituation[indexI][indexJ] = 'o';
            mUserBoy.setSituation(mCharsSituation);
            mUserGirl.setSituation(mCharsSituation);
            mImageButtonActiveUserBoy.setVisibility(View.GONE);
            mImageButtonDeactivateBoy.setVisibility(View.VISIBLE);
            mImageButtonActiveUserGirl.setVisibility(View.VISIBLE);
            mImageButtonDeactivateGirl.setVisibility(View.GONE);
        }
    }

    private void setViewId(View view) {
        mImageButtonActiveUserGirl = view.findViewById(R.id.image_btn_user_girl_active);
        mImageButtonActiveUserBoy = view.findViewById(R.id.image_btn_user_boy_active);
        mImageButtonDeactivateGirl = view.findViewById(R.id.image_btn_user_girl_deactive);
        mImageButtonDeactivateBoy = view.findViewById(R.id.image_btn_user_boy_deactive);
        mImageButtonSetting = view.findViewById(R.id.image_btn_setting);
        mLayoutMain = view.findViewById(R.id.layout_main);
        mButtonSatrtTic = view.findViewById(R.id.btn_start_tic);
        mButtonFinishTic = view.findViewById(R.id.btn_finish_tic);
    }

    private void outWinner(String mUserSex) {
        String message = mUserSex + " won";
        if (mUserSex.equals(mUserGirl.getSexUser())) {
            scoreGirl++;
            mUserGirl.setScore(scoreGirl);
        } else {
            scoreBoy++;
            mUserBoy.setScore(scoreBoy);
        }
        Snackbar snackbar = Snackbar.make(mLayoutMain, message, Snackbar.LENGTH_LONG);
        snackbar.show();
        for (int i = 0; i < mRow; i++) {
            for (int j = 0; j < mColumn; j++) {
                mButtons[i][j].setEnabled(false);
            }
        }

    }

}