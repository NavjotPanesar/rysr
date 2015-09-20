package co.rysr.rysr.Utils;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alvin on 19/09/15.
 */
public class FireBaseUtil {

    private Firebase rootRef;

    public FireBaseUtil(){

        // Connect to the database when you create the class
        this.rootRef = new Firebase("https://fiery-fire-7147.firebaseio.com");

    }

    public String createUser(String name){
        // Connect to the user table
        Firebase userTable = this.rootRef.child("users");
        // Generate UniqueID
        Firebase thisUser = userTable.push();

        Map<String, String> user = new HashMap<String, String>();
        user.put("name", name);

        // Add the user to the database
        thisUser.setValue(user);
        String postId = thisUser.getKey();
        return postId;

    }

    public void addSleepData(String userId, Map<Integer,Integer> sleepData){
        // Go to the user's data table
        Firebase thisUserTable = this.rootRef.child("users").child(userId);
        // Insert sleep data
        thisUserTable.setValue(sleepData);

    }


}
