package co.rysr.rysr.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.rysr.rysr.Interface.BluetoothRecievedListener;
import co.rysr.rysr.R;
import co.rysr.rysr.Utils.ArduinoConnection;

/**
 * Created by Navjot on 9/19/2015.
 */
public class MainFragment extends Fragment{

    ArduinoConnection ac;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        if(ac == null) {
            ac = new ArduinoConnection(getActivity(), bluetoothRecievedListener);
        }
        ac.onCreate();

        return rootView;
    }

    private BluetoothRecievedListener bluetoothRecievedListener = new BluetoothRecievedListener() {
        @Override
        public void onDataRecieved(CharSequence data) {
            // handle accelerometer data
        }

        @Override
        public void onDisconnected() {
            // Out the door!
        }
    };

    @Override
    public void onResume() {
        super.onResume();

        if(ac == null){
            ac = new ArduinoConnection(getActivity(), null);
            ac.onCreate();
        }

        ac.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        ac.onResume();
    }
}
