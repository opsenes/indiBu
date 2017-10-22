package com.internship.mts.internproject.utils.validation;


import android.content.Context;
import android.support.annotation.IntDef;
import android.text.TextUtils;
import android.util.Patterns;

import com.internship.mts.internproject.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public class EmailValidatorUtil implements Validator {

    private String email;

    @IntDef({VALIDATION_SUCCESS, EmailValidationStatus.VALIDATION_EMPTY, EmailValidationStatus.VALIDATION_NOT_EMAIL})
    @Retention(RetentionPolicy.SOURCE)
    @interface EmailValidationStatus {
        int VALIDATION_EMPTY = 1;
        int VALIDATION_NOT_EMAIL = 2;

    }

    public EmailValidatorUtil(String email) {
        this.email = email;
    }

    @Override
    public int validate() {
        if (TextUtils.isEmpty(email)) {
            return EmailValidationStatus.VALIDATION_EMPTY;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return EmailValidationStatus.VALIDATION_NOT_EMAIL;
        }

        return VALIDATION_SUCCESS;
    }

    @Override
    public String getErrorMessage(Context context, @EmailValidationStatus int validationStatus) {
        switch (validationStatus) {
            case VALIDATION_SUCCESS :
                return null;

            case EmailValidationStatus.VALIDATION_EMPTY :
                return context.getResources().getString(R.string.err_msg_email_empty);

            case EmailValidationStatus.VALIDATION_NOT_EMAIL :
                return context.getResources().getString(R.string.err_msg_email);

        }

        return null;
    }
}
