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
import com.example.h3t_project.model.Product;

import java.util.List;

public class SlideViewProductAdapter extends PagerAdapter {

  public List<Product> products;
  LayoutInflater inflater;
  private final Context context;


  public SlideViewProductAdapter(Context context, List<Product> products) {
    this.context = context;
    this.products = products;
  }

  @Override
  public int getCount() {
    return products.size();
  }

  @Override
  public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
    return (view == object);
  }

  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, int position) {
    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View view = inflater.inflate(R.layout.slideshow_layout, container, false);
    int imageA = products.get(0).getImage_id();
    int imageB = products.get(1).getImage_id();
    int imageC = products.get(2).getImage_id();
    int[] images = {imageA, imageB, imageC};
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
