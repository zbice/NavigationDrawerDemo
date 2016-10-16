package com.example.ayase.navigationdrawerdemo.dummy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * read, provide and save vocabulary
 */
//TODO:Glossary need implement serializable
public class Glossary implements Serializable {
    public static final List<Vocabulary> GLOSSARY = new ArrayList<>();
    public static final Map<String, Vocabulary> GLOSSARY_MAP = new HashMap<>();

    public void addWord(String word) {
        if (GLOSSARY_MAP.containsKey(word)) {
            return;
        }
        Vocabulary v = new Vocabulary(word);

        GLOSSARY_MAP.put(v.word, v);
        GLOSSARY.add(v);

    }

    public void addMeaning(String word, String meaning, String example) {
        if (!GLOSSARY_MAP.containsKey(word)) {
            addWord(word);
        }
        //get vocabulary, put meaning and example
        Vocabulary v = GLOSSARY_MAP.get(word);
        GLOSSARY_MAP.remove(word);
        v.meaning.add(meaning);
        v.example.put(meaning, example);

        GLOSSARY_MAP.put(word, v);

        //add meaning and example for list
        for (Vocabulary item : GLOSSARY
                ) {
            if (item.word.equals(word)) {
                item.meaning.add(meaning);
                item.example.put(meaning, example);
            }
        }
    }

    public void removeWord(String word) {
        if (GLOSSARY_MAP.containsKey(word)) {
            GLOSSARY.remove(word);
        }
    }

    //add some member for debug
    static {
        for (int i = 0; i < 25; i++) {
            String word=String.valueOf(new Random().nextInt());
            String meaning=String.valueOf(new Random().nextDouble());
            String example=String.valueOf(new Random().nextBoolean());

            Vocabulary v=new Vocabulary(word);
            v.meaning.add(meaning);
            v.example.put(meaning,example);

            GLOSSARY.add(v);
        }
    }

    public static class Vocabulary {
        public final String word;
        public final List<String> meaning = new ArrayList<>();      //the list of meaning
        public final Map<String, String> example = new HashMap<>(); //the list of example, finding by meaning
        public final Date addingDate = new Date();

        public Vocabulary(String word) {
            this.word = word;
        }
    }
}
