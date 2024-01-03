package academy.pocu.comp2500.assignment4;

public enum CanvasInfo {
    LOW_LIMIT_PRINTABLE_ASCII((char) 32),
    HIGH_LIMIT_PRINTABLE_ASCII((char) 126),
    LOWER_A('a'),
    LOWER_Z('z'),
    UPPER_A('A'),
    UPPER_Z('Z'),
    INIT_CHARACTER((char) 32),
    NULL_CHARACTER((char) 0x00);

    private char character;

    private CanvasInfo(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}
