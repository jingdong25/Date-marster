package com.example.date.view;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.example.date.R;
import com.example.date.widget.TestLineChartView;
import com.example.date.window.Windows;

import java.util.ArrayList;
import java.util.List;

public class DiagramActivity extends AppCompatActivity {

    private TestLineChartView testLineChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagram);
        Windows windows = new Windows();
        windows.setWindow(getWindow());
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        testLineChartView = findViewById(R.id.testLineChartView);
        List<Double> values = new ArrayList<>();
        values.add(new Double("0"));
        values.add(new Double("20"));
        values.add(new Double("40"));
        values.add(new Double("60"));
        values.add(new Double("80"));
        values.add(new Double("100"));
        List<String> list = new ArrayList<>();
        list.add(new String("1月"));
        list.add(new String("2月"));
        list.add(new String("3月"));
        list.add(new String("43月"));
        list.add(new String("54月"));
        list.add(new String("5月"));
        testLineChartView.setData(values,list,false);
        testLineChartView.setTextSize(18,18,18,18);
    }

}
