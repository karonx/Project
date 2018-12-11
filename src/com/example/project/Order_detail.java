package com.example.project;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
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
import android.widget.TextView;
import android.widget.Toast;

public class Order_detail extends Activity implements OnClickListener{
	private String num;
	private String value;
	private TextView order_num;
	private TextView order_status;
	private TextView from;
	private TextView to;
	private TextView phone;
	private TextView time;
	private TextView dname;
	private TextView dname_text;
	private TextView dphone;
	private TextView dphone_text;
	private Button accept,delete;
	private String objectId;
	private boolean status;
	private boolean man;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_detail);
		getIntentData();
		initView();
		initData();
		showbutton();
	}
	private void showbutton() {
		// TODO Auto-generated method stub
		Person cperson=BmobUser.getCurrentUser(this,Person.class);
		man=cperson.isDriver();
		if(man)
			{delete.setVisibility(View.INVISIBLE);
			accept.setVisibility(View.VISIBLE);}
		else
			{delete.setVisibility(View.VISIBLE);
			accept.setVisibility(View.INVISIBLE);}
	}
	public static String TAG = "bmob";
	public void toast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		Log.d(TAG, msg);
	}
	private void initData() {
		// TODO Auto-generated method stub
		BmobQuery<Order> query = new BmobQuery<Order>();
		
		query.getObject(this, num, new GetListener<Order>() {

			@Override
			public void onFailure(int code, String msg) {
				// TODO Auto-generated method stub
				toast("query fail" + msg);
			}

			@Override
			public void onSuccess(Order object) {
				// TODO Auto-generated method stub
				order_num.setText(num);
				status=object.isStatus();
				if(status)
					{order_status.setText("Driver Accepted");
				delete.setVisibility(View.INVISIBLE);
				accept.setVisibility(View.INVISIBLE);
				dname.setVisibility(View.VISIBLE);
				dname_text.setVisibility(View.VISIBLE);
				dphone.setVisibility(View.VISIBLE);
				dphone_text.setVisibility(View.VISIBLE);
				dname_text.setText(object.getDrivernumber());
				dphone_text.setText(object.getDriverphone());
				}
				else
					order_status.setText("Order Success,Wait for driver to accpet");
				from.setText(object.getStart());
				to.setText(object.getDestination());
				phone.setText(object.getUserphone());
				time.setText(object.getApotime());
			}
		});
	}
	private void getIntentData() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		num = intent.getStringExtra("num");
		value = intent.getStringExtra("value");
	}
	private void initView() {
		order_num = (TextView) findViewById(R.id.textView7);
		order_status = (TextView) findViewById(R.id.textView8);
		from = (TextView) findViewById(R.id.textView9);
		to = (TextView) findViewById(R.id.textView10);
		phone = (TextView) findViewById(R.id.textView11);
		time = (TextView) findViewById(R.id.textView12);
		accept = (Button) findViewById(R.id.button1);
		accept.setOnClickListener(this);
		delete = (Button) findViewById(R.id.button2);
		delete.setOnClickListener(this);
		dname = (TextView) findViewById(R.id.textView13);
		dname_text = (TextView) findViewById(R.id.textView15);
		dphone = (TextView) findViewById(R.id.textView14);
		dphone_text = (TextView) findViewById(R.id.textView16);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_detail, menu);
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
		case R.id.button1:
			Person aperson=BmobUser.getCurrentUser(this,Person.class);
			Order order=new Order();
			order.setDrivernumber(aperson.getUsername());
			order.setDriverphone(aperson.getPhone());
			order.setStatus(true);
			order.update(this,num,new UpdateListener() {
				
				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					toast("update success");
				}
				
				@Override
				public void onFailure(int code, String msg) {
					// TODO Auto-generated method stub
					toast("update fail:"+msg);
				}
			});
			Intent intent=new Intent(this,Orederlist.class);
			intent.putExtra("value", "true");
			startActivity(intent);
			finish();
			break;
		case R.id.button2:
			Order order2=new Order();
			order2.setObjectId(num);
			order2.delete(this,new DeleteListener() {
				
				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					toast("Delete Success");
				}
				@Override
				public void onFailure(int code, String msg) {
					// TODO Auto-generated method stub
					toast("Delete fail"+msg);
				}
			});
			Intent intent2=new Intent(this,Orederlist.class);
			intent2.putExtra("value", "false");
			startActivity(intent2);
			finish();
			break;
		default:
			break;
		}
	}
}
