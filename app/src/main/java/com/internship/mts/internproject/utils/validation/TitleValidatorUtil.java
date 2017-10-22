package com.internship.mts.internproject.utils.validation;

import android.content.Context;
import android.support.annotation.IntDef;
import android.text.TextUtils;

import com.internship.mts.internproject.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class TitleValidatorUtil implements Validator {

    private String title;

    @IntDef({VALIDATION_SUCCESS, TitleValidationStatus.VALIDATION_EMPTY,
            TitleValidationStatus.VALIDATION_SHORT_NAME})
    @Retention(RetentionPolicy.SOURCE)
    @interface TitleValidationStatus {
        int VALIDATION_EMPTY = 1;
        int VALIDATION_SHORT_NAME = 2;
    }

    public TitleValidatorUtil(String title) {
        this.title = title;
    }

    @Override
    public int validate() {
        if (TextUtils.isEmpty(title)) {
            return TitleValidationStatus.VALIDATION_EMPTY;
        }
        if (title.length() < 5) {
            return TitleValidationStatus.VALIDATION_SHORT_NAME;
        }
        return VALIDATION_SUCCESS;
    }

    @Override
    public String getErrorMessage(Context context, int validationStatus) {
        switch (validationStatus) {
            case VALIDATION_SUCCESS :
                return null;

            case TitleValidationStatus.VALIDATION_EMPTY :
                return context.getResources().getString(R.string.err_msg_title_empty);

            case TitleValidationStatus.VALIDATION_SHORT_NAME :
                return context.getResources().getString(R.string.err_msg_title_short);

        }
        return null;
    }
}
