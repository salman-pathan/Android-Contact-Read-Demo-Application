package com.codiodes.contactreaddemo.dagger.module;

import com.codiodes.contactreaddemo.contact.model.ContactsProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Salman on 4/23/2016.
 */

@Module
public class ContactProviderModule {

    @Provides
    public ContactsProvider provideContactsProvider() {
        return  new ContactsProvider();
    }

}
