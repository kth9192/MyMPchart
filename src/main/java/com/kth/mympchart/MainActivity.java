package com.kth.mympchart;

import android.annotation.SuppressLint;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.kth.mympchart.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry( 0, 4f));
        entries.add(new Entry( 1, 8f));
        entries.add(new Entry( 2, 6f));
        entries.add(new Entry( 3, 12f));
        entries.add(new Entry( 4, 16f));
        entries.add(new Entry(5, 19f));
        entries.add(new Entry( 6, 4f));
        entries.add(new Entry( 7, 8f));
        entries.add(new Entry( 8, 6f));
        entries.add(new Entry( 9, 12f));
        entries.add(new Entry( 10, 16f));
        entries.add(new Entry(11, 19f));
        entries.add(new Entry( 12, 4f));
        entries.add(new Entry( 13, 8f));
        entries.add(new Entry( 14, 6f));
        entries.add(new Entry( 15, 12f));
        entries.add(new Entry(16, 19f));
        entries.add(new Entry( 17, 4f));
        entries.add(new Entry( 18, 8f));
        entries.add(new Entry( 19, 6f));
        entries.add(new Entry( 20, 12f));

        ILineDataSet lineDataSet = new LineDataSet(entries, "test");
        ((LineDataSet) lineDataSet).setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setDrawFilled(true);
        ((LineDataSet) lineDataSet).setColor(Color.parseColor("#8BC34A"));
        ((LineDataSet) lineDataSet).setLineWidth(10);

        if (Utils.getSDKInt() >= 18) {
            // fill drawable only supported on api level 18 and above
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fill_chart);
            ((LineDataSet) lineDataSet).setFillDrawable(drawable);
        }
        else {
            ((LineDataSet) lineDataSet).setFillColor(Color.BLACK);
        }

        LineData data = new LineData(lineDataSet);
        activityMainBinding.chart.setData(data);
        activityMainBinding.chart.setVisibleXRangeMaximum(3); // 최대 x 범위

        //격자관련
        activityMainBinding.chart.setDrawGridBackground(false);
        activityMainBinding.chart.getAxisLeft().setDrawGridLines(false);
        activityMainBinding.chart.getAxisRight().setDrawGridLines(false);
        activityMainBinding.chart.getXAxis().setDrawGridLines(false);

        //우측
        activityMainBinding.chart.getAxisRight().setDrawAxisLine(false);
        activityMainBinding.chart.getAxisRight().setDrawLabels(false);

        //상단
        activityMainBinding.chart.getXAxis().setDrawAxisLine(false);
        activityMainBinding.chart.getXAxis().setDrawLabels(false);

        XAxis xAxis = activityMainBinding.chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);

        activityMainBinding.chart.setDrawBorders(false);

        activityMainBinding.chart.animateY(2000, Easing.EaseOutBounce);
        activityMainBinding.chart.invalidate();
    }
}
