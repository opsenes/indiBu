package com.internship.mts.internproject.utils.validation;

import android.content.Context;
import android.support.annotation.IntDef;
import android.text.TextUtils;

import com.internship.mts.internproject.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LastNameValidatorUtil implements Validator {

    private String lastName;

    @IntDef({VALIDATION_SUCCESS, LastNameValidationStatus.VALIDATION_EMPTY,
            LastNameValidationStatus.VALIDATION_SHORT_LASTNAME})
    @Retention(RetentionPolicy.SOURCE)
    @interface LastNameValidationStatus {
        int VALIDATION_EMPTY = 1;
        int VALIDATION_SHORT_LASTNAME = 2;
    }

    public LastNameValidatorUtil(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int validate() {
        if (TextUtils.isEmpty(lastName)) {
            return LastNameValidationStatus.VALIDATION_EMPTY;
        }

        if (lastName.length() < 3) {
            return LastNameValidationStatus.VALIDATION_SHORT_LASTNAME;
        }

        return VALIDATION_SUCCESS;
    }

    @Override
    public String getErrorMessage(Context context, @LastNameValidationStatus int validationStatus) {
        switch (validationStatus) {
            case VALIDATION_SUCCESS :
                return null;

            case LastNameValidationStatus.VALIDATION_EMPTY :
                return context.getResources().getString(R.string.err_msg_lastname_empty);

            case LastNameValidationStatus.VALIDATION_SHORT_LASTNAME :
                return context.getResources().getString(R.string.err_msg_lastname_short);

        }

        return null;
    }
}

