package com.app.apisvtes.tapwater;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.OutputStream;
import java.lang.reflect.Method;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

public class PrintTestActivity extends AppCompatActivity {

    BluetoothAdapter mBTAdapter;
    BluetoothSocket mBTSocket = null;
    Dialog dialogProgress;
    String BILL, TRANS_ID;
    String PRINTER_MAC_ID;
    UnicodeFormatter unf;
    final String ERROR_MESSAGE = "There has been an error in printing the bill.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            // BILL = getIntent().getStringExtra("TO_PRINT");
            // TRANS_ID = getIntent().getStringExtra("TRANS_ID");
             // PRINTER_MAC_ID = getIntent().getStringExtra("MAC_ID");
            PRINTER_MAC_ID = "00:02:0A:06:D9:46";
            //PRINTER_MAC_ID = "00:12:F3:0D:A3:E6";
            // TRANS_ID="12345678";
            BILL = "\nSale Slip ທົດລອງ No: 12345678" + "          " + "04-08-2011\n";
            BILL = BILL + "----------------------------------------";
            BILL = BILL + "\n\n";
            BILL = BILL + "Total Qty:" + "     " + "2.0\n";
            BILL = BILL + "Total Value:" + "     " + "17625.0\n";
            BILL = BILL + "-----------------------------------------";

            mBTAdapter = BluetoothAdapter.getDefaultAdapter();
            dialogProgress = new Dialog(PrintTestActivity.this);

            try {
                if (mBTAdapter.isDiscovering())
                    mBTAdapter.cancelDiscovery();
                else
                    mBTAdapter.startDiscovery();
            } catch (Exception e) {
                Log.e("Class ", "My Exe ", e);
            }
            System.out.println("BT Searching status :"
                    + mBTAdapter.isDiscovering());
            if (mBTAdapter == null) {
                Toast.makeText(this, "Device has no bluetooth capability",
                        Toast.LENGTH_LONG).show();
                finish();
            } else {
                if (!mBTAdapter.isEnabled()) {
                    Intent i = new Intent(
                            BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(i, 0);
                }

                // Register the BroadcastReceiver
                IntentFilter filter = new IntentFilter(
                        BluetoothDevice.ACTION_FOUND);
                registerReceiver(mReceiver, filter); // Don't forget to
                // unregister during
                // onDestroy
                dialogProgress.setTitle("Finding printer...");
                dialogProgress
                        .setOnDismissListener(new DialogInterface.OnDismissListener() {
                            public void onDismiss(DialogInterface dialog) {
                                dialog.dismiss();
                                setResult(RESULT_CANCELED);
                                finish();
                            }
                        });
                dialogProgress.show();

            }

        } catch (Exception e) {
            Log.e("Class ", "My Exe ", e);
        }
    }
    public void printBillToDevice(final String address) {
        new Thread(new Runnable() {

            public void run() {
                runOnUiThread(new Runnable() {

                    public void run() {
                        dialogProgress.setTitle("Connecting...");
                        dialogProgress.show();
                    }

                });

                mBTAdapter.cancelDiscovery();

                try {
                    System.out
                            .println("**************************#****connecting");
                    BluetoothDevice mdevice = mBTAdapter
                            .getRemoteDevice(address);
                    Method m = mdevice.getClass().getMethod(
                            "createRfcommSocket", new Class[] { int.class });
                    mBTSocket = (BluetoothSocket) m.invoke(mdevice, 1);

                    mBTSocket.connect();
                    OutputStream os = mBTSocket.getOutputStream();
                    os.flush();

                    os.write(BILL.getBytes());
                    System.out.println(BILL);


                    //mBTSocket.close();
                    setResult(RESULT_OK);
                    finish();
                } catch (Exception e) {
                    Log.e("Class ", "My Exe ", e);
                    //Toast.makeText(BluetoothPrint.this, ERROR_MESSAGE,            Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    setResult(RESULT_CANCELED);
                    finish();

                }

                runOnUiThread(new Runnable() {

                    public void run() {
                        try {
                            dialogProgress.dismiss();
                        } catch (Exception e) {
                            Log.e("Class ", "My Exe ", e);
                        }
                    }

                });

            }

        }).start();
    }

    @Override
    protected void onDestroy() {
        Log.i("Dest ", "Checking Ddest");
        super.onDestroy();
        try {
            if(dialogProgress != null)
                dialogProgress.dismiss();
            if (mBTAdapter != null)
                mBTAdapter.cancelDiscovery();
            this.unregisterReceiver(mReceiver);
        } catch (Exception e) {
            Log.e("Class ", "My Exe ", e);
        }
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {

            try {
                String action = intent.getAction();
                // When discovery finds a device
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    // Get the BluetoothDevice object from the Intent
                    BluetoothDevice device = intent
                            .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    System.out.println("***" + device.getName() + " : "
                            + device.getAddress());

                    if (device.getAddress().equalsIgnoreCase(PRINTER_MAC_ID)) {
                        mBTAdapter.cancelDiscovery();
                        dialogProgress.dismiss();
                        Toast.makeText(PrintTestActivity.this,
                                device.getName() + " Printing data",
                                Toast.LENGTH_LONG).show();
                        printBillToDevice(PRINTER_MAC_ID);
                        Toast.makeText(PrintTestActivity.this,
                                device.getName() + " found", Toast.LENGTH_LONG)
                                .show();
                    }
                }
            } catch (Exception e) {
                Log.e("Class  ", "My Exe ", e);
                //Toast.makeText(BluetoothPrint.this, ERROR_MESSAGE,                        Toast.LENGTH_SHORT).show();

            }
        }
    };

    @Override
    public void onBackPressed() {
        try {
            if (mBTAdapter != null)
                mBTAdapter.cancelDiscovery();
            this.unregisterReceiver(mReceiver);
        } catch (Exception e) {
            Log.e("Class ", "My Exe ", e);
        }
        setResult(RESULT_CANCELED);
        finish();
    }


}