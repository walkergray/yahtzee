package com.company;

import java.util.ArrayList;
import java.util.Collection;

abstract class DieLikeObject<T>
{

    private ArrayList<T> faces;

    protected DieLikeObject(Collection<T> collection)
    {
        this.faces = new ArrayList<>(collection);
    }

    public abstract void roll();
    protected ArrayList<T> getFaces()
    {
        return faces;
    }
}
