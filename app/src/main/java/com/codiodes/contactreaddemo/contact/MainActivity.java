package com.codiodes.contactreaddemo.contact;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

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

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends CRDActivity implements IContactView {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 12;
    ProgressDialog mProgressDialog;

    @Bind(R.id.contact_list)
    RecyclerView mContactList;

    @Inject
    IContactPresenter mContactPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContactList = (RecyclerView) findViewById(R.id.contact_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mContactList.setLayoutManager(layoutManager);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.loading_contacts));
        mProgressDialog.setIndeterminate(true);
        loadContacts();
    }


    @Override
    public void toggleProgressBar(boolean toggleStatus) {
        Log.d(TAG, "toggleProgressBar: " + toggleStatus);
        if (toggleStatus) {
            mProgressDialog.show();
        } else {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void loadContacts() {
        Log.d(TAG, "Loading Contacts..");

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        } else {
            mContactPresenter.loadContacts();
        }
    }

    @Override
    public void onLoadContacts(List<Contact> contactList) {
        Log.d(TAG, "Loading Contacts Completed");
        toggleProgressBar(false);
        ContactAdapter contactAdapter = new ContactAdapter(contactList);
        mContactList.setAdapter(contactAdapter);
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
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    toggleProgressBar(true);
                    mContactPresenter.loadContacts();
                } else {
                    Toast.makeText(MainActivity.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}
