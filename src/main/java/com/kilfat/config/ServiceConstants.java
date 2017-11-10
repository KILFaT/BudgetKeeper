package com.kilfat.config;

import com.kilfat.database.entity.enums.RoleType;

public interface ServiceConstants {

    String API_URL = "/api/";
    String USER = "user/";
    String ACCOUNT = "account/";
    String CATEGORY = "category/";
    String TRANSACTION = "transaction/";
    String TEMPLATE = "template/";

    String USER_PATH = API_URL + USER;
    String ACCOUNT_PATH = API_URL + ACCOUNT;
    String CATEGORY_PATH = API_URL + CATEGORY;
    String TRANSACTION_PATH = API_URL + TRANSACTION;
    String TEMPLATE_PATH = API_URL + TEMPLATE;

    String ROLE_ADMIN = RoleType.ADMIN.toString();
    String ROLE_USER = RoleType.USER.toString();

    String REALM = "BUDGET_KEEPER";
    String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
}
