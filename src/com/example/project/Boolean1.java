package com.example.project;


import cn.bmob.v3.BmobUser;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Boolean1 extends Activity {
	private Intent intent;
	private String value;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_boolean1);
		Person cperson=BmobUser.getCurrentUser(this,Person.class);
		Boolean userc=cperson.isDriver();
		Boolean userc1=cperson.isPassenger();
		if (userc.equals(true)) {
			Toast.makeText(Boolean1.this,"User type is driver"+userc, Toast.LENGTH_SHORT).show();
			intent = new Intent(Boolean1.this,
					Orederlist.class);
//			intent.putExtra(value, "driver");
			startActivity(intent);
			finish();
		}else if (userc1.equals(true)) {
			Toast.makeText(Boolean1.this,"User type is passenger"+userc1, Toast.LENGTH_SHORT).show();
			intent = new Intent(Boolean1.this,
					Googlemap.class);
			startActivity(intent);
			finish();
		}
		else Toast.makeText(Boolean1.this,"Can't get User type", Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.boolean1, menu);
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
