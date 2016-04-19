package com.codiodes.contactreaddemo.dagger;

import android.app.Activity;

import com.codiodes.contactreaddemo.contact.MainActivity;
import com.codiodes.contactreaddemo.contact.presenter.IContactPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Salman on 4/19/2016.
 */
@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {ContactModule.class}
)
public interface ContactComponent {
    void inject(MainActivity activity);
    IContactPresenter getContactPresenter();
}
