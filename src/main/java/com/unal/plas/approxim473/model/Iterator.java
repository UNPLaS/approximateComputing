package com.unal.plas.approxim473.model;

import com.unal.plas.grammars.CPP14Parser;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
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
}
