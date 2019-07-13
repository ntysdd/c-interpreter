package ntysdd.c.interpreter;

import java.util.Locale;

public enum KeywordEnum {
    AUTO,
    BREAK,
    CASE,
    CHAR,
    CONST,
    CONTINUE,
    DEFAULT,
    DO,
    DOUBLE,
    ELSE,
    ENUM,
    EXTERN,
    FLOAT,
    FOR,
    GOTO,
    IF,
    INT,
    LONG,
    REGISTER,
    RETURN,
    SHORT,
    SIGNED,
    SIZEOF,
    STATIC,
    STRUCT,
    SWITCH,
    TYPEDEF,
    UNION,
    UNSIGNED,
    VOID,
    VOLATILE,
    WHILE,
    _BOOL,
    _COMPLEX,
    _IMAGINARY,
    INLINE,
    RESTRICT,
    _ALIGNAS,
    _ALIGNOF,
    _ATOMIC,
    _GENERIC,
    _NORETURN,
    _STATIC_ASSERT,
    _THREAD_LOCAL,

    ;

    @Override
    public String toString() {
        String text = name();
        if (text.startsWith("_")) {
            return text.substring(0, 2)
                + text.substring(2).toLowerCase(Locale.ENGLISH);
        } else {
            return text.toLowerCase(Locale.ENGLISH);
        }
    }
}
