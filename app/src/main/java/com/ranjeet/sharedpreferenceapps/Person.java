package com.ranjeet.sharedpreferenceapps;

/**
 * Created by Sreekanthp on 07-12-2017.
 */

public class Person {
    private String mName;
    private int mAge;
    public Person(String name, int age) {
        this.mName = name;
        this.mAge = age;
    }
    public String getName() {
        return mName;
    }
    public void setName(String mName) {
        this.mName = mName;
    }
    public int getAge() {
        return mAge;
    }
    public void setAge(int mAge) {
        this.mAge = mAge;
    }
}