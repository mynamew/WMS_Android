package com.timi.sz.wms_android.bean;

import java.util.List;

/**
 * 登录的返回
 */

public class LoginBean {


    /**
     * userId : 2
     * fullName : ADMIN
     * currentOrgUnit : {"id":1,"parentId":null,"code":"00000","displayName":"玖坤集团"}
     * token : DfAM7PQ44MyPnm2VLuH9MC1pj31TYyEYOjtZ_lTKwUIQf3Fwh0eEpzI4DFpcqLoKzJMqN_KFo898hkp9P81BOdINXWQ2ZIeIrGoDlWPgxo-spI8EK1DbRdTg557qQq6O8DFetd-IVYI7Ci0T_6o6SEWR6vYbT26NPNH5e5k6oVS62dWOIZ5a4Botf88m6M_IbXhIAXhXhOBDc_0-4PqvHefujw-RPZhqBj16fvx0NJl68WiV1I-L4YEWydmnggDgj6HwNBE2AebsAuyD5OXP_-aYUnv2zP21Ebm0DrrF7Q26eTOGNZi8buAvDK3604auFLCkOvfOL2dmA4clxfEszI3iQ2bkRbQMVEEUgOKFxLLU5gkSgmGOoTcFv2gBjIzts-_cjBJYnPGkDSrSxQjuTrk8YeCWphzHtVFBtsMBcrCSxbz15HslfIh4tpvQbhnUhxtmcoO7NdHgTyKbiwL6OfBn9nlsKhUpS9PBnh3WftuEGoZUEC9dr8uL_QbGnEuJckDuaPU--_js-c9GYwTzHOJDFy6mnOz2PHGSI7GoJFSK9rD0znMK6YU24o_Utm16hBBcysNrv2zKYOrlD6RryA
     * grantPermission : {"permissionCode":"WPDA","permissionName":"WPDA","childPermissions":[{"permissionCode":"InStock","permissionName":"入库作业","childPermissions":[{"permissionCode":"ReceiveByOrder","permissionName":"采购单","childPermissions":null},{"permissionCode":"ReceiveByDelivery","permissionName":"送货单","childPermissions":null}]}]}
     * orgUnits : [{"id":1,"parentId":null,"code":"00000","displayName":"/  玖坤集团 "},{"id":3,"parentId":1,"code":"00000.00002","displayName":"/  玖坤集团 /  苏州玖智坤 "}]
     */

    private int userId;
    private String fullName;
    private CurrentOrgUnitBean currentOrgUnit;
    private String token;
    private GrantPermissionBean grantPermission;
    private List<OrgUnitsBean> orgUnits;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public CurrentOrgUnitBean getCurrentOrgUnit() {
        return currentOrgUnit;
    }

    public void setCurrentOrgUnit(CurrentOrgUnitBean currentOrgUnit) {
        this.currentOrgUnit = currentOrgUnit;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public GrantPermissionBean getGrantPermission() {
        return grantPermission;
    }

    public void setGrantPermission(GrantPermissionBean grantPermission) {
        this.grantPermission = grantPermission;
    }

    public List<OrgUnitsBean> getOrgUnits() {
        return orgUnits;
    }

    public void setOrgUnits(List<OrgUnitsBean> orgUnits) {
        this.orgUnits = orgUnits;
    }

    public static class CurrentOrgUnitBean {
        /**
         * id : 1
         * parentId : null
         * code : 00000
         * displayName : 玖坤集团
         */

        private int id;
        private Object parentId;
        private String code;
        private String displayName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }
    }

    public static class GrantPermissionBean {
        /**
         * permissionCode : WPDA
         * permissionName : WPDA
         * childPermissions : [{"permissionCode":"InStock","permissionName":"入库作业","childPermissions":[{"permissionCode":"ReceiveByOrder","permissionName":"采购单","childPermissions":null},{"permissionCode":"ReceiveByDelivery","permissionName":"送货单","childPermissions":null}]}]
         */

        private String permissionCode;
        private String permissionName;
        private List<ChildPermissionsBeanX> childPermissions;

        public String getPermissionCode() {
            return permissionCode;
        }

        public void setPermissionCode(String permissionCode) {
            this.permissionCode = permissionCode;
        }

        public String getPermissionName() {
            return permissionName;
        }

        public void setPermissionName(String permissionName) {
            this.permissionName = permissionName;
        }

        public List<ChildPermissionsBeanX> getChildPermissions() {
            return childPermissions;
        }

        public void setChildPermissions(List<ChildPermissionsBeanX> childPermissions) {
            this.childPermissions = childPermissions;
        }

        public static class ChildPermissionsBeanX {
            /**
             * permissionCode : InStock
             * permissionName : 入库作业
             * childPermissions : [{"permissionCode":"ReceiveByOrder","permissionName":"采购单","childPermissions":null},{"permissionCode":"ReceiveByDelivery","permissionName":"送货单","childPermissions":null}]
             */

            private String permissionCode;
            private String permissionName;
            private List<ChildPermissionsBean> childPermissions;

            public String getPermissionCode() {
                return permissionCode;
            }

            public void setPermissionCode(String permissionCode) {
                this.permissionCode = permissionCode;
            }

            public String getPermissionName() {
                return permissionName;
            }

            public void setPermissionName(String permissionName) {
                this.permissionName = permissionName;
            }

            public List<ChildPermissionsBean> getChildPermissions() {
                return childPermissions;
            }

            public void setChildPermissions(List<ChildPermissionsBean> childPermissions) {
                this.childPermissions = childPermissions;
            }

            public static class ChildPermissionsBean {
                /**
                 * permissionCode : ReceiveByOrder
                 * permissionName : 采购单
                 * childPermissions : null
                 */

                private String permissionCode;
                private String permissionName;
                private Object childPermissions;

                public String getPermissionCode() {
                    return permissionCode;
                }

                public void setPermissionCode(String permissionCode) {
                    this.permissionCode = permissionCode;
                }

                public String getPermissionName() {
                    return permissionName;
                }

                public void setPermissionName(String permissionName) {
                    this.permissionName = permissionName;
                }

                public Object getChildPermissions() {
                    return childPermissions;
                }

                public void setChildPermissions(Object childPermissions) {
                    this.childPermissions = childPermissions;
                }
            }
        }
    }

    public static class OrgUnitsBean {
        /**
         * id : 1
         * parentId : null
         * code : 00000
         * displayName : /  玖坤集团
         */

        private int id;
        private Object parentId;
        private String code;
        private String displayName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }
    }
}
