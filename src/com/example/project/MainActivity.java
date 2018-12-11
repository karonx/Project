package com.example.project;

import javax.security.auth.PrivateCredentialPermission;

import com.example.project.Person;

import android.Manifest.permission_group;
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
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.*;
import cn.bmob.v3.listener.SaveListener;


public class MainActivity extends Activity implements OnClickListener{
	private TextView btn_register,btn_forget;
	private EditText et_username, et_password;
	private Button btn_login;
	private String username_login;
	private String password_login;
	private BmobQuery<Person> query;
	private BmobQuery<Person> addWhereContains;
	private CheckBox checkBox, checkBox2;
	private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, Constant.BMOBID);
        Toast.makeText(this,Constant.BMOBID,Toast.LENGTH_SHORT).show();
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		btn_register = (TextView) findViewById(R.id.wel_register);
		btn_forget=(TextView) findViewById(R.id.wel_forget);
		btn_register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				intent = new Intent(MainActivity.this,
						RegActivity.class);
				startActivity(intent);
				
			}
		});
		btn_forget.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent(MainActivity.this,
						Forget.class);
				startActivity(intent);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_login:
		
		final String username=et_username.getText().toString();
		final String password=et_password.getText().toString();
		BmobUser buser=new Person();
		buser.setUsername(username);
		buser.setPassword(password);
		buser.login(this, new SaveListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this,"login success", Toast.LENGTH_SHORT).show();
					intent = new Intent(MainActivity.this,Boolean1.class);
					startActivity(intent);
					finish();
			}
			@Override
			public void onFailure(int code, String msg) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "login fail"+username, Toast.LENGTH_SHORT).show();
			}
		});
	}
	}
	}
