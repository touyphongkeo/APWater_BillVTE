package com.app.apisvtes.uploads;

public class infor {

    private final String hh_id;
    private final String hh_code;
    private final String hh_head_name;
    private final String total_hh_members;
    private final String total_hh_members_female;

    public infor(String hh_id, String hh_code, String hh_head_name, String total_hh_members, String total_hh_members_female) {
        this.hh_id = hh_id;
        this.hh_code = hh_code;
        this.hh_head_name = hh_head_name;
        this.total_hh_members = total_hh_members;
        this.total_hh_members_female = total_hh_members_female;
    }

    public String gethh_id() {
        return hh_id;
    }
    public String gethh_code() {
        return hh_code;
    }
    public String gethh_head_name() {
        return hh_head_name;
    }
    public String gettotal_hh_members() {
        return total_hh_members;
    }
    public String gettotal_hh_members_female() {
        return total_hh_members_female;
    }
}