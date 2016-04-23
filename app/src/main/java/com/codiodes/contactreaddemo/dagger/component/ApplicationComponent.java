package com.codiodes.contactreaddemo.dagger.component;

import android.app.Application;

import com.codiodes.contactreaddemo.dagger.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Salman on 4/18/2016.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(Application application);
    Application getApplication();
}
