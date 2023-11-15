package sd.java.lab5;

import sd.java.lab5.textprocessor.Sentence;
import sd.java.lab5.textprocessor.SentenceElement;
import sd.java.lab5.textprocessor.Text;
import sd.java.lab5.textprocessor.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Lab5Application {
    Lab5Application() {}

    public static void run() {
        Scanner scanner = new Scanner(System.in);

        Text text = getInputText(scanner);
        int lengthToReplace = getInputLengthToReplace(scanner);
        Text replacement = getInputReplacement(scanner);

        Text modifiedText = replaceWordsOfLength(text, lengthToReplace, replacement);

        System.out.println("Modified Text:");
        System.out.println(modifiedText);
    }

    private static Text getInputText(Scanner scanner) {
        Text text;
        while (true) {
            try {
                System.out.print("Enter the text: ");
                text = new Text(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid input. Please enter a valid text.");
            }
        }
        return text;
    }

    private static int getInputLengthToReplace(Scanner scanner) {
        int lengthToReplace;
        while (true) {
            try {
                System.out.print("Enter the word length to replace: ");
                lengthToReplace = scanner.nextInt();

                if (lengthToReplace < 1) {
                    System.out.println("Please enter a positive number.");
                }

                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid int.");
                scanner.nextLine();
            }
        }
        return lengthToReplace;
    }

    private static Text getInputReplacement(Scanner scanner) {
        Text replacement;
        while (true) {
            try {
                System.out.print("Enter the replacement text: ");
                replacement = new Text(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid input. Please enter a valid text.");
            }
        }
        return replacement;
    }

    private static Text replaceWordsOfLength(Text text, int length, Text replacement) {
        // Check for null or invalid inputs
        if (text == null || length <= 0 || replacement == null) {
            return text;
        }

        // List to store modified sentences
        List<Sentence> modifiedSentences = new ArrayList<>();

        // Iterate through each sentence in the input text
        for (Sentence sentence : text.sentences) {
            // List to store modified sentence elements
            List<SentenceElement> modifiedElements = new ArrayList<>();

            // Iterate through each element in the sentence
            for (SentenceElement element : sentence.elements) {
                if (element instanceof Word word) {
                    // If the length of the word matches the specified length, add replacement elements
                    if (word.letters.size() == length) {
                        modifiedElements.addAll(replacement.sentences.stream()// Convert the replacement Text into a Stream of Sentences
                                .flatMap(s -> s.elements.stream()) // For each Sentence, convert its elements into a Stream
                                .toList()); // Collect all the elements into a List
                    } else {
                        // If the length does not match, add the original word
                        modifiedElements.add(word);
                    }
                } else {
                    // If the element is not a Word, add it as is
                    modifiedElements.add(element);
                }
            }
            // Create a new Sentence with the modified elements and add it to the list
            modifiedSentences.add(new Sentence(modifiedElements));
        }
        // Create a new Text object from the modified sentences and return it to split into sentences one more time
        return new Text(new Text(modifiedSentences).toString());
    }
}
