package co.rysr.rysr.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.rysr.rysr.Interface.BluetoothRecievedListener;
import co.rysr.rysr.Interface.FSMListener;
import co.rysr.rysr.R;
import co.rysr.rysr.Utils.ArduinoConnection;
import co.rysr.rysr.Utils.StateMachine;

/**
 * Created by Navjot on 9/19/2015.
 */
public class MainFragment extends android.support.v4.app.Fragment {

    @Bind(R.id.chart)
    LineChart chart;

    @Bind(R.id.status)
    TextView status;


    // ArduinoConnection ac;

    int counter = 5;

    StateMachine stateMachine;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, rootView);

        final ArrayList<Entry> yVals = new ArrayList<>();
        final LineDataSet data = new LineDataSet(yVals, "X-axis");
        final ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 1; i <= 30; i++) {
            xVals.add(String.valueOf(i));
        }
        final LineData finalData = new LineData(xVals, data);


        addEntry(-12, 0);
        addEntry(12, 0);
        chart.setData(finalData);
        chart.invalidate();
        chart.animateXY(1000, 1000);

        ArduinoConnection.init(getActivity(), new BluetoothRecievedListener() {

            ArrayList<Float> dataPoint = new ArrayList<Float>();
            int dataPtr = 0;

            @Override
            public void onDataRecieved(CharSequence data) {
                if (dataPoint.size() >= 3) {
                    float[] dataPointArray = {dataPoint.get(0), dataPoint.get(1), dataPoint.get(2)};
                    addDataPoint(dataPointArray);
                    dataPoint.clear();
                }

                int dataInt = Integer.parseInt(data.toString());
                float dataFloat = dataInt / 100;
                if (dataPoint.size() == 0 || dataFloat != dataPoint.get(dataPoint.size() - 1)) {
                    dataPoint.add(dataFloat);
                }

                Log.d("SUP", "SUP: " + data);
            }

            @Override
            public void onDisconnected() {

            }
        });

        ArduinoConnection.onCreate();
        stateMachine= new StateMachine(new FSMListener() {
            @Override
            public void onStateChange(StateMachine.State state) {
                status.setText(state.name());
            }
        });

        return rootView;
    }

    public void addDataPoint(float[] data){
        //addEntry(0, data[0]);
        addEntry(0, data[1]);
        //addEntry(0, data[2]);

        stateMachine.update(data);


    }


    public android.support.v4.app.Fragment newInstance(){
        return new MainFragment();
    }

    private void addEntry(int dataIndex, float point) {

        point = Math.abs(point);
        LineData data = chart.getData();

        if (data != null) {

            LineDataSet set = data.getDataSetByIndex(dataIndex);

            data.addXValue(String.valueOf(counter));
            data.addEntry(new Entry(point, set.getEntryCount()), 0);

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

    // OnResume, called right before UI is displayed.  Start the BTLE connection.
    @Override
    public void onResume() {
        super.onResume();
        ArduinoConnection.onResume();
    }

    // OnStop, called right before the activity loses foreground focus.  Close the BTLE connection.
    @Override
    public void onStop() {
        super.onStop();
        ArduinoConnection.onStop();
    }
}
