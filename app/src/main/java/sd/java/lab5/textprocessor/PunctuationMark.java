package sd.java.lab5.textprocessor;

import java.util.regex.Pattern;

public class PunctuationMark implements SentenceElement {
    // Regular expression pattern for validating punctuation marks
    private static final Pattern PUNCTUATION_PATTERN = Pattern.compile("\\p{Punct}");

    private char punctuationMark;

    public PunctuationMark(char punctuationMark) {
        setPunctuationMark(punctuationMark);
    }

    public char getPunctuationMark() {
        return punctuationMark;
    }

    public void setPunctuationMark(char punctuationMark) {
        // Check if the provided value is a valid punctuation mark using the regular expression pattern
        if (!PUNCTUATION_PATTERN.matcher(String.valueOf(punctuationMark)).matches()) {
            throw new IllegalArgumentException("Invalid punctuation mark: " + punctuationMark);
        }
        this.punctuationMark = punctuationMark;
    }

    @Override
    public String toString() {
        return String.valueOf(punctuationMark);
    }
}
