package com.unal.plas.approxim473.model;

import java.util.ArrayList;
import java.util.List;

public class Function {
    private String returnType;

    private String name;

    private List<String> returnStatement  = new ArrayList<>();

    private List<Attribute> parameterList = new ArrayList<>();

    private List<Attribute> variableList = new ArrayList<>();

    private String statement;

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Attribute> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<Attribute> parameterList) {
        this.parameterList = parameterList;
    }

    public List<Attribute> getVariableList() {
        return variableList;
    }

    public void setVariableList(List<Attribute> variableList) {
        this.variableList = variableList;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
}