package com.app.apisvtes.uploads;

public class Member {

    private final String hh_member_id;
    private final String first_name;
    private final String member_surname;
    private final String contact_number;


    public Member(String hh_member_id, String first_name, String member_surname, String contact_number) {
        this.hh_member_id = hh_member_id;
        this.first_name = first_name;
        this.member_surname = member_surname;
        this.contact_number = contact_number;
    }

    public String gethh_member_id() {
        return hh_member_id;
    }

    public String getfirst_name() {
        return first_name;
    }

    public String getmember_surname() {
        return member_surname;
    }

    public String getcontact_number() {
        return contact_number;
    }
}