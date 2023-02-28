package com.app.apisvtes.uploads;

public class AddEnroll {

    private final String hh_member_id;
    private final String CCT_ID;
    private final String hh_code;
    private final String provice_id;

    public AddEnroll(String hh_member_id, String CCT_ID, String hh_code, String provice_id) {
        this.hh_member_id = hh_member_id;
        this.CCT_ID = CCT_ID;
        this.hh_code = hh_code;
        this.provice_id = provice_id;
    }

    public String gethh_member_id() {
        return hh_member_id;
    }

    public String getCCT_ID() {
        return CCT_ID;
    }

    public String gethh_code() {
        return hh_code;
    }

    public String getprovice_id() {
        return provice_id;
    }
}