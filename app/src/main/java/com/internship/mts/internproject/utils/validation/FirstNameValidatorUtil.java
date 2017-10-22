package com.internship.mts.internproject.utils.validation;

import android.content.Context;
import android.support.annotation.IntDef;
import android.text.TextUtils;

import com.internship.mts.internproject.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class FirstNameValidatorUtil implements Validator {

    private String firstName;

    @IntDef({VALIDATION_SUCCESS, FirstNameValidationStatus.VALIDATION_EMPTY,
            FirstNameValidationStatus.VALIDATION_SHORT_NAME})
    @Retention(RetentionPolicy.SOURCE)
    @interface FirstNameValidationStatus {
        int VALIDATION_EMPTY = 1;
        int VALIDATION_SHORT_NAME = 2;
    }

    public FirstNameValidatorUtil(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public int validate() {
        if (TextUtils.isEmpty(firstName)) {
            return FirstNameValidationStatus.VALIDATION_EMPTY;
        }

        if (firstName.length() < 3) {
            return FirstNameValidationStatus.VALIDATION_SHORT_NAME;
        }

        return VALIDATION_SUCCESS;
    }

    @Override
    public String getErrorMessage(Context context, @FirstNameValidationStatus int validationStatus) {
        switch (validationStatus) {
            case VALIDATION_SUCCESS :
                return null;

            case FirstNameValidationStatus.VALIDATION_EMPTY :
                return context.getResources().getString(R.string.err_msg_name_empty);

            case FirstNameValidationStatus.VALIDATION_SHORT_NAME :
                return context.getResources().getString(R.string.err_msg_name_short);

        }

        return null;
    }
}

