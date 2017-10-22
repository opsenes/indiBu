package com.internship.mts.internproject.utils.validation;

import android.content.Context;
import android.support.annotation.IntDef;

import com.internship.mts.internproject.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class InterestsValidatorUtil implements Validator {

    private boolean[] interests;

    @IntDef({VALIDATION_SUCCESS, InterestValidationStatus.VALIDATION_NOT_SELECTED})
    @Retention(RetentionPolicy.SOURCE)
    @interface InterestValidationStatus {
        int VALIDATION_NOT_SELECTED = 1;
    }

    public InterestsValidatorUtil(boolean[] interests) {
        this.interests = interests;
    }

    @Override
    public int validate() {
        boolean selected = false;
        for(boolean interestChoice: interests) {
            selected = selected || interestChoice;
        }

        if (!selected) {
            return InterestValidationStatus.VALIDATION_NOT_SELECTED;
        }

        return VALIDATION_SUCCESS;
    }

    @Override
    public String getErrorMessage(Context context, @InterestValidationStatus int validationStatus) {
        switch (validationStatus) {
            case VALIDATION_SUCCESS:
                return null;

            case InterestValidationStatus.VALIDATION_NOT_SELECTED:
                return context.getResources().getString(R.string.err_interest_not_selected);

        }

        return null;
    }
}
