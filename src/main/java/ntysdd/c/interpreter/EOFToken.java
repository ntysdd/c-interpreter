package ntysdd.c.interpreter;

public enum EOFToken implements Token {
    EOF;

    @Override
    public TokenType getTokenType() {
        return TokenType.EOF;
    }

    @Override
    public String getSourceCode() {
        return "";
    }
}
