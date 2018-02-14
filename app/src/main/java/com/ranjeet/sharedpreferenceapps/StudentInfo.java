package com.ranjeet.sharedpreferenceapps;

/**
 * Created by Sreekanthp on 07-12-2017.
 */

public class StudentInfo {
    String Name, RollNo;
    int Image;
    public StudentInfo(String name, String rollNo, int image) {
        Name = name;
        RollNo = rollNo;
        Image = image;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getRollNo() {
        return RollNo;
    }
    public void setRollNo(String rollNo) {
        RollNo = rollNo;
    }
    public int getImage() {
        return Image;
    }
    public void setImage(int image) {
        Image = image;
    }
}
