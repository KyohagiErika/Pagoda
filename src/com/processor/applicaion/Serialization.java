package com.processor.applicaion;

import java.io.*;
import java.util.Base64;

public class Serialization {
    private static final String CHARACTER_PATTERN = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String getRandomIDString(int length) {
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < length; i++) {
            id.append((int) (Math.random()*CHARACTER_PATTERN.length()));
        }
        return id.toString();
    }

    public static String encode(Object obj) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(byteStream);
        oos.writeObject(obj);
        oos.close();
        byte[] encodedData = byteStream.toByteArray();
        byteStream.close();
        return Base64.getEncoder().encodeToString(encodedData);
    }

    public static Object decode(String data) throws IOException, ClassNotFoundException {
        byte[] encodedData = Base64.getDecoder().decode(data);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(encodedData));
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }
}
