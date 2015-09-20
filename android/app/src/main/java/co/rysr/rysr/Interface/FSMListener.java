package co.rysr.rysr.Interface;

import co.rysr.rysr.Utils.StateMachine;

/**
 * Created by Navjot on 9/20/2015.
 */
public interface FSMListener {
    public void onStateChange(StateMachine.State state);
}
