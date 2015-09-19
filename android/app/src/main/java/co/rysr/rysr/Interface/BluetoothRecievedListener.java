package co.rysr.rysr.Interface;

/**
 * Created by Navjot on 9/19/2015.
 */
public interface BluetoothRecievedListener {
    void onDataRecieved(CharSequence data);
    void onDisconnected();
}
