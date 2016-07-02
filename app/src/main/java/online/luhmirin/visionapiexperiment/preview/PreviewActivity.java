package online.luhmirin.visionapiexperiment.preview;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import online.luhmirin.visionapiexperiment.R;
import timber.log.Timber;

public class PreviewActivity extends AppCompatActivity implements PreviewContract {

    @BindView(R.id.preview_root)
    protected View root;

    private PreviewPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_activity);
        ButterKnife.bind(this);

        presenter = new PreviewPresenter(this);
    }

    @OnClick(R.id.preview_sources_camera)
    protected void openCameraClicked() {
        presenter.openCameraClicked();
    }

    @OnClick(R.id.preview_sources_galery)
    protected void openGaleryClicked() {
        presenter.openGaleryClicked();
    }


    @Override
    public void showMessage(String message) {
        Timber.wtf("Show message: %s", message);
        Snackbar.make(root, message, Snackbar.LENGTH_SHORT).show();
    }
}
