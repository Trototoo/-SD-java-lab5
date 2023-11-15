package sd.java.lab5.textprocessor;

public class Letter {
    private char value;

    public Letter(char value) {
        setValue(value);
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        // Check if the provided value is a letter or digit
        if (!Character.isLetterOrDigit(value)) {
            throw new IllegalArgumentException("Invalid letter: " + value);
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
