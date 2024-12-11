package com.example.truckingwellness;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomOutline extends androidx.appcompat.widget.AppCompatTextView {

    public CustomOutline(@NonNull Context context) {
        super(context);
    }

    public CustomOutline(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomOutline(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private Paint p = new Paint();
    private String mText;

    @Override
    protected void onDraw(Canvas canvas) {
        mText = this.getText().toString();


        p.setTextSize(this.getTextSize());
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeJoin(Paint.Join.ROUND);
        p.setStrokeWidth(3);
        canvas.drawText(mText,10,getLineHeight(),p);

        p.clearShadowLayer();
        p.setTextSize(this.getTextSize());
        p.setLetterSpacing(this.getLetterSpacing());
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeJoin(Paint.Join.ROUND);
        p.setStrokeWidth(3);
        p.setColor(Color.rgb(193,21,27));
        canvas.drawText(mText,10,getLineHeight(),p);

        p.setStyle(Paint.Style.FILL);
        p.setColor(this.getTextColors().getDefaultColor());
        canvas.drawText(mText,10,getLineHeight(),p);

    }


}