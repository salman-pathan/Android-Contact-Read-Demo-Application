package com.codiodes.contactreaddemo.dagger.component;

import com.codiodes.contactreaddemo.contact.model.ContactsProvider;
import com.codiodes.contactreaddemo.contact.presenter.IContactPresenter;
import com.codiodes.contactreaddemo.dagger.module.ContactProviderModule;
import com.codiodes.contactreaddemo.dagger.scope.ActivityScope;

import dagger.Component;

/**
 * Created by Salman on 4/23/2016.
 */
@ActivityScope
@Component(
        modules = {ContactProviderModule.class}
)
public interface ContactProviderComponent {
    void inject(IContactPresenter contactPresenter);
    ContactsProvider provideContactsProvider();
}
