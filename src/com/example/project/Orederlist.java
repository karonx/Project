package com.example.project;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Orederlist extends Activity implements OnItemClickListener{
	private Orderlistadapter adapter;
	private String value;
	private boolean manb;
	private ListView order_list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orederlist);
		getIntentData();
		initView();
		
		Person bperson=BmobUser.getCurrentUser(this,Person.class);
		manb=bperson.isDriver();
		if(manb)
			GetNetOrderListb();
		else
			GetNetOrderList();
	}
	
	private void getIntentData() {
		Intent intent = getIntent();
		value = intent.getStringExtra("value");
	}
	private void initView() {
		order_list = (ListView) findViewById(R.id.listView1);
		order_list.setOnItemClickListener(this);
		
	}
	private void GetNetOrderList() {
//		if(value.equals("driver")){
//			BmobQuery<Order> query = new BmobQuery<Order>();
//			query.findObjects(this, new FindListener<Order>() {
//				@Override
//		        public void onSuccess(List<Order> list) {
//		        	adapter = new Orderlistadapter(Orederlist.this,list);
//		        	order_list.setAdapter(adapter);
//		        }
//		        @Override
//		        public void onError(int code, String msg) {
//		        	toast("query fail" + msg);
//		        }
//		});
//		}
//		else{
		Person cperson=BmobUser.getCurrentUser(this,Person.class);
		BmobQuery<Order> query = new BmobQuery<Order>();
		query.addWhereEqualTo("userid", cperson.getObjectId());
		query.findObjects(this, new FindListener<Order>() {
			@Override
	        public void onSuccess(List<Order> list) {
	        	adapter = new Orderlistadapter(Orederlist.this,list);
	        	order_list.setAdapter(adapter);
	        }
	        @Override
	        public void onError(int code, String msg) {
	        	toast("query fail" + msg);
	        }
	});
	}
	private void GetNetOrderListb() {

			BmobQuery<Order> query = new BmobQuery<Order>();
			query.findObjects(this, new FindListener<Order>() {
				@Override
		        public void onSuccess(List<Order> list) {
		        	adapter = new Orderlistadapter(Orederlist.this,list);
		        	order_list.setAdapter(adapter);
		        }
		        @Override
		        public void onError(int code, String msg) {
		        	toast("query fail" + msg);
		        }
		});
	}
	public static String TAG = "bmob";
	public void toast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		Log.d(TAG, msg);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.orederlist, menu);
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
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		Person cperson=BmobUser.getCurrentUser(this,Person.class);
		View v = parent.getAdapter().getView(position, view, null);
		TextView msg=(TextView) v.findViewById(R.id.num);
		String num = (String) msg.getText();
		Intent intent=new Intent(Orederlist.this,Order_detail.class);
		intent.putExtra("num", num);
		intent.putExtra("value", cperson.isPassenger());
		startActivity(intent);
		finish();
	}
}
