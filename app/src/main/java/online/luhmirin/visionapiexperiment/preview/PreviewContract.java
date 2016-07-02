package online.luhmirin.visionapiexperiment.preview;

import android.graphics.Bitmap;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.text.TextBlock;

interface PreviewContract {

    void showMessage(String message);

    void startCamera();

    void startGalery();

    void setPreviewImage(Bitmap imageBitmap);

    void enableFilterButtons();

    void disableFilterButtons();

    void foundFace(Face face);

    void foundBarcode(Barcode barcode);

    void foundText(TextBlock textBlock);
}
