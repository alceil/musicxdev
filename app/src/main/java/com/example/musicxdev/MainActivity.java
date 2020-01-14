package com.example.musicxdev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer musicplayer;
    AudioManager audioManager;
    public void play(View view)
    {
        musicplayer.start();
    }
    public void pause(View view)
    {
        musicplayer.pause();
    }
    public void stop(View view)
    {
        musicplayer.stop();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicplayer = MediaPlayer.create(this,R.raw.music);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVol =audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVol =audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        SeekBar  seekvol = findViewById(R.id.seekvol);
        seekvol.setMax(maxVol);
        seekvol.setProgress(curVol);

        seekvol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final SeekBar seekProg = findViewById(R.id.seekProg);
        seekProg.setMax(musicplayer.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekProg.setProgress(musicplayer.getCurrentPosition());
            }
        },0,900);
        seekProg.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                musicplayer.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });