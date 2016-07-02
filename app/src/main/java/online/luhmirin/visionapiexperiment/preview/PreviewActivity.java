package online.luhmirin.visionapiexperiment.preview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.text.TextBlock;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import online.luhmirin.visionapiexperiment.R;
import online.luhmirin.visionapiexperiment.common.IntentUtils;
import online.luhmirin.visionapiexperiment.preview.detector.BarcodeDetectorWrapper;
import online.luhmirin.visionapiexperiment.preview.detector.FaceDetectorWrapper;
import online.luhmirin.visionapiexperiment.preview.detector.TextDetectorWrapper;
import timber.log.Timber;

public class PreviewActivity extends AppCompatActivity implements PreviewContract {

    private static final int REQUEST_IMAGE_CAPTURE = 241;
    private static final int REQUEST_IMAGE_GALERY = 513;

    @BindView(R.id.preview_root)
    View root;

    @BindView(R.id.preview_image)
    ImageView imagePreview;

    @BindViews({
            R.id.preview_detect_barcodes,
            R.id.preview_detect_faces,
            R.id.preview_detect_text
    })
    List<Button> filterButtons;

    private PreviewPresenter presenter;


    private static final ButterKnife.Setter<View, Boolean> ENABLED = (view, value, index) -> view.setEnabled(value);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_activity);
        ButterKnife.bind(this);

        presenter = new PreviewPresenter(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
            switch (requestCode) {
                case REQUEST_IMAGE_CAPTURE:
                    presenter.imageReceived(IntentUtils.getBitmapFromCameraIntent(data));
                    break;
                case REQUEST_IMAGE_GALERY:
                    presenter.imageReceived(IntentUtils.getBitmapFromGalery(this, data));
                    break;
                default:
                    presenter.imageNotReceived();
                    break;
            }
    }

    // butterknife methods

    @OnClick(R.id.preview_sources_camera)
    protected void openCameraClicked() {
        presenter.openCameraClicked();
    }

    @OnClick(R.id.preview_sources_galery)
    protected void openGaleryClicked() {
        presenter.openGaleryClicked();
    }

    @OnClick(R.id.preview_detect_faces)
    protected void detectFacesClicked() {
        presenter.detectFaces(new FaceDetectorWrapper(this));
    }

    @OnClick(R.id.preview_detect_barcodes)
    protected void detectBarcodesClicked() {
        presenter.detectBarcodes(new BarcodeDetectorWrapper(this));
    }

    @OnClick(R.id.preview_detect_text)
    protected void detectTextClicked() {
        presenter.detectText(new TextDetectorWrapper(this));
    }

    // Contract methods

    @Override
    public void showMessage(String message) {
        Timber.wtf("Show message: %s", message);
        Snackbar.make(root, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void startCamera() {
        IntentUtils.startCameraIntent(this, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    public void startGalery() {
        IntentUtils.startGaleryIntent(this, REQUEST_IMAGE_GALERY);
    }

    @Override
    public void setPreviewImage(Bitmap imageBitmap) {
        imagePreview.setImageBitmap(imageBitmap);
    }

    @Override
    public void enableFilterButtons() {
        ButterKnife.apply(filterButtons, ENABLED, true);
    }

    @Override
    public void disableFilterButtons() {
        ButterKnife.apply(filterButtons, ENABLED, false);
    }

    @Override
    public void foundFace(Face face) {
        Timber.wtf("==== Face ====");
        Timber.wtf("position %s", face.getPosition().toString());
        Timber.wtf("height %f", face.getHeight());
        Timber.wtf("width %f", face.getWidth());
        Timber.d("-----");
        Timber.wtf("smiling %f", face.getIsSmilingProbability());
        Timber.wtf("left open %f", face.getIsLeftEyeOpenProbability());
        Timber.wtf("right open %f", face.getIsRightEyeOpenProbability());
        Timber.d("-----");
        Timber.wtf("euler Y: %f", face.getEulerY());
        Timber.wtf("euler Z: %f", face.getEulerY());
        Timber.wtf("========");
    }

    @Override
    public void foundBarcode(Barcode barcode) {
        Timber.wtf("==== Barcode ====");
        Timber.wtf("box %s", barcode.getBoundingBox().toString());
        Timber.wtf("format %d", barcode.format);
        Timber.wtf("value %s", barcode.rawValue);
        Timber.wtf("========");
    }

    @Override
    public void foundText(TextBlock textBlock) {
        Timber.wtf("==== Text ====");
        Timber.wtf("position %s", textBlock.getBoundingBox().toString());
        Timber.wtf("value %s", textBlock.getValue());
        Timber.wtf("========");
    }
}
