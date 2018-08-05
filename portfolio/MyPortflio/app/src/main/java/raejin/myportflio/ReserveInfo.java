package raejin.myportflio;

import java.util.Calendar;

public class ReserveInfo {
    String name;
    Calendar date;
    int member;


    public ReserveInfo(String name, int member, Calendar date) {
        this.name = name;
        this.member = member;
        this.date = date;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMember() {
        return member;
    }

    public void setMember(int member) {
        this.member = member;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }




}
