package com.codiodes.contactreaddemo.contact.view;

import com.codiodes.contactreaddemo.contact.entity.Contact;

import java.util.List;

/**
 * Created by Salman on 4/17/2016.
 */
public interface IContactView {

    void toggleProgressBar(boolean toggleStatus);
    void loadContacts();
    void onLoadContacts(List<Contact> contactList);
}
