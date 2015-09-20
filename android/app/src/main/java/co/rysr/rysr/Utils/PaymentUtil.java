package co.rysr.rysr.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import co.rysr.rysr.API.BraintreeService;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class PaymentUtil {

    private static final String PREFERENCE_NAME = "preferences_nonce";
    private static final String PREFERENCE_KEY_NONCE = "preferences_nonce";

    private static PaymentUtil instance;

    private Context context;
    private BraintreeService service;
    private String clientToken;

    public static void init(Context context) {
        instance = new PaymentUtil(context);
    }

    public static PaymentUtil getInstance() {
        return instance;
    }

    public PaymentUtil(Context context) {
        this.context = context;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.21.157.65:5000/checkout")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.service = retrofit.create(BraintreeService.class);

        this.service.getClientToken().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    PaymentUtil.this.clientToken = response.body().string();
                    Log.i("RYSY", "Client Token:" + PaymentUtil.this.clientToken);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("RYSR", t.getMessage());
                t.printStackTrace();
            }
        });
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

    public BraintreeService getRESTService() {
        return service;
    }

    public String getClientToken() {
        return clientToken;
    }
}
