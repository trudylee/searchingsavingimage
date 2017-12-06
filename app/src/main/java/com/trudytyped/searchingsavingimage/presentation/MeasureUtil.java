package com.trudytyped.searchingsavingimage.presentation;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class MeasureUtil {

    public static int getGridWidth(View itemView, int gridCount) {
        WindowManager wm = (WindowManager) itemView.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point displaySize = new Point();
        display.getSize(displaySize);
        int displayWidth = displaySize.x;

        return displayWidth / gridCount;
    }

}
