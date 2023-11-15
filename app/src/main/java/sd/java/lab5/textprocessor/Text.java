package sd.java.lab5.textprocessor;

import java.util.ArrayList;
import java.util.List;

public class Text {
    public List<Sentence> sentences;

    public Text(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public Text(String text) {
        // Check for invalid input
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Invalid text: " + text);
        }

        // Initialize the list of sentences
        this.sentences = new ArrayList<>();

        // Split the input text into sentences and create Sentence objects
        List<String> sentenceStrings = splitIntoSentences(text);

        for (String sentence : sentenceStrings) {
            sentences.add(new Sentence(sentence));
        }
    }

    private static List<String> splitIntoSentences(String text) {
        List<String> sentences = new ArrayList<>();
        StringBuilder currentSentence = new StringBuilder();
        boolean withinWord = false;

        // Iterate through each character in the input text
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            currentSentence.append(c);

            if (c == '.' || c == '!' || c == '?') {
                withinWord = false;

                // Check if there's another punctuation mark after this one
                int nextIndex = i + 1;
                while (nextIndex < text.length() && (text.charAt(nextIndex) == '.' || text.charAt(nextIndex) == '!' || text.charAt(nextIndex) == '?')) {
                    currentSentence.append(text.charAt(nextIndex));
                    nextIndex++;
                }
                // Trimming
                int start = 0;
                int end = currentSentence.length() - 1;

                // Find the start index of non-whitespace characters
                while (start < currentSentence.length() && Character.isWhitespace(currentSentence.charAt(start))) {
                    start++;
                }

                // Find the end index of non-whitespace characters
                while (end >= 0 && Character.isWhitespace(currentSentence.charAt(end))) {
                    end--;
                }

                // Build the trimmed sentence using StringBuilder
                StringBuilder trimmedSentence = new StringBuilder();
                for (int index = start; index <= end; index++) {
                    trimmedSentence.append(currentSentence.charAt(index));
                }

                sentences.add(trimmedSentence.toString());
                currentSentence.setLength(0);  // Reset the current sentence

                i = nextIndex - 1;  // Skip the consecutive punctuation marks

            } else if (Character.isLetterOrDigit(c)) {
                withinWord = true;
            } else if (withinWord && Character.isWhitespace(c)) {
                withinWord = false;
            }
        }

        // Add the last sentence if present
        if (!currentSentence.isEmpty()) {
            // Manual trimming
            int start = 0;
            int end = currentSentence.length() - 1;

            while (start < currentSentence.length() && Character.isWhitespace(currentSentence.charAt(start))) {
                start++;
            }

            while (end >= 0 && Character.isWhitespace(currentSentence.charAt(end))) {
                end--;
            }

            StringBuilder trimmedSentence = new StringBuilder();
            for (int i = start; i <= end; i++) {
                trimmedSentence.append(currentSentence.charAt(i));
            }

            // Add the trimmed sentence to the list
            sentences.add(trimmedSentence.toString());
        }

        return sentences;
    }

    @Override
    public String toString() {
        return sentences.stream().map(Sentence::toString).collect(java.util.stream.Collectors.joining("\n"));
    }
}
