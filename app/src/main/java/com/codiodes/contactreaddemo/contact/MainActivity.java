package com.codiodes.contactreaddemo.contact;

import android.os.Bundle;
import android.util.Log;

import com.codiodes.contactreaddemo.CRDApplication;
import com.codiodes.contactreaddemo.R;
import com.codiodes.contactreaddemo.contact.entity.Contact;
import com.codiodes.contactreaddemo.contact.presenter.IContactPresenter;
import com.codiodes.contactreaddemo.contact.view.IContactView;
import com.codiodes.contactreaddemo.dagger.ApplicationComponent;
import com.codiodes.contactreaddemo.dagger.ContactComponent;
import com.codiodes.contactreaddemo.dagger.ContactModule;
import com.codiodes.contactreaddemo.dagger.DaggerContactComponent;
import com.codiodes.contactreaddemo.view.CRDActivity;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends CRDActivity implements IContactView {

    private static final String TAG = MainActivity.class.getSimpleName();
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
        mContactPresenter.loadContacts();
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
}
