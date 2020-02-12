package com.example.whatsappclone.utils;

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
}
