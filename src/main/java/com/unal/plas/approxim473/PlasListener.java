package com.unal.plas.approxim473;

import com.sun.tools.javac.util.StringUtils;
import com.unal.plas.grammars.CPP14BaseListener;
import com.unal.plas.grammars.CPP14Parser;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

public class PlasListener extends CPP14BaseListener {

    Map<String,Integer> ruleIndexMap;
    Integer identation=0;
    String tabulatorSymbol ="\t";
    List<Object> scopeStack = new ArrayList<Object>();
    List<Integer> indexContextStackFunction = new ArrayList<Integer>();
    List<Integer> indexContextStackLoop = new ArrayList<Integer>();


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
        identation++;
        scopeStack.add("loop");
        print(scopeStack.toString());
        print("entrando de ciclo");
        super.enterCannonicalforestatement(ctx);
    }

    @Override
    public void exitCannonicalforestatement(CPP14Parser.CannonicalforestatementContext ctx) {
        identation--;
        scopeStack.remove("loop");
        print("Saliendo de ciclo");
        ctx.removeLastChild();
        super.exitCannonicalforestatement(ctx);
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        super.visitTerminal(node);
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
    public void enterJumpstatement(CPP14Parser.JumpstatementContext ctx) {
        System.out.println("Retorno: " + ctx.getText());
        System.out.println("Retorno: " + ctx.getChild(0).getText().equals("return"));
        for(int i=0;i<ctx.getChildCount();i++){
            System.out.println(ctx.getChild(i).getText().equals("return"));
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
        scopeStack.add("function");
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
    public void enterFunctiondefinition(CPP14Parser.FunctiondefinitionContext ctx) {
        System.out.println("definiendo funcion "+ctx.declarator().ptrdeclarator().noptrdeclarator().noptrdeclarator().getText()+" "+ctx.declarator().ptrdeclarator().noptrdeclarator().parametersandqualifiers().getText());
        super.enterFunctiondefinition(ctx);
    }
}
