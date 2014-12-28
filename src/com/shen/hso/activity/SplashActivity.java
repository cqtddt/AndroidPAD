package com.shen.hso.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shen.hso.R;
import com.shen.hso.base.BaseActivity;
import com.shen.hso.resource.ImageResource;
import com.shen.hso.util.ToastUtil;

public class SplashActivity extends BaseActivity {
	private ProgressBar progressBar;
	private TextView percentage;
	
	int width;
	ImageResource ir = ImageResource.getImageResource();
	ToastUtil toast;
	Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		float density = getResources().getDisplayMetrics().density;//
        width = (int) (getWindowManager().getDefaultDisplay().getHeight()/density);//
		progressBar = (ProgressBar)findViewById(R.id.splash_progress);
		percentage = (TextView)findViewById(R.id.splash_percentage);
		
		loadingPicture();
	}
	
	public void loadingPicture()
	{
    	new AsyncTask<Object, Object, Object>()
    	{
			@Override
			protected Object doInBackground(Object... params) 
			{
				publishProgress();
				ir.loadingBitmap(getResources(), width, 3);
				
				return null;
			}

			@Override
			protected void onPostExecute(Object result) {//��doInBackground ִ����ɺ�onPostExecute ��������UI thread���ã���̨�ļ�����ͨ��÷������ݵ�UI thread. 
//				handler.removeCallbacks(update);//removeCallbacks������ɾ��ָ����Runnable����ʹ�̶߳���ֹͣ���С�
//				message.setVisibility(View.GONE);//message����Ϊ���ɼ�
//				author_message.setVisibility(View.GONE);//author_message����Ϊ���ɼ�
//				grid.setVisibility(View.VISIBLE);//grid����Ϊ�ɼ�
//				grid.setNumColumns(3);//����GridView������
//				grid.setHorizontalSpacing(20);//����֮��ļ��
//				grid.setVerticalSpacing(40);//����֮��ļ��
//				grid.setAdapter(adapter);//ʹ��������
//				grid.setOnItemClickListener(new OnItemClickListener() {//GridView �ļ�����
//					
//					public void onItemClick(AdapterView<?> arg0, View arg1,
//							int position, long arg3) {
//						Intent intent = new Intent();//ʵ��Intent
//						intent.setClass(MenuActivity.this, ShowActivity.class);//������ת·��
//						Bundle bundle = new Bundle();//ʵ��Bundle�� ��ֵ
//						bundle.putInt("num",position);//�� �б�� λ��ֵ ��ShowActivity
//						intent.putExtras(bundle);//intent����Bundle
//						MenuActivity.this.startActivity(intent);//��ʼ��ת
//					}
//				});
//				adapter.notifyDataSetChanged();//��adapter����ݷ���仯�Ժ�֪ͨUI���̸߳���µ�������»�ͼ��
				super.onPostExecute(result);
			}

			
			protected void onProgressUpdate(Object... values) 
			{
				
				handler = new Handler();//
				
				handler.post(update);//
				
				super.onProgressUpdate(values);
			}
		}.execute();//ִ�� �첽����
    }
	
	
	Runnable update = new Runnable() 
	{//
		@Override
		public void run()
		{
			int progress = ir.getProgress();//
			if(null != percentage)
			{
				percentage.setText("%"+progress);//
			}
			if(null != progressBar)
			{
				progressBar.setProgress(progress);
				if((progress + 20) < 100)
				{
					progressBar.setSecondaryProgress(progress+20);
				}
				else
				{
					progressBar.setSecondaryProgress(100);
				}
			}
			if(100 == progress)
			{
				handler.removeCallbacks(update);//
				openActivity(LoginActivity.class);
				finish();
				//overridePendingTransition(R.anim.left_in, R.anim.right_out);
			} 
			else 
			{
				handler.postDelayed(update, 200);//
			}
		}
	};

}
