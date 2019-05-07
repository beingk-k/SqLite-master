package model;

public class Word {

    private int WordId;
    private String word;
    private String meaning;

    public Word(){}

    public Word(int wordId, String word, String meaning) {
        WordId = wordId;
        this.word = word;
        this.meaning = meaning;
    }

    public int getWordId() {
        return WordId;
    }

    public void setWordId(int wordId) {
        WordId = wordId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
