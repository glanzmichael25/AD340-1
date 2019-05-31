package com.telpirion.demo.quiz2answerkey;

import java.util.Random;

public class GossipNetwork {


    private static String[] TEMPLATES = {
            "%s is a lovely thought.",
            "I heard about %s, too.",
            "You don't say?! Who else knew about %s?"
    };

    public static String getLatest(String rumor) {
        Random random = new Random();
        int templateIndex = random.nextInt(3);

        String template = TEMPLATES[templateIndex];
        return String.format(template, rumor);
    }
}
