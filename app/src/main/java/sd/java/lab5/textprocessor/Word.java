package sd.java.lab5.textprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Word implements SentenceElement {
    public List<Letter> letters;

    public Word(List<Letter> letters) {
        this.letters = letters;
    }

    public Word(String word) {
        // Check for invalid input
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Invalid word: " + word);
        }

        // Initialize the list of letters
        this.letters = new ArrayList<>();

        // Iterate through each character in the input word
        for (char c : word.toCharArray()) {
            // Check if the character is a valid letter or digit
            if (!Character.isLetterOrDigit(c)) {
                throw new IllegalArgumentException("Invalid word: " + word);
            }
            letters.add(new Letter(c));
        }
    }

    @Override
    public String toString() {
        return letters.stream().map(Letter::toString).collect(Collectors.joining(""));
    }
}
