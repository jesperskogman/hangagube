import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class hangagube {
    public static void main(String[] args) {

        String correctWord = getRandomWord();

        char[] correctWordHiddenArray = correctWord.replaceAll( "[a-öA-Ö]", "_").toCharArray();

        String[] state = {
                " ---| \n" ,
                " |  0 \n" ,
                " | -|- \n" ,
                " | / \\ \n" ,
                " |/   \\  \n"

        };

        printWord(correctWordHiddenArray);
        int j = 0;
        int score = 0;
        while (j != state.length && score < correctWord.length()){


            Scanner tgb = new Scanner(System.in);
            System.out.println("Gissa på en bokstav");
            String gissning = tgb.next();

            if (correctWord.contains(gissning)) {
                for (int i = 0; i < correctWord.length(); i++) {
                    if (correctWord.charAt(i) == gissning.charAt(0)) {

                        correctWordHiddenArray[i] = gissning.charAt(0);
                        printWord(correctWordHiddenArray);
                    }
                }
            } else {
                printGubbe(state,j);
                j++;
            }
            if (j == 5){
                System.out.println("Du förlorade! Ordet var " + correctWord);
            }
            if (score == correctWord.length()){
                System.out.println("Du vann!");
            }
        }
  

    }
    private static void printGubbe(String[] gubbe, int j){
        for (int i = 0; i < j+1; i++) {
            System.out.print(gubbe[i]);

        }
    
    }


    private static void printWord(char[] correctWordHiddenArray) {
        for (char c : correctWordHiddenArray) {

            System.out.print(c + " ");
        }

        System.out.print("\n");
    }


    private static String getRandomWord() {
        String correctWord;
        Scanner inputFile;

        try {
            inputFile = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e){
            System.out.println("Filen finns inte, använd tangentbordet istället!");
            inputFile = new Scanner(System.in);
        }
        ArrayList<String> wordlist = new ArrayList<>();
        while (inputFile.hasNextLine()){
            wordlist.add(inputFile.nextLine());
        }


        Random R = new Random();
        int wordIndex = R.nextInt(wordlist.size());
        correctWord = wordlist.get(wordIndex);
        return correctWord;
    }
}
