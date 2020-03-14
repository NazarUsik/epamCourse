package ua.nure.usik.practice6.part1;

public class Word {
    private String content;
    private int frequency;

    public Word(String content) {
        this.content = content;
        this.frequency = 1;
    }


    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getContent() {
        return content;
    }

    public void increaseFrequency(){
        this.frequency++;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        Word word = (Word)obj;
        return this.content.equals(word.content);
    }

    @Override
    public int hashCode() {
        return this.frequency;
    }

    @Override
    public String toString() {
        return this.content + " : " + this.frequency;
    }
}
