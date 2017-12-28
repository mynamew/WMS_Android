package com.timi.sz.wms_android.bean;

import java.util.List;

/**
 * 登录的返回
 */

public class LoginBean {


    /**
     * userId : 55
     * fullName : mes
     * currentOrgUnit : {"id":1,"parentId":null,"code":"00000","displayName":"默认组织"}
     * token : fOZCgWO-a74_rNTHUYj4_FD_6rwu6ApkMyVjiF157Og9Pu833wPeWyVWz0mvfDqgCBZpmRc4Uc2m4_wQnSar8AWS6ulM5u6wHdWNButMTDOAjR2cRNo36oSRy4jvsd1DfuTYNQsCELCQiQITkmxxg4OTc4mKgZstMTtzS3MPzc9ACWSiviX2NWLz5N-bhRQXp8EiUQsYkIA3lXsKxT7FiBXVJyv9HtCIMdJlnrsBG38FXCh9jdVQJw-zysqexTtrywJFVLfueNHI2NvRWLhON0OW3X2N8vcsH5HmdynVfMncBoArk-WRXDqAyH7FwtiubKlS3QzIHzhBGMeBu659e21vB3_H83AxduXoUADc6AWwYZ0EF-L0z1Ve_3Q9_27RpF8v8Ro54BEVGFvlUHWI07QGOjFQpLawZe9S0HcReZvPXOV5VGeUTH6vHsiY1DT4KrHo0sE-n-KCHUg06AvY9Sxi3I0FUQq77Q3RN68-NEZVznuV00VFZ4EqHAXVBlZK7_9L-_Oy135qZ9xBAYYvTQ
     * grantPermission : {"permissionCode":"Pages.WPDA","permissionName":"WPDA","childPermissions":[{"permissionCode":"Pages.WPDA.InStock","permissionName":"入库作业","childPermissions":[{"permissionCode":"Pages.WPDA.InStock.ReceiveByOrder","permissionName":"采购单收货","childPermissions":null},{"permissionCode":"Pages.WPDA.InStock.ReceiveByDelivery","permissionName":"送货单收货","childPermissions":null},{"permissionCode":"Pages.WPDA.IQC","permissionName":"来料质检","childPermissions":null},{"permissionCode":"Pages.WPDA.BarcodeEdit","permissionName":"无质检条码修改","childPermissions":null},{"permissionCode":"Pages.WPDA.PurInstock","permissionName":"来料入库","childPermissions":null},{"permissionCode":"Pages.WPDA.OtherInstock","permissionName":"其他入库","childPermissions":null}]},{"permissionCode":"Pages.WPDA.OutStock","permissionName":"出库作业","childPermissions":[{"permissionCode":"Pages.WPDA.PurReturn","permissionName":"采购退料","childPermissions":null},{"permissionCode":"Pages.WPDA.WWPick","permissionName":"委外发料","childPermissions":null},{"permissionCode":"Pages.WPDA.WWFeed","permissionName":"委外补料","childPermissions":null},{"permissionCode":"Pages.WPDA.WPDA_WWTransfer","permissionName":"委外调拨","childPermissions":null},{"permissionCode":"Pages.WPDA.PrdPick","permissionName":"生产领料","childPermissions":null},{"permissionCode":"Pages.WPDA.PrdTransfer","permissionName":"生产调拨","childPermissions":null},{"permissionCode":"Pages.WPDA.PrdFeed","permissionName":"生产补料","childPermissions":null},{"permissionCode":"Pages.WPDA.SalesOutStock","permissionName":"销售出库","childPermissions":null},{"permissionCode":"Pages.WPDA.OtherOutStock","permissionName":"其他出库","childPermissions":null}]},{"permissionCode":"Pages.WPDA.StockIn","permissionName":"库内作业","childPermissions":[{"permissionCode":"Pages.WPDA.Transfer","permissionName":"调拨","childPermissions":null},{"permissionCode":"Pages.WPDA.QueryStock","permissionName":"库存查询","childPermissions":null},{"permissionCode":"Pages.WPDA.MatConvert","permissionName":"形态转换","childPermissions":null},{"permissionCode":"Pages.WPDA.StockAdjust","permissionName":"库位调整","childPermissions":null}]}]}
     * orgUnits : [{"id":1,"parentId":null,"code":"00000","displayName":"/  默认组织 "}]
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
         * displayName : 默认组织
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
         * permissionCode : Pages.WPDA
         * permissionName : WPDA
         * childPermissions : [{"permissionCode":"Pages.WPDA.InStock","permissionName":"入库作业","childPermissions":[{"permissionCode":"Pages.WPDA.InStock.ReceiveByOrder","permissionName":"采购单收货","childPermissions":null},{"permissionCode":"Pages.WPDA.InStock.ReceiveByDelivery","permissionName":"送货单收货","childPermissions":null},{"permissionCode":"Pages.WPDA.IQC","permissionName":"来料质检","childPermissions":null},{"permissionCode":"Pages.WPDA.BarcodeEdit","permissionName":"无质检条码修改","childPermissions":null},{"permissionCode":"Pages.WPDA.PurInstock","permissionName":"来料入库","childPermissions":null},{"permissionCode":"Pages.WPDA.OtherInstock","permissionName":"其他入库","childPermissions":null}]},{"permissionCode":"Pages.WPDA.OutStock","permissionName":"出库作业","childPermissions":[{"permissionCode":"Pages.WPDA.PurReturn","permissionName":"采购退料","childPermissions":null},{"permissionCode":"Pages.WPDA.WWPick","permissionName":"委外发料","childPermissions":null},{"permissionCode":"Pages.WPDA.WWFeed","permissionName":"委外补料","childPermissions":null},{"permissionCode":"Pages.WPDA.WPDA_WWTransfer","permissionName":"委外调拨","childPermissions":null},{"permissionCode":"Pages.WPDA.PrdPick","permissionName":"生产领料","childPermissions":null},{"permissionCode":"Pages.WPDA.PrdTransfer","permissionName":"生产调拨","childPermissions":null},{"permissionCode":"Pages.WPDA.PrdFeed","permissionName":"生产补料","childPermissions":null},{"permissionCode":"Pages.WPDA.SalesOutStock","permissionName":"销售出库","childPermissions":null},{"permissionCode":"Pages.WPDA.OtherOutStock","permissionName":"其他出库","childPermissions":null}]},{"permissionCode":"Pages.WPDA.StockIn","permissionName":"库内作业","childPermissions":[{"permissionCode":"Pages.WPDA.Transfer","permissionName":"调拨","childPermissions":null},{"permissionCode":"Pages.WPDA.QueryStock","permissionName":"库存查询","childPermissions":null},{"permissionCode":"Pages.WPDA.MatConvert","permissionName":"形态转换","childPermissions":null},{"permissionCode":"Pages.WPDA.StockAdjust","permissionName":"库位调整","childPermissions":null}]}]
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
             * permissionCode : Pages.WPDA.InStock
             * permissionName : 入库作业
             * childPermissions : [{"permissionCode":"Pages.WPDA.InStock.ReceiveByOrder","permissionName":"采购单收货","childPermissions":null},{"permissionCode":"Pages.WPDA.InStock.ReceiveByDelivery","permissionName":"送货单收货","childPermissions":null},{"permissionCode":"Pages.WPDA.IQC","permissionName":"来料质检","childPermissions":null},{"permissionCode":"Pages.WPDA.BarcodeEdit","permissionName":"无质检条码修改","childPermissions":null},{"permissionCode":"Pages.WPDA.PurInstock","permissionName":"来料入库","childPermissions":null},{"permissionCode":"Pages.WPDA.OtherInstock","permissionName":"其他入库","childPermissions":null}]
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
                 * permissionCode : Pages.WPDA.InStock.ReceiveByOrder
                 * permissionName : 采购单收货
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
         * displayName : /  默认组织
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
