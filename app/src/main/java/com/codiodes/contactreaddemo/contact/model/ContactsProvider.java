package com.codiodes.contactreaddemo.contact.model;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.codiodes.contactreaddemo.CRDApplication;
import com.codiodes.contactreaddemo.contact.entity.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salman on 4/17/2016.
 */
public class ContactsProvider {

    private static final String TAG = ContactsProvider.class.getSimpleName();
    private Uri CONTACT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    private static final String CONTACT_ID = ContactsContract.Contacts._ID;
    private static final String  CONTACT_NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
    private static final String CONTACT_PHONE_NO = ContactsContract.CommonDataKinds.Phone.NUMBER;
    private static final String CONTACT_PHOTO = ContactsContract.CommonDataKinds.Photo.PHOTO;
    private final ContentResolver mContentResolver;

    public ContactsProvider() {
        mContentResolver = CRDApplication.getAppContext().getContentResolver();
    }

    public List<Contact> getContacts() {
        List<Contact> contactList = new ArrayList<>();
        String[] projection = new String[]{CONTACT_ID, CONTACT_NAME, CONTACT_PHONE_NO, CONTACT_PHOTO};
        Cursor cursor = mContentResolver.query(CONTACT_URI, projection, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Contact contact = getContact(cursor);
                contactList.add(contact);
            }
            cursor.close();
        }

        return contactList;
    }

    private Contact getContact(Cursor cursor) {
        String contactId = cursor.getString(cursor.getColumnIndex(CONTACT_ID));
        String name = cursor.getString(cursor.getColumnIndex(CONTACT_NAME));
//        Uri phoneUri = Uri.withAppendedPath(CONTACT_URI, String.valueOf(contactId));
        String phoneUri = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        Uri personUri = ContentUris.withAppendedId(CONTACT_URI, Long.parseLong(contactId));
        Uri photoUri = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            photoUri = Uri.withAppendedPath(personUri, ContactsContract.Contacts.Photo.PHOTO);
        }
        if (name.trim().equals("")) {
            return null;
        }
        Contact contact = new Contact(contactId, name, String.valueOf(phoneUri), photoUri);
        Log.d(TAG, contact.toString());
        return contact;
    }
}
