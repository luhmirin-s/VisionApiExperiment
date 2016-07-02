package online.luhmirin.visionapiexperiment.preview;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.SparseArray;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.text.TextBlock;

import online.luhmirin.visionapiexperiment.preview.detector.BarcodeDetectorWrapper;
import online.luhmirin.visionapiexperiment.preview.detector.DetectorWrapper;
import online.luhmirin.visionapiexperiment.preview.detector.FaceDetectorWrapper;
import online.luhmirin.visionapiexperiment.preview.detector.TextDetectorWrapper;
import timber.log.Timber;

class PreviewPresenter {

    private PreviewContract contract;
    private Bitmap imageBitmap;

    PreviewPresenter(PreviewContract contract) {
        this.contract = contract;
    }

    void openCameraClicked() {
        Timber.wtf("open camera");
        contract.startCamera();
    }

    void openGaleryClicked() {
        Timber.wtf("open galery");
        contract.startGalery();
    }

    void imageReceived(@Nullable Bitmap imageBitmap) {
        if (imageBitmap == null) {
            imageNotReceived();
        } else {
            this.imageBitmap = imageBitmap;
            contract.setPreviewImage(imageBitmap);
            contract.enableFilterButtons();
        }
    }

    void imageNotReceived() {
        contract.showMessage("Not able to import message");
        contract.disableFilterButtons();
    }

    void detectFaces(FaceDetectorWrapper detection) {
        checkIfDetectorOperational(detection);

        SparseArray<Face> faces = detection.detect(imageBitmap);
        if (faces.size() <= 0) {
            contract.showMessage("Nothing found");
        } else {
            for (int i = 0; i < faces.size(); i++) {
                contract.foundFace(faces.valueAt(i));
            }
        }
        detection.done();
    }

     void detectBarcodes(BarcodeDetectorWrapper detection) {
        checkIfDetectorOperational(detection);

        SparseArray<Barcode> barcode = detection.detect(imageBitmap);
        if (barcode.size() <= 0) {
            contract.showMessage("Nothing found");
        } else {
            for (int i = 0; i < barcode.size(); i++) {
                contract.foundBarcode(barcode.valueAt(i));
            }
        }
        detection.done();
    }

     void detectText(TextDetectorWrapper detection) {
        checkIfDetectorOperational(detection);

        SparseArray<TextBlock> textBlocks = detection.detect(imageBitmap);
        if (textBlocks.size() <= 0) {
            contract.showMessage("Nothing found");
        } else {
            for (int i = 0; i < textBlocks.size(); i++) {
                contract.foundText(textBlocks.valueAt(i));
            }
        }
        detection.done();
    }

    private void checkIfDetectorOperational(DetectorWrapper detection) {
        if (!detection.operational()) {
            contract.showMessage("DetectorWrapper not available");
        }
    }

}
