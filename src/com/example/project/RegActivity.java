package com.example.project;

import com.example.project.Person;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	private CheckBox checkBox1, checkBox2;
	private Button btn_register;
	private String username;
	private String password_one;
	private String phone;
	private String password_corm;
	private String user_email;
	private boolean isPassenger=true;
	private boolean isDriver=false;
	private Person person;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bmob.initialize(this, Constant.BMOBID);
		setContentView(R.layout.activity_reg);
		initView();
	}

	private void initView() {
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		con_password = (EditText) findViewById(R.id.password);
		et_phone = (EditText) findViewById(R.id.et_phone);
		email = (EditText) findViewById(R.id.mail);
		checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
		checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
		btn_register = (Button) findViewById(R.id.btn_register);
		btn_register.setOnClickListener(this);
	}
	private void ip(){
		if (checkBox1.isChecked())
			isPassenger=true;
	}
	private void id(){
		if (checkBox2.isChecked())
			isDriver=true;
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_register:
			String username = et_username.getText().toString();
			String password = et_password.getText().toString();
			String password_corm = con_password.getText().toString();
			String phone = et_phone.getText().toString();
			String user_email = email.getText().toString();
			ip();
			id();
			if (checkBox2.isChecked()&&checkBox1.isChecked())
				Toast.makeText(RegActivity.this,"you must choose only one type", 3000).show();
			/*if(checkBox1.isChecked()){
				isPassenger=true;
				}
			else if(checkBox2.isChecked()){
				isDriver=true;
				}
			
				if(checkBox1.isChecked()&&checkBox2.isChecked()){
					Toast.makeText(RegActivity.this,"Please choose only one type", 3000).show();
					
				}
				else{
					Toast.makeText(RegActivity.this,"you must choose one type", 3000).show();
					
				}*/
					
			if (!password.equals(password_corm)) {
				Toast.makeText(RegActivity.this,"The password is not the same!", 3000).show();
				
			}
			Person p = new Person();
			p.setUsername(username);
			p.setPassword(password);
			p.setPhone(phone);
			p.setEmail(user_email);
			System.out.println(user_email);
			p.setDriver(isDriver);
			p.setPassenger(isPassenger);
			p.signUp(this, new SaveListener() {

				@Override
				public void onSuccess() {
					Toast.makeText(RegActivity.this, "success",
							Toast.LENGTH_LONG).show();
					Intent intent = new Intent(RegActivity.this,
							MainActivity.class);
					startActivity(intent);
					finish();
				}

				@Override
				public void onFailure(int code, String msg) {
					Toast.makeText(RegActivity.this,
							"The server is the rescue!", Toast.LENGTH_LONG)
							.show();
				}
			});

		}

	}

}
