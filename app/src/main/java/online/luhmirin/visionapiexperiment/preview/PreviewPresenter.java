package online.luhmirin.visionapiexperiment.preview;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.text.TextBlock;

import online.luhmirin.visionapiexperiment.preview.detector.BarcodeDetectorWrapper;
import online.luhmirin.visionapiexperiment.preview.detector.DetectorListener;
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

    void detectFaces(FaceDetectorWrapper detection, DetectorListener<Face> detectorListener) {
        checkIfDetectorOperational(detection);

        detection.detect(imageBitmap);
        if (!detection.hasFoundItems()) {
            contract.showMessage("Nothing found");
        } else {
            detectorListener.onResultFound(detection.getResults());
        }
        detection.done();
    }

    void detectBarcodes(BarcodeDetectorWrapper detection, DetectorListener<Barcode> detectorListener) {
        checkIfDetectorOperational(detection);

        detection.detect(imageBitmap);
        if (!detection.hasFoundItems()) {
            contract.showMessage("Nothing found");
        } else {
            detectorListener.onResultFound(detection.getResults());
        }
        detection.done();
    }

    void detectText(TextDetectorWrapper detection, DetectorListener<TextBlock> detectorListener) {
        checkIfDetectorOperational(detection);

        detection.detect(imageBitmap);
        if (!detection.hasFoundItems()) {
            contract.showMessage("Nothing found");
        } else {
            detectorListener.onResultFound(detection.getResults());
        }
        detection.done();
    }

    private void checkIfDetectorOperational(DetectorWrapper detection) {
        if (!detection.operational()) {
            contract.showMessage("DetectorWrapper not available");
        }
    }

}
