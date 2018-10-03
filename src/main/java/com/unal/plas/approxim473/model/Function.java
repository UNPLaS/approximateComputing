package com.unal.plas.approxim473.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Function {

    private String returnType;
    private String name;
    private List<String> returnStatement  = new ArrayList<>();
    private List<Attribute> parameterList = new ArrayList<>();
    private List<Attribute> variableList = new ArrayList<>();
    private String statement;

}