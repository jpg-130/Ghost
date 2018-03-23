package com.google.engedu.ghost;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;
    private Random random = new Random();

    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while ((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
                words.add(line.trim());
        }
    }

    @Override
    public boolean isWord(String word) {
        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {

        if (prefix == null || prefix.equals("")) {
            // if prefix is empty return any word
            String c=words.get(random.nextInt(words.size())).substring(0);
            return c;
        }
        // return a word that has prefix as prefix
        //else {if(prefix!=null)
            return binarysearch(prefix,0,words.size());
        //}

        //return null;
    }


    public String binarysearch(String prefix, int start, int end) {
        int mid;
        int m=random.nextInt(17);
        int c=random.nextInt(5);
        //Log.d("TAG","buiiugo");
        while (start <= end) {
            mid = (start + end) / 2;
            if (words.get(mid+m).startsWith(prefix)) {//012345
                return words.get(mid+m); // break and return word
            }
            if (words.get(mid+c).startsWith(prefix)) {//012345
                return words.get(mid+c); // break and return word
            }
            if (words.get(mid).startsWith(prefix)) {//012345
                return words.get(mid); // break and return word
            }
            if (words.get(mid).compareTo(prefix) < 0) {
                start = mid + 1;
                return binarysearch(prefix,start,end);
            } else {
                end = mid - 1;
                return binarysearch(prefix,start,end);
            }

        }
        return null;
    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        String selected = null;
        return selected;
    }
}
