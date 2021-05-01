package com.example.recordandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    MediaRecorder mediaRecorder;

    public static String fileName = "recorded.3gp";

    String file = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);

        mediaRecorder.setOutputFile(file);

    }


    public void onClick(View v) {


        if (v.getId() == R.id.btnRecord) {

            record();


        } else if (v.getId() == R.id.btnStop) {

            StopAudio();

        } else if (v.getId() == R.id.btnPlay) {

            play();

        }
    }

    private void play() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(file);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        textView.setText("Playing Recorded Audio .......");
    }

    private void StopAudio() {
        mediaRecorder.stop();
        mediaRecorder.release();
        textView.setText("Recording Stopped ......");

    }

    private void record() {
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        textView.setText("Audio Recording .......");
    }
}