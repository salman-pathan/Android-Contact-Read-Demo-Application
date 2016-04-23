package com.codiodes.contactreaddemo.contact;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.codiodes.contactreaddemo.CRDApplication;
import com.codiodes.contactreaddemo.R;
import com.codiodes.contactreaddemo.contact.entity.Contact;
import com.codiodes.contactreaddemo.contact.presenter.IContactPresenter;
import com.codiodes.contactreaddemo.contact.view.IContactView;
import com.codiodes.contactreaddemo.dagger.component.ApplicationComponent;
import com.codiodes.contactreaddemo.dagger.component.ContactComponent;
import com.codiodes.contactreaddemo.dagger.component.DaggerContactComponent;
import com.codiodes.contactreaddemo.dagger.module.ContactModule;
import com.codiodes.contactreaddemo.view.CRDActivity;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends CRDActivity implements IContactView {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 12;
    //    @Bind(R.id.contact_list)
//    RecyclerView mContactList;

    @Inject
    IContactPresenter mContactPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadContacts();
    }


    @Override
    public void toggleProgressBar(boolean toggleStatus) {
        Log.d(TAG, "toggleProgressBar: " + toggleStatus);
    }

    @Override
    public void loadContacts() {
        Log.d(TAG, "Loading Contacts..");

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            
            
            
        }
    }

    @Override
    public void onLoadContacts(List<Contact> contactList) {
        Log.d(TAG, "Loading Contacts Completed");
        toggleProgressBar(false);
    }

    @Override
    protected void setupComponents() {
        ApplicationComponent applicationComponent = ((CRDApplication) getApplication()).getAppComponent();
        ContactComponent mContactComponent = DaggerContactComponent.builder()
                .applicationComponent(applicationComponent)
                .contactModule(new ContactModule(this))
                .build();
        mContactComponent.inject(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    mContactPresenter.loadContacts();


                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
