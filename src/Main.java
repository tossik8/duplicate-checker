import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, you've started the Duplicate Checker Application");
        printMenu();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                switch (scanner.nextInt()) {
                    case 1 -> {
                        System.out.println("Once you are done typing and want to see the result, press Enter without providing any data");
                        System.out.println("Input your text");
                        checkDuplicateWords();
                    }
                    case 2 -> {
                        System.out.println("Once you are done typing and want to see the result, press Enter without providing any data");
                        System.out.println("Input your text");
                        checkDuplicateLines();
                    }
                    case 3 -> {
                        System.out.println("Once you are done typing and want to see the result, press Enter without providing any data");
                        findWord();
                    }
                    case 4 -> {
                        System.out.println("Once you are done typing and want to see the result, press Enter without providing any data");
                        System.out.println("Input your text");
                        countEachWord();
                    }
                    case 5 -> {
                        System.out.println("Once you are done typing and want to see the result, press Enter without providing any data");
                        System.out.println("Input your text");
                        findLongestWords();
                    }
                    case 6 ->{
                        System.out.println("Once you are done typing and want to see the result, press Enter without providing any data");
                        System.out.println("Input your text");
                        showRepeatedWords();
                    }
                    case 7 -> {
                        System.out.println("Once you are done typing and want to see the result, press Enter without providing any data");
                        System.out.println("Input your text");
                        stringToInt();
                    }
                    case 8 ->{
                        System.out.println("Once you are done typing and want to see the result, press Enter without providing any data");
                        System.out.println("Input your text");
                        capitalizeEachWord();
                    }
                    case 9 ->{
                        System.out.println("Once you are done typing and want to see the result, press Enter without providing any data");
                        System.out.println("Input your text");
                        convertToToggleCase();
                    }
                    case 10 ->{
                        System.out.println("Once you are done typing and want to see the result, press Enter without providing any data");
                        System.out.println("Input your text");
                        convertToSentenceCase();
                    }
                    case 11 ->{
                        System.out.println("Once you are done typing and want to see the result, press Enter without providing any data");
                        System.out.println("Input your text");
                        convertToCamelCase();
                    }
                    case 12 ->{
                        System.out.println("Goodbye");
                        return;
                    }
                    default -> System.out.println("Wrong input");
                }
                printMenu();
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input");
                printMenu();
                scanner = new Scanner(System.in);

            }
        }
    }
    public static void convertToCamelCase(){
        String originalText = getInput(new Scanner(System.in)).toString();
        originalText = modifyInput(originalText);
        originalText = removeWeirdCharacters(originalText);
        originalText = originalText.replaceAll("'","");
        String[] sentences = originalText.split("[?.!]+");
        StringBuilder modified = new StringBuilder();
        for(String sentence: sentences){
            sentence = sentence.toLowerCase();
            for(int i = 0; i < sentence.length(); ++i){
                if(sentence.charAt(i) == ' '){
                    ++i;
                    modified.append(sentence.substring(i, i+1).toUpperCase());
                }
                else if(Character.isDigit(sentence.charAt(i)) && i+1 < sentence.length() &&
                        Character.isLetter(sentence.charAt(i+1))){
                    modified.append(sentence.charAt(i)).append(sentence.substring(i+1, i+2).toUpperCase());
                    ++i;
                }
                else{
                    modified.append(sentence.charAt(i));
                }
            }
        }
        System.out.println(modified);
    }
    public static String removeWeirdCharacters(String original){
        original = original.replaceAll("[\\u005c@$#^&*><|/~№%+=_]+", "");
        return original;
    }

    private static void getPunctuationMarks(String originalText, ArrayList<String> punctuationMarks) {
        Pattern pattern = Pattern.compile("[.!?]+");
        Matcher matcher = pattern.matcher(originalText);
        while (matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            punctuationMarks.add(originalText.substring(start,end));
        }
    }

    public static void convertToSentenceCase(){
        String originalText = getInput(new Scanner(System.in)).toString();
        ArrayList<String> punctuationMarks = new ArrayList<>();
        getPunctuationMarks(originalText, punctuationMarks);
        String[] sentences = originalText.split("[?.!]+");
        StringBuilder modifiedText = new StringBuilder();
        int position = 0;
        for(String sentence : sentences){
            int index = findFirstLetter(sentence);
            if(index == sentence.length()){
                modifiedText.append(sentence);
            }
            else{
                sentence = sentence.substring(0, index)+sentence.substring(index,index+1).toUpperCase() + sentence.substring(index+1).toLowerCase();
                modifiedText.append(sentence);
            }
            if(position<punctuationMarks.size()){
                modifiedText.append(punctuationMarks.get(position++));
            }
        }
        System.out.println(modifiedText);
    }

    public static void convertToToggleCase(){
        String originalText = getInput(new Scanner(System.in)).toString();
        StringBuilder toggleText = new StringBuilder();
        for(int i = 0; i < originalText.length();++i){
            if(originalText.charAt(i)>='a' && originalText.charAt(i) <='z'){
                toggleText.append((char)(originalText.charAt(i) - 32));
            }
            else if(originalText.charAt(i) >='A' && originalText.charAt(i)<='Z'){
                toggleText.append((char)(originalText.charAt(i) + 32));
            }
            else toggleText.append(originalText.charAt(i));
        }
        System.out.println(toggleText);
    }

    public static int findFirstLowerCaseLetter(String s){
        int index = 0;
        while(s.length()>index && !(s.charAt(index)>= 'a' && s.charAt(index) <='z')){
            if(s.charAt(index)>= 'A' && s.charAt(index) <='Z') index = s.length();
            ++index;
        }
        return index;
    }

    public static int findFirstLetter(String s){
        int index = 0;
        while(s.length()>index && !(s.charAt(index)>= 'a' && s.charAt(index) <='z' ||
                s.charAt(index)>= 'A' && s.charAt(index) <='Z'||
                s.charAt(index)>='0' && s.charAt(index) <='9')){
            ++index;
        }
        return index;
    }

    public static void capitalizeEachWord(){
        String originalText = getInput(new Scanner(System.in)).toString();
        String[] words = originalText.split("\\s+");
        originalText = "";
        for(String s : words){
            int index = findFirstLowerCaseLetter(s);
            if(index < s.length()){
                s = s.substring(0, index) + (char)(s.charAt(index) - 32) + s.substring(index+1);
            }
            originalText+=s+" ";
        }
        System.out.println(originalText);

    }

    public static void showRepeatedWords(){
        String originalText = getInput(new Scanner(System.in)).toString(), text = modifyInput(originalText);
        text = removePunctuation(text);
        String[] words = text.split("\\s+");
        Set<String> repeatedWordsSet = new HashSet<>();
        Stream<String> repeatedWords = Arrays.stream(words).filter(word->{
            if(repeatedWordsSet.contains(word)){
                return true;
            }
            else {
                repeatedWordsSet.add(word);
                return false;
            }
        });
        System.out.println(originalText);
        System.out.println("Repeated words:\n");
        repeatedWords.distinct().forEach(System.out::println);
    }

    public static void stringToInt(){
        String originalText = getInput(new Scanner(System.in)).toString();
        BigInteger totalValue = BigInteger.valueOf(0);
        for(int i = 0; i < originalText.length(); ++i){
            totalValue = totalValue.add(BigInteger.valueOf(originalText.charAt(i)));
        }
        System.out.println(totalValue);
    }

    public static void countEachWord() {
        String originalText = getInput(new Scanner(System.in)).toString(), text = modifyInput(originalText);
        text = removePunctuation(text);
        String[] words = text.split("\\s+");
        HashMap<String, Integer> wordsMap = new HashMap<>();
        Arrays.stream(words).forEach(word->{
            if(wordsMap.containsKey(word)){
                wordsMap.put(word, wordsMap.get(word)+1);
            }
            else{
                wordsMap.put(word, 1);
            }
        });
        for(Map.Entry<String, Integer> entry : wordsMap.entrySet()){
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
    public static void findLongestWords(){
        String originalText = getInput(new Scanner(System.in)).toString(), text = modifyInput(originalText);
        text = removePunctuation(text);
        String[] words = text.split("\\s+");
        Set<String> longestWords = new HashSet<>();
        String longest = words[0];
        for(int i = 1; i < words.length; ++i){
            if(words[i].length() > longest.length()){
                longest = words[i];
            }
        }
        longestWords.add(longest);
        for(int i = 1; i < words.length; ++i){
            if(words[i].length() == longest.length()){
                longestWords.add(words[i]);
            }
        }
        for(String word : longestWords){
            System.out.println(word + " - " + word.length() + " characters");
        }
    }
    public static void checkDuplicateWords(){
        String originalText = getInput(new Scanner(System.in)).toString(), text = modifyInput(originalText);
        text = removePunctuation(text);
        String[] words = text.split("\\s+");
        long distinct  = Arrays.stream(words).distinct().count();
        StringBuilder sb = new StringBuilder();
        Arrays.stream(words).distinct().forEach(word -> sb.append(word).append(' '));
        printWords(originalText, sb.toString(), words, distinct);
    }
    public static void checkDuplicateLines(){
        String originalText = getInput(new Scanner(System.in)).toString(), text = modifyInput(originalText);
        String[] lines = text.split("[.!?]+ +|\n+");
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
        System.out.println("3 - Find out how many times a word occurs in a text");
        System.out.println("4 - Count how many times each word occurs in a text");
        System.out.println("5 - Find the longest words in a text");
        System.out.println("6 - Show the words which are repeated in a text");
        System.out.println("7 - Convert your text into a number");
        System.out.println("8 - Capitalize Each Word");
        System.out.println("9 - tOGGLE cASE");
        System.out.println("10 - Sentence case.");
        System.out.println("11 - camelCase");
        System.out.println("12 - Quit");
    }
    public static void findWord(){
        System.out.println("Enter the text");
        String text = getInput(new Scanner(System.in)).toString();
        System.out.println("Enter a word/sentence/etc.");
        String word = new Scanner(System.in).nextLine();
        boolean answer = isStrict(new Scanner(System.in));
        Pattern pattern;
        if(answer)
             pattern= Pattern.compile("\\b" + word +"\\b");
        else {
            pattern=Pattern.compile(word, Pattern.CASE_INSENSITIVE);
        }
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while(matcher.find()){
            ++count;
        }
        if(count == 1){
            System.out.println("The word '"  +  word + "' occurs " + 1 + " time in the text");
        }
        else{
            System.out.println("The word '" + word + "' occurs " + count + " times in the text");
        }
    }
    public static boolean isStrict(Scanner scanner){
        System.out.println("Should it be an exact match?[y/n]");
        String answer = scanner.nextLine();
        return answer.equals("y");
    }
    public static StringBuilder getInput(Scanner scanner){
        StringBuilder text2 = new StringBuilder();
        String line;
        while((line = scanner.nextLine()).length() != 0){
            text2.append(line).append('\n');

        }
        return text2;
    }
    public static String removePunctuation(String original){
        return original.replaceAll("[.!?]+ "," ");
    }
    public static String modifyInput(String original){
        original = original.trim();
        original = original.replaceAll("[(){}\\[\\]]+","");
        original = original.replaceAll("[:;,-]+", "");
        original = original.replaceAll("([\"\\u2018\\u2019\\u201c\\u201d'])+(.+)\1+", "$2");
        return  original;
    }
    public static void printWords(String original, String modified, String[] words, long distinct){
        if(original.equals("")) return;
        System.out.println("Original text:\n" + original);
        System.out.println("Modified text:\n" + modified);
        if(words.length == 1){
            System.out.println("There is 1 word in total");
        }
        else{
            System.out.println("There are " + words.length + " words in total");
        }

        if(distinct == 1){
            System.out.println("There is 1 distinct word");
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
