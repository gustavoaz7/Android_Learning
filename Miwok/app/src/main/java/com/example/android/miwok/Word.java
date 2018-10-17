package com.example.android.miwok;

public class Word {

    private static final int NO_IMAGE = -1;
    private String mDefault;
    private String mMiwok;
    private int mImageResourceId = NO_IMAGE;

    public Word(String defaultWord, String miwokWord) {
        mDefault = defaultWord;
        mMiwok = miwokWord;
    }

    public Word(String defaultWord, String miwokWord, int imageResourceId) {
        mDefault = defaultWord;
        mMiwok = miwokWord;
        mImageResourceId = imageResourceId;
    }

    public String getDefault() {
        return mDefault;
    }

    public String getMiwok() {
        return mMiwok;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE;
    }
}
