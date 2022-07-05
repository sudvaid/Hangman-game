import java.util.Scanner; 
import java.util.ArrayList;

public class Hangmangame {

    static Scanner s = new Scanner(System.in);
    static ArrayList <String> words = new ArrayList<String>();
    static ArrayList <String> worddisplay = new ArrayList<String>();
    static int wrongguesses = 6;


    public static void main (String[] args) {

        
        System.out.println("Welcome to the hangman game by sudvaid! In order to play, you will need to provide three different words that are over 5 letters in length. ");
        
        try { 
            Thread.sleep(2000); // 2000ms = 2s 
        } catch(InterruptedException ex){ 
        
            System.exit(0); 
        }
        
        System.out.println("The computer will randomly select one, and you will have to guess the letters that are in the word. If you miss more than 6 letters, you lose! ");
        
        try { 
            Thread.sleep(2000); // 2000ms = 2s 
        } catch(InterruptedException ex){ 
        
            System.exit(0); 
        }
        
        addwords();
        addwords();
        addwords();

        while (wordsmatching()) {
          wordsmatching();
        }
        
        System.out.println("Now the computer will choose a word");

        int word_index = (int)(Math.random()*3);

        String wordtoguess = new String(words.get(word_index));

        
        for (int k = 0; k < wordtoguess.length(); k++) {
            worddisplay.add("_");
        }
        
        print_display();
        
        System.out.println("Ok, time to start guessing!");

        guessing(wordtoguess);

        System.out.println("Let's see how you did!");
        
        try { 
            Thread.sleep(2000); // 2000ms = 2s 
        } catch(InterruptedException ex){ 
        
            System.exit(0); 
        }
   
        System.out.println("The results are in! You missed " + countmisses(wordtoguess) + " letters. The correct word was " + wordtoguess + "!");

        try { 
            Thread.sleep(2000); // 2000ms = 2s 
        } catch(InterruptedException ex){ 
        
            System.exit(0); 
        }
        
        congrats(wordtoguess);
    } 



    public static void addwords() {
    
      System.out.println("Ok, enter a word!");
      String word = s.next();
      
      if (word.length() >= 5 && word.matches("[a-zA-Z]+")) {
        words.add(word);
      }
      else {
        System.out.println("Oops, guess you didn't pay attention! The words must be at least 5 letters long. Try again. ");
        addwords();
        
      }
    }
    
    
    public static boolean wordsmatching() {
      
      if ((words.get(0).compareTo(words.get(1)) != 0) && (words.get(1).compareTo(words.get(2)) != 0)) {
        return false; 
      }
      
      else if ((words.get(0).compareTo(words.get(1)) != 0) && (words.get(1).compareTo(words.get(2)) == 0)){
        System.out.println("Looks like your second and third words are amtching! Enter a new third word!");
        String thirdword = s.next();
        words.set(2, thirdword);
        return true;
      } 
        
      else if ((words.get(0).compareTo(words.get(1)) == 0) && (words.get(1).compareTo(words.get(2)) != 0)){
        System.out.println("Looks like your first and second words are amtching! Enter a new second word!");
        String secondword = s.next();
        words.set(1, secondword);
        return true;
      } 
      
      else if ((words.get(0).compareTo(words.get(2)) == 0) && (words.get(1).compareTo(words.get(2)) != 0)){
        System.out.println("Looks like your first and third words are amtching! Enter a new third word!");
        String thirdwordagain = s.next();
        words.set(2, thirdwordagain);
        return true;
      } 
      
      else if ((words.get(0).compareTo(words.get(1)) == 0) && (words.get(1).compareTo(words.get(2)) == 0)){
        System.out.println("Looks like all your words are matching! Enter a new second word!");
        String secondwordagain = s.next();
        words.set(1, secondwordagain);
        System.out.println("Now, a new third word!");
        String thirdwordagainagain = s.next();
        words.set(2, thirdwordagainagain);
        return true;
      } 
      
      else {
       return true; 
      }
    }

    public static void guessing(String wordguess) {
      
      ArrayList <String> wordtobeguessed = new ArrayList <String>();
      
      for (int y = 0; y <wordguess.length(); y++) {
        wordtobeguessed.add(wordguess.substring(y, y+1));
      }
      
      while (wrongguesses != 0 && worddisplay.contains("_")) {

            System.out.println("What is the letter you guess?");
            String letter = s.next();
           
            if ((wordguess.indexOf(letter) != -1) && (letter.length() == 1)) {
              for (int q = 0; q < wordguess.length(); q++) {
                    if (letter.compareTo(wordtobeguessed.get(q)) == 0) {
                        worddisplay.set(q, letter);
                    }
                }
            } 
              
            else if (letter.length() > 1) {
              System.out.println("That guess was more than one letter long! Only guess one letter at a time, please!");
              
              try { 
                Thread.sleep(2000); // 2000ms = 2s 
              } catch(InterruptedException ex){ 
        
                System.exit(0); 
              }
              
            }
            
            else {
                wrongguesses--;
                
            }
            
            System.out.println("You have " + wrongguesses + " wrong guesses left.");
            print_display();
            guessing(wordguess);

        }

    }

    public static void print_display() {

        for (int j = 0; j < worddisplay.size(); j++) {
            System.out.print(worddisplay.get(j) + " ");

        }
        System.out.print("\n");
    }


    public static int countmisses(String guessingword) {
      
      int misses = 0;
      
       for (int n = 0; n < guessingword.length(); n++) {
            if (worddisplay.get(n).compareTo(guessingword.substring(n, n+1))!=0) {
                misses ++;
            }
        }
       
       return misses;
  
    }
    
    
    public static void congrats(String guessword) {
      
      if (countmisses(guessword) == 0) {
            System.out.println("Congratulations!");
        }
        else {
            System.out.println ("Better luck next time!"); 
        }

    }
      
    
}

