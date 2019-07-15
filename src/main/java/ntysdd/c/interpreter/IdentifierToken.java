package ntysdd.c.interpreter;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public final class IdentifierToken implements Token {
    private final String sourceCode;

    @Override
    public String getSourceCode() {
        return sourceCode;
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.IDENTIFIER;
    }
}
