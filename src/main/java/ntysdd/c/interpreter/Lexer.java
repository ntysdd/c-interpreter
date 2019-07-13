package ntysdd.c.interpreter;

import java.io.InputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.BufferedInputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import lombok.SneakyThrows;

public final class Lexer extends Thread {
    private final InputStream is;
    private final BlockingQueue<Token> output;

    public Lexer(InputStream is) {
        this.is = new BufferedInputStream(is);
        this.output = new LinkedBlockingQueue<>(1000);
    }

    private enum State {
        INIT,
        ;
    }

    private State state;

    @SneakyThrows(IOException.class)
    @Override
    public void run() {
        state = State.INIT;
        try {
            run0();
        } catch (InterruptedException e) {
            // ignore
        }
    }

    private void run0() throws IOException, InterruptedException {
        int ch;
        while (true) {
            ch = getChar();
            switch (ch) {
            case '#':
                if (peekChar() == '#') {
                    getChar();
                    emit(OpToken.DOUBLE_HASH);
                } else {
                    emit(OpToken.HASH);
                }
                break;
            case '<':
                if (peekChar() == '<') {
                    getChar();
                    emit(OpToken.LEFT_SHIFT);
                } else if (peekChar() == '=') {
                    getChar();
                    emit(OpToken.LESS_OR_EQUAL);
                } else {
                    emit(OpToken.LEFT_ANGLE);
                }
                break;
            case '>':
                if (peekChar() == '>') {
                    getChar();
                    emit(OpToken.RIGHT_SHIFT);
                } else if (peekChar() == '=') {
                    getChar();
                    emit(OpToken.GREATER_OR_EQUAL);
                } else {
                    emit(OpToken.RIGHT_ANGLE);
                }
                break;
            case '(':
                emit(OpToken.LEFT_PAREN);
                break;
            case ')':
                emit(OpToken.RIGHT_PAREN);
                break;
            case '[':
                emit(OpToken.LEFT_SQUARE_BRACKET);
                break;
            case ']':
                emit(OpToken.RIGHT_SQUARE_BRACKET);
                break;
            case '{':
                emit(OpToken.LEFT_BRACE);
                break;
            case '}':
                emit(OpToken.RIGHT_BRACE);
                break;
            case ';':
                emit(OpToken.SEMICOLON);
                break;
            case '.':
                emit(OpToken.DOT);
                break;
            case ',':
                emit(OpToken.COMMA);
                break;
            case '+':
                if (peekChar() == '+') {
                    getChar();
                    emit(OpToken.DOUBLE_PLUS);
                } else {
                    emit(OpToken.PLUS);
                }
                break;
            case '-':
                if (peekChar() == '-') {
                    getChar();
                    emit(OpToken.DOUBLE_MINUS);
                } else if (peekChar() == '>') {
                    getChar();
                    emit(OpToken.ARROW);
                } else {
                    emit(OpToken.MINUS);
                }
                break;
            case '*':
                emit(OpToken.STAR);
                break;
            case '/':
                if (peekChar() == '/') {
                    getChar();
                    StringBuilder sb = new StringBuilder();
                    sb.append("//");
                    while (true) {
                        int c = getChar();
                        if (c != -1) {
                            sb.append((char) (c & 0xff));
                        }
                        if (c == -1 || c == '\n') {
                            break;
                        }
                    }
                    emit(new CommentToken(sb.toString()));
                } else if (peekChar() == '*') {
                    getChar();
                    StringBuilder sb = new StringBuilder();
                    sb.append("/*");
                    while (true) {
                        int c = getChar();
                        if (c == -1) {
                            throw new EOFException();
                        }
                        sb.append((char) (c & 0xff));
                        if (c == '*' && peekChar() == '/') {
                            getChar();
                            sb.append('/');
                            break;
                        }
                    }
                    emit(new CommentToken(sb.toString()));
                } else {
                    emit(OpToken.DIVIDE);
                }
                break;
            case '%':
                emit(OpToken.REMAINDER);
                break;
            case '=':
                if (peekChar() == '=') {
                    getChar();
                    emit(OpToken.EQUAL);
                } else {
                    emit(OpToken.ASSIGNMENT);
                }
                break;
            case -1:
                emit(OpToken.EOF);
                break;
            default:
                throw new RuntimeException();
            }
        }
    }

    private void emit(Token token) throws InterruptedException {
        output.put(token);
    }

    private int lookAhead;
    private boolean hasLookAhead;
    private boolean eof;

    private int getChar() throws IOException {
        if (eof) {
            return -1;
        }
        if (hasLookAhead) {
            hasLookAhead = false;
            return lookAhead;
        }
        int c = is.read();
        if (c == -1) {
            eof = true;
        }
        return c;
    }

    private int peekChar() throws IOException {
        if (eof) {
            return -1;
        }
        if (hasLookAhead) {
            return lookAhead;
        }
        int c = is.read();
        if (c == -1) {
            eof = true;
        }
        lookAhead = c;
        hasLookAhead = true;
        return c;
    }
}
