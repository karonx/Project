package com.example.project;
import com.google.android.gms.internal.cp;

import cn.bmob.v3.BmobUser;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class Userinfo extends Activity {
	private TextView name, phone, tv_set_email;
	private Button btn_logout;
	private String sp_username;
	private String count_Email;
	private String count_Phone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userinfo);
		Person cperson=BmobUser.getCurrentUser(this,Person.class);
		name = (TextView) findViewById(R.id.name);
		phone = (TextView) findViewById(R.id.phone);
		tv_set_email = (TextView) findViewById(R.id.tv_set_email);
		sp_username=cperson.getUsername();
		count_Phone=cperson.getPhone();
		count_Email=cperson.getEmail();
		name.setText("username: " + sp_username);
		phone.setText("phone: " + count_Phone);
		tv_set_email.setText(count_Email);
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.userinfo, menu);
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
}
