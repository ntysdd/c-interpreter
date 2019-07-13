package ntysdd.c.interpreter;

import lombok.ToString;

@ToString
public enum OpToken implements Token {
    HASH("#", TokenType.HASH),
    DOUBLE_HASH("##", TokenType.DOUBLE_HASH),
    LEFT_ANGLE("<", TokenType.LEFT_ANGLE),
    RIGHT_ANGLE(">", TokenType.RIGHT_ANGLE),
    LEFT_SHIFT("<<", TokenType.LEFT_SHIFT),
    RIGHT_SHIFT(">>", TokenType.RIGHT_SHIFT),
    LESS_OR_EQUAL("<=", TokenType.LESS_OR_EQUAL),
    GREATER_OR_EQUAL(">=", TokenType.GREATER_OR_EQUAL),
    LEFT_PAREN("(", TokenType.LEFT_PAREN),
    RIGHT_PAREN(")", TokenType.RIGHT_PAREN),
    LEFT_SQUARE_BRACKET("[", TokenType.LEFT_SQUARE_BRACKET),
    RIGHT_SQUARE_BRACKET("]", TokenType.RIGHT_SQUARE_BRACKET),
    LEFT_BRACE("{", TokenType.LEFT_BRACE),
    RIGHT_BRACE("}", TokenType.RIGHT_BRACE),
    SEMICOLON(";", TokenType.SEMICOLON),
    DOT(".", TokenType.DOT),
    ARROW("->", TokenType.ARROW),
    COMMA(",", TokenType.COMMA),
    PLUS("+", TokenType.PLUS),
    MINUS("-", TokenType.MINUS),
    STAR("*", TokenType.STAR),
    DIVIDE("/", TokenType.DIVIDE),
    REMAINDER("%", TokenType.REMAINDER),
    DOUBLE_PLUS("++", TokenType.DOUBLE_PLUS),
    DOUBLE_MINUS("--", TokenType.DOUBLE_MINUS),
    ASSIGNMENT("=", TokenType.ASSIGNMENT),
    EQUAL("==", TokenType.EQUAL),
    ;

    private OpToken(String sourceCode, TokenType tokenType) {
        this.sourceCode = sourceCode;
        this.tokenType = tokenType;
    }

    private final String sourceCode;
    private final TokenType tokenType;

    @Override
    public String getSourceCode() {
        return sourceCode;
    }

    @Override
    public TokenType getTokenType() {
        return tokenType;
    }
}
