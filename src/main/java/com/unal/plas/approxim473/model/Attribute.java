package com.unal.plas.approxim473.model;

import lombok.Data;

@Data
public class Attribute {

    String type;
    String name;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return type+"_"+name;
    }
}
