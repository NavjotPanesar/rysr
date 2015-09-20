package co.rysr.rysr.Utils;

import android.content.Context;
import android.content.SharedPreferences;


public class PaymentUtil {

    private static final String PREFERENCE_NAME = "preferences_nonce";
    private static final String PREFERENCE_KEY_NONCE = "preferences_nonce";

    private static PaymentUtil instance;

    private Context context;

    public static void init(Context context) {
        instance = new PaymentUtil(context);
    }

    public static PaymentUtil getInstance() {
        return instance;
    }

    public PaymentUtil(Context context) {
        this.context = context;
    }

    public void saveNonce(String nonce) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREFERENCE_KEY_NONCE, nonce);
        editor.apply();
    }

    public String getNonce() {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getString(PREFERENCE_KEY_NONCE, "");
    }
}
