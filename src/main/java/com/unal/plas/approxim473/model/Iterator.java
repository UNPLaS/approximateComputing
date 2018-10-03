package com.unal.plas.approxim473.model;

import com.unal.plas.grammars.CPP14Parser;

import java.util.ArrayList;
import java.util.List;

public class Iterator {
    private String type;
    private List<Attribute> parameterList = new ArrayList<>();
    private List<Attribute> variableList = new ArrayList<>();
    private List<String> functionsCalled = new ArrayList<>();
    private List<String> assignations = new ArrayList<>();
    private CPP14Parser.ConditionContext condition;
    private CPP14Parser.ExpressionContext expresion;
    private CPP14Parser.StatementContext statement;

    public Iterator(CPP14Parser.CannonicalforestatementContext ctx) {
        type="for";
        statement = ctx.statement();
        condition  =ctx.condition();
        expresion = ctx.expression();
    }

    public String getType() {
        return type;
    }

    public List<Attribute> getParameterList() {
        return parameterList;
    }

    public List<Attribute> getVariableList() {
        return variableList;
    }

    public List<String> getFunctionsCalled() {
        return functionsCalled;
    }

    public List<String> getAssignations() {
        return assignations;
    }

    public CPP14Parser.ConditionContext getCondition() {
        return condition;
    }

    public CPP14Parser.ExpressionContext getExpresion() {
        return expresion;
    }

    public CPP14Parser.StatementContext getStatement() {
        return statement;
    }
}
