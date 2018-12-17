package com.bluehomestudio.luckywheeldemo;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.OnLuckyWheelReachTheTarget;
import com.bluehomestudio.luckywheel.WheelItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<WheelItem> wheelItems = new ArrayList<>();
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


        final LuckyWheel lw = (LuckyWheel) findViewById(R.id.lwv);
        lw.addWheelItems(wheelItems);
        lw.setWheelBackgoundColor(Color.RED);
        lw.rotateWheelTo(1);

        lw.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
            @Override
            public void onReachTarget() {
                Toast.makeText(MainActivity.this, "Target Reached", Toast.LENGTH_LONG).show();
            }
        });

        TextView durationValue = (TextView) findViewById(R.id.durationValue);
        final SeekBar durationBar = ((SeekBar) findViewById(R.id.duration_bar));
        durationBar.setOnSeekBarChangeListener(new SynchTextView(durationValue));

        TextView rotationValue = (TextView) findViewById(R.id.rotationValue);
        final SeekBar rotationBar = ((SeekBar) findViewById(R.id.rotations_bar));
        rotationBar.setOnSeekBarChangeListener(new SynchTextView(rotationValue));

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lw.setRotationTime(durationBar.getProgress());
                lw.setRotations(rotationBar.getProgress());
                lw.rotateWheelTo(new Random().nextInt(wheelItems.size()));
            }
        });
    }

    private static class SynchTextView implements SeekBar.OnSeekBarChangeListener {

        private final TextView textView;

        private SynchTextView(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            textView.setText(String.valueOf(seekBar.getProgress()));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // do nothing
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            textView.setText(String.valueOf(seekBar.getProgress()));
        }
    }

}
