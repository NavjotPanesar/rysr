package co.rysr.rysr;

import android.app.Application;
import android.content.res.Configuration;

import com.firebase.client.Firebase;

import co.rysr.rysr.Utils.PaymentUtil;

/**
 * Created by alvin on 19/09/15.
 */
public class RysrApplication extends Application{
        private static RysrApplication sInstance;

        public static RysrApplication getInstance(){
            return sInstance;
        }

        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
        }

        @Override
        public void onCreate() {
            super.onCreate();
            sInstance = this;
            Firebase.setAndroidContext(this);
            PaymentUtil.init(this);
        }

        @Override
        public void onLowMemory() {
            super.onLowMemory();
        }

        @Override
        public void onTerminate() {
            super.onTerminate();
        }

}
