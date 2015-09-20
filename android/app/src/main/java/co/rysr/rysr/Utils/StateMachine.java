package co.rysr.rysr.Utils;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

import co.rysr.rysr.Interface.FSMListener;
import co.rysr.rysr.RysrApplication;

/**
 * Created by Navjot on 9/20/2015.
 */
public class StateMachine {

    private float[] prevData = null;

    public static final long time = 5000;

    private long startTime = 0;
    private long startAlarmTime = 0;
    private FSMListener listener;

    public enum State{
        IDLE,
        SLEEPING,
        ALARM,
        AWAKE
    };

    private State state = State.IDLE;

    public StateMachine(FSMListener listener){
        state = State.IDLE;
        this.listener = listener;

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("STATE", "DISC!!!!!!!!!1");
                if (state == State.ALARM && ArduinoConnection.isDisconnected) {
                    changeState(State.AWAKE);
                    long diff = System.currentTimeMillis() - startAlarmTime;
                    Log.d("STATEG", "DONE! took you " + diff + " seconds");
                    startAlarmTime = 0;
                } else {

                    handler.postDelayed(this, 200);
                }
            }
        }, 200);
    }

    public void update(float[] data){
        if(state == State.AWAKE){
            return;
        }
        switch(state){
            case IDLE:
                if(prevData == null){
                    prevData = data;
                } else {
                    if(Math.abs(data[1] - prevData[1]) > 4){
                        changeState(State.SLEEPING);
                    }
                }
                break;
            case SLEEPING:
                if(startTime == 0){
                    startTime = System.currentTimeMillis();
                } else if(System.currentTimeMillis() - startTime >= (10000)){
                    changeState(State.ALARM);

                    startTime = 0;
                }
                break;
            case ALARM:
                if(startAlarmTime == 0){
                    startAlarmTime = System.currentTimeMillis();
                }
                ArduinoConnection.sendClick("z");


                break;
            default:
                break;
        }
        prevData = data;

        Log.d("STATE", "state: " + state.name());

    }

    private void changeState(State newState){
        state = newState;
        if(listener != null){
            listener.onStateChange(newState);
        }
    }

}
