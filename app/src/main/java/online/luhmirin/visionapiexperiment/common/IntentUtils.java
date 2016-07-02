package online.luhmirin.visionapiexperiment.common;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import java.io.IOException;

import online.luhmirin.visionapiexperiment.R;

public class IntentUtils {

    public static void startCameraIntent(Activity activity, int request) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(takePictureIntent, request);
        }
    }

    @Nullable
    public static Bitmap getBitmapFromCameraIntent(Intent data) {
        Bundle extras = data.getExtras();
        return (Bitmap) extras.get("data");
    }

    public static void startGaleryIntent(Activity activity, int request) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        // Always show the chooser (if there are multiple options available)
        activity.startActivityForResult(Intent.createChooser(intent, activity.getString(R.string.get_picture)), request);
    }

    @Nullable
    public static Bitmap getBitmapFromGalery(Context context, Intent data) {
        try {
            return MediaStore.Images.Media.getBitmap(context.getContentResolver(), data.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
