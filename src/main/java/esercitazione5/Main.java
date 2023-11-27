package esercitazione5;

import nodes.ProgramNode;
import org.apache.commons.io.FilenameUtils;
import visitor.MyCTranslatorVisitor;
import visitor.MyScopeVisitor;
import visitor.MyTypeVisitor;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static String c_out = "test/c_out/";

    public static void main(String[] args) throws Exception {
        String[] array = args[0].split("/");
        String nomeFile = array[array.length-1];
        String inPathFile = args[0];
        String cGenerated = "test/c_out/"+nomeFile.substring(0,nomeFile.length()-4)+".c";

        parser p = new parser(new Yylex(new FileReader(new File(inPathFile))));

        ProgramNode programNode = (ProgramNode) p.debug_parse().value;

        MyScopeVisitor myScopeVisitor = new MyScopeVisitor();
        myScopeVisitor.visit(programNode);

        MyTypeVisitor typeCheckerVisitor = new MyTypeVisitor();
        typeCheckerVisitor.visit(programNode);

        MyCTranslatorVisitor cTranslatorVisitor = new MyCTranslatorVisitor();
        String codeGeneratorC = cTranslatorVisitor.visit(programNode);
        logger.info("C code generation done!");
        fileGenerator(codeGeneratorC,c_out + cGenerated);

    }

    private static void fileGenerator(String txt,String filePath) throws IOException {
        File file = new File(c_out + FilenameUtils.getName(filePath));
        try(FileWriter fw = new FileWriter(file)) {
            fw.write(txt);
            fw.flush();
        } catch (IOException e) {
            logger.severe("Error generating file: ");
            logger.severe(e.getMessage());
        }
    }

}
