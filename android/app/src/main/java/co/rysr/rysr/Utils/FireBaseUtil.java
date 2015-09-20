package co.rysr.rysr.Utils;

import com.firebase.client.Firebase;

/**
 * Created by alvin on 19/09/15.
 */
public class FireBaseUtil {
    public void connectToFirebase(){
        // Connection for firebase
        Firebase rootRef = new Firebase("https://fiery-fire-7147.firebaseio.com");
    }
}
