package com.codiodes.contactreaddemo.dagger;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.codiodes.contactreaddemo.contact.presenter.IContactPresenter;

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
