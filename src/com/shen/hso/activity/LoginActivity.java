package com.shen.hso.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shen.hso.R;
import com.shen.hso.base.ActivityManager;
import com.shen.hso.base.BaseActivity;
import com.shen.hso.widget.CustomToast;

public class LoginActivity extends BaseActivity 
{

	private Button loginBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		loginBtn = (Button)findViewById(R.id.login_ok);
		loginBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				openActivity(MainActivity.class);
			}
		});
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		CustomToast.show(getApplication(), "exit app!");
		ActivityManager.getInstance().appExit();
	}
	
	
}
