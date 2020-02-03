package am.leon.sociallogin;

import android.app.Application;

public class BaseApp extends Application {

    private SocialGlobalConst aConst;
    private static BaseApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        aConst = SocialGlobalConst.getInstance();
        initAppConst();
    }


    public static synchronized BaseApp getInstance() {
        return instance;
    }

    private void initAppConst() {
        aConst.setTwitter_CONSUMER_KEY("Cevf2j1lZ9iid7czOXhLwbFDE");
        aConst.setTwitter_CONSUMER_SECRET("wxV1ob1mGIh4s6EziC1DdgK6FhzGQFstynGGzmAu6zIDvosmal");
    }
}