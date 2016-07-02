package online.luhmirin.visionapiexperiment.preview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import online.luhmirin.visionapiexperiment.R;
import online.luhmirin.visionapiexperiment.common.IntentUtils;
import timber.log.Timber;

public class PreviewActivity extends AppCompatActivity implements PreviewContract {

    private static final int REQUEST_IMAGE_CAPTURE = 241;
    private static final int REQUEST_IMAGE_GALERY = 513;

    @BindView(R.id.preview_root)
    View root;

    @BindView(R.id.preview_image)
    ImageView imagePreview;

    @BindViews({R.id.preview_filters_barcodes, R.id.preview_filters_faces, R.id.preview_filters_text})
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

    @OnClick(R.id.preview_filters_faces)
    protected void filterFacesClicked() {
    }

    @OnClick(R.id.preview_filters_barcodes)
    protected void filterBarcodesClicked() {
    }

    @OnClick(R.id.preview_filters_text)
    protected void filterTextClicked() {
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

}
