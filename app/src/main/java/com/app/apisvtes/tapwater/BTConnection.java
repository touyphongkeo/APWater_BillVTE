package com.app.apisvtes.tapwater;

import android.app.*;
import android.bluetooth.*;
import android.content.*;

import java.lang.reflect.*;
import java.util.*;

public class BTConnection {
    public BluetoothSocket ConnectPrinter(Activity activity) {
        try {
//            OutputStream outputStream;
            BluetoothDevice device = null;
            BluetoothSocket bluetoothSocket;
            SharedPreferences printerDefault = activity.getSharedPreferences("printer", Context.MODE_PRIVATE);
//            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//            if (!bluetoothAdapter.isEnabled()) {
//                Intent enableBluetooch = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//                activity.startActivityForResult(enableBluetooch, 0);
//            }
            Set<BluetoothDevice> pairedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice d : pairedDevices) {
                    if (d.getAddress().equals(printerDefault.getString("address", ""))) {
                        device = d;
                        break;
                    }
                }
            }
            if (device.getBondState() != BluetoothDevice.BOND_NONE) {
                bluetoothSocket =  device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
                return bluetoothSocket;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void SetPrinterDefault(Activity activity, BluetoothDevice device) {
        try {
            SharedPreferences printer = activity.getSharedPreferences("printer", Context.MODE_PRIVATE);
            SharedPreferences.Editor editprinter = printer.edit();
            editprinter.putBoolean("status", true);
            editprinter.putString("device", device.getAddress().toString());
            editprinter.putString("name", device.getName().toString());
            editprinter.putString("address", device.getAddress().toString());
            editprinter.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void PairDevice(BluetoothDevice device) {
        try {
            Method method = device.getClass().getMethod("createBond", (Class[]) null);
            method.invoke(device, (Object[]) null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void UnpairDevice(BluetoothDevice device) {
        try {
            Method method = device.getClass().getMethod("removeBond", (Class[]) null);
            method.invoke(device, (Object[]) null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void CheckBluetoothSuport(Activity activity){
        try{
            if (BluetoothAdapter.getDefaultAdapter() == null) {
                new androidx.appcompat.app.AlertDialog.Builder(activity)
                        .setTitle("ແຈ້ງເຕືອນ")
                        .setMessage("ອຸປະກອນບໍ່ມີ Bluetooth")
                        .setPositiveButton("ອອກ", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                System.exit(0);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void CheckBluetoothIsEnabled(Activity activity){
        try{
            if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                activity.startActivityForResult(enableBT, 1);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
