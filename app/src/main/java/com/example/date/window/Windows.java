package com.example.date.window;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.Window;

import com.example.date.view.DiagramActivity;

public class Windows {
    public void setWindow(Window window){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = window.getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            window.setNavigationBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

    }

}
