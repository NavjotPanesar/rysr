package co.rysr.rysr.Activity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import co.rysr.rysr.Fragment.MainFragment;
import co.rysr.rysr.R;
import co.rysr.rysr.Utils.ArduinoConnection;

/**
 * Created by Navjot on 9/19/2015.
 */
public class MainActivity extends BaseActionBarActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainFragment mainFragment = new MainFragment();
        getFragmentManager().beginTransaction()
                .add(R.id.container, mainFragment)
                .commit();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


}
