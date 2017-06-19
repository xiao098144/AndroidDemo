/*
 * Copyright 2014 trinea.cn All right reserved. This software is the confidential and proprietary information of
 * trinea.cn ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with trinea.cn.
 */
package com.xiao.demo.recyclerview.widget.autoscrollviewpager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * ImagePagerAdapter
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2014-2-23
 */
public class ImagePagerAdapter extends RecyclingPagerAdapter
{

	private Context context;

	private ArrayList<Drawable> imgList;

	private int size;

	private boolean isInfiniteLoop;

	public ImagePagerAdapter(Context context, ArrayList<Drawable> imgList)
	{
		this.context = context;
		this.imgList = imgList;
		this.size = imgList == null ? 0 : imgList.size();

		isInfiniteLoop = true;
	}

	@Override
	public int getCount()
	{
		// Infinite loop
		return isInfiniteLoop ? Integer.MAX_VALUE : size;
	}

	/**
	 * get really position
	 * 
	 * @param position
	 * @return
	 */
	private int getPosition(int position)
	{
		return isInfiniteLoop ? position % size : position;
	}

	@Override
	public View getView( final int position, View view, ViewGroup container)
	{
		ViewHolder holder;
		if (view == null)
		{
			holder = new ViewHolder();
			holder.imageView = new ImageView(context);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			holder.imageView.setLayoutParams(lp);
			holder.imageView.setScaleType(ScaleType.FIT_XY);
			view = holder.imageView;
			view.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) view.getTag();
		}

		try {
			holder.imageView.setImageDrawable(imgList.get(getPosition(position)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return view;
	}

	private static class ViewHolder
	{

		ImageView imageView;
	}

	/**
	 * @return the isInfiniteLoop
	 */
	public boolean isInfiniteLoop()
	{
		return isInfiniteLoop;
	}

	/**
	 * @param isInfiniteLoop
	 *          the isInfiniteLoop to set
	 */
	public ImagePagerAdapter setInfiniteLoop(boolean isInfiniteLoop)
	{
		this.isInfiniteLoop = isInfiniteLoop;
		return this;
	}
}
