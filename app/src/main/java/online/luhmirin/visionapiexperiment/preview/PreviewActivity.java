package online.luhmirin.visionapiexperiment.preview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import online.luhmirin.visionapiexperiment.R;
import online.luhmirin.visionapiexperiment.common.IntentUtils;
import timber.log.Timber;

public class PreviewActivity extends AppCompatActivity implements PreviewContract {

    private static final int REQUEST_IMAGE_CAPTURE = 241;
    private static final int REQUEST_IMAGE_GALERY = 513;


    @BindView(R.id.preview_root)
    protected View root;

    @BindView(R.id.preview_image)
    protected ImageView imagePreview;

    private PreviewPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_activity);
        ButterKnife.bind(this);

        presenter = new PreviewPresenter(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
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
}
