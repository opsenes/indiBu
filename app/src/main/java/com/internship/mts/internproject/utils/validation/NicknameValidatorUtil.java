package com.internship.mts.internproject.utils.validation;

import android.content.Context;
import android.support.annotation.IntDef;
import android.text.TextUtils;

import com.internship.mts.internproject.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class NicknameValidatorUtil implements Validator {

    private String nickname;

    @IntDef({VALIDATION_SUCCESS, NicknameValidationStatus.VALIDATION_EMPTY,
            NicknameValidationStatus.VALIDATION_SHORT_NICKNAME,
            NicknameValidationStatus.VALIDATION_LONG_NICKNAME,
            NicknameValidationStatus.VALIDATION_NOT_NICKNAME})
    @Retention(RetentionPolicy.SOURCE)
    @interface NicknameValidationStatus {
        int VALIDATION_EMPTY = 1;
        int VALIDATION_SHORT_NICKNAME = 100;
        int VALIDATION_LONG_NICKNAME = 101;
        int VALIDATION_NOT_NICKNAME = 102;
    }

    public NicknameValidatorUtil(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public int validate() {
        if (TextUtils.isEmpty(nickname)) {
            return NicknameValidationStatus.VALIDATION_EMPTY;
        }

        if (nickname.length() < 3) {
            return NicknameValidationStatus.VALIDATION_SHORT_NICKNAME;
        }

        if(nickname.length() > 15) {
            return NicknameValidationStatus.VALIDATION_LONG_NICKNAME;
        }

        if(nickname.contains("@") || nickname.contains(" ")) {
            return NicknameValidationStatus.VALIDATION_NOT_NICKNAME;
        }

        return VALIDATION_SUCCESS;
    }

    @Override
    public String getErrorMessage(Context context, @NicknameValidationStatus int validationStatus) {
        switch (validationStatus) {
            case VALIDATION_SUCCESS :
                return null;

            case NicknameValidationStatus.VALIDATION_EMPTY :
                return context.getResources().getString(R.string.err_msg_nickname_empty);

            case NicknameValidationStatus.VALIDATION_SHORT_NICKNAME:
                return context.getResources().getString(R.string.err_msg_nickname_short);

            case NicknameValidationStatus.VALIDATION_LONG_NICKNAME:
                return context.getResources().getString(R.string.err_msg_nickname_long);

            case NicknameValidationStatus.VALIDATION_NOT_NICKNAME:
                return context.getResources().getString(R.string.err_msg_not_nickname);

        }

        return null;
    }
}

