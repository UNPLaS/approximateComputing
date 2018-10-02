package com.unal.plas;


import com.unal.plas.approxim473.PlasListener;
import com.unal.plas.approxim473.PlasVisitor;
import com.unal.plas.approxim473.lexer.PlasLexer;
import com.unal.plas.approxim473.parser.PlasParser;
import com.unal.plas.grammars.CPP14Parser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.*;

import java.util.Map;

public class App
{
    public static void main( String[] args )
    {

        ANTLRInputStream inputStream;
        inputStream = new ANTLRInputStream(
                "#include <iostream> \n" +
                        "#include <vector> \n" +
                        "int b; \n" +
                        "using namespace std; \n" +
                        "int holaMundo(int a, int b);" +
                        "int holaMundo(int v, int v){" +
                        "return 1;}" +
                        "int main() \n" +
                        "{ \n" +
                        " \n" +
                        "    for(int i = 0; i < 3; ++i)  \n" +
                        "    { \n" +
                        "        for(j = 0; j < 3; ++j)  \n" +
                        "    { \n" +
                        "       for(k = 0; k < 3; ++ks)  \n" +
                        "    { \n" +
                        "        cout << v[j] << \" \"; \n" +
                        "holamundo(1,2);" +
                        "break;" +
                        "    } \n" +
                        "    } \n" +
                        "    } \n" +
                        "  \n" +
                        "\n" +
                        "  \n" +
                        "holamundo(1,2);\n" +
                        "a=holamundo(3,4,4,5);\n" +
                        "a=holamundo();\n" +
                        "    return 0; \n" +
                        "    return a==0; \n" +
                        "} ");
        PlasLexer plasLexer = new PlasLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(plasLexer);
        PlasParser plasParser = new PlasParser(commonTokenStream);
        Map<String, Integer> ruleIndexMap = plasLexer.getRuleIndexMap();
        ParseTreeWalker walker = new ParseTreeWalker();


        ParseTree entryPoint = plasParser.translationunit();
        PlasListener listener = new PlasListener(ruleIndexMap);
        walker.walk(listener,entryPoint);

        /*
        CPP14Parser.TranslationunitContext fileContext = plasParser.translationunit();
        PlasVisitor visitor = new PlasVisitor();
        visitor.visit(fileContext);
        */


    }

}
