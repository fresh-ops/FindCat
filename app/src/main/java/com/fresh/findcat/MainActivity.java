package com.fresh.findcat;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView seekField;
    private TextView hint;
    private float catX;
    private float catY;
    private final int catSize = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hint = findViewById(R.id.hint);
        seekField = findViewById(R.id.seekField);
        seekField.setOnTouchListener(listener);
        Random random = new Random();
        seekField.post(new Runnable() {
            @Override
            public void run() {
                catX = random.nextInt(seekField.getWidth());
                catY = random.nextInt(seekField.getHeight());
            }
        });
    }

    private void checkCatFound(float x, float y) {
        if (Math.abs(catX - x) <= catSize && Math.abs(catY - y) <= catSize) {
            hint.setText(R.string.cat_found);
            showCat();
        }
    }

    private void showCat() {
        Toast toast = Toast.makeText(this, "", Toast.LENGTH_LONG);
        int x = (int) (catX + seekField.getLeft() - 65);
        int y = (int) (catY + seekField.getTop() - 65);
        toast.setGravity(Gravity.START|Gravity.TOP, x, y);
        toast.show();
    }

    private final View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                checkCatFound(event.getX(), event.getY());
            }
            return false;
        }
    };
}