package ca.jrvs.apps.practice;
import java.util.regex.*;

public class RegexExcImp implements RegexExc {

  public boolean matchJpeg(String filename) {
    String acceptableRegex = "([^\\s]+(\\.(?i)(jpe?g))$)";
    Pattern pattern = Pattern.compile(acceptableRegex);

    if (filename == null) {
      return false;
    }

    Matcher matcher = pattern.matcher(filename);
    return matcher.matches();
  }
  public boolean matchIP(String ip) {
    String acceptableRegex = "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$";
    Pattern pattern = Pattern.compile(acceptableRegex);

    if (ip == null) {
      return false;
    }

    Matcher matcher = pattern.matcher(ip);
    return matcher.matches();
  }
  public boolean isEmptyLine(String line) {
    String acceptableRegex = "^[ \t\n]*$";
    Pattern pattern = Pattern.compile(acceptableRegex);

    if (line == null) {
      return false;
    }

    Matcher matcher = pattern.matcher(line);
    return matcher.matches();
  }
}

class Main {
  public static void main(String[] args) {
    String jpegTest1 = "joHn_SmiTh.jpg";
    String jpegTest2 = "afo3$423_.jpeg";
    String jpegTest3 = "df%43.jp";
    String jpegTest4 = "apples.JpEg";

    String ipTest1 = "0.0.0.0";
    String ipTest2 = "111.23.4.999";
    String ipTest3 = "1111.0.0.0";
    String ipTest4 = "888.888.888.888.888";

    String blankTest1 = "";
    String blankTest2 = "       ";
    String blankTest3 = "           _";
    String blankTest4 = "        .      ";

    RegexExcImp regex = new RegexExcImp();

    System.out.println(regex.matchJpeg(jpegTest1));
    System.out.println(regex.matchJpeg(jpegTest2));
    System.out.println(regex.matchJpeg(jpegTest3));
    System.out.println(regex.matchJpeg(jpegTest4));
    System.out.println("");
    System.out.println(regex.matchIP(ipTest1));
    System.out.println(regex.matchIP(ipTest2));
    System.out.println(regex.matchIP(ipTest3));
    System.out.println(regex.matchIP(ipTest4));
    System.out.println("");
    System.out.println(regex.isEmptyLine(blankTest1));
    System.out.println(regex.isEmptyLine(blankTest2));
    System.out.println(regex.isEmptyLine(blankTest3));
    System.out.println(regex.isEmptyLine(blankTest4));
  }
}
