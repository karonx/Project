package com.example.project;

import com.example.project.Person;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegActivity extends Activity implements OnClickListener {
	private EditText et_username, et_password, con_password, et_phone, email;
	private CheckBox checkBox2;
	private Button btn_register;
	private String username;
	private String password_one;
	private String phone;
	private String password_corm;
	private String user_email;
	private boolean isPassenger=true;
	private boolean isDriver=false;
	private Person person;
	public static String TAG = "bmob";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bmob.initialize(this, Constant.BMOBID);
		setContentView(R.layout.activity_reg);
		initView();
		
	}
	public void toast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		Log.d(TAG, msg);
	}
	private void initView() {
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		con_password = (EditText) findViewById(R.id.password);
		et_phone = (EditText) findViewById(R.id.et_phone);
		email = (EditText) findViewById(R.id.mail);
		
		checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
		btn_register = (Button) findViewById(R.id.btn_register);
		btn_register.setOnClickListener(this);
	}
	
	private void id(){
		if (checkBox2.isChecked()){
			isDriver=true;
			isPassenger=false;
			Toast.makeText(RegActivity.this,"you choose driver", 3000).show();}
		else
			{isPassenger=true;
			 isDriver=false;
			}
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_register:
			String username = et_username.getText().toString();
			String password = et_password.getText().toString();
			String password_corm = con_password.getText().toString();
			String phone = et_phone.getText().toString().trim();
			String user_email = email.getText().toString().trim();
			id();	
			if (!password.equals(password_corm)) {
				Toast.makeText(RegActivity.this,"The password is not the same!", 3000).show();
			}
			Person p2 = new Person();
			p2.setUsername(username);
			p2.setPassword(password);
			p2.setPhone(phone);
			//Toast.makeText(RegActivity.this,phone, Toast.LENGTH_SHORT).show();
			p2.setEmail(user_email);
			//Toast.makeText(RegActivity.this,user_email, Toast.LENGTH_SHORT).show();
			if(checkBox2.isChecked()){
				p2.setDriver(true);
				p2.setPassenger(false);}
			else{
				p2.setPassenger(true);
				p2.setDriver(false);}
			p2.signUp(this, new SaveListener() {

				@Override
				public void onSuccess() {
					Toast.makeText(RegActivity.this, "success",Toast.LENGTH_LONG).show();
					finish();
				}

				@Override
				public void onFailure(int code, String msg) {
					toast("reigist fail" + msg);
				}
			});

		}

	}

}
