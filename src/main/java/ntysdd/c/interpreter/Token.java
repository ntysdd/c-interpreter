package ntysdd.c.interpreter;

public interface Token {
    TokenType getTokenType();
    String getSourceCode();

    static Token EOF = EOFToken.EOF;
}
