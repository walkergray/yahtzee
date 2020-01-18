package com.company;

import java.util.ArrayList;
import java.util.List;

public class ScoreLinesSixSidedDieYahtzee
{
	final private int FULL_HOUSE_VALUE = 25;
	final private int SMALL_STRAIGHT_VALUE = 30;
	final private int LARGE_STRAIGHT_VALUE = 40;
	final private int YAHTZEE_VALUE = 50;

	private int oneLine;
	private int twoLine;
	private int threeLine;
	private int fourLine;
	private int fiveLine;
	private int sixLine;
	private int threeOfAKindLine;
	private int fourOfAKindLine;
	private int fullHouseLine;
	private int smallStraightLine;
	private int largeStraightLine;
	private int yahtzeeLine;
	private int chanceLine;

	public ScoreLinesSixSidedDieYahtzee(ArrayList<SixSidedDie> sortedSetOfDice)
	{
		oneLine = 0;
		threeLine = 0;
		fourLine = 0;
		fiveLine = 0;
		sixLine = 0;
		threeOfAKindLine = 0;
		fourOfAKindLine = 0;
		fullHouseLine = 0;
		smallStraightLine = 0;
		largeStraightLine = 0;
		yahtzeeLine = 0;
		chanceLine = 0;

		//sets one-six lines and chance
		sortedSetOfDice.forEach((die) ->
		{
			switch(die.getCurrentFace())
			{
				case 1:
					oneLine += die.getCurrentFace();
					break;
				case 2:
					twoLine += die.getCurrentFace();
					break;
				case 3:
					threeLine += die.getCurrentFace();
					break;
				case 4:
					fourLine += die.getCurrentFace();
					break;
				case 5:
					fiveLine += die.getCurrentFace();
					break;
				default:
					sixLine += die.getCurrentFace();
			}
			chanceLine += die.getCurrentFace();
		});

		//sets 3 of a kind
		for(int i = (sortedSetOfDice.size()-3); i >= 0; i--)
		{
			List<SixSidedDie> checkForThreeKind = sortedSetOfDice.subList(i, i+3);
			if(checkForThreeKind.get(0).getCurrentFace()==checkForThreeKind.get(1).getCurrentFace() &&
			   checkForThreeKind.get(0).getCurrentFace()==checkForThreeKind.get(2).getCurrentFace())
			{
				threeOfAKindLine = chanceLine;
				if(i == (sortedSetOfDice.size()-1)-3) //full house check
				{
					if(sortedSetOfDice.get(0).getCurrentFace()==sortedSetOfDice.get(1).getCurrentFace())
					{
						fullHouseLine = FULL_HOUSE_VALUE;
					}
				} else if(i == 0) {
					if(sortedSetOfDice.get((sortedSetOfDice.size()-1)).getCurrentFace()==sortedSetOfDice.get((sortedSetOfDice.size()-2)).getCurrentFace())
					{
						fullHouseLine = FULL_HOUSE_VALUE;
					}
				}
				break;
			}
		}

		//sets 4 of a kind
		if(oneLine >= 1*4   ||
		   twoLine >= 2*4   ||
		   threeLine >= 3*4 ||
		   fourLine >= 4*4  ||
		   fiveLine >= 5*4  ||
		   sixLine >= 6*4)
		{
			fourOfAKindLine = chanceLine;
		}

		//small straight, large straight, and yahtzee are behind a condition because efficiency
		if(fourOfAKindLine == 0)
		{
			//small straight
			if(threeLine >= 3 && fourLine >= 4)
			{
				boolean oneLineGreater = oneLine >= 1;
				boolean twoLineGreater = twoLine >= 2;
				boolean fiveLineGreater = fiveLine >= 5;
				boolean sixLineGreater = sixLine >= 6;

				if(twoLineGreater && (oneLineGreater || fiveLineGreater) || sixLineGreater && fiveLineGreater)
				{
					smallStraightLine = SMALL_STRAIGHT_VALUE;
				}
			}

			//large straight

			if(twoLine >= 2 && threeLine >= 3 && fourLine >= 4 && fiveLine >= 5)
			{
				boolean oneLineGreater = oneLine >= 1;
				boolean sixLineGreater = sixLine >= 6;

				if(oneLineGreater || sixLineGreater)
				{
					largeStraightLine = LARGE_STRAIGHT_VALUE;
				}
			}

			//yahtzee
			if(oneLine == 1*sortedSetOfDice.size()   ||
			   twoLine == 2*sortedSetOfDice.size()   ||
			   threeLine == 3*sortedSetOfDice.size() ||
			   fourLine == 4*sortedSetOfDice.size()  ||
			   fiveLine == 5*sortedSetOfDice.size()  ||
			   sixLine == 6*sortedSetOfDice.size())
			{
				yahtzeeLine = YAHTZEE_VALUE;
			}
		}
	}

	//private function to go back and 0 the ones already chosen can be added at future time

	public int getOneLine()
	{
		return oneLine;
	}

	public int getTwoLine()
	{
		return twoLine;
	}

	public int getThreeLine()
	{
		return threeLine;
	}

	public int getFourLine()
	{
		return fourLine;
	}

	public int getFiveLine()
	{
		return fiveLine;
	}

	public int getSixLine()
	{
		return sixLine;
	}

	public int getThreeOfAKindLine()
	{
		return threeOfAKindLine;
	}

	public int getFourOfAKindLine()
	{
		return fourOfAKindLine;
	}

	public int getFullHouseLine()
	{
		return fullHouseLine;
	}

	public int getSmallStraightLine()
	{
		return smallStraightLine;
	}

	public int getLargeStraightLine()
	{
		return largeStraightLine;
	}

	public int getYahtzeeLine()
	{
		return yahtzeeLine;
	}

	public int getChanceLine()
	{
		return chanceLine;
	}
}
