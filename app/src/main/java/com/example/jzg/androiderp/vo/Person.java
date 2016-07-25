package com.example.jzg.androiderp.vo;

import io.realm.RealmObject;

/**
 * author: guochen
 * date: 2016/7/25 15:46
 * email: 
 */
public class Person extends RealmObject  {
    public String name;
    private int age;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", userName='" + userName + '\'' +
                '}';
    }
}
