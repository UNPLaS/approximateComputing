package com.unal.plas.approxim473;

import com.unal.plas.approxim473.lexer.PlasLexer;
import com.unal.plas.grammars.CPP14BaseVisitor;
import com.unal.plas.grammars.CPP14Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class PlasVisitor extends CPP14BaseVisitor {


    @Override
    public Object visitCannonicalforestatement(CPP14Parser.CannonicalforestatementContext ctx) {

        return super.visitCannonicalforestatement(ctx);
    }




    @Override
    public Object visitFunctiondefinition(CPP14Parser.FunctiondefinitionContext ctx) {
        System.out.println(""+ctx.getText());
        System.out.println(""+ctx.declspecifierseq().getText());
        //Explorar los hijos hasta encontrar estas estructuras de definición de funciones

        if(ctx.declarator().ptrdeclarator().noptrdeclarator().noptrdeclarator().getText().equals("main")){
            System.out.println("función main");
        }
        System.out.println(ctx.declarator().ptrdeclarator().noptrdeclarator().noptrdeclarator().getText());
        System.out.println(ctx.declarator().ptrdeclarator().noptrdeclarator().parametersandqualifiers().getText());
        return super.visitFunctiondefinition(ctx);
    }

    @Override
    public Object visitFunctionbody(CPP14Parser.FunctionbodyContext ctx) {
        System.out.println("CUERPO");
        System.out.println("\t"+ctx.compoundstatement().getText());
        System.out.println("FINCUERPO");
        return super.visitFunctionbody(ctx);
    }

    @Override
    public Object visitPostfixexpression(CPP14Parser.PostfixexpressionContext ctx) {
        int count = ctx.children.size();
        if(count==4) {
            for (int i = 0; i <count; i++) {
                if(ctx.children.get(i).getClass().toString().equals("class org.antlr.v4.runtime.tree.TerminalNodeImpl")){
                    if((ctx.children.get(i).getText().equals("("))){
                        System.out.println("Llamado a funcion: "+ ctx.getText());
                    }
                }
            }
        }
        return super.visitPostfixexpression(ctx);
    }

    @Override
    public Object visitAssignmentexpression(CPP14Parser.AssignmentexpressionContext ctx) {

        System.out.println("asignación "+ctx.getText());


        return super.visitAssignmentexpression(ctx);
    }


}
