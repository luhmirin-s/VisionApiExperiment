package online.luhmirin.visionapiexperiment.preview.overlay;


import android.graphics.RectF;

public class OverlayBox {

    private RectF box;
    private OverlayClickListener listener;

    public OverlayBox(RectF box, OverlayClickListener listener) {
        this.box = box;
        this.listener = listener;
    }

    public RectF getRectF() {
        return box;
    }

    public OverlayClickListener getListener() {
        return listener;
    }

    public boolean contains(float x, float y) {
        return box.contains(x, y);
    }
}
