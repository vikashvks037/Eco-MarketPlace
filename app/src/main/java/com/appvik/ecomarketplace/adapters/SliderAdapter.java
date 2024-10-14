package com.appvik.ecomarketplace.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.appvik.ecomarketplace.R;

public class SliderAdapter extends PagerAdapter {
    private final Context context;
    private final LayoutInflater layoutInflater;

    // Arrays for images, headings, and descriptions
    private final int[] imagesArray = {
            R.drawable.onboardscreen1,
            R.drawable.onboardscreen2,
            R.drawable.onboardscreen3
    };

    private final int[] headingArray = {
            R.string.first_slide,
            R.string.second_slide,
            R.string.third_slide
    };

    private final int[] descriptionArray = {
            R.string.description,
            R.string.description,
            R.string.description
    };

    public SliderAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context); // Initialize LayoutInflater
    }

    @Override
    public int getCount() {
        return headingArray.length; // Return the number of slides
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object; // Correct view comparison
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // Inflate the slide layout
        View view = layoutInflater.inflate(R.layout.sliding_layout, container, false);

        // Find the views in the inflated layout
        ImageView imageView = view.findViewById(R.id.slider_img);
        TextView heading = view.findViewById(R.id.heading);
        TextView description = view.findViewById(R.id.description);

        // Set the image and text based on the current position
        imageView.setImageResource(imagesArray[position]);
        heading.setText(headingArray[position]);
        description.setText(descriptionArray[position]);

        // Add the view to the container
        container.addView(view);
        return view; // Return the created view
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // Remove the view from the container
        container.removeView((View) object);
    }
}
