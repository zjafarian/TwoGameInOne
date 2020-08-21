package com.example.twogameinone.controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.twogameinone.R;
import com.example.twogameinone.model.ColorBackground;
import com.example.twogameinone.model.Setting;

import static android.app.Activity.RESULT_OK;
import static com.example.twogameinone.controller.FourInARowFragment.EXTRA_IS_FOUR_IN_ROW;
import static com.example.twogameinone.controller.FourInARowFragment.EXTRA_PUT_SETTING_FOUR_IN_ROW;
import static com.example.twogameinone.controller.TicTacToeFragment.EXTRA_IS_TIC_TAC_TOE;
import static com.example.twogameinone.controller.TicTacToeFragment.EXTRA_PUT_SETTING_TIC_TOC_TOE;
import static com.example.twogameinone.model.ColorBackground.Blue;
import static com.example.twogameinone.model.ColorBackground.Red;
import static com.example.twogameinone.model.ColorBackground.White;


public class SettingFragment extends Fragment {
    public static final String EXTRA_GET_SETTING = "getSetting";
    private RadioGroup mRadioGroupRow;
    private RadioGroup mRadioGroupColumn;
    private RadioButton mRadioButtonGirl;
    private RadioButton mRadioButtonBoy;
    private RadioButton[] mRadioButtonsColors;
    private RadioButton[] mRadioButtonsRows;
    private RadioButton[] mRadioButtonsColumns;
    private Setting mSetting;
    private Setting defaultSetting;
    private Button mButtonSave;
    private Button mButtonDiscard;
    private int[] arrayColorId = {R.id.btn_color_red, R.id.btn_color_blue, R.id.btn_color_green,
            R.id.btn_color_white};
    private int[] arrayRowId = {R.id.btn_radio_5_row, R.id.btn_radio_6_row, R.id.btn_radio_7_row,
            R.id.btn_radio_8_row, R.id.btn_radio_9_row, R.id.btn_radio_10_row};
    private int[] arrayColumnId = {R.id.btn_radio_5_column, R.id.btn_radio_6_column,
            R.id.btn_radio_7_column, R.id.btn_radio_8_column, R.id.btn_radio_9_column,
            R.id.btn_radio_10_column};


    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity().getIntent().getExtras().getBoolean(EXTRA_IS_TIC_TAC_TOE)) {
            mSetting = new Setting(TicTacToeFragment.sGameNameTicTacToe);
            mSetting = (Setting) getActivity().getIntent().getSerializableExtra(EXTRA_PUT_SETTING_TIC_TOC_TOE);
        } else if (getActivity().getIntent().getExtras().getBoolean(EXTRA_IS_FOUR_IN_ROW)) {
            mSetting = new Setting(FourInARowFragment.sGameNameFourInRow);
            mSetting = (Setting) getActivity().getIntent().getSerializableExtra(EXTRA_PUT_SETTING_FOUR_IN_ROW);
        }
        defaultSetting = mSetting;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        setFindView(view);
        mRadioButtonsColors = new RadioButton[]{view.findViewById(R.id.btn_color_red),
                view.findViewById(R.id.btn_color_blue), view.findViewById(R.id.btn_color_green),
                view.findViewById(R.id.btn_color_white)};
        mRadioButtonsRows = new RadioButton[]{view.findViewById(R.id.btn_radio_5_row),
                view.findViewById(R.id.btn_radio_6_row), view.findViewById(R.id.btn_radio_7_row),
                view.findViewById(R.id.btn_radio_8_row), view.findViewById(R.id.btn_radio_9_row),
                view.findViewById(R.id.btn_radio_10_row)};

        mRadioButtonsColumns = new RadioButton[]{view.findViewById(R.id.btn_radio_5_column),
                view.findViewById(R.id.btn_radio_6_column), view.findViewById(R.id.btn_radio_7_column),
                view.findViewById(R.id.btn_radio_8_column), view.findViewById(R.id.btn_radio_9_column),
                view.findViewById(R.id.btn_radio_10_column)};
        setFieldSetting();
        setListener(view);
        return view;
    }

    private void setFieldSetting() {
        switch (mSetting.getRow()) {
            case 5:
                mRadioButtonsRows[0].setChecked(true);

                break;
            case 6:
                mRadioButtonsRows[1].setChecked(true);
                break;
            case 7:
                mRadioButtonsRows[2].setChecked(true);
                break;
            case 8:
                mRadioButtonsRows[3].setChecked(true);
                break;
            case 9:
                mRadioButtonsRows[4].setChecked(true);
                break;
            case 10:
                mRadioButtonsRows[5].setChecked(true);
                break;
        }

        switch (mSetting.getColumn()) {
            case 5:
                mRadioButtonsColumns[0].setChecked(true);

                break;
            case 6:
                mRadioButtonsColumns[1].setChecked(true);
                break;
            case 7:
                mRadioButtonsColumns[2].setChecked(true);
                break;
            case 8:
                mRadioButtonsColumns[3].setChecked(true);
                break;
            case 9:
                mRadioButtonsColumns[4].setChecked(true);
                break;
            case 10:
                mRadioButtonsColumns[5].setChecked(true);
                break;
        }

        switch (mSetting.getColorBackground()) {
            case Red:
                mRadioButtonsColors[0].setChecked(true);
                break;
            case Blue:
                mRadioButtonsColors[1].setChecked(true);
                break;
            case Green:
                mRadioButtonsColors[2].setChecked(true);
                break;
            case White:
                mRadioButtonsColors[3].setChecked(true);
                break;
        }
        switch (mSetting.getSexUser()) {
            case "girl":
                mRadioButtonGirl.setChecked(true);
                break;
            case "boy":
                mRadioButtonBoy.setChecked(true);
                break;
        }


    }

    private void setFindView(View view) {
        mRadioGroupRow = view.findViewById(R.id.btn_radio_group_row);
        mRadioGroupColumn = view.findViewById(R.id.btn_radio_group_column);
        mRadioButtonGirl = view.findViewById(R.id.btn_user_girl);
        mRadioButtonBoy = view.findViewById(R.id.btn_user_boy);
    }

    private void setListener(View view) {
        if (mSetting.getGameName() == TicTacToeFragment.sGameNameTicTacToe) {
            for (int id : arrayRowId) {
                final RadioButton btn = view.findViewById(id);
                btn.setEnabled(false);
            }
            mRadioGroupRow.setEnabled(false);
            mRadioGroupColumn.setEnabled(false);
        } else {
            for (final int id : arrayRowId) {
                final RadioButton btnRadio = view.findViewById(id);
                btnRadio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (RadioButton btn : mRadioButtonsColors) {
                            switch (id) {
                                case R.id.btn_radio_5_row:
                                    mSetting.setRow(5);
                                    break;
                                case R.id.btn_radio_6_row:
                                    mSetting.setRow(6);
                                    break;
                                case R.id.btn_radio_7_row:
                                    mSetting.setRow(7);
                                    break;
                                case R.id.btn_radio_8_row:
                                    mSetting.setRow(8);
                                    break;
                                case R.id.btn_radio_9_row:
                                    mSetting.setRow(9);
                                    break;
                                case R.id.btn_radio_10_row:
                                    mSetting.setRow(10);
                                    break;
                            }
                        }
                    }
                });
            }
            for (final int id : arrayColumnId) {
                final RadioButton btnRadio = view.findViewById(id);
                btnRadio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (RadioButton btn : mRadioButtonsColors) {
                            switch (id) {
                                case R.id.btn_radio_5_column:
                                    mSetting.setColumn(5);
                                    break;
                                case R.id.btn_radio_6_column:
                                    mSetting.setColumn(6);
                                    break;
                                case R.id.btn_radio_7_column:
                                    mSetting.setColumn(7);
                                    break;
                                case R.id.btn_radio_8_column:
                                    mSetting.setColumn(8);
                                    break;
                                case R.id.btn_radio_9_column:
                                    mSetting.setColumn(9);
                                    break;
                                case R.id.btn_radio_10_column:
                                    mSetting.setColumn(10);
                                    break;
                            }
                        }
                    }
                });
            }
        }
        for (final int id : arrayColorId) {
            final RadioButton btnRadio = view.findViewById(id);
            btnRadio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (RadioButton btn : mRadioButtonsColors) {
                        switch (id) {
                            case R.id.btn_color_red:
                                mSetting.setColorBackground(Red);
                                break;
                            case R.id.btn_color_blue:
                                mSetting.setColorBackground(Blue);
                                break;
                            case R.id.btn_color_green:
                                mSetting.setColorBackground(Blue);
                                break;
                            case R.id.btn_color_white:
                                mSetting.setColorBackground(White);
                                break;
                        }
                    }
                }
            });
        }
        mRadioButtonGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSetting.setSexUser("girl");
            }
        });
        mRadioButtonBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSetting.setSexUser("boy");
            }
        });

        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(EXTRA_GET_SETTING, mSetting);
                getActivity().setResult(RESULT_OK, intent);
                getActivity().finish();
            }
        });

        mButtonDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSetting = defaultSetting;
                getActivity().finish();
            }
        });


    }


}