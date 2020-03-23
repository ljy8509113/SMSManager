package com.sms.send.smsmanager;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.sms.send.smsmanager.model.MyContacts;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class ContactList {

    private static ContactList instance;
    public static ContactList getInstance(Activity activity){
        if(instance == null){
            instance = new ContactList();
        }
        instance._activity = activity;
        return instance;
    }

    public Activity _activity;

    public ArrayList<MyContacts> getContactList() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[] {
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.Contacts.PHOTO_ID,
                ContactsContract.Contacts._ID
        };

        String[] selectionArgs =null;
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

        Cursor cursor = _activity.getContentResolver().query(uri, projection, null,
                selectionArgs, sortOrder);

        int no = 0;

        LinkedHashSet<MyContacts> hasList = new LinkedHashSet<>();
        ArrayList<MyContacts> contactsList;

        if (cursor.moveToFirst()) {
            do {
                long photo_id = cursor.getLong(2);
                long person = cursor.getLong(3);

                MyContacts myContact = new MyContacts();
                myContact.phone = cursor.getString(0);
                myContact.fullName = cursor.getString(1);
                myContact.thumnailId = photo_id;
                myContact.personId = person;
                myContact.no = no + 1;

                if (myContact.phone.startsWith("01")) {
                    hasList.add(myContact);
                    //contactsList.add(myContact);
                    Log.d("<<CONTACTS>>", "name=" + myContact.fullName + ", phone=" + myContact.phone);
                }

            } while (cursor.moveToNext());
        }

        contactsList = new ArrayList<MyContacts>(hasList);
        for (int i = 0; i < contactsList.size(); i++) {
            contactsList.get(i).id = i;
        }

        if (cursor != null) {
            cursor.close();
        }

        return contactsList;
    }

}
