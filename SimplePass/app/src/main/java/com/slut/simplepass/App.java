package com.slut.simplepass;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.slut.simplepass.db.Const;
import com.slut.simplepass.utils.ToastUtils;

/**
 * Created by 七月在线科技 on 2016/11/22.
 */

public class App extends Application {

    private static Context context = null;//全局上下文
    private static DaoSession daoSession;
    private int activityCount;

    @Override
    public void onCreate() {
        super.onCreate();
        setContext(getApplicationContext());
        initDaoSession();
        regActivityLifeCycle();
    }

    private void regActivityLifeCycle() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                activityCount++;
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                activityCount--;
                if (activityCount == 0) {
                    ToastUtils.showShort("app已处于后台运行状态");
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        App.context = context;
    }

    private void initDaoSession() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(App.getContext(), Const.DB_NAME);
        SQLiteDatabase database = devOpenHelper.getWritableDatabase();
        App.daoSession = new DaoMaster(database).newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
