package co.rysr.rysr;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by alvin on 19/09/15.
 */
public class RysrApplication extends Application{

        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
        }

        @Override
        public void onCreate() {
            super.onCreate();
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
