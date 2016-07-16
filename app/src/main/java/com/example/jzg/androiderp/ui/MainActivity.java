package com.example.jzg.androiderp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jzg.androiderp.Base.BaseActivity;
import com.example.jzg.androiderp.R;
import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.button6)
    Button button6;
    @BindView(R.id.button7)
    Button button7;
    @BindView(R.id.button8)
    Button button8;
    @BindView(R.id.button9)
    Button button9;
    @BindView(R.id.button10)
    Button button10;
    @BindView(R.id.button11)
    Button button11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        ButterKnife.bind(this);
        initCander();

    }

    private void initCander() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        CalendarPickerView calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        Date today = new Date();
        calendar.init(today, nextYear.getTime())
                .withSelectedDate(today);
    }


    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5,
            R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10
    ,R.id.button11})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                Intent intent = new Intent(MainActivity.this, LoadPicActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent1 = new Intent(MainActivity.this, CamaeraActivity.class);
                startActivity(intent1);
                break;
            case R.id.button3:
                Intent intent2 = new Intent(MainActivity.this, ChoosePicActivity.class);
                startActivity(intent2);
                break;
            case R.id.button4:
                Intent intent3 = new Intent(MainActivity.this, RxJavaActivity.class);
                startActivity(intent3);
                break;
            case R.id.button5:
                Intent intent4 = new Intent(MainActivity.this, PopActivity.class);
                startActivity(intent4);
                break;
            case R.id.button6:
                Intent intent5 = new Intent(MainActivity.this, HListViewActivity.class);
                startActivity(intent5);
                break;
            case R.id.button7:
                Intent intent6 = new Intent(MainActivity.this, CollapsingtoolbarActivity.class);
                startActivity(intent6);
                break;
            case R.id.button8:
                Intent intent7 = new Intent(MainActivity.this, RxBindingActivity.class);
                startActivity(intent7);
                break;
            case R.id.button9:
                Intent intent8 = new Intent(MainActivity.this, CoordinatorLayoutActivity.class);
                startActivity(intent8);
                break;
            case R.id.button10:
                Intent intent9 = new Intent(MainActivity.this, TableActivity.class);
                startActivity(intent9);
            case R.id.button11:
                Intent intent10 = new Intent(MainActivity.this, ToolBarActivity.class);
                startActivity(intent10);
                break;
        }
    }


//    @Override
//    @OnClick(R.id.button1)
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.button1:
//                Intent intent = new Intent(MainActivity.this,LoadPicActivity.class);
//                startActivity(intent);
//                break;
//            default:
//
//                break;
//        }
//    }
}
