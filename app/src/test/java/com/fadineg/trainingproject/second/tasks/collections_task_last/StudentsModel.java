package com.fadineg.trainingproject.second.tasks.collections_task_last;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentsModel {
    private String name, secondName, family;
    private Integer yearOfBirth, course, groupNumber;
    private int p1, p2, p3, p4, p5;

    public StudentsModel(String name, String secondName, String family,
                         int yearOfBirth, int course, int groupNumber,
                         int p1, int p2, int p3, int p4, int p5) {
        this.name = name;
        this.secondName = secondName;
        this.family = family;
        this.yearOfBirth = yearOfBirth;
        this.course = course;
        this.groupNumber = groupNumber;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
    }

    public String getName() {

        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(final String secondName) {
        this.secondName = secondName;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(final String family) {
        this.family = family;
    }


    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(final Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(final Integer course) {
        this.course = course;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(final Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Integer getP1() {
        return p1;
    }

    public void setP1(final Integer p1) {
        this.p1 = p1;
    }

    public Integer getP2() {
        return p2;
    }

    public void setP2(final Integer p2) {
        this.p2 = p2;
    }

    public Integer getP3() {
        return p3;
    }

    public void setP3(final Integer p3) {
        this.p3 = p3;
    }

    public Integer getP4() {
        return p4;
    }

    public void setP4(final Integer p4) {
        this.p4 = p4;
    }

    public Integer getP5() {
        return p5;
    }

    public void setP5(final Integer p5) {
        this.p5 = p5;
    }

}
