package com.example.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView stopwatchTextView;
    private Button startButton;
    private Button stopButton;
    private Button resetButton;

    private boolean isRunning = false;
    private int seconds = 0;
    private Handler handler = new Handler();
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stopwatchTextView = findViewById(R.id.stopwatchTextView);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        resetButton = findViewById(R.id.resetButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStopwatch();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopStopwatch();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetStopwatch();
            }
        });
    }

    private void startStopwatch() {
        if (!isRunning) {
            isRunning = true;
            startButton.setEnabled(false);
            stopButton.setEnabled(true);

            runnable = new Runnable() {
                @Override
                public void run() {
                    seconds++;
                    updateStopwatchTextView();
                    handler.postDelayed(this, 1000);
                }
            };
            handler.postDelayed(runnable, 1000);
        }
    }

    private void stopStopwatch() {
        if (isRunning) {
            isRunning = false;
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            handler.removeCallbacks(runnable);
        }
    }

    private void resetStopwatch() {
        isRunning = false;
        seconds = 0;
        updateStopwatchTextView();
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        handler.removeCallbacks(runnable);
    }

    private void updateStopwatchTextView() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;

        String time = String.format("%02d:%02d:%02d", hours, minutes, secs);
        stopwatchTextView.setText(time);
    }
}
