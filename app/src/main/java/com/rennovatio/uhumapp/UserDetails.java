package com.rennovatio.uhumapp;

import java.io.Serializable;

public class UserDetails implements Serializable {
    String Name;
    String MobNo;
    String Email;
    String Age;
    String ProfPic;

    public UserDetails() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobNo() {
        return MobNo;
    }

    public void setMobNo(String mobNo) {
        MobNo = mobNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getProfPic() {
        return ProfPic;
    }

    public void setProfPic(String profPic) {
        ProfPic = profPic;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }
}
