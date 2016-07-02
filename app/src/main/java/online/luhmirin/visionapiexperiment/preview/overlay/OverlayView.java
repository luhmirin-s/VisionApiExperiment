package online.luhmirin.visionapiexperiment.preview.overlay;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class OverlayView extends View {

    private final Paint paint;

    private float scale;

    private float widthMargin = 0f;
    private float heightMargin = 0f;

    private List<OverlayBox> boxes;

    public OverlayView(Context context) {
        this(context, null);
    }

    public OverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        boxes = new ArrayList<>();

        paint = new Paint();
        paint.setColor(Color.parseColor("#FFFF5722"));
        paint.setStrokeWidth(2f);
        paint.setStyle(Paint.Style.STROKE);

        setOnTouchListener((view, motionEvent) -> {
            float x = motionEvent.getX();
            float y = motionEvent.getY();

            for (OverlayBox box : boxes) {
                if (box.contains(x, y)) {
                    box.getListener().onClick();
                    return true;
                }
            }
            return false;
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);

        for (OverlayBox box : boxes) {
            canvas.drawRect(box.getRectF(), paint);
        }

        super.onDraw(canvas);
    }

    public void setBitmap(Bitmap imageBitmap) {
        float viewHeight = getHeight();
        float viewWidth = getWidth();
        float bitmapHeight = imageBitmap.getHeight();
        float bitmapWidth = imageBitmap.getWidth();

        scale = Math.min(
                viewHeight / bitmapHeight,
                viewWidth / bitmapWidth
        );

        // spaces from sides
        if (bitmapHeight > bitmapWidth) {
            widthMargin = (viewWidth - scale(bitmapWidth)) / 2;
        }
        // spaces from top and bottom
        if (bitmapWidth > bitmapHeight) {
            heightMargin = (viewHeight - scale(bitmapHeight)) / 2;
        }

        boxes.clear();
        invalidate();
    }

    public void addBox(Rect box, OverlayClickListener onClickListener) {
        RectF newBox = new RectF(
                scale(box.left) + widthMargin,
                scale(box.top) + heightMargin,
                scale(box.right) + widthMargin,
                scale(box.bottom) + heightMargin
        );
        boxes.add(new OverlayBox(newBox, onClickListener));
        invalidate();
    }

    private float scale(float v) {
        return v * scale;
    }


}
