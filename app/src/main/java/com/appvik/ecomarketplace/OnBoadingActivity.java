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

    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private Button btn, nextBtn; // Removed backBtn
    private SliderAdapter sliderAdapter;
    private TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boading);

        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        btn = findViewById(R.id.get_started_btn);
        nextBtn = findViewById(R.id.next_btn);

        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDots(0);

        btn.setOnClickListener(view -> {
            startActivity(new Intent(OnBoadingActivity.this, LoginActivity.class));
            finish();
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                addDots(position);
                btn.setVisibility(position == sliderAdapter.getCount() - 1 ? View.VISIBLE : View.INVISIBLE);
                // Removed backBtn visibility control since back button no longer exists
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
    }

    private void addDots(int position) {
        int count = sliderAdapter.getCount();
        dots = new TextView[count];
        dotsLayout.removeAllViews();

        for (int i = 0; i < count; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;")); // Bullet point
            dots[i].setTextSize(40);
            dots[i].setPadding(8, 0, 8, 0);
            dotsLayout.addView(dots[i]);

            // Set click listener on each dot
            final int index = i; // Store index for the click listener
            dots[i].setOnClickListener(v -> viewPager.setCurrentItem(index)); // Move to the clicked page
        }

        // Highlight the active dot
        if (dots.length > 0) {
            for (int i = 0; i < dots.length; i++) {
                dots[i].setTextColor(i == position ? getResources().getColor(R.color.black) : getResources().getColor(R.color.teal_200));
            }
        }
    }
}
