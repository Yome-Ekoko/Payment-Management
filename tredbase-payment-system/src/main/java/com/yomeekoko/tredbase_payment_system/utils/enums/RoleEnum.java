package com.yomeekoko.tredbase_payment_system.utils.enums;


import java.util.Arrays;
import java.util.List;

public enum RoleEnum {


    ROLE_ADMIN(Arrays.asList(
            PermissionEnum.READ_ONE_PARENT,
            PermissionEnum.READ_ALL_PARENT,
            PermissionEnum.CREATE_ONE_PARENT,
            PermissionEnum.UPDATE_ONE_PARENT,
            PermissionEnum.DELETE_ONE_PARENT,
            PermissionEnum.READ_ONE_STUDENT,
            PermissionEnum.READ_ALL_STUDENT,
            PermissionEnum.DISABLE_ONE_STUDENT,
            PermissionEnum.DELETE_ONE_STUDENT,
            PermissionEnum.CREATE_ONE_STUDENT,
            PermissionEnum.UPDATE_ONE_STUDENT,
            PermissionEnum.READ_MY_PROFILE,
            PermissionEnum.READ_ALL_PAYMENT,
            PermissionEnum.PROCESS_ALL_PAYMENT,
            PermissionEnum.ADD_RATE,
            PermissionEnum.ADD_ADMINISTRATORS

            )),


    ROLE_CUSTOMER(Arrays.asList(
            PermissionEnum.READ_MY_PROFILE
    ));

    private List<PermissionEnum> permissions;

    RoleEnum(List<PermissionEnum> permissions) {
        this.permissions = permissions;
    }

    public List<PermissionEnum> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionEnum> permissions) {
        this.permissions = permissions;
    }
}
