package com.codiodes.contactreaddemo.contact.entity;

import android.net.Uri;

/**
 * Created by Salman on 4/17/2016.
 */
public class Contact {

    private String contactId;
    private String contactName;
    private String contactPhone;
    private Uri photoUri;

    public Contact(String contactId, String contactName, String contactPhone, Uri photoUri) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.photoUri = photoUri;
    }

    public String getContactId() {
        return contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public Uri getPhotoUri() {
        return photoUri;
    }

    @Override
    public String toString() {
        return "Contact Id :" + contactId + System.getProperty("line.separator") +
                "Contact Name" + contactName + System.getProperty("line.separator") +
                "Contact Phone" + contactPhone + System.getProperty("line.separator") +
                "Photo URI" + photoUri;
    }
}
