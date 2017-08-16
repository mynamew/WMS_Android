package com.timi.sz.wms_android.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 登录的返回
 */

public class LoginBean {


    /**
     * code : 1
     * data : [{"cuserid":"1001A610000000000007","user_name":"葛佩","user_code":"oa_gp","user_password":"U_U++--V455ed16521c98aec0ad3ef0db8ddd684","user_note":"","pwdlevelcode":"update","pwdparam":"2017-05-08","identityverifycode":"staticpwd","abledate":"2013-01-01 00:00:00","disabledate":"","islocked":"N","pk_base_doc":"","pk_psndoc":"","base_doc_type":0,"pk_org":"0001A6100000000001ME","pk_group":"0001A6100000000001ME","creator":"0001A6100000000001MP","creationtime":"2013-05-20 14:08:38","modifier":"0001A61000000000DJQB","modifiedtime":"2015-08-17 08:45:29","user_type":1,"pk_usergroupforcreate":"1001A610000000000006","format":"FMT0Z000000000000000","isca":"N","enablestate":2,"contentlang":"zhCN0000000000000000","user_code_q":"OA_GP","dataoriginflag":"","email":"oa_gp@sz-hkcw.com","systype":"","user_name2":"","user_name3":"","user_name4":"","user_name5":"","user_name6":""},{"cuserid":"1001A610000000054XLI","user_name":"葛佩","user_code":"gep","user_password":"U_U++--V59ffb730629a4bded0c7ed0cc7f610f4","user_note":"","pwdlevelcode":"update","pwdparam":"2017-04-28","identityverifycode":"staticpwd","abledate":"2014-10-16 00:00:00","disabledate":"","islocked":"N","pk_base_doc":"0001A610000000024W9D","pk_psndoc":"0001A610000000024W9D","base_doc_type":0,"pk_org":"0001A6100000000009OU","pk_group":"0001A6100000000001ME","creator":"1001A610000000007L6N","creationtime":"2014-10-16 10:07:18","modifier":"0001A61000000000DJQB","modifiedtime":"2015-11-18 13:04:07","user_type":1,"pk_usergroupforcreate":"1001A610000000006RUJ","format":"FMT0Z000000000000000","isca":"N","enablestate":2,"contentlang":"zhCN0000000000000000","user_code_q":"GEP","dataoriginflag":"","email":"gep@sz-hkcw.com","systype":"1","user_name2":"","user_name3":"","user_name4":"","user_name5":"","user_name6":""}]
     */

    private int code;
    private List<DataBean> data;

    protected LoginBean(Parcel in) {
        code = in.readInt();
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }



    public static class DataBean implements Parcelable {
        /**
         * cuserid : 1001A610000000000007
         * user_name : 葛佩
         * user_code : oa_gp
         * user_password : U_U++--V455ed16521c98aec0ad3ef0db8ddd684
         * user_note :
         * pwdlevelcode : update
         * pwdparam : 2017-05-08
         * identityverifycode : staticpwd
         * abledate : 2013-01-01 00:00:00
         * disabledate :
         * islocked : N
         * pk_base_doc :
         * pk_psndoc :
         * base_doc_type : 0
         * pk_org : 0001A6100000000001ME
         * pk_group : 0001A6100000000001ME
         * creator : 0001A6100000000001MP
         * creationtime : 2013-05-20 14:08:38
         * modifier : 0001A61000000000DJQB
         * modifiedtime : 2015-08-17 08:45:29
         * user_type : 1
         * pk_usergroupforcreate : 1001A610000000000006
         * format : FMT0Z000000000000000
         * isca : N
         * enablestate : 2
         * contentlang : zhCN0000000000000000
         * user_code_q : OA_GP
         * dataoriginflag :
         * email : oa_gp@sz-hkcw.com
         * systype :
         * user_name2 :
         * user_name3 :
         * user_name4 :
         * user_name5 :
         * user_name6 :
         */

