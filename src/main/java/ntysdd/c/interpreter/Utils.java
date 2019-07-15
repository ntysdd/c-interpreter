package ntysdd.c.interpreter;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {
    public boolean isAsciiAlphabet(char c) {
        return ('a' <= c && c <= 'z') ||
            ('A' <= c && c <= 'Z');
    }

    public boolean isAsciiNumber(char c) {
        return '0' <= c && c <= '9';
    }
}
