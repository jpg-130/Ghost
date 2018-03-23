/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private static int wordLength = DEFAULT_WORD_LENGTH;
    private static String selectedWord=null;

    ArrayList<String> wordList=new ArrayList<String>();
    HashMap<String,ArrayList<String>> lettersToWord=new HashMap<>();
    HashSet<String> wordSet=new HashSet<>();
    private static HashMap<Integer, ArrayList<String>> sizeToWords = new HashMap<>();

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null) { //reading every line
            String word = line.trim();
            wordSet.add(word); //adding each word to a hashset
            //wordList.add(word);
            if (lettersToWord.containsKey(sortLetters(word))){
                ArrayList<String> anagrams = lettersToWord.get(sortLetters(word));
                anagrams.add(word);
                lettersToWord.put(sortLetters(word),anagrams);
            }
            else {
                ArrayList<String> anagrams=new ArrayList<>();
                anagrams.add(word);
                lettersToWord.put(sortLetters(word),anagrams);
            }
            if(sizeToWords.containsKey(word.length())) {
                sizeToWords.get(word.length()).add(word);
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(word);
                sizeToWords.put(word.length(), list);
            }

        }
    }

    public boolean isGoodWord(String word, String base) {
        if (wordSet.contains(word)&& !base.contains(word)){
            return true;
        }
        return false;
    }

    public List<String> getAnagrams(String word) {
        ArrayList<String> result = new ArrayList<String>();
        /*String sortedTargetWord=sortLetters(targetWord);
        for (int i=0;i<wordList.size(); i++){
            String sortedWordListWord=sortLetters(wordList.get(i));
            if(sortedTargetWord.equalsIgnoreCase(sortedWordListWord))
                result.add(wordList.get(i));
        }*/
        if (lettersToWord.containsKey(sortLetters(word))){
            //get list of anagrams
            ArrayList<String> listAnagrams= lettersToWord.get(sortLetters(word));
            //add each anagram
            for (int i=0; i < listAnagrams.size(); i++){
                if (isGoodWord(word, listAnagrams.get(i))){
                    result.add(listAnagrams.get(i));
                }
            }
        }

        return result;
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();

        for (char letter ='a'; letter <='z'; letter++){
            if (lettersToWord.containsKey(sortLetters(word + letter))){
            //get list of anagrams
                ArrayList<String> listAnagrams= lettersToWord.get(sortLetters(word + letter));
                //add each anagram
                for (int i=0; i < listAnagrams.size(); i++){
                    if (isGoodWord(word, listAnagrams.get(i))){
                        result.add(listAnagrams.get(i));
                    }
                }
            }
        }
        return result;
    }

    public String pickGoodStarterWord() {

       /* int randomNumber;
        int numAnagrams =0;

        ArrayList<String> listWordsMaxLength = lettersToWord.get(wordLength);
        int arraySize =listWordsMaxLength.size();

        while(numAnagrams< MIN_NUM_ANAGRAMS){
            randomNumber= random.nextInt(arraySize);
        }*/
        //String selectedWord;
        //selectedWord
        int rand = random.nextInt(62997);
        ArrayList<String> list = sizeToWords.get(wordLength);

        while (list.size() < 1){
            wordLength++;
            list = sizeToWords.get(wordLength);
        }

        int i = Math.abs(random.nextInt() % list.size());
        String answer = new String();
        int counter = 0;

        for ( ; ; i++, counter++) {
            String word = list.get(i);
            String sortedword = sortLetters(word);
            ArrayList<String> sortedwordList = lettersToWord.get(sortedword);
            if (sortedwordList.size() >= MIN_NUM_ANAGRAMS) {
                answer = word;
                break;
            }

            if (i == list.size() - 1) {
                i = 0;
            }

            if (counter == list.size()){
                if (wordLength < MAX_WORD_LENGTH){
                    wordLength++;
                }
                list = sizeToWords.get(wordLength);
                i = Math.abs(random.nextInt() % list.size());
                counter = 0;
            }
        }

        if (wordLength < MAX_WORD_LENGTH) {
            wordLength++;
        }

        return answer;
    }
     //   return "fail";

    public String sortLetters(String targetWord) {
        char[] sortedletters = targetWord.toCharArray();
        Arrays.sort(sortedletters);
        String sortedTargetWord = new String(sortedletters);
        return sortedTargetWord;
    }
}
