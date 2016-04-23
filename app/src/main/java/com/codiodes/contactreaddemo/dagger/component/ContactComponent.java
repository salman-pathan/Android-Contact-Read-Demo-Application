package com.codiodes.contactreaddemo.dagger.component;

import com.codiodes.contactreaddemo.contact.MainActivity;
import com.codiodes.contactreaddemo.contact.model.ContactsProvider;
import com.codiodes.contactreaddemo.contact.presenter.IContactPresenter;
import com.codiodes.contactreaddemo.dagger.module.ContactModule;
import com.codiodes.contactreaddemo.dagger.scope.ActivityScope;

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
    void inject(IContactPresenter contactPresenter);
    IContactPresenter getContactPresenter();
    ContactsProvider getContactsProvider();
}
