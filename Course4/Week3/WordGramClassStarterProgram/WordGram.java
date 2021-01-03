
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        // TODO: Complete this method
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        // TODO: Complete this method
        for (int m = 0; m < myWords.length; m++) {
            ret = ret + myWords[m] + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        if (other.length() != myWords.length) {
            return false;
        }
        
        for (int m = 0; m < myWords.length; m++) {
            if (!myWords[m].equals(other.wordAt(m))) {
                return false;
            }
        }
        return true;

    }

    public WordGram shiftAdd(String word) { 
        // WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method
        String[] outWords = new String[myWords.length];
        System.arraycopy(myWords, 1, outWords, 0, myWords.length-1);
        outWords[myWords.length - 1] = word;
        WordGram out = new WordGram(outWords, 0, myWords.length);
        
        return out;
    }
    
    public int hashCode() {
        // String str = String.join(" ", myWords);
        // myHash = str.hashCode();
        
        myHash = toString().hashCode();
        return myHash;
    }

}