package com.hencoder.hencoderpracticedraw3.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Practice14GetFontMetricsView extends View {
    Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    String[] texts = {"A", "a", "J", "j", "Â", "â"};
    int top = 200;
    int bottom = 400;
    float deff =0;

    public Practice14GetFontMetricsView(Context context) {
        super(context);
    }

    public Practice14GetFontMetricsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice14GetFontMetricsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(20);
        paint1.setColor(Color.parseColor("#E91E63"));
        paint2.setTextSize(160);

        /*  ascent / descent: 上图中绿色和橙色的线，它们的作用是限制普通字符的顶部和底部范围。
            普通的字符，上不会高过 ascent ，下不会低过 descent ，
            例如上图中大部分的字形都显示在 ascent 和 descent 两条线的范围内。
            具体到 Android 的绘制中， ascent 的值是图中绿线和 baseline 的相对位移，它的值为负（因为它在 baseline 的上方）；
            descent 的值是图中橙线和 baseline 相对位移，值为正（因为它在 baseline 的下方）。*/
        Paint.FontMetrics fontMetrics = paint2.getFontMetrics();
        deff = -(fontMetrics.descent + fontMetrics.ascent) / 2;
        Log.d("DEFF","Deff: " + deff);//54.6875
        Log.d("DEFF","fontMetrics.descent: " + fontMetrics.descent);//39.0625
        Log.d("DEFF","fontMetrics.ascent: " + fontMetrics.ascent);// -148.4375
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(50, top, getWidth() - 50, bottom, paint1);

        // 使用 Paint.getFontMetrics() 计算出文字的显示区域
        // 然后计算出文字的绘制位置，从而让文字上下居中
        // 这种居中算法的优点是，可以让不同的文字的 baseline 对齐

        int middle = (top + bottom) / 2;
        canvas.drawText(texts[0], 100, middle + deff, paint2);
        canvas.drawText(texts[1], 200, middle + deff, paint2);
        canvas.drawText(texts[2], 300, middle + deff, paint2);
        canvas.drawText(texts[3], 400, middle + deff, paint2);
        canvas.drawText(texts[4], 500, middle + deff, paint2);
        canvas.drawText(texts[5], 600, middle + deff, paint2);
    }
}
