package ntysdd.c.interpreter;

public interface Token {
    TokenType getTokenType();
    String getSourceCode();

    static Token EOF = new Token() {
        @Override
        public TokenType getTokenType() {
            return TokenType.EOF;
        }

        @Override
        public String getSourceCode() {
            return "";
        }
    };
}
