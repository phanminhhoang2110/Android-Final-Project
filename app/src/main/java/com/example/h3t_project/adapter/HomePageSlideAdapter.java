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

public class HomePageSlideAdapter extends PagerAdapter {

  public int[] images = {
    R.drawable.banner1,
    R.drawable.banner2
  };
  LayoutInflater inflater;
  private final Context context;

  public HomePageSlideAdapter(Context context) {
    this.context = context;
  }

  @Override
  public int getCount() {
    return images.length;
  }

  @Override
  public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
    return (view == object);
  }

  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, int position) {
    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View view = inflater.inflate(R.layout.home_page_slideshow_layout, container, false);
    ImageView imageView = view.findViewById(R.id.imageViewProduct);
    imageView.setImageResource(images[position]);
    container.addView(view);
    return view;
  }

  @Override
  public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    container.removeView((LinearLayout) object);
  }
}
