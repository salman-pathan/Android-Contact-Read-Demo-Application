package com.codiodes.contactreaddemo.contact;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codiodes.contactreaddemo.CRDApplication;
import com.codiodes.contactreaddemo.R;
import com.codiodes.contactreaddemo.contact.entity.Contact;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Salman on 4/23/2016.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> mContactList;

    public ContactAdapter(List<Contact> mContactList) {
        this.mContactList = mContactList;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact contact = mContactList.get(position);
        holder.mContactName.setText(contact.getContactName());
        holder.mPhoneNumber.setText(contact.getContactPhone());
        Picasso.with(CRDApplication.getAppContext())
        .load(contact.getPhotoUri())
        .placeholder(R.drawable.person_placeholder)
        .error(R.drawable.person_placeholder)
        .into(holder.mContactPicture);
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        public TextView mContactName;
        public TextView mPhoneNumber;
        public ImageView mContactPicture;

        public ContactViewHolder(View itemView) {
            super(itemView);
            mContactName = (TextView) itemView.findViewById(R.id.contact_name);
            mPhoneNumber = (TextView) itemView.findViewById(R.id.contact_number);
            mContactPicture = (ImageView) itemView.findViewById(R.id.contact_image);
        }
    }

}
