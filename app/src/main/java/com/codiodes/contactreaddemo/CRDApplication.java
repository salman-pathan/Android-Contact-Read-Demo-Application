package com.codiodes.contactreaddemo;

import android.app.Application;
import android.content.Context;

import com.codiodes.contactreaddemo.dagger.ApplicationComponent;
import com.codiodes.contactreaddemo.dagger.ApplicationModule;
import com.codiodes.contactreaddemo.dagger.DaggerApplicationComponent;


/**
 * Created by Salman on 4/17/2016.
 */
public class CRDApplication extends Application {

    public static Context mContext;
    private ApplicationComponent mComponent;

    public CRDApplication() {
        mContext = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mComponent.inject(this);
    }

    public ApplicationComponent getAppComponent() {
        return mComponent;
    }

    public static Context getAppContext() {
        return mContext;
    }
}
