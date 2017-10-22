package com.internship.mts.internproject.utils;

import android.support.annotation.NonNull;
import android.text.format.DateFormat;

import java.sql.Timestamp;
import java.util.Date;

public class DateFormatUtil {
    @NonNull
    public static String formatDate(long stringDate) {
        return DateFormat.format(
                "HH:mm dd/MM/yyyy",
                new Date(new Timestamp(stringDate).getTime())
        ).toString();
    }
}
