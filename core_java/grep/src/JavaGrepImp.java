package ca.jrvs.apps.grep;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class JavaGrepImp implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  // private variables, will be set with command line arguments in the main class
  private String regex;
  private String rootPath;
  private String outFile;


  public String getRootPath() {
    return rootPath;
  }


  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }


  public String getRegex() {
    return regex;
  }


  public void setRegex(String regex) {
    this.regex = regex;
  }


  public String getOutFile() {
    return outFile;
  }


  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }


  public List<File> listFiles(String rootPath) {
    // converting String to File class, so we can make use of listFiles() method
    File root = new File( rootPath );
    return Arrays.asList(root.listFiles());
    }


  public List<String> readLines(File inputFile) throws IllegalArgumentException, IOException {
    // converting File to Path class in order to use readAllLines() method
    Path filePath = inputFile.toPath();
    return Files.readAllLines(filePath);
    }


  public boolean containsPattern(String line) {
    return line.matches(regex);
    }


  public void writeToFile(List<String> lines) throws IOException {
    FileWriter writer = new FileWriter(outFile);
    for (String line : lines) {
      writer.write(line + System.lineSeparator());
    }
    writer.close();
  }


  public void process() throws IOException {
    // empty List to add lines to
    List<String> matchedLines = new ArrayList<>();

    try {
      for (File file : listFiles(rootPath)) {
        for (String line : readLines(file)) {
          if (containsPattern(line)) {
            matchedLines.add(line);
            writeToFile(matchedLines);}}}
    } catch (Exception ex) {
      logger.error("Error: unable to process", ex);
    }
  }

  public static void main(String[] args) throws IOException {

    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outfile");
      }

    BasicConfigurator.configure();

    JavaGrepImp javaGrepImp = new JavaGrepImp();

    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);
    javaGrepImp.process();


    }

  }
