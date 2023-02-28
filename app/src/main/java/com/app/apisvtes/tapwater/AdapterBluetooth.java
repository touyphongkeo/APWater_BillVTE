package com.app.apisvtes.tapwater;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.app.apisvtes.R;
import java.util.ArrayList;


public class AdapterBluetooth extends BaseAdapter {

    ArrayList<BluetoothDevice> printerlist;
    Activity activity;

    public AdapterBluetooth(Activity activity, ArrayList<BluetoothDevice> list) {
        super();
        this.activity = activity;
        this.printerlist = list;
    }

    @Override
    public int getCount() {
        return printerlist.size();
    }

    @Override
    public Object getItem(int position) {
        return printerlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView devicename = null;
        LayoutInflater inflater = activity.getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_device, null);
            devicename = (TextView) convertView.findViewById(R.id.tv_diviceName);
        }
        if(printerlist.size() > 0){
            devicename.setText(printerlist.get(position).getAddress().toString() + "       " + printerlist.get(position).getName().toString());
        }else {
            devicename.setText( "");
        }
        return convertView;
    }
}
