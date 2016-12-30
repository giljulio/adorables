package com.giljulio.adorables.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.widget.ImageView;

public class ColorUtils {

    @ColorInt
    public static int extractColor(ImageView src) {
        // experimented using palette... but it was unsuccessful as it was returning
        // colors near to the thumbnail background but not the exact color.
        // This is also much faster O(1)!
        Bitmap bitmap = ((BitmapDrawable) src.getDrawable()).getBitmap();
        return bitmap.getPixel(0, 0);
    }

    /**
     * Returns darker version of specified <code>color</code>.
     */
    public static @CheckResult @ColorInt int darker(@ColorInt int color, float factor) {
        int a = Color.alpha(color);
        int r = Color.red( color );
        int g = Color.green( color );
        int b = Color.blue( color );

        return Color.argb( a,
                Math.max( (int)(r * factor), 0 ),
                Math.max( (int)(g * factor), 0 ),
                Math.max( (int)(b * factor), 0 ) );
    }

    /**
     * Set the alpha component of {@code color} to be {@code alpha}.
     */
     public static @CheckResult @ColorInt int modifyAlpha(@ColorInt int color,
                                                          @IntRange(from = 0, to = 255) int alpha) {
        return (color & 0x00ffffff) | (alpha << 24);
    }

    /**
     * Set the alpha component of {@code color} to be {@code alpha}.
     */
    public static @CheckResult @ColorInt int modifyAlpha(@ColorInt int color,
                                                         @FloatRange(from = 0f, to = 1f) float alpha) {
        return modifyAlpha(color, (int) (255f * alpha));
    }
}
