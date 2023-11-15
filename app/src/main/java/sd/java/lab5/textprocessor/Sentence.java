package sd.java.lab5.textprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Sentence {
    public List<SentenceElement> elements;

    public Sentence(List<SentenceElement> elements) {
        this.elements = elements;
    }

    public Sentence(String sentence) {
        // Check for invalid input
        if (sentence == null || sentence.isEmpty()) {
            throw new IllegalArgumentException("Invalid sentence: " + sentence);
        }

        this.elements = new ArrayList<>();
        List<Letter> currentWord = new ArrayList<>();

        // Iterate through each character in the input sentence
        for (char c : sentence.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                // If the character is a letter or digit, add it to the current word
                currentWord.add(new Letter(c));
            } else {
                if (!currentWord.isEmpty()) {
                    // If the current word is not empty, add it as a Word and reset the current word
                    elements.add(new Word(new ArrayList<>(currentWord)));
                    currentWord.clear();  // Reset the current word
                }

                // If the character is a punctuation mark, add it as a PunctuationMark
                if (Pattern.matches("\\p{Punct}", String.valueOf(c))) {
                    elements.add(new PunctuationMark(c));
                }
            }
        }

        // Add the last word if present
        if (!currentWord.isEmpty()) {
            elements.add(new Word(new ArrayList<>(currentWord)));
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        // Iterate through each element in the sentence
        for (SentenceElement element : elements) {
            if (element instanceof PunctuationMark) {
                // If the element is a PunctuationMark, append it directly
                result.append(element);
            } else {
                // If the element is a Word, append it with a leading space
                result.append(" ").append(element.toString());
            }
        }

        return result.toString().trim();
    }

}
