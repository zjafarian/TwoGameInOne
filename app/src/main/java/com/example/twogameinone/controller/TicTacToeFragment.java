package com.example.twogameinone.controller;

import android.app.Activity;
import android.content.Intent;
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
    private ImageButton mImageButtonActiveUserGirl;
    private ImageButton mImageButtonActiveUserBoy;
    private ImageButton mImageButtonDeactivateGirl;
    private ImageButton mImageButtonDeactivateBoy;
    private ImageButton mImageButtonSetting;
    private Button mButtonOneOne;
    private Button mButtonOneTwo;
    private Button mButtonOneThree;
    private Button mButtonTwoOne;
    private Button mButtonTwoTwo;
    private Button mButtonTwoThree;
    private Button mButtonThreeOne;
    private Button mButtonThreeTwo;
    private Button mButtonThreeThree;
    private User mUserGirl;
    private User mUserBoy;
    private int mRow;
    private int mColumn;
    private boolean mCheckSettingTicTocToe=false;
    private Setting mSettingTicTocTOe;
    private char[][] mCharsSituation;
    private View mLayoutMain;
    public static String sGameNameTicTacToe = "TicTacToe";
    private int scoreGirl = 0;
    private int scoreBoy = 0;


    public TicTacToeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSettingTicTocTOe = new Setting(sGameNameTicTacToe);
        mRow = mSettingTicTocTOe.getRow();
        mColumn = mSettingTicTocTOe.getColumn();
        mCharsSituation = new char[mRow][mColumn];
        for (int i = 0; i < mRow; i++) {
            for (int j = 0; j < mColumn; j++) {
                mCharsSituation[i][j] = ' ';
            }
        }
        mUserGirl = new User("girl", '*', mCharsSituation, mRow, mColumn, "TicTacToe");
        mUserBoy = new User("boy", 'o', mCharsSituation, mRow, mColumn, "TicTocToe");
        if (savedInstanceState!=null){
            mUserGirl = (User) savedInstanceState.getSerializable(SAVE_USER_GIRL);
            mUserBoy = (User) savedInstanceState.getSerializable(SAVE_USER_BOY);
            mCharsSituation=mUserGirl.getSituation();
            if (mCheckSettingTicTocToe) {
                mCheckSettingTicTocToe = savedInstanceState.getBoolean(SAVE_SETTING_TIC_TOC_TOE);
                setRowAndColumn();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
        setViewId(view);
        if (mCheckSettingTicTocToe) {
            changeColorBackground();
            changeSituationFirstPlayer();
        }
        setListener();
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null)
            return;
        if (requestCode == EXTRA_TIC_TAC_TOE_REQUEST_CODE) {
            mCheckSettingTicTocToe=true;
            mSettingTicTocTOe = (Setting) data.getSerializableExtra(SettingFragment.EXTRA_GET_SETTING);
            changeColorBackground();
            changeSituationFirstPlayer();
            setRowAndColumn();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVE_USER_GIRL,mUserGirl);
        outState.putSerializable(SAVE_USER_BOY,mUserBoy);
        outState.putBoolean(CHECK_SETTING_TIC_TAC_TOE,mCheckSettingTicTocToe);
        outState.putSerializable(SAVE_SETTING_TIC_TOC_TOE,mSettingTicTocTOe);
    }

    private void setRowAndColumn() {
        mRow = mSettingTicTocTOe.getRow();
        mColumn = mSettingTicTocTOe.getColumn();
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

    private void setListener() {
        mButtonOneOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mImageButtonActiveUserGirl.getVisibility() == View.VISIBLE) {
                    if (mCharsSituation[0][0] == ' ') {
                        mButtonOneOne.setPadding(90, 10, 0, 0);
                        Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_close);
                        mButtonOneOne.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
                        startGame(mUserGirl.getSexUser(), 0, 0);
                        mUserGirl.setSituation(mCharsSituation);
                        if (mUserGirl.reviewWinner(sGameNameTicTacToe, mCharsSituation, mUserGirl.getSymbolUser())) {
                            outWinner("girl");
                        }
                    } else {
                        setToast();
                    }
                } else if (mImageButtonActiveUserBoy.getVisibility() == View.VISIBLE) {
                    if (mCharsSituation[0][0] == ' ') {
                        mButtonOneOne.setPadding(90, 10, 0, 0);
                        Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_circle);
                        mButtonOneOne.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
                        startGame(mUserBoy.getSexUser(), 0, 0);
                        mUserBoy.setSituation(mCharsSituation);
                        if (mUserBoy.reviewWinner(sGameNameTicTacToe, mCharsSituation, mUserBoy.getSymbolUser())) {
                            outWinner("boy");
                        }
                    } else {
                        setToast();
                    }
                }
            }
        });

        mButtonOneTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mImageButtonActiveUserGirl.getVisibility() == View.VISIBLE) {
                    if (mCharsSituation[0][1] == ' ') {
                        mButtonOneTwo.setPadding(90, 10, 0, 0);
                        Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_close);
                        mButtonOneTwo.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
                        startGame(mUserGirl.getSexUser(), 0, 1);
                        mUserGirl.setSituation(mCharsSituation);
                        if (mUserGirl.reviewWinner(sGameNameTicTacToe, mCharsSituation, mUserGirl.getSymbolUser())) {
                            outWinner("girl");
                        }
                    } else {
                        setToast();
                    }
                } else if (mImageButtonActiveUserBoy.getVisibility() == View.VISIBLE) {
                    if (mCharsSituation[0][1] == ' ') {
                        mButtonOneTwo.setPadding(90, 10, 0, 0);
                        Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_circle);
                        mButtonOneTwo.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
                        startGame(mUserBoy.getSexUser(), 0, 1);
                        mUserBoy.setSituation(mCharsSituation);
                        if (mUserBoy.reviewWinner(sGameNameTicTacToe, mCharsSituation, mUserBoy.getSymbolUser())) {
                            outWinner("boy");
                        }
                    } else {
                        setToast();
                    }
                }

            }
        });

        mButtonOneThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mImageButtonActiveUserGirl.getVisibility() == View.VISIBLE) {
                    if (mCharsSituation[0][2] == ' ') {
                        mButtonOneThree.setPadding(90, 10, 0, 0);
                        Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_close);
                        mButtonOneThree.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
                        startGame(mUserGirl.getSexUser(), 0, 2);
                        mUserGirl.setSituation(mCharsSituation);
                        if (mUserGirl.reviewWinner(sGameNameTicTacToe, mCharsSituation, mUserGirl.getSymbolUser())) {
                            outWinner("girl");
                        }
                    } else {
                        setToast();
                    }
                } else if (mImageButtonActiveUserBoy.getVisibility() == View.VISIBLE) {
                    if (mCharsSituation[0][2] == ' ') {
                        mButtonOneThree.setPadding(90, 10, 0, 0);
                        Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_circle);
                        mButtonOneThree.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
                        startGame(mUserBoy.getSexUser(), 0, 2);
                        mUserBoy.setSituation(mCharsSituation);
                        if (mUserBoy.reviewWinner(sGameNameTicTacToe, mCharsSituation, mUserBoy.getSymbolUser())) {
                            outWinner("boy");
                        }
                    } else {
                        setToast();
                    }
                }

            }
        });

        mButtonTwoOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mImageButtonActiveUserGirl.getVisibility() == View.VISIBLE) {
                    if (mCharsSituation[1][0] == ' ') {
                        mButtonTwoOne.setPadding(90, 10, 0, 0);
                        Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_close);
                        mButtonTwoOne.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
                        startGame(mUserGirl.getSexUser(), 1, 0);
                        mUserGirl.setSituation(mCharsSituation);
                        if (mUserGirl.reviewWinner(sGameNameTicTacToe, mCharsSituation, mUserGirl.getSymbolUser())) {
                            outWinner("girl");
                        }
                    } else {
                        setToast();
                    }
                } else if (mImageButtonActiveUserBoy.getVisibility() == View.VISIBLE) {
                    if (mCharsSituation[1][0] == ' ') {
                        mButtonTwoOne.setPadding(90, 10, 0, 0);
                        Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_circle);
                        mButtonTwoOne.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
                        startGame(mUserBoy.getSexUser(), 1, 0);
                        mUserBoy.setSituation(mCharsSituation);
                        if (mUserBoy.reviewWinner(sGameNameTicTacToe, mCharsSituation, mUserBoy.getSymbolUser())) {
                            outWinner("boy");
                        }
                    } else {
                        setToast();
                    }
                }

            }
        });

        mButtonTwoTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mImageButtonActiveUserGirl.getVisibility() == View.VISIBLE) {
                    if (mCharsSituation[1][1] == ' ') {
                        mButtonTwoTwo.setPadding(90, 10, 0, 0);
                        Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_close);
                        mButtonTwoTwo.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
                        startGame(mUserGirl.getSexUser(), 1, 1);
                        mUserGirl.setSituation(mCharsSituation);
                        if (mUserGirl.reviewWinner(sGameNameTicTacToe, mCharsSituation, mUserGirl.getSymbolUser())) {
                            outWinner("girl");
                        }
                    } else {
                        setToast();
                    }
                } else if (mImageButtonActiveUserBoy.getVisibility() == View.VISIBLE) {
                    if (mCharsSituation[1][1] == ' ') {
                        mButtonTwoTwo.setPadding(90, 10, 0, 0);
                        Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_circle);
                        mButtonTwoTwo.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
                        startGame(mUserBoy.getSexUser(), 1, 1);
                        mUserBoy.setSituation(mCharsSituation);
                        if (mUserBoy.reviewWinner(sGameNameTicTacToe, mCharsSituation, mUserBoy.getSymbolUser())) {
                            outWinner("boy");
                        }
                    } else {
                        setToast();
                    }
                }

            }
        });

        mButtonTwoThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mImageButtonActiveUserGirl.getVisibility() == View.VISIBLE) {
                    if (mCharsSituation[1][2] == ' ') {
                        mButtonTwoThree.setPadding(90, 10, 0, 0);
                        Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_close);
                        mButtonTwoThree.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
                        startGame(mUserGirl.getSexUser(), 1, 2);
                        mUserGirl.setSituation(mCharsSituation);
                        if (mUserGirl.reviewWinner(sGameNameTicTacToe, mCharsSituation, mUserGirl.getSymbolUser())) {
                            outWinner("girl");
                        }

                    } else {
                        setToast();
                    }
                } else if (mImageButtonActiveUserBoy.getVisibility() == View.VISIBLE) {
                    if (mCharsSituation[1][2] == ' ') {
                        mButtonTwoThree.setPadding(90, 10, 0, 0);
                        Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_circle);
                        mButtonTwoThree.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
                        startGame(mUserBoy.getSexUser(), 1, 2);
                        mUserBoy.setSituation(mCharsSituation);
                        if (mUserBoy.reviewWinner(sGameNameTicTacToe, mCharsSituation, mUserBoy.getSymbolUser())) {
                            outWinner("boy");
                        }
                    } else {
                        setToast();
                    }
                }

            }
        });

        mButtonThreeOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mImageButtonActiveUserGirl.getVisibility() == View.VISIBLE) {
                    if (mCharsSituation[2][0] == ' ') {
                        mButtonThreeOne.setPadding(90, 10, 0, 0);
                        Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_close);
                        mButtonThreeOne.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
                        startGame(mUserGirl.getSexUser(), 2, 0);
                        mUserGirl.setSituation(mCharsSituation);
                        if (mUserGirl.reviewWinner(sGameNameTicTacToe, mCharsSituation, mUserGirl.getSymbolUser())) {
                            outWinner("girl");
                        }
                    } else {
                        setToast();
                    }
                } else if (mImageButtonActiveUserBoy.getVisibility() == View.VISIBLE) {
                    if (mCharsSituation[2][0] == ' ') {
                        mButtonThreeOne.setPadding(90, 10, 0, 0);
                        Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_circle);
                        mButtonThreeOne.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
                        startGame(mUserBoy.getSexUser(), 2, 0);
                        mUserBoy.setSituation(mCharsSituation);
                        if (mUserBoy.reviewWinner(sGameNameTicTacToe, mCharsSituation, mUserBoy.getSymbolUser())) {
                            outWinner("boy");
                        }
                    } else {
                        setToast();
                    }
                }

            }
        });

        mButtonThreeTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mImageButtonActiveUserGirl.getVisibility() == View.VISIBLE) {
                    if (mCharsSituation[2][1] == ' ') {
                        mButtonThreeTwo.setPadding(90, 10, 0, 0);
                        Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_close);
                        mButtonThreeTwo.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
                        startGame(mUserGirl.getSexUser(), 2, 1);
                        mUserGirl.setSituation(mCharsSituation);
                        if (mUserGirl.reviewWinner(sGameNameTicTacToe, mCharsSituation, mUserGirl.getSymbolUser())) {
                            outWinner("girl");
                        }
                    } else {
                        setToast();
                    }
                } else if (mImageButtonActiveUserBoy.getVisibility() == View.VISIBLE) {
                    if (mCharsSituation[2][1] == ' ') {
                        mButtonThreeTwo.setPadding(90, 10, 0, 0);
                        Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_circle);
                        mButtonThreeTwo.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
                        startGame(mUserBoy.getSexUser(), 2, 1);
                        mUserBoy.setSituation(mCharsSituation);
                        if (mUserBoy.reviewWinner(sGameNameTicTacToe, mCharsSituation, mUserBoy.getSymbolUser())) {
                            outWinner("boy");
                        }
                    } else {
                        setToast();
                    }
                }

            }
        });

        mButtonThreeThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mImageButtonActiveUserGirl.getVisibility() == View.VISIBLE) {
                    if (mCharsSituation[2][2] == ' ') {
                        mButtonThreeThree.setPadding(90, 10, 0, 0);
                        Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_close);
                        mButtonThreeThree.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
                        startGame(mUserGirl.getSexUser(), 2, 2);
                        mUserGirl.setSituation(mCharsSituation);
                        if (mUserGirl.reviewWinner(sGameNameTicTacToe, mCharsSituation, mUserBoy.getSymbolUser())) {
                            outWinner("girl");
                        }

                    } else {
                        setToast();
                    }
                } else if (mImageButtonActiveUserBoy.getVisibility() == View.VISIBLE) {
                    if (mCharsSituation[2][2] == ' ') {
                        mButtonThreeThree.setPadding(90, 10, 0, 0);
                        Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_circle);
                        mButtonThreeThree.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
                        startGame(mUserBoy.getSexUser(), 2, 2);
                        mUserBoy.setSituation(mCharsSituation);
                        if (mUserBoy.reviewWinner(sGameNameTicTacToe, mCharsSituation, mUserBoy.getSymbolUser())) {
                            outWinner("boy");
                        }
                    } else {
                        setToast();
                    }
                }

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

    private void startGame(String userSex, int row, int column) {
        if (userSex.equals("girl")) {
            mCharsSituation[row][column] = '*';
            mImageButtonActiveUserGirl.setVisibility(View.GONE);
            mImageButtonDeactivateGirl.setVisibility(View.VISIBLE);
            mImageButtonActiveUserBoy.setVisibility(View.VISIBLE);
            mImageButtonDeactivateBoy.setVisibility(View.GONE);
        } else if (userSex.equals("boy")) {
            mCharsSituation[row][column] = 'o';
            mImageButtonActiveUserBoy.setVisibility(View.GONE);
            mImageButtonDeactivateBoy.setVisibility(View.VISIBLE);
            mImageButtonActiveUserGirl.setVisibility(View.VISIBLE);
            mImageButtonDeactivateGirl.setVisibility(View.GONE);
        }


    }

    private void setToast() {
        Toast toast = Toast.makeText(getContext(), R.string.selectable_cell, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    private void setViewId(View view) {
        mImageButtonActiveUserGirl = view.findViewById(R.id.image_btn_user_girl_active);
        mImageButtonActiveUserBoy = view.findViewById(R.id.image_btn_user_boy_active);
        mImageButtonDeactivateGirl = view.findViewById(R.id.image_btn_user_girl_deactive);
        mImageButtonDeactivateBoy = view.findViewById(R.id.image_btn_user_boy_deactive);
        mImageButtonSetting = view.findViewById(R.id.image_btn_setting);
        mButtonOneOne = view.findViewById(R.id.btn_1_1);
        mButtonOneTwo = view.findViewById(R.id.btn_1_2);
        mButtonOneThree = view.findViewById(R.id.btn_1_3);
        mButtonTwoOne = view.findViewById(R.id.btn_2_1);
        mButtonTwoTwo = view.findViewById(R.id.btn_2_2);
        mButtonTwoThree = view.findViewById(R.id.btn_2_3);
        mButtonThreeOne = view.findViewById(R.id.btn_3_1);
        mButtonThreeTwo = view.findViewById(R.id.btn_3_2);
        mButtonThreeThree = view.findViewById(R.id.btn_3_3);
        mLayoutMain = view.findViewById(R.id.layout_main);
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
        mButtonOneOne.setEnabled(false);
        mButtonOneTwo.setEnabled(false);
        mButtonOneThree.setEnabled(false);
        mButtonTwoOne.setEnabled(false);
        mButtonTwoTwo.setEnabled(false);
        mButtonTwoThree.setEnabled(false);
        mButtonThreeOne.setEnabled(false);
        mButtonThreeTwo.setEnabled(false);
        mButtonThreeThree.setEnabled(false);
        Snackbar snackbar = Snackbar.make(mLayoutMain, message, Snackbar.LENGTH_LONG);
        snackbar.show();
        for (int i = 0; i < mRow; i++) {
            for (int j = 0; j < mColumn; j++) {
                mCharsSituation[i][j] = ' ';
            }
        }
        mUserGirl.setSituation(mCharsSituation);
        mUserBoy.setSituation(mCharsSituation);

    }

}