package com.appvik.ecomarketplace;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class OnBoadingActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;
    Button btn, nextBtn, backBtn;
    SliderAdapter sliderAdapter;
    TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boading);

        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        btn = findViewById(R.id.get_started_btn);
        nextBtn = findViewById(R.id.next_btn);
        backBtn = findViewById(R.id.back_btn);
        addDots(0);

        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoadingActivity.this,RegistrationActivity.class));
                finish();
            }
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                addDots(position);
                btn.setVisibility(position == sliderAdapter.getCount() - 1 ? View.VISIBLE : View.INVISIBLE);
                backBtn.setVisibility(position == 0 ? View.INVISIBLE : View.VISIBLE); // Hide back button on first page
                nextBtn.setVisibility(position == sliderAdapter.getCount() - 1 ? View.INVISIBLE : View.VISIBLE); // Hide next button on last page
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        // Set click listener for the next button
        nextBtn.setOnClickListener(v -> {
            int currentItem = viewPager.getCurrentItem();
            if (currentItem < sliderAdapter.getCount() - 1) {
                viewPager.setCurrentItem(currentItem + 1); // Move to the next page
            }
        });

        // Set click listener for the back button
        backBtn.setOnClickListener(v -> {
            int currentItem = viewPager.getCurrentItem();
            if (currentItem > 0) {
                viewPager.setCurrentItem(currentItem - 1); // Move to the previous page
            }
        });
    }

    private void addDots(int position) {
        int count = sliderAdapter.getCount();
        dots = new TextView[count];
        dotsLayout.removeAllViews();

        for (int i = 0; i < count; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(40); // Set the desired size for dots
            dots[i].setPadding(8, 0, 8, 0); // Optional: add padding for spacing
            dotsLayout.addView(dots[i]);

            // Set a click listener for each dot
            final int index = i; // Final variable for inner class
            dots[i].setOnClickListener(v -> {
                viewPager.setCurrentItem(index); // Change page on dot click
            });
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.blue));
        }
    }
}
