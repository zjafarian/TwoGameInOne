package com.example.twogameinone.controller;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
    private Setting mSettingTicTocTOe;
    private char[][] mCharsSituation;
    private View mLayoutMain;

    public TicTacToeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCharsSituation = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        mUserGirl = new User("girl", '*', mCharsSituation, 3, "TicTacToe");
        mUserBoy = new User("boy", 'o', mCharsSituation, 3, "TicTocToe");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
        setViewId(view);
        setListener();
        return view;
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
                        if (mUserGirl.reviewWinner(mCharsSituation)) {
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
                        if (mUserGirl.reviewWinner(mCharsSituation)) {
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
                        if (mUserGirl.reviewWinner(mCharsSituation)) {
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
                        if (mUserGirl.reviewWinner(mCharsSituation)) {
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
                        if (mUserGirl.reviewWinner(mCharsSituation)) {
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
                        if (mUserGirl.reviewWinner(mCharsSituation)) {
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
                    } else {
                        setToast();
                    }
                } else if (mImageButtonActiveUserBoy.getVisibility() == View.VISIBLE) {
                    if (mCharsSituation[1][0] == ' ') {
                        mButtonTwoOne.setPadding(90, 10, 0, 0);
                        Drawable icon = getActivity().getResources().getDrawable(R.drawable.ic_circle);
                        mButtonTwoOne.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
                        startGame(mUserBoy.getSexUser(), 1, 0);
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
                        if (mUserGirl.reviewWinner(mCharsSituation)) {
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
                        if (mUserGirl.reviewWinner(mCharsSituation)) {
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
                        if (mUserGirl.reviewWinner(mCharsSituation)) {
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
                        if (mUserGirl.reviewWinner(mCharsSituation)) {
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
                        if (mUserGirl.reviewWinner(mCharsSituation)) {
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
                        if (mUserGirl.reviewWinner(mCharsSituation)) {
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
                        if (mUserGirl.reviewWinner(mCharsSituation)) {
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
                        if (mUserGirl.reviewWinner(mCharsSituation)) {
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
                        if (mUserGirl.reviewWinner(mCharsSituation)) {
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
                        if (mUserGirl.reviewWinner(mCharsSituation)) {
                            outWinner("boy");
                        }
                    } else {
                        setToast();
                    }
                }

            }
        });


    }

    private void startGame(String userSex, int row, int column) {
        if (userSex.equals("girl")) {
            mCharsSituation[row][column] = '*';
            mUserGirl.reviewWinner(mCharsSituation);
            mImageButtonActiveUserGirl.setVisibility(View.GONE);
            mImageButtonDeactivateGirl.setVisibility(View.VISIBLE);
            mImageButtonActiveUserBoy.setVisibility(View.VISIBLE);
            mImageButtonDeactivateBoy.setVisibility(View.GONE);
        } else if (userSex.equals("boy")) {
            mCharsSituation[row][column] = 'o';
            mUserBoy.reviewWinner(mCharsSituation);
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
        String message = mUserSex + "won";
        mButtonOneOne.setEnabled(false);
        mButtonOneTwo.setEnabled(false);
        mButtonOneThree.setEnabled(false);
        mButtonTwoOne.setEnabled(false);
        mButtonTwoTwo.setEnabled(false);
        mButtonTwoThree.setEnabled(false);
        mButtonThreeOne.setEnabled(false);
        mButtonThreeTwo.setEnabled(false);
        mButtonThreeThree.setEnabled(false);
        Snackbar snackbar = Snackbar.make(mLayoutMain, message , Snackbar.LENGTH_LONG);
        snackbar.show();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("save_user_girl", mUserGirl);
        outState.putSerializable("save_user_boy", mUserBoy);
    }
}