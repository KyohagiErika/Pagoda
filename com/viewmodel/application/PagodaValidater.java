package com.viewmodel.application;

import com.processor.applicaion.Validater;

public class PagodaValidater extends Validater {
    public static final String USERNAME_PATTERN = "^[A-Za-z0-9_-]{10,45}$";
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{10,45}$";
    public static final String PHONE_NUMBER_PATTERN = "^[0-9]{10}$";
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String STAR_RANK_PATTERN = "^[1-5]{1}$";
    public static final String ROOM_SIZE_PATTERN = "^0?[1-9]|[1-9][0-9]$";
    public static final String PRICE_PER_NIGHT_PATTERN = "^[0-9]+$";
    public static final String CAPACITY_PATTERN = "^[1-9]{1}$";
    public static final String DATE_PATTERN = "^(20\\d\\d)-((0?[1-9])|(1[012]))-((0?[1-9])|([12][0-9])|(3[01])) (([01]?[0-9])|(2[0-3]))$";
    public static final String CCCD_PATTERN = "^[0-9]{12}$";
    public static final String VOTE_PATTERN = "^0?[0-9]|10{1,2}$";
    public static final String ROOM_ID_PATTERN = "^[0-9]{3,10}$";
    public static final String URL_PATTERN = "^(?i)(https?|ftp|file|www)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    public static final String PAYMENT_METHOD_PATTERN = "^CASH|CREDIT$";
}
