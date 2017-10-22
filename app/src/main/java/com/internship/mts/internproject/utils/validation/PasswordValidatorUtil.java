package com.internship.mts.internproject.utils.validation;

import android.content.Context;
import android.support.annotation.IntDef;
import android.text.TextUtils;

import com.internship.mts.internproject.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PasswordValidatorUtil implements Validator {

    private String password;

    @IntDef({VALIDATION_SUCCESS, PasswordValidationStatus.VALIDATION_EMPTY,
            PasswordValidationStatus.VALIDATION_SHORT_PASSWORD,
            PasswordValidationStatus.VALIDATION_NOT_SAME})
    @Retention(RetentionPolicy.SOURCE)
    @interface PasswordValidationStatus {
        int VALIDATION_EMPTY = 1;
        int VALIDATION_SHORT_PASSWORD = 2;
        int VALIDATION_NOT_SAME = 3;
    }

    public PasswordValidatorUtil(String password) {
        this.password = password;
    }

    @Override
    public int validate() {
        if (TextUtils.isEmpty(password)) {
            return PasswordValidationStatus.VALIDATION_EMPTY;
        }

        if (password.length() < 3) {
            return PasswordValidationStatus.VALIDATION_SHORT_PASSWORD;
        }

        return VALIDATION_SUCCESS;
    }

    @Override
    public String getErrorMessage(Context context, @PasswordValidationStatus int validationStatus) {
        switch (validationStatus) {
            case VALIDATION_SUCCESS :
                return null;

            case PasswordValidationStatus.VALIDATION_EMPTY :
                return context.getResources().getString(R.string.err_msg_password_empty);

            case PasswordValidationStatus.VALIDATION_SHORT_PASSWORD:
                return context.getResources().getString(R.string.err_msg_password_short);

            case PasswordValidationStatus.VALIDATION_NOT_SAME:
                return context.getResources().getString(R.string.err_msg_password_not_same);

        }

        return null;
    }

    public int samePasswords(String firstPassword, String secondPassword) {
        if (!TextUtils.equals(firstPassword, secondPassword)) {
            return PasswordValidationStatus.VALIDATION_NOT_SAME;
        }

        return VALIDATION_SUCCESS;
    }
}

