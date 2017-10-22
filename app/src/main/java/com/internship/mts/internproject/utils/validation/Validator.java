package com.internship.mts.internproject.utils.validation;

import android.content.Context;

public interface Validator {

   public static final int VALIDATION_SUCCESS = 0;

   public abstract int validate();

   public abstract String getErrorMessage(Context context, int validationStatus);

}
