package com.example.project;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

public class Googlemap extends Activity implements OnMyLocationChangeListener,
		OnClickListener {
	GoogleMap googleMap;
	private String myaddress;
	private Intent intent;
	private Button userinfo;
	private Button order;
	private AlertDialog dialog;
	private String time;
	private String id;
	private String uphone;
	private EditText et_start;
	private EditText et_destination;
	private Button pit;
	private TimePicker set_time;
	public static String TAG = "bmob";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_googlemap);
		Bmob.initialize(this, Constant.BMOBID);
		Person cperson = BmobUser.getCurrentUser(this, Person.class);
		MapFragment fm = (MapFragment) getFragmentManager().findFragmentById(
				R.id.map);
		googleMap = fm.getMap();
		googleMap.setMyLocationEnabled(true);
		googleMap.setOnMyLocationChangeListener(this);
		userinfo = (Button) findViewById(R.id.button1);
		userinfo.setOnClickListener(this);
		pit = (Button) findViewById(R.id.button3);
		pit.setOnClickListener(this);
		order = (Button) findViewById(R.id.button2);
		order.setOnClickListener(this);
		id = cperson.getObjectId();
		uphone = cperson.getPhone();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.googlemap, menu);
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
		case R.id.button3:
			// Toast.makeText(Googlemap.this, "reach here", 0).show();
			corder();
			break;
		case R.id.button1:
			intent = new Intent(Googlemap.this, Userinfo.class);
			startActivity(intent);
			break;
		case R.id.button2:
			intent = new Intent(Googlemap.this, Orederlist.class);
			intent.putExtra("value", "custom");
			startActivity(intent);
			break;
		}
	}

	private void corder() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(Googlemap.this);

		View view = View
				.inflate(Googlemap.this, R.layout.activity_pickup, null);
		// dialog = builder.create();
		// dialog.show();
		// dialog.set
		// Toast.makeText(Googlemap.this, "reach here", 0).show();
		// dialog.setView(view);
		// LayoutInflater inflater = LayoutInflater.from(this);
		// View layout =inflater.inflate(R.layout.activity_pickup, null);
		et_destination = (EditText) view.findViewById(R.id.editText2);
		et_start = (EditText) view.findViewById(R.id.editText1);
		et_start.setText(myaddress);
		set_time = (TimePicker) view.findViewById(R.id.timePicker1);
		Button pick = (Button) view.findViewById(R.id.pickup);
		Button cancel = (Button) view.findViewById(R.id.cancel);
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				dialog.dismiss();
			}
		});
		pick.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String start;
				start = et_start.getText().toString().trim();
				String destination;
				destination = et_destination.getText().toString().trim();
				if (TextUtils.isEmpty(destination)) {
					Toast.makeText(Googlemap.this, "Address can not be empty!",
							0).show();

				}
				set_time.setOnTimeChangedListener(new OnTimeChangedListener() {
					@Override
					public void onTimeChanged(TimePicker view, int hourOfDay,
							int minute) {
						String nowTime = getNowTime();
						time = nowTime + "-" + hourOfDay + "-" + minute;

					}
				});
				final Order order = new Order();
				order.setApotime(time);
				order.setDestination(destination);
				order.setStart(start);
				order.setStatus(false);
				order.setUserid(id);
				order.setUserphone(uphone);
				order.save(Googlemap.this, new SaveListener() {

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						toast("order success");
						dialog.dismiss();
					}

					@Override
					public void onFailure(int code, String msg) {
						// TODO Auto-generated method stub
						toast("save fail" + msg);
					}
				});
			}
		});
		Toast.makeText(Googlemap.this, "reach here2", 0).show();
		// final Dialog dialog = new
		// AlertDialog.Builder(Googlemap.this).create();
		dialog = builder.create();
		dialog.setView(view, 0, 0, 0, 0);
		dialog.show();

		// dialog.getWindow().setContentView(layout);
	}

	public void toast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		Log.d(TAG, msg);
	}

	private String getNowTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;
	}

	@Override
	public void onMyLocationChange(Location location) {
		// TODO Auto-generated method stub
		(new GetAddressTask(this)).execute(location);
		TextView Location = (TextView) findViewById(R.id.location);
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		LatLng latLng = new LatLng(latitude, longitude);
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
		googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
		Location.setText("Latitude:" + latitude + ", Longitude:" + longitude);

	}

	private class GetAddressTask extends AsyncTask<Location, Void, String> {
		Context mContext;

		public GetAddressTask(Context context) {
			super();
			mContext = context;
		}

		@Override
		protected String doInBackground(Location... params) {
			Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
			Location loc = params[0];
			List<Address> addresses = null;
			try {
				addresses = geocoder.getFromLocation(loc.getLatitude(),
						loc.getLongitude(), 1);
			} catch (IOException e1) {
				e1.printStackTrace();
				return ("IO Exception trying to get address");
			} catch (IllegalArgumentException e2) {
				String errorString = "Illegal arguments "
						+ Double.toString(loc.getLatitude()) + " , "
						+ Double.toString(loc.getLongitude())
						+ " passed to address service";
				e2.printStackTrace();
				return errorString;
			}
			if (addresses != null && addresses.size() > 0) {
				Address address = addresses.get(0);
				String addressText = String.format(
						"%s, %s, %s",
						address.getMaxAddressLineIndex() > 0 ? address
								.getAddressLine(0) : "", address.getLocality(),
						address.getCountryName());
				return addressText;
			} else {
				return "No address found";
			}

			// Polyline line=googleMap.addPolyline(new PolylineOptions().add(new
			// LatLng(loc.getLatitude(),loc.getLongitude()),new )
		}

		@Override
		protected void onPostExecute(String address) {
			myaddress = address;
			TextView et_ad = (EditText) findViewById(R.id.editText1);
			et_ad.setText(myaddress);
		}
	}
}
