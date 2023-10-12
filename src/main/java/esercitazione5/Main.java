package esercitazione5;

import nodes.*;
import visitor.MySyntaxTree;

import java.io.File;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {
        String inPathFile = args[0];

        //controllo sul file in input: vedere se il file è ".txt"
        if (!(inPathFile.endsWith(".txt"))){
            System.err.println("Dare in input un file .txt");
            System.exit(1);
        }
        parser p = new parser(new Yylex(new FileReader(new File(inPathFile))));

        ProgramNode programNode = (ProgramNode) p.debug_parse().value;

        MySyntaxTree visitor = new MySyntaxTree();
        System.out.println(programNode.accept(visitor));

    }
}
