package com.example.android.miwok;

public class Word {

    private String mDefault;
    private String mMiwok;

    public Word(String defaultWord, String miwokWord) {
        mDefault = defaultWord;
        mMiwok = miwokWord;
    }

    public String getDefault() {
        return mDefault;
    }

    public String getMiwok() {
        return mMiwok;
    }
}
