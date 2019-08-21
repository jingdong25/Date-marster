package com.example.date.view;
import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.date.R;
import com.example.date.util.DateFormats;
import com.example.date.widget.CalendarView;
import com.example.date.window.Windows;
import com.loonggg.bottomsheetpopupdialoglib.ShareBottomPopupDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = MainActivity.class.getSimpleName();
    private CalendarView mCalendarView;
    private TextView mTxtDate;
    private PopupWindow popupWindow1;
    private View dialogView;
    private TextView textView;
    private RadioGroup select_group;
    private ShareBottomPopupDialog shareBottomPopupDialog;
    private RadioButton radio_morning;
    private RadioButton radio_afternoon;
    private RadioButton radio_allday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置实时更新
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Windows windows = new Windows();
        windows.setWindow(getWindow());
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mTxtDate = (TextView) findViewById(R.id.txt_date);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        // 设置已选的日期
        mCalendarView.setSelectDate(initData());
        // 定指显示的日期, 如当前月的下个月
        Calendar calendar = mCalendarView.getCalendar();
//        DateUtils dateUtils = new DateUtils();
        calendar.add(Calendar.MONTH, 0);
        mCalendarView.setCalendar(calendar);
        // 设置字体
        mCalendarView.setTypeface(Typeface.SERIF);
        initDialog();
       initLinsenters();
        // 设置是否能够改变日期状态
        mCalendarView.setChangeDateStatus(true);
        // 设置是否能够点击
        mCalendarView.setClickable(true);
        setCurDate();
    }
    private void initLinsenters() {
        //设置日期状态改变监听
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, boolean select, int year, int month, int day) {
                if (select == true) {
                    shareBottomPopupDialog.showPopup(dialogView);
                }
            }
        });

        // 设置日期点击监听
        mCalendarView.setOnDataClickListener(new CalendarView.OnDataClickListener() {

            @Override
            public void onDataClick(@NonNull CalendarView view, int year, int month, int day) {
                int a = month+1;
                String month1 = String.valueOf(a);
                String day1 = String.valueOf(day);
                String year1 = String.valueOf(year);
                String d = year1+"-"+month1+"-"+day1;
                DateFormats dateFormats = new DateFormats();
                String s = dateFormats.setDate(d);
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                try {
//                    Date parse = sdf.parse(d);
//                    String format = sdf.format(parse);
//                    String[] split = format.split("-");
//                    String s1 = split[0];
//                    String s2 = split[1];
//                    String s3 = split[2];
//                    sum = s1+s2+s3;
//                    Log.e("sd", sum);
//                    Log.e("tagdateNowStr",format);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
                textView.setText("您选择的时间是：" + year + "年" + (month + 1) + "月" + day + "日");
                initLinsenter(s);
            }
        });

    }

    private void initDialog() {
        dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.alert_selectday, null);
        initView();
        shareBottomPopupDialog = new ShareBottomPopupDialog(MainActivity.this, dialogView);
    }

    @SuppressLint("ResourceAsColor")
    private void initView() {
        textView = dialogView.findViewById(R.id.text_date);
        select_group = dialogView.findViewById(R.id.select_group);
        radio_afternoon = dialogView.findViewById(R.id.radio_afternoon);
        radio_allday = dialogView.findViewById(R.id.radio_allday);
        radio_morning = dialogView.findViewById(R.id.radio_morning);

    }
    private void initLinsenter(final String s) {
        select_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_morning:
                       initStauts(0);
                        break;
                    case R.id.radio_allday:
                       initStauts(1);
                        break;
                    case R.id.radio_afternoon:
                       initStauts(2);
                        break;
                }
            }
            @SuppressLint("ResourceAsColor")
            private void initStauts(int a) {
                mCalendarView.setCheckedStatus(a,s);
                mCalendarView.invalidate();
                shareBottomPopupDialog.dismiss();
                switch (a){
                    case 0:radio_morning.setChecked(false);break;
                    case 1:radio_allday.setChecked(false);break;
                    case 2:radio_afternoon.setChecked(false);break;
                }

            }
        });
    }
    private List<String> initData() {
        List<String> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        SimpleDateFormat sdf = new SimpleDateFormat(mCalendarView.getDateFormatPattern(), Locale.CHINA);
        String format = sdf.format(calendar.getTime());
        Log.e("tag",format+"");
        dates.add(format);
        return dates;
    }

    public void next(View v){
        mCalendarView.nextMonth();
        setCurDate();
    }

    public void last(View v){
        mCalendarView.lastMonth();
        setCurDate();
    }

    private void setCurDate(){
        mTxtDate.setText(mCalendarView.getYear() + "年" + (mCalendarView.getMonth() + 1) + "月");
    }
}
