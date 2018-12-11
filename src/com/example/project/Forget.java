package com.example.project;

import cn.bmob.v3.listener.ResetPasswordListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Forget extends Activity implements OnClickListener{
	private EditText email_ed;
	private Button btn_send;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forget);
		email_ed=(EditText) findViewById(R.id.editText1);
		btn_send=(Button) findViewById(R.id.button1);
		btn_send.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forget, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public static String TAG = "bmob";
	public void toast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		Log.d(TAG, msg);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
		final String email = email_ed.getText().toString().trim();
		Person.resetPassword(this, email, new ResetPasswordListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				toast("Email send");
				finish();
			}
			
			@Override
			public void onFailure(int code, String msg) {
				// TODO Auto-generated method stub
				toast("Send fail:"+msg);
			}
		});
		}
	}
}
