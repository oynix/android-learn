# CircleImageView圆形的ImageView

Android 系统默认的 ImageView 是矩形，但有时页面上需要展示圆形的图片，如头像。这里提供一种最简单的实现思路。

将圆形遮罩当作目标（DST），图片当作源（SRC）

```java
public class CircleImageView extends AppCompatImageView {

    private Paint mPaint;
    private Xfermode mXfermodeDstOut;
    private Path mPath;

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mXfermodeDstOut = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mPath == null) {
            int cx = getWidth() / 2;
            int cy = getHeight() / 2;
            mPath = new Path();
            mPath.moveTo(0, 0);
            mPath.lineTo(getWidth(), 0);
            mPath.lineTo(getWidth(), getHeight());
            mPath.lineTo(0, getHeight());
            mPath.addCircle(cx, cy, Math.min(cx, cy), Path.Direction.CCW);
        }
        canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint, Canvas.ALL_SAVE_FLAG);
        super.onDraw(canvas);
        mPaint.setXfermode(mXfermodeDstOut);
        canvas.drawPath(mPath, mPaint);
        mPaint.setXfermode(null);
        canvas.restore();
    }
}
```

源文件：[https://github.com/oynix/widgetlib/blob/master/widget/src/main/java/com/oynix/widget/CircleImageView.java](https://github.com/oynix/widgetlib/blob/master/widget/src/main/java/com/oynix/widget/CircleImageView.java)

