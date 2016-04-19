package com.codiodes.contactreaddemo.dagger;

import com.codiodes.contactreaddemo.contact.model.ContactsProvider;
import com.codiodes.contactreaddemo.contact.presenter.ContactPresenterCompl;
import com.codiodes.contactreaddemo.contact.presenter.IContactPresenter;
import com.codiodes.contactreaddemo.contact.view.IContactView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Salman on 4/18/2016.
 */

@Module
public class ContactModule {

    private IContactView mContactView;

    public ContactModule(IContactView mContactView) {
        this.mContactView = mContactView;
    }

    @Provides
    public ContactsProvider provideContactsProvider() {
        return  new ContactsProvider();
    }

    @Provides
    public IContactView provideContactView() {
        return mContactView;
    }

    @Provides
    public IContactPresenter provideContactPresenter(IContactView contactView) {
        return new ContactPresenterCompl(contactView);
    }

}
