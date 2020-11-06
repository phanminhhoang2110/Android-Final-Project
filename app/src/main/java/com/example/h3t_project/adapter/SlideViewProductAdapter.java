package com.example.h3t_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.h3t_project.R;

public class SlideViewProductAdapter extends PagerAdapter {

  private Context context;
  LayoutInflater inflater;
  public int[] images = {
    R.drawable.slide1,
    R.drawable.slide2,
    R.drawable.slide3
  };

  public SlideViewProductAdapter(Context context) {
    this.context = context;
  }

  @Override
  public int getCount() {
    return images.length;
  }

  @Override
  public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
    return (view == (LinearLayout) object);
  }

  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, int position) {
    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View view = inflater.inflate(R.layout.slideshow_layout, container, false);
    ImageView imageView = (ImageView) view.findViewById(R.id.imageViewProduct);
    imageView.setImageResource(images[position]);
    container.addView(view);
    return view;
  }

  @Override
  public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    container.removeView((LinearLayout)object);
  }
}
