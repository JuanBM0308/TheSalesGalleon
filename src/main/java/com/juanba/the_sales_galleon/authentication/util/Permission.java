package com.juanba.the_sales_galleon.authentication.util;

public enum Permission {
    // * Products permissions
    READ_ALL_PRODUCTS,
    FIND_PRODUCT,
    READ_ACTIVE_PRODUCTS,
    SAVE_ONE_PRODUCT,
    DELETE_ONE_PRODUCT,
    UPDATE_ONE_PRODUCT,

    // * User permissions
    READ_ALL_USERS,
    FIND_USER_BY_ID,
    FIND_USER_BY_EMAIL,
    DEACTIVATE_USER,
    ACTIVATE_USER,
    SAVE_ONE_USER,
    DELETE_ONE_USER,
    UPDATE_ONE_USER,
    UPDATE_USER_PASSWORD,
    GENERATE_VERIFICATION_CODE,

    //* Category permissions
    READ_ALL_CATEGORIES,
    FIND_CATEGORY,

    // * VendorRating permissions
    FIND_RATING_VENDOR,
    CREATE_ONE_RATING_VENDOR,
    DELETE_ONE_RATING;
}
