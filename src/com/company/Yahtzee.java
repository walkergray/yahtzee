package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Yahtzee
{
	static final int NUMBER_OF_DICE = 5;
	static final int NUMBER_OF_ROLLS_PER_TURN = 3;

    /*
    	plan:
        const numOfDice ===== final numOfDice
        create ArrayList<SixSidedDie> using numOfDice
        function for initialize
        function for primary execution loop
        function for executing rolls
        function for viewing (outputting) current dice' face
        function for calculating and returning scores
        function for outputting scores
        need way of designating which dice to not roll --- or which dice TO roll
        function to parse the user inputted string for dice to keep
        globals should be declared in main - better efficiency. if needed, create a second main
        function for requesting and accepting user input;
        const numRollsPerTurn;
     */

    public static ArrayList<SixSidedDie> initialize(int numOfDice)
	{
		ArrayList<SixSidedDie> boxOfDice = new ArrayList<>();
		for(int i = 0; i < numOfDice; i++)
		{
			boxOfDice.add(new SixSidedDie());
		}
		return boxOfDice;
	}

	public static void printScores(ArrayList<SixSidedDie> setOfDice)
	{
		setOfDice.sort(null);
		System.out.print("Here is your sorted hand:");
		viewTheDice(setOfDice);
		ScoreLinesSixSidedDieYahtzee scoreSheet = new ScoreLinesSixSidedDieYahtzee(setOfDice);
		System.out.printf("Score %d on the 1 line%n", scoreSheet.getOneLine());
		System.out.printf("Score %d on the 2 line%n", scoreSheet.getTwoLine());
		System.out.printf("Score %d on the 3 line%n", scoreSheet.getThreeLine());
		System.out.printf("Score %d on the 4 line%n", scoreSheet.getFourLine());
		System.out.printf("Score %d on the 5 line%n", scoreSheet.getFiveLine());
		System.out.printf("Score %d on the 6 line%n", scoreSheet.getSixLine());
		System.out.printf("Score %d on the 3 of a Kind line%n", scoreSheet.getThreeOfAKindLine());
		System.out.printf("Score %d on the 4 of a Kind line%n", scoreSheet.getFourOfAKindLine());
		System.out.printf("Score %d on the Full House line%n", scoreSheet.getFullHouseLine());
		System.out.printf("Score %d on the Small Straight line%n", scoreSheet.getSmallStraightLine());
		System.out.printf("Score %d on the Large Straight line%n", scoreSheet.getLargeStraightLine());
		System.out.printf("Score %d on the Yahtzee line%n", scoreSheet.getYahtzeeLine());
		System.out.printf("Score %d on the Chance line%n", scoreSheet.getChanceLine());
		System.out.println();
	}

	public static char continuePlayingPrompt()
	{
		Scanner askTheUser = new Scanner(System.in);
		System.out.print("Enter 'y' to play again ");
		return askTheUser.next().toLowerCase().charAt(0);
	}

	public static String getUserInput()
	{
		Scanner askTheUser = new Scanner(System.in);
		System.out.print("enter dice to keep (y or n) ");
		String userInput = askTheUser.nextLine();
		if(userInput.length() > NUMBER_OF_DICE)
		{
			userInput = userInput.substring(0,NUMBER_OF_DICE);
		} else {
			while(userInput.length() < NUMBER_OF_DICE)
			{
				userInput += "n";
			}
		}
		return userInput;
	}

	public static boolean[] parseKeepString(String diceToKeep)
	{
		String[] individualResponses = diceToKeep.split("(?!^)");
		boolean[] diceChoices = new boolean[NUMBER_OF_DICE];
		Arrays.fill(diceChoices, true);
		for(int i = 0; i < NUMBER_OF_DICE; i++)
		{
			diceChoices[i] = individualResponses[i].toLowerCase().equals("n");
		}
		return diceChoices;
	}

	public static void rollTheDice(ArrayList<SixSidedDie> cupOfDice)
	{
		cupOfDice.forEach(SixSidedDie::roll);
	}

	public static void viewTheDice(ArrayList<SixSidedDie> setOfDice)
	{
		setOfDice.forEach((die) -> System.out.printf(" %d", die.getCurrentFace()));
		System.out.println();
	}

	public static void playOneRound(ArrayList<SixSidedDie> setOfDice)
	{
		String diceToKeep = "nnnnn";
		for(int timesRolled = 0; !diceToKeep.equals("yyyyy");)
		{
			boolean[] diceChosenForRoll = parseKeepString(diceToKeep);
			ArrayList<SixSidedDie> cupOfDice = new ArrayList<>();
			for (int i = 0; i < NUMBER_OF_DICE; i++)
			{
				if (diceChosenForRoll[i])
				{
					cupOfDice.add(setOfDice.get(i));
				}
			}
			rollTheDice(cupOfDice);
			timesRolled++;
			if(timesRolled >= NUMBER_OF_ROLLS_PER_TURN)
				break;
			System.out.print("Your roll was:");
			viewTheDice(setOfDice);
			diceToKeep = getUserInput();
		}
	}

	public static void playYahtzeeGame(ArrayList<SixSidedDie> setOfDice)
	{
		char userContinuePlaying = 'y';
		while(userContinuePlaying == 'y')
		{
			rollTheDice(setOfDice); //shuffle
			playOneRound(setOfDice);
			printScores(setOfDice);
			userContinuePlaying = continuePlayingPrompt();
		}
	}

	public static void main(String[] args)
	{
		ArrayList<SixSidedDie> setOfDice = initialize(NUMBER_OF_DICE);
		playYahtzeeGame(setOfDice);
	}
}
