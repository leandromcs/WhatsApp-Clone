package com.example.whatsappclone.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.example.whatsappclone.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class FirebaseUtils {

    public static String createConversaKey(String a, String b) {
        Long a1 = Long.valueOf(a.replace("+", ""));
        Long a2 = Long.valueOf(b.replace("+", ""));
        Integer comparacao = a1.compareTo(a2);

        if (comparacao.equals(0)) {
            return a + "-" + b;
        } else if (comparacao < 0) {
            return a + "-" + b;
        } else {
            return b + "-" + a;
        }

    }

    public static List<Contato> getContactList(ContentResolver cr) {
        List<Contato> contatos = new ArrayList<>();

        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contatos.add(new Contato(name, phoneNo));
                    }
                    pCur.close();
                }
            }
        }
        if (cur != null) {
            cur.close();
        }

        return contatos;
    }
}
