package com.internship.mts.internproject.utils.validation;

import android.content.Context;
import android.support.annotation.IntDef;
import com.internship.mts.internproject.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EmailNicknameValidatorUtil implements Validator {

    private String input;

    @IntDef({VALIDATION_SUCCESS, EmailNicknameValidationStatus.VALIDATION_EMPTY,
            EmailNicknameValidationStatus.VALIDATION_SHORT_NICKNAME,
            EmailNicknameValidationStatus.VALIDATION_NOT_EMAIL,
            EmailNicknameValidationStatus.VALIDATION_LONG_NICKNAME,
            EmailNicknameValidationStatus.VALIDATION_NOT_NICKNAME})
    @Retention(RetentionPolicy.SOURCE)
    @interface EmailNicknameValidationStatus {
        int VALIDATION_EMPTY = 1;
        int VALIDATION_NOT_EMAIL = 2;
        int VALIDATION_SHORT_NICKNAME = 100;
        int VALIDATION_LONG_NICKNAME = 101;
        int VALIDATION_NOT_NICKNAME = 102;
    }

    public EmailNicknameValidatorUtil(String input) {
        this.input = input;
    }

    @Override
    public int validate(){
        if (input.contains("@")) {
            EmailValidatorUtil emailValidationUtil = new EmailValidatorUtil(input);
            return emailValidationUtil.validate();
        } else {
            NicknameValidatorUtil nicknameValidationUtil = new NicknameValidatorUtil(input);
            return nicknameValidationUtil.validate();
        }
    }

    @Override
    public String getErrorMessage(Context context, int validationStatus) {
        switch (validationStatus) {
            default:
                return null;

            case EmailNicknameValidationStatus.VALIDATION_EMPTY:
                return context.getResources().getString(R.string.err_msg_nickname_email_empty);

            case EmailNicknameValidationStatus.VALIDATION_NOT_EMAIL:
                return context.getResources().getString(R.string.err_msg_email);

            case EmailNicknameValidationStatus.VALIDATION_SHORT_NICKNAME:
                return context.getResources().getString(R.string.err_msg_nickname_short);

            case EmailNicknameValidationStatus.VALIDATION_LONG_NICKNAME:
                return context.getResources().getString(R.string.err_msg_nickname_long);

            case EmailNicknameValidationStatus.VALIDATION_NOT_NICKNAME:
                return context.getResources().getString(R.string.err_msg_not_nickname);

        }

    }
}
