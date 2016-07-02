package online.luhmirin.visionapiexperiment.preview.detector;


import android.graphics.Bitmap;
import android.util.SparseArray;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public abstract class DetectorWrapper<T> {

    Detector<T> detector;

    private SparseArray<T> foundItems;

    public boolean operational() {
        Timber.wtf("operational: %b", detector.isOperational());
        return detector.isOperational();
    }

    public void detect(Bitmap bitmap) {
        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        this.foundItems = detector.detect(frame);
    }

    public boolean hasFoundItems() {
        return foundItems.size() > 0;
    }

    public List<T> getResults() {
        List<T> results = new ArrayList<>(foundItems.size());
        for (int i = 0; i < foundItems.size(); i++) {
            results.add(foundItems.valueAt(i));
        }
        return results;
    }

    public void done() {
        detector.release();
    }
}
