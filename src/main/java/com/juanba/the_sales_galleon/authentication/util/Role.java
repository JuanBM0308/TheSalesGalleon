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
            Permission.GENERATE_VERIFICATION_CODE
    )),
    CUSTOMER(Arrays.asList(
            // * Products permissions
            Permission.READ_ALL_PRODUCTS,
            Permission.FIND_PRODUCT,
            Permission.READ_ACTIVE_PRODUCTS,

            // * User permissions
            Permission.UPDATE_USER_PASSWORD,
            Permission.GENERATE_VERIFICATION_CODE
    )),
    VENDOR(Arrays.asList(
            // * Products permissions
            Permission.SAVE_ONE_PRODUCT,
            Permission.UPDATE_ONE_PRODUCT,

            // * User permissions
            Permission.FIND_USER_BY_EMAIL,
            Permission.UPDATE_USER_PASSWORD,
            Permission.GENERATE_VERIFICATION_CODE
    )),
    VISITOR(Arrays.asList(
            // * Products permissions
            Permission.READ_ALL_PRODUCTS
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
