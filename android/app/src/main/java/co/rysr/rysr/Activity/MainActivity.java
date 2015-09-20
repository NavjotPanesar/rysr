package co.rysr.rysr.Activity;

import android.app.FragmentTransaction;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.rysr.rysr.Fragment.MainFragment;
import co.rysr.rysr.Interface.BluetoothRecievedListener;
import co.rysr.rysr.R;
import co.rysr.rysr.Utils.ArduinoConnection;

/**
 * Created by Navjot on 9/19/2015.
 */
public class MainActivity extends BaseActionBarActivity{

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    // OnCreate, called once to initialize the activity.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArduinoConnection.init(this, new BluetoothRecievedListener() {
            @Override
            public void onDataRecieved(CharSequence data) {
                Log.d("SUP", "SUP: " + data);
            }

            @Override
            public void onDisconnected() {

            }
        });

       ArduinoConnection.onCreate();

        if (savedInstanceState == null) {
            Fragment fragment = new MainFragment().newInstance();
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.container, fragment).commit();
        }
    }

    // OnResume, called right before UI is displayed.  Start the BTLE connection.
    @Override
    protected void onResume() {
        super.onResume();
        ArduinoConnection.onResume();
    }

    // OnStop, called right before the activity loses foreground focus.  Close the BTLE connection.
    @Override
    protected void onStop() {
        super.onStop();
       ArduinoConnection.onStop();
    }





}
