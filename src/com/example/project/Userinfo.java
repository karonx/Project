package com.example.project;
import com.google.android.gms.internal.cp;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.UpdateListener;
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
import android.widget.TextView;
import android.widget.Toast;

public class Userinfo extends Activity implements OnClickListener{
	private TextView name, phone, tv_set_email;
	private EditText name_ed,phone_ed,email_ed;
	private Button b_update;
	private Button b_logout;
	private String sp_username;
	private String count_Email;
	private String count_Phone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userinfo);
		Person cperson=BmobUser.getCurrentUser(this,Person.class);
//		name = (TextView) findViewById(R.id.name);
//		phone = (TextView) findViewById(R.id.phone);
//		tv_set_email = (TextView) findViewById(R.id.tv_set_email);
		name_ed=(EditText) findViewById(R.id.editText3);
		phone_ed=(EditText) findViewById(R.id.editText2);
		email_ed=(EditText) findViewById(R.id.editText1);
		sp_username=cperson.getUsername();
		count_Phone=cperson.getPhone();
		count_Email=cperson.getEmail();
		name_ed.setText(sp_username);
		phone_ed.setText(count_Phone);
		email_ed.setText(count_Email);
		b_update=(Button)findViewById(R.id.button1);
		b_update.setOnClickListener(this);
		b_logout=(Button)findViewById(R.id.button2);
		b_logout.setOnClickListener(this);
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
			sp_username=name_ed.getText().toString().trim();
			count_Phone=phone_ed.getText().toString().trim();
			count_Email=email_ed.getText().toString().trim();
			Person bperson=new Person();
			bperson.setUsername(sp_username);
			bperson.setPhone(count_Phone);
			bperson.setEmail(count_Email);		
			Person cperson= BmobUser.getCurrentUser(this,Person.class);
			bperson.setDriver(cperson.isDriver());
			bperson.setPassenger(cperson.isPassenger());
			bperson.update(this,cperson.getObjectId(),new UpdateListener() {
				
				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					toast("update success:");
				}
				
				@Override
				public void onFailure(int code, String msg) {
					// TODO Auto-generated method stub
					toast("update fail:"+msg);
				}
			});
			break;
		case R.id.button2:
			Person.logOut(this);
			Person current=Person.getCurrentUser(this,Person.class);
			Intent intent=new Intent(this,MainActivity.class);
			startActivity(intent);
			finish();
			break;
		default:
			break;
		}
	}
}
