package com.internship.mts.internproject.utils;

import android.content.Context;

import com.internship.mts.internproject.SignupActivity;

import java.io.File;
import java.io.IOException;

import id.zelory.compressor.Compressor;

public class ImageUtil {
    public static File compressImage(Context context, File imageFile, int maxSize) {
        try {
            return new Compressor(context)
                    .setMaxHeight(maxSize)
                    .setMaxWidth(maxSize)
                    .compressToFile(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
