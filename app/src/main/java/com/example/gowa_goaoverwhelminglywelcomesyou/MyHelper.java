package com.example.gowa_goaoverwhelminglywelcomesyou;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class MyHelper {
    public static String getbase64Image(Bitmap img){
        ByteArrayOutputStream baos =new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes,Base64.DEFAULT);
        return encodedImage;
    }
}
