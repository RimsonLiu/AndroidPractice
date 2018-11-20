package com.rimson.c.inflatepractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View parentView = LayoutInflater.from(this).inflate(R.layout.parent, null);
        View view = View.inflate(this, R.layout.parent, null);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) parentView.getLayoutParams();
        if (layoutParams != null) {
            Log.d(TAG, "before:" + layoutParams.width);
            Log.d(TAG, "before:" + layoutParams.height);
        }
        parentView = LayoutInflater.from(this).inflate(R.layout.child, (ViewGroup) parentView, false);
        layoutParams = (LinearLayout.LayoutParams) parentView.getLayoutParams();
        if (layoutParams != null) {
            Log.d(TAG, "after:" + layoutParams.width);
            Log.d(TAG, "after:" + layoutParams.height);
        }
        setContentView(parentView);

    }
}
