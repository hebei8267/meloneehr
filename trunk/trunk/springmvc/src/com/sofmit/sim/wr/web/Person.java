package com.sofmit.sim.wr.web;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private int age;
    private List<Cat> catList = new ArrayList<Cat>();

    {
        for (int i = 0; i < 10; i++) {
            Cat c = new Cat();
            c.setName("cat name" + i);
            c.setAge(i + 1);

            catList.add(c);
        }
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

    public List<Cat> getCatList() {
        return catList;
    }

    public void setCatList(List<Cat> catList) {
        this.catList = catList;
    }

}
