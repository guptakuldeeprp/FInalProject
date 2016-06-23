package com.example;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by kuldeep.gupta on 21-06-2016.
 */
public class JokeFactory {


    private static List<Joke> jokeCache = new ArrayList<>();

    static {
        // build cache
        jokeCache.add(new Joke("This is a really funny joke!"));
        jokeCache.add(new Joke("This is another awesome joke!"));
        jokeCache.add(new Joke("Here's a super funny joke!"));
    }

    public static Joke getJoke() {
        //int i = randomWithRange(0,jokeCache.size() - 1);
        int i = (int)(Math.random()*jokeCache.size());
        return jokeCache.get(i);
    }

    private static int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 5; i++) {
            System.out.println(JokeFactory.getJoke());
        }
    }

}
