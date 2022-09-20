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
                    start();
                }
                case 2 -> {
                    System.out.println("Goodbye");
                    return;
                }
                default -> System.out.println("Wrong input");
            }
            printMenu();
        }

    }
    public static void start(){
        String originalText = getInput(new Scanner(System.in)).toString();
        String text = modifyInput(originalText);
        String[] words = text.split("[\s\t\n]+");
        long distinct  = Arrays.stream(words).distinct().count();
        StringBuilder sb = new StringBuilder();
        Arrays.stream(words).distinct().forEach(word -> sb.append(word).append(' '));
        printResults(originalText, sb.toString(), words, distinct);
    }
    public static void printMenu(){
        System.out.println("1 - Input text");
        System.out.println("2 - Quit");
    }
    public static StringBuilder getInput(Scanner scanner){
        StringBuilder text2 = new StringBuilder();
        String line;
        while((line = scanner.nextLine()).length() != 0 && scanner.hasNextLine()){
            text2.append(line).append('\n');
        }
        return text2;
    }
    public static String modifyInput(String original){
        original = original.trim();
        original = original.replaceAll("[\"\\u2018\\u2019\\u201c\\u201d:;,.!?-]", "");
        original = original.replaceAll("'(.+)'", "$1");
        return  original;
    }
    public static void printResults(String original, String modified, String[] words, long distinct){
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
}
