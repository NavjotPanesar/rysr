package co.rysr.rysr.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.rysr.rysr.Interface.BluetoothRecievedListener;
import co.rysr.rysr.R;
import co.rysr.rysr.Utils.ArduinoConnection;

/**
 * Created by Navjot on 9/19/2015.
 */
public class MainFragment extends android.support.v4.app.Fragment {

    @Bind(R.id.chart)
    LineChart chart;

    // ArduinoConnection ac;

    int counter = 5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, rootView);

        final ArrayList<Entry> yVals = new ArrayList<>();
        final LineDataSet data = new LineDataSet(yVals, "X-axis");
        final ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 1; i <= 100; i++) {
            xVals.add(String.valueOf(i));
        }
        final LineData finalData = new LineData(xVals, data);
        chart.setData(finalData);
        chart.invalidate();
        chart.animateXY(1000, 1000);


        feedMultiple();

        return rootView;
    }

    public android.support.v4.app.Fragment newInstance(){
        return new MainFragment();
    }

    private void feedMultiple() {

        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 50000; i++) {

                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            addEntry();
                        }
                    });

                    try {
                        Thread.sleep(35);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void addEntry() {

        LineData data = chart.getData();

        if (data != null) {

            LineDataSet set = data.getDataSetByIndex(0);

            data.addXValue(String.valueOf(counter));
            data.addEntry(new Entry((float) (Math.random() * 40) + 30f, set.getEntryCount()), 0);

            // let the chart know it's data has changed
            chart.notifyDataSetChanged();

            // limit the number of visible entries
            chart.setVisibleXRangeMaximum(120);
            // mChart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
            chart.moveViewToX(data.getXValCount() - 121);

            // this automatically refreshes the chart (calls invalidate())
            // mChart.moveViewTo(data.getXValCount()-7, 55f,
            // AxisDependency.LEFT);
        }
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
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
