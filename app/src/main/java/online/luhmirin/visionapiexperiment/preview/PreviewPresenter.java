package online.luhmirin.visionapiexperiment.preview;

import timber.log.Timber;

class PreviewPresenter {

    private PreviewContract contract;

    PreviewPresenter(PreviewContract contract) {
        this.contract = contract;
    }

    void openCameraClicked() {
        Timber.wtf("open camera");
        contract.showMessage("Camera opening");
    }

    void openGaleryClicked() {
        Timber.wtf("open galery");
        contract.showMessage("Galery opening");
    }


}
