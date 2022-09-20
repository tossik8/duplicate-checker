import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        System.out.println("Hello, you've started the Duplicate Checker Application");
        printMenu();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()){
            switch (scanner.nextInt()){
                case 1 -> {
                    System.out.println("Once you are done typing and want to see the result, press Enter without providing any data");
                    System.out.println("Input your text");
                    checkDuplicateWords();
                }
                case 2 ->{
                    System.out.println("Once you are done typing and want to see the result, press Enter without providing any data");
                    System.out.println("Input your text");
                    checkDuplicateLines();
                }
                case 3 -> {
                    System.out.println("Goodbye");
                    return;
                }
                default -> System.out.println("Wrong input");
            }
            printMenu();
        }

    }
    public static void checkDuplicateWords(){
        String originalText = getInput(new Scanner(System.in)).toString(), text = modifyInput(originalText);
        text = removePunctuation(text);
        String[] words = text.split("[\s\t\n]+");
        long distinct  = Arrays.stream(words).distinct().count();
        StringBuilder sb = new StringBuilder();
        Arrays.stream(words).distinct().forEach(word -> sb.append(word).append(' '));
        printWords(originalText, sb.toString(), words, distinct);
    }
    public static void checkDuplicateLines(){
        String originalText = getInput(new Scanner(System.in)).toString(), text = modifyInput(originalText);
        String[] lines = text.split("[.!?\n]+");
        long distinct = Arrays.stream(lines).distinct().count();
        StringBuilder modified = new StringBuilder();
        Arrays.stream(lines).distinct().forEach(line->{
            line = line.trim();
            modified.append(line).append('\n');
        });
        printLines(originalText, modified.toString(), lines, distinct);
    }
    public static void printMenu(){
        System.out.println("1 - Check duplicate words");
        System.out.println("2 - Check duplicate lines");
        System.out.println("3 - Quit");
    }
    public static StringBuilder getInput(Scanner scanner){
        StringBuilder text2 = new StringBuilder();
        String line;
        while((line = scanner.nextLine()).length() != 0 && scanner.hasNextLine()){
            text2.append(line).append('\n');
        }
        return text2;
    }
    public static String removePunctuation(String original){
        return original.replaceAll("[.!?]+","");
    }
    public static String modifyInput(String original){
        original = original.trim();
        original = original.replaceAll("[:;,-]+", "");
        original = original.replaceAll("([\"\\u2018\\u2019\\u201c\\u201d'])+(.+)\1+", "$2");
        return  original;
    }
    public static void printWords(String original, String modified, String[] words, long distinct){
        System.out.println("Original text:\n" + original);
        System.out.println("Modified text:\n" + modified);
        if(words.length == 1){
            System.out.println("There is " + 1 + " word in total");
        }
        else{
            System.out.println("There are " + words.length + " words in total");
        }
        if(distinct == 1){
            System.out.println("There is " + 1 + " distinct word");
        }
        else System.out.println("There are " + distinct + " distinct words");
    }
    public static void printLines(String original, String modified, String[] lines, long distinct){
        System.out.println("Original text:\n" + original);
        System.out.println("Modified text:\n" + modified);
        if(lines.length == 1){
            System.out.println("There is " + 1 + " line in total");
        }
        else{
            System.out.println("There are " + lines.length + " lines in total");
        }
        if(distinct == 1){
            System.out.println("There is " + 1 + " distinct line");
        }
        else System.out.println("There are " + distinct + " distinct lines");
    }
}
