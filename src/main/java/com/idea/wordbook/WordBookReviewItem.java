package com.idea.wordbook;

import java.util.Date;

public class WordBookReviewItem {
    private String word;
    private String explanation;
    private int reviewCount; // 复习次数
    private Date lastReviewedTime; // 最后复习时间

    public WordBookReviewItem(String word, String explanation) {
        this.word = word;
        this.explanation = explanation;
        this.reviewCount = 0;
        this.lastReviewedTime = new Date();
    }

    public String getWord() {
        return word;
    }

    public String getExplanation() {
        return explanation;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void incrementReviewCount() {
        this.reviewCount++;
    }

    public Date getLastReviewedTime() {
        return lastReviewedTime;
    }

    public void setLastReviewedTime(Date lastReviewedTime) {
        this.lastReviewedTime = lastReviewedTime;
    }
}
