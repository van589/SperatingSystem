package com.chapter.work5;

import java.util.Arrays;

public class Process {
    private String name;
    private Sources Allocation;
    private Sources Need;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sources getAllocation() {
        return Allocation;
    }

    public void setAllocation(Sources allocation) {
        Allocation = allocation;
    }

    public Sources getNeed() {
        return Need;
    }

    public void setNeed(Sources need) {
        Need = need;
    }

    @Override
    public String toString() {
        return "Process{" +
                "name='" + name + '\'' +
                ", Allocation=" + Allocation +
                ", Need=" + Need +
                '}';
    }
}