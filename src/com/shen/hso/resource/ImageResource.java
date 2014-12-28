package com.shen.hso.resource;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.shen.hso.R;

public class ImageResource 
{
	private List<Bitmap> bitmapResource;
	private static ImageResource imageResource;
	
	private static int fore[];
	private static int back[];
	
	public ImageResource()
	{
		bitmapResource = new ArrayList<Bitmap>();
		fore = new int[]{R.drawable.a0, R.drawable.a1, R.drawable.a2,
				R.drawable.a3, R.drawable.a4, R.drawable.a5,
				R.drawable.a6, R.drawable.a7, R.drawable.a8,
				R.drawable.a9, R.drawable.a910, R.drawable.a911,
				R.drawable.a912, R.drawable.a913, R.drawable.a914,R.drawable.a0, R.drawable.a1, R.drawable.a2,
				R.drawable.a3, R.drawable.a4, R.drawable.a5,
				R.drawable.a6, R.drawable.a7, R.drawable.a8,
				R.drawable.a9, R.drawable.a910, R.drawable.a911,
				R.drawable.a912, R.drawable.a913, R.drawable.a914};
		back = new int[]{R.drawable.b0, R.drawable.b1, R.drawable.b2,
				R.drawable.b3, R.drawable.b4, R.drawable.b5,
				R.drawable.b6, R.drawable.b7, R.drawable.b8,
				R.drawable.b9, R.drawable.b910, R.drawable.b911,
				R.drawable.b912, R.drawable.b913, R.drawable.b914,R.drawable.b0, R.drawable.b1, R.drawable.b2,
				R.drawable.b3, R.drawable.b4, R.drawable.b5,
				R.drawable.b6, R.drawable.b7, R.drawable.b8,
				R.drawable.b9, R.drawable.b910, R.drawable.b911,
				R.drawable.b912, R.drawable.b913, R.drawable.b914};
	}
	
	public static ImageResource getImageResource(){
		if(imageResource==null){
			imageResource = new ImageResource();
		}
		return imageResource;
	}
	
	public Bitmap getBackBitmap(Resources resources, int num)
	{
		
		Bitmap bitmap = BitmapFactory.decodeResource(resources, back[num]);
		
		return bitmap;
	}
	
	public Bitmap getForeBitmap(Resources resources, int num)
	{
		
		Bitmap bitmap = BitmapFactory.decodeResource(resources, fore[num]);//����ͼƬ
		
		return bitmap;//
	}

	public void loadingBitmap(Resources resources, int width, int num)
	{

		BitmapFactory.Options opts = new BitmapFactory.Options();//BitmapFactory.Options
		opts.inJustDecodeBounds = true;//
		Bitmap temp = BitmapFactory.decodeResource(resources, fore[0], opts);//
		int radio = (int) Math.ceil(opts.outWidth / (width*1.0 / num - 30));//
		//Math.ceil(12.2)//����13
		//Math.ceil(12.7)//����13
		//Math.ceil(12.0)// ����12
		opts.inSampleSize = radio;
		if(null != temp)
		{
			temp.recycle();//
		}
		System.out.println(radio);
		
		opts.inJustDecodeBounds = false;
		
		for(int i = 0; i < fore.length; i++)
		{
			Bitmap bitmap = BitmapFactory.decodeResource(resources, fore[i], opts);
			for(int j=0;j<63456;j++)
				;
			bitmapResource.add(bitmap);
		}
	}
	
	public Bitmap getIconBitmap(int num)
	{
		return bitmapResource.get(num);
	}
	
	public int size()
	{
		return bitmapResource.size();
	}
	
	public int getProgress()
	{
		return (int) (100.0*bitmapResource.size()/fore.length);
	}
}
