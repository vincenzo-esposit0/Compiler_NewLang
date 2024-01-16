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
    private static String c_out = "test_files/c_out/";
    private static String exec_dir = "test_files/exec/";

    public static void main(String[] args) throws Exception {
        String[] array = args[0].split("/");
        String nomeFile = array[array.length-1];
        String nameFileWithoutExtension = nomeFile.substring(0,nomeFile.length()-4);

        String inPathFile = args[0];
        String cGenerated = "test_files/c_out/"+nomeFile.substring(0,nomeFile.length()-4)+".c";

        parser p = new parser(new Yylex(new FileReader(new File(inPathFile))));

        ProgramNode programNode = (ProgramNode) p.parse().value;

        MyScopeVisitor myScopeVisitor = new MyScopeVisitor();
        myScopeVisitor.visit(programNode);

        MyTypeVisitor typeCheckerVisitor = new MyTypeVisitor();
        typeCheckerVisitor.visit(programNode);
        logger.info("Semantic analysis done!");

        MyCTranslatorVisitor cTranslatorVisitor = new MyCTranslatorVisitor();
        String codeGeneratorC = cTranslatorVisitor.visit(programNode);
        logger.info("C code generation done!");
        fileGenerator(codeGeneratorC,c_out + cGenerated);


        //Codice per compilare il file c in eseguibile
        logger.info("Starting C compiler...");
        Runtime rt = Runtime.getRuntime();
        String cCompilerCmd = "gcc -o " + exec_dir + nameFileWithoutExtension + " " + c_out + nameFileWithoutExtension + ".c" + " -lm";
        try {
            Process compileProcess = rt.exec(cCompilerCmd);
            int exitCode = compileProcess.waitFor();
            if (exitCode == 0) {
                logger.info("GCC compilation completed successfully");
            } else {
                logger.severe("Something went wrong while compiling with GCC");
            }
        } catch (IOException e) {
            logger.severe("Error executing GCC build command");
            logger.severe(e.getMessage());
        } catch (InterruptedException e) {
            logger.info("Interrupted while waiting for the build to finish.");
            logger.severe(e.getMessage());
            Thread.currentThread().interrupt();
        }

        /**
        try {
            String[] path = new String[] {"/usr/bin/open", "-a", exec_dir + nameFileWithoutExtension};
            Process process = new ProcessBuilder(path).start();

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                logger.severe("Error executing command. Exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            logger.severe("Error executing command");
            logger.severe(e.getMessage());
        }
         */

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
