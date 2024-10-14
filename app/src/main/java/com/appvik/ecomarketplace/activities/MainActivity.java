package com.appvik.ecomarketplace.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.appvik.ecomarketplace.R;
import com.appvik.ecomarketplace.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {
    private Fragment homeFragment; // Declare the Fragment
    private VideoView videoView; // Declare the VideoView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize the VideoView
//        videoView = findViewById(R.id.videoView);
//        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);
//        videoView.setVideoURI(videoUri);
//        videoView.start();

        // HomeFragment
            homeFragment = new HomeFragment(); // Initialize the HomeFragment
            loadFragment(homeFragment);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container, fragment);
        transaction.commit();
    }
}
