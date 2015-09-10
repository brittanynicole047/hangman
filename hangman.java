import java.util.Random;
import java.util.Scanner;


public class Hangman {

	//Secret Word List
	static final String[] SECRET_WORDS = {"notions", "measure", "product", "foliage",
		"garbage", "minutes", "chowder", "recital", "concoct", "brownie"};
	
	//global variables used
	 final static int MAX_BAD_GUESSES= 7;
	 static int badGuessesCount; 
	 static String secret; 
	 static boolean Keeplooping = true; 
	
	
	//main method containing game loop
	public static void main(String[] args){
		System.out.println("Welcome to Hangman!");
		System.out.println("Generating a secret word....."); 
		
		Scanner scan = new Scanner(System.in);
		String input;
		String word = null;  //the random word that the player will be guessing
		
		
		//main game begins
		do {
			getGameWord();
		
			//array to keep track of the correct letters guessed
			//   _ inserted for letters not guessed
		  	char[] correctLetters= new char [secret.length()]; 
			for (int i= 0; i<correctLetters.length; i++){
				correctLetters[i]='_'; 			
			}	
			
			//array to keep track of incorrect letters guessed
			char incorrectLetters[] = new char [MAX_BAD_GUESSES]; 			
	
		
			while (Keeplooping){
		
				printCorrectLetters(correctLetters);
				System.out.println();
				printIncorrectGuesses(incorrectLetters);	
		
				System.out.println("\nEnter a letter as your guess:");
				String guess = scan.next();
				guess = guess.toLowerCase(); 
				char l = guess.charAt(0);
				
		
				//checks guess & adds to incorrect guesses if incorrect
				if(!fillInGuess(secret, correctLetters, l)){	
					System.out.println("Sorry Incorrect Guess!!!");
					incorrectLetters [badGuessesCount] = l;  
					badGuessesCount++; 
					showMan(badGuessesCount);
				}
			
				else{
					System.out.println("GOOD GUESS!!"); 
				}
				
				//if else statement determining if player wins or loses
				if (badGuessesCount== MAX_BAD_GUESSES){
					System.out.println("GAMEOVER!!!!"); 
					System.out.println("Your Word was " +secret); 
					Keeplooping = false; 
				}
	
				else if (wordGuessed(correctLetters)== true){
					printCorrectLetters(correctLetters);
					System.out.println("YOU WIN!!"); 
					Keeplooping = false; 
				}
		
			}	
			
			System.out.println("Would you like to play again Y or N ? "); 
			input = scan.next();
			input = input.toLowerCase(); 
			Keeplooping = true;
			badGuessesCount = 0; 
			
			
		}while(input.equals("y"));
			
		   System.out.println("Goodbye!"); 
		   
		   scan.close(); 
	}
			
	//adds correct guesses to the correctLetters[]
	public static boolean fillInGuess(String word, char[] correctLetters, char guess){
		boolean found=false;
		
		for (int i=0; i<word.length(); i++) {
			if(word.charAt(i) == guess){
				found = true;
				correctLetters[i] = guess;
				
			}
		}
			return found;
	}
	
	
    //returns true if the correctLetters[] is full of letters & no _
	private static boolean wordGuessed(char[] correctLetters) {
		boolean found = true;
		
        for (int i=0; i<correctLetters.length; i++) {
        	if(correctLetters[i] == '_') {
        		found = false; 		
			
        	}
        }
		return found;
    }

	
	 //Prints the letters which were incorrectly guessed so far	
	public static void printIncorrectGuesses(char [] incorrectGuesses){
		System.out.print("Incorrect Guesses: ");
		for (int i=0; i<incorrectGuesses.length; i++) 
		
		System.out.print(incorrectGuesses[i]);
		
	}

	//shows correct letters guessed mixed with _
	public static void printCorrectLetters(char [] correctLetters){
		System.out.println("Your word is...");
			for (int i=0; i<correctLetters.length; i++){
			System.out.print(correctLetters[i] +  " ");	
			
		}	
	}
	
	//picks a random word from the list
	public static void getGameWord(){
	   
		Random ran = new Random();		
	    int ranWord= ran.nextInt(SECRET_WORDS.length);    		
	    secret= SECRET_WORDS[ranWord];  
	     	
	
	}
	
	
	//displays the hangman parts when an incorrect guess is made
	public static void showMan(int badGuessesCount)
	{
		int poleLines = 6;   // number of lines for hanging pole
		System.out.println("  ____");
		System.out.println("  |  |");

		if (badGuessesCount == 7) {
			System.out.println("  |  |");
			System.out.println("  |  |");
		}

		if (badGuessesCount > 0) {              
			System.out.println("  |  O");
			poleLines = 5;
		}
    
		if (badGuessesCount > 1) {
			poleLines = 4;
			if (badGuessesCount == 2) {
				System.out.println("  |  |");
			} else if (badGuessesCount == 3) {
				System.out.println("  | \\|");
			} else if (badGuessesCount >= 4) {
				System.out.println("  | \\|/");
			}
		}
    
		if (badGuessesCount > 4) {
			poleLines = 3;
			System.out.println("  |  |");
		}
    
		if (badGuessesCount == 6) {
			poleLines = 2;
			System.out.println("  | /");
		} else if (badGuessesCount >= 7) {
			poleLines = 0;
			System.out.println("  | / \\");
		}

		for (int k = 0; k < poleLines; k++) {
			System.out.println("  |");
		}
		System.out.println("__|__");
		System.out.println();
	}
			
}
