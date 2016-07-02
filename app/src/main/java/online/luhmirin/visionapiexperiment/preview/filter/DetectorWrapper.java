package online.luhmirin.visionapiexperiment.preview.filter;


import android.graphics.Bitmap;
import android.util.SparseArray;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;

import timber.log.Timber;

public abstract class DetectorWrapper<T> {

    Detector<T> detector;

    public boolean operational() {
        Timber.wtf("operational: %b", detector.isOperational());
        return detector.isOperational();
    }

    public SparseArray<T> detect(Bitmap bitmap) {
        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        return detector.detect(frame);
    }

    public void done() {
        detector.release();
    }
}
