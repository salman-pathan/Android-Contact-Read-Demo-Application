package com.codiodes.contactreaddemo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;

/**
 * Created by Salman on 4/17/2016.
 */
public class CRDActivity extends CRDAbstractActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        injectViews();
        setupComponents();
    }

//    @Override
//    protected void injectViews() {
//        ButterKnife.bind(this);
//    }

    @Override
    protected void setupComponents() {

    }
}
