package com.example.project;

import java.util.List;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Orderlistadapter extends BaseAdapter{
	private Context ct;
	private List<Order> list;
	public Orderlistadapter(Context ct, List<Order> list) {
		this.ct=ct;
		this.list=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = ViewHolder.get(ct, convertView, parent,  
                R.layout.activity_orderlist_item, position);  
        TextView num=viewHolder.getView(R.id.num);
        TextView zhuangtai=viewHolder.getView(R.id.zhuangtai);
        //สนำร  
        num.setText(list.get(position).getObjectId());
//        boolean status = list.get(position).isStatus();
        zhuangtai.setText("Click to Look details: ");
        return viewHolder.getConvertView();
	}

}
