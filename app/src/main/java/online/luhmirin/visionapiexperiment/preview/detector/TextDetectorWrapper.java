package online.luhmirin.visionapiexperiment.preview.detector;

import android.content.Context;

import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class TextDetectorWrapper extends DetectorWrapper<TextBlock> {

    public TextDetectorWrapper(Context context) {
        this.detector = new TextRecognizer.Builder(context).build();
    }
}
