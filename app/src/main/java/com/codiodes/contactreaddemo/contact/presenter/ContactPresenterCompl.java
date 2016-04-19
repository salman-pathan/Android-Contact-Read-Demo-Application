package com.codiodes.contactreaddemo.contact.presenter;

import android.content.Context;

import com.codiodes.contactreaddemo.CRDApplication;
import com.codiodes.contactreaddemo.contact.model.ContactsProvider;
import com.codiodes.contactreaddemo.contact.view.IContactView;

import javax.inject.Inject;

/**
 * Created by Salman on 4/17/2016.
 */
public class ContactPresenterCompl implements IContactPresenter {


    private IContactView mContactView;
    private ContactsProvider mContactsProvider;

    @Inject
    public ContactPresenterCompl(IContactView contactView) {
        mContactView = contactView;
    }

    @Override
    public void loadContacts() {
        mContactView.toggleProgressBar(true);
        mContactView.onLoadContacts(mContactsProvider.getContacts());
    }

}
