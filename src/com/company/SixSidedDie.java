package com.company;
import java.util.Arrays;
import java.util.Random;

public class SixSidedDie extends DieLikeObject<Integer> implements Comparable<SixSidedDie>
{
    private int currentFace;

    public SixSidedDie()
    {
        super(Arrays.asList(1, 2, 3, 4, 5, 6));
        roll();
    }

    @Override
    public void roll()
    {
        Random rand = new Random(System.nanoTime());
        currentFace = getFaces().get(rand.nextInt(getFaces().size()));
    }

    public int getCurrentFace()
    {
        return currentFace;
    }

    @Override
    public int compareTo(SixSidedDie o)
    {
        return this.getCurrentFace() - o.getCurrentFace();
    }
}