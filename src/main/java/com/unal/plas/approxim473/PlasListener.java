package com.unal.plas.approxim473;

import com.sun.tools.javac.util.StringUtils;
import com.unal.plas.approxim473.model.Function;
import com.unal.plas.approxim473.model.Iterator;
import com.unal.plas.grammars.CPP14BaseListener;
import com.unal.plas.grammars.CPP14Parser;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

public class PlasListener extends CPP14BaseListener {

    private Map<String,Integer> ruleIndexMap;
    private Integer identation=0;
    private String tabulatorSymbol ="\t";
    private List<Object> scopeStack = new ArrayList<>();
    private List<Integer> indexContextStackFunction = new ArrayList<>();
    private List<Integer> indexContextStackLoop = new ArrayList<>();

    private String getTabulation(int i){
       return String.join("", Collections.nCopies(i, tabulatorSymbol));
    }

    private void print(String string){
        System.out.println(getTabulation(identation)+string);
    }

    public PlasListener(Map<String,Integer> ruleIndexMap) {
        super();
        this.ruleIndexMap=ruleIndexMap;
    }

    @Override
    public void enterCannonicalforestatement(CPP14Parser.CannonicalforestatementContext ctx) {
        Iterator iterator = new Iterator(ctx);
        scopeStack.add(iterator);
        identation++;
        indexContextStackLoop.add(scopeStack.size()-1);
        print("entrando de ciclo -> candidato a aproximacion");
        super.enterCannonicalforestatement(ctx);
    }

    @Override
    public void exitCannonicalforestatement(CPP14Parser.CannonicalforestatementContext ctx) {
        identation--;
        scopeStack.remove("loop");
        print("Saliendo de ciclo");
        ctx.removeLastChild();
        Object t = scopeStack.get(indexContextStackLoop.get(indexContextStackLoop.size() - 1));
        print(t.getClass().toString());
        super.exitCannonicalforestatement(ctx);
    }

    @Override
    public void enterPostfixexpression(CPP14Parser.PostfixexpressionContext ctx) {
        int count = ctx.children.size();
        if(count==4) {
            for (int i = 0; i <count; i++) {
                if(ctx.children.get(i).getClass().toString().equals("class org.antlr.v4.runtime.tree.TerminalNodeImpl")){
                    if((ctx.children.get(i).getText().equals("("))){
                        print("Llamado a funcion: "+ ctx.getText());
                    }
                }
            }
        }
        super.enterPostfixexpression(ctx);
    }


    @Override
    public void exitCompoundstatement(CPP14Parser.CompoundstatementContext ctx) {
        print(ctx.getText());
        super.exitCompoundstatement(ctx);
    }

    @Override
    public void enterJumpstatement(CPP14Parser.JumpstatementContext ctx) {
        for(int i=0;i<ctx.getChildCount();i++){
            if(ctx.getChild(i).getText().equals("return")){
                print("retorno de funcion");
            }
        }
        super.enterJumpstatement(ctx);
    }

    @Override
    public void exitPostfixexpression(CPP14Parser.PostfixexpressionContext ctx) {
        super.exitPostfixexpression(ctx);
    }


    @Override
    public void enterFunctionbody(CPP14Parser.FunctionbodyContext ctx) {
        identation++;
        scopeStack.add(new Function());
        indexContextStackFunction.add(scopeStack.size()-1);
        print(scopeStack.toString());
        super.enterFunctionbody(ctx);
    }

    @Override
    public void exitFunctionbody(CPP14Parser.FunctionbodyContext ctx) {
        identation--;
        scopeStack.remove("function");
        super.exitFunctionbody(ctx);
    }

    @Override
    public void enterNoptrdeclaratorB(CPP14Parser.NoptrdeclaratorBContext ctx) {
        print("Definición "+ctx.getText());
        super.enterNoptrdeclaratorB(ctx);
    }

    @Override
    public void enterNoptrdeclaratorA(CPP14Parser.NoptrdeclaratorAContext ctx) {
        print("Variable "+ctx.getText());
        super.enterNoptrdeclaratorA(ctx);
    }

    @Override
    public void enterFunctiondefinition(CPP14Parser.FunctiondefinitionContext ctx) {
        // System.out.println("definiendo funcion "+ctx.declarator().ptrdeclarator().noptrdeclarator().noptrdeclarator().getText()+" "+ctx.declarator().ptrdeclarator().noptrdeclarator().parametersandqualifiers().getText());
        super.enterFunctiondefinition(ctx);
    }

}
