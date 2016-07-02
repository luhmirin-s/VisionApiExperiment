package online.luhmirin.visionapiexperiment.preview;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import timber.log.Timber;

class PreviewPresenter {

    private PreviewContract contract;

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
            contract.setPreviewImage(imageBitmap);
            contract.enableFilterButtons();
        }
    }

    void imageNotReceived() {
        contract.showMessage("Not able to import message");
        contract.disableFilterButtons();
    }
}
