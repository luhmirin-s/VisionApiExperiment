package online.luhmirin.visionapiexperiment.preview;

import android.graphics.Bitmap;

interface PreviewContract {

    void showMessage(String message);

    void startCamera();

    void startGalery();

    void setPreviewImage(Bitmap imageBitmap);
}
