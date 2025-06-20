package com.juanba.the_sales_galleon.authentication.util;

import java.util.Arrays;
import java.util.List;

public enum Role {
    ADMINISTRATOR(Arrays.asList(
            // * Products permissions
            Permission.READ_ALL_PRODUCTS,
            Permission.FIND_PRODUCT,
            Permission.READ_ACTIVE_PRODUCTS,
            Permission.SAVE_ONE_PRODUCT,
            Permission.DELETE_ONE_PRODUCT,
            Permission.UPDATE_ONE_PRODUCT,

            // * User permissions
            Permission.READ_ALL_USERS,
            Permission.FIND_USER_BY_ID,
            Permission.FIND_USER_BY_EMAIL,
            Permission.DEACTIVATE_USER,
            Permission.ACTIVATE_USER,
            Permission.SAVE_ONE_USER,
            Permission.DELETE_ONE_USER,
            Permission.UPDATE_ONE_USER,
            Permission.UPDATE_USER_PASSWORD,
            Permission.GENERATE_VERIFICATION_CODE,

            //* Category permissions
            Permission.READ_ALL_CATEGORIES,
            Permission.FIND_CATEGORY,

            // * VendorRating permissions
            Permission.FIND_RATING_VENDOR,
            Permission.CREATE_ONE_RATING_VENDOR,
            Permission.DELETE_ONE_RATING
    )),
    CUSTOMER(Arrays.asList(
            // * Products permissions
            Permission.READ_ALL_PRODUCTS,
            Permission.FIND_PRODUCT,
            Permission.READ_ACTIVE_PRODUCTS,

            // * User permissions
            Permission.UPDATE_USER_PASSWORD,
            Permission.GENERATE_VERIFICATION_CODE,

            //* Category permissions
            Permission.READ_ALL_CATEGORIES,
            Permission.FIND_CATEGORY,

            // * VendorRating permissions
            Permission.FIND_RATING_VENDOR,
            Permission.CREATE_ONE_RATING_VENDOR
    )),
    VENDOR(Arrays.asList(
            // * Products permissions
            Permission.SAVE_ONE_PRODUCT,
            Permission.UPDATE_ONE_PRODUCT,

            // * User permissions
            Permission.FIND_USER_BY_EMAIL,
            Permission.UPDATE_USER_PASSWORD,
            Permission.GENERATE_VERIFICATION_CODE,

            //* Category permissions
            Permission.READ_ALL_CATEGORIES,
            Permission.FIND_CATEGORY,

            // * VendorRating permissions
            Permission.FIND_RATING_VENDOR,
            Permission.CREATE_ONE_RATING_VENDOR,
            Permission.DELETE_ONE_RATING
    )),
    VISITOR(Arrays.asList(
            // * Products permissions
            Permission.READ_ALL_PRODUCTS,

            //* Category permissions
            Permission.READ_ALL_CATEGORIES,
            Permission.FIND_CATEGORY,

            // * VendorRating permissions
            Permission.FIND_RATING_VENDOR,
            Permission.CREATE_ONE_RATING_VENDOR
    ));

    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