        private String cuserid;
        private String user_name;
        private String user_code;
        private String user_password;
        private String user_note;
        private String pwdlevelcode;
        private String pwdparam;
        private String identityverifycode;
        private String abledate;
        private String disabledate;
        private String islocked;
        private String pk_base_doc;
        private String pk_psndoc;
        private int base_doc_type;
        private String pk_org;
        private String pk_group;
        private String creator;
        private String creationtime;
        private String modifier;
        private String modifiedtime;
        private int user_type;
        private String pk_usergroupforcreate;
        private String format;
        private String isca;
        private int enablestate;
        private String contentlang;
        private String user_code_q;
        private String dataoriginflag;
        private String email;
        private String systype;
        private String user_name2;
        private String user_name3;
        private String user_name4;
        private String user_name5;
        private String user_name6;

        protected DataBean(Parcel in) {
            cuserid = in.readString();
            user_name = in.readString();
            user_code = in.readString();
            user_password = in.readString();
            user_note = in.readString();
            pwdlevelcode = in.readString();
            pwdparam = in.readString();
            identityverifycode = in.readString();
            abledate = in.readString();
            disabledate = in.readString();
            islocked = in.readString();
            pk_base_doc = in.readString();
            pk_psndoc = in.readString();
            base_doc_type = in.readInt();
            pk_org = in.readString();
            pk_group = in.readString();
            creator = in.readString();
            creationtime = in.readString();
            modifier = in.readString();
            modifiedtime = in.readString();
            user_type = in.readInt();
            pk_usergroupforcreate = in.readString();
            format = in.readString();
            isca = in.readString();
            enablestate = in.readInt();
            contentlang = in.readString();
            user_code_q = in.readString();
            dataoriginflag = in.readString();
            email = in.readString();
            systype = in.readString();
            user_name2 = in.readString();
            user_name3 = in.readString();
            user_name4 = in.readString();
            user_name5 = in.readString();
            user_name6 = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public String getCuserid() {
            return cuserid;
        }

        public void setCuserid(String cuserid) {
            this.cuserid = cuserid;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public String getUser_password() {
            return user_password;
        }

        public void setUser_password(String user_password) {
            this.user_password = user_password;
        }

        public String getUser_note() {
            return user_note;
        }

        public void setUser_note(String user_note) {
            this.user_note = user_note;
        }

        public String getPwdlevelcode() {
            return pwdlevelcode;
        }

        public void setPwdlevelcode(String pwdlevelcode) {
            this.pwdlevelcode = pwdlevelcode;
        }

        public String getPwdparam() {
            return pwdparam;
        }

        public void setPwdparam(String pwdparam) {
            this.pwdparam = pwdparam;
        }

        public String getIdentityverifycode() {
            return identityverifycode;
        }

        public void setIdentityverifycode(String identityverifycode) {
            this.identityverifycode = identityverifycode;
        }

        public String getAbledate() {
            return abledate;
        }

        public void setAbledate(String abledate) {
            this.abledate = abledate;
        }

        public String getDisabledate() {
            return disabledate;
        }

        public void setDisabledate(String disabledate) {
            this.disabledate = disabledate;
        }

        public String getIslocked() {
            return islocked;
        }

        public void setIslocked(String islocked) {
            this.islocked = islocked;
        }

        public String getPk_base_doc() {
            return pk_base_doc;
        }

        public void setPk_base_doc(String pk_base_doc) {
            this.pk_base_doc = pk_base_doc;
        }

        public String getPk_psndoc() {
            return pk_psndoc;
        }

        public void setPk_psndoc(String pk_psndoc) {
            this.pk_psndoc = pk_psndoc;
        }

        public int getBase_doc_type() {
            return base_doc_type;
        }

        public void setBase_doc_type(int base_doc_type) {
            this.base_doc_type = base_doc_type;
        }

        public String getPk_org() {
            return pk_org;
        }

        public void setPk_org(String pk_org) {
            this.pk_org = pk_org;
        }

        public String getPk_group() {
            return pk_group;
        }

        public void setPk_group(String pk_group) {
            this.pk_group = pk_group;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public String getCreationtime() {
            return creationtime;
        }

        public void setCreationtime(String creationtime) {
            this.creationtime = creationtime;
        }

        public String getModifier() {
            return modifier;
        }

        public void setModifier(String modifier) {
            this.modifier = modifier;
        }

        public String getModifiedtime() {
            return modifiedtime;
        }

        public void setModifiedtime(String modifiedtime) {
            this.modifiedtime = modifiedtime;
        }

        public int getUser_type() {
            return user_type;
        }

        public void setUser_type(int user_type) {
            this.user_type = user_type;
        }

        public String getPk_usergroupforcreate() {
            return pk_usergroupforcreate;
        }

        public void setPk_usergroupforcreate(String pk_usergroupforcreate) {
            this.pk_usergroupforcreate = pk_usergroupforcreate;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public String getIsca() {
            return isca;
        }

        public void setIsca(String isca) {
            this.isca = isca;
        }

        public int getEnablestate() {
            return enablestate;
        }

        public void setEnablestate(int enablestate) {
            this.enablestate = enablestate;
        }

        public String getContentlang() {
            return contentlang;
        }

        public void setContentlang(String contentlang) {
            this.contentlang = contentlang;
        }

        public String getUser_code_q() {
            return user_code_q;
        }

        public void setUser_code_q(String user_code_q) {
            this.user_code_q = user_code_q;
        }

        public String getDataoriginflag() {
            return dataoriginflag;
        }

        public void setDataoriginflag(String dataoriginflag) {
            this.dataoriginflag = dataoriginflag;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSystype() {
            return systype;
        }

        public void setSystype(String systype) {
            this.systype = systype;
        }

        public String getUser_name2() {
            return user_name2;
        }

        public void setUser_name2(String user_name2) {
            this.user_name2 = user_name2;
        }

        public String getUser_name3() {
            return user_name3;
        }

        public void setUser_name3(String user_name3) {
            this.user_name3 = user_name3;
        }

        public String getUser_name4() {
            return user_name4;
        }

        public void setUser_name4(String user_name4) {
            this.user_name4 = user_name4;
        }

        public String getUser_name5() {
            return user_name5;
        }

        public void setUser_name5(String user_name5) {
            this.user_name5 = user_name5;
        }

        public String getUser_name6() {
            return user_name6;
        }

        public void setUser_name6(String user_name6) {
            this.user_name6 = user_name6;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(cuserid);
            dest.writeString(user_name);
            dest.writeString(user_code);
            dest.writeString(user_password);
            dest.writeString(user_note);
            dest.writeString(pwdlevelcode);
            dest.writeString(pwdparam);
            dest.writeString(identityverifycode);
            dest.writeString(abledate);
            dest.writeString(disabledate);
            dest.writeString(islocked);
            dest.writeString(pk_base_doc);
            dest.writeString(pk_psndoc);
            dest.writeInt(base_doc_type);
            dest.writeString(pk_org);
            dest.writeString(pk_group);
            dest.writeString(creator);
            dest.writeString(creationtime);
            dest.writeString(modifier);
            dest.writeString(modifiedtime);
            dest.writeInt(user_type);
            dest.writeString(pk_usergroupforcreate);
            dest.writeString(format);
            dest.writeString(isca);
            dest.writeInt(enablestate);
            dest.writeString(contentlang);
            dest.writeString(user_code_q);
            dest.writeString(dataoriginflag);
            dest.writeString(email);
            dest.writeString(systype);
            dest.writeString(user_name2);
            dest.writeString(user_name3);
            dest.writeString(user_name4);
            dest.writeString(user_name5);
            dest.writeString(user_name6);
        }
    }
}
