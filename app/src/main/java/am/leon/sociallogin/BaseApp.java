package am.leon.sociallogin;

import android.app.Application;

public class BaseApp extends Application {

    private static BaseApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    public static synchronized BaseApp getInstance() {
        return instance;
    }

}