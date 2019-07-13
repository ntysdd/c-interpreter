package ntysdd.c.interpreter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommentToken implements Token {
    private final String sourceCode;

    @Override
    public String getSourceCode() {
        return sourceCode;
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.COMMENT;
    }
}
