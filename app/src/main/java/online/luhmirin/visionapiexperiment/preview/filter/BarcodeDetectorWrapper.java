package online.luhmirin.visionapiexperiment.preview.filter;

import android.content.Context;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class BarcodeDetectorWrapper extends DetectorWrapper<Barcode> {

    public BarcodeDetectorWrapper(Context context) {
        this.detector = new BarcodeDetector.Builder(context).build();
    }
}
