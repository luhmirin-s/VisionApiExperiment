package online.luhmirin.visionapiexperiment.preview.filter;


import android.content.Context;

import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

public class FaceDetectorWrapper extends DetectorWrapper<Face> {

    public FaceDetectorWrapper(Context context) {
        this.detector = new FaceDetector.Builder(context)
                .setTrackingEnabled(true)
                .build();
    }

}
