package com.bluehomestudio.luckywheeldemo;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.OnLuckyWheelReachTheTarget;
import com.bluehomestudio.luckywheel.WheelItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<WheelItem> wheelItems = new ArrayList<>();
        wheelItems.add(new WheelItem(Color.LTGRAY, BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_action_name)));
        wheelItems.add(new WheelItem(Color.BLUE, BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_action_name)));
        wheelItems.add(new WheelItem(Color.BLACK, BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_action_name)));
        wheelItems.add(new WheelItem(Color.GRAY, BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_action_name)));
        wheelItems.add(new WheelItem(Color.RED, BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_action_name)));
        wheelItems.add(new WheelItem(Color.GREEN, BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_action_name)));


        LuckyWheel lw = (LuckyWheel) findViewById(R.id.lwv);
        lw.addWheelItems(wheelItems);

        lw.rotateWheelTo(1);

        lw.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
            @Override
            public void onReachTarget() {
                Toast.makeText(MainActivity.this, "Target Reached", Toast.LENGTH_LONG).show();
            }
        });

    }
}
