package ntysdd.c.interpreter;

import java.io.FileInputStream;
import java.io.IOException;

import lombok.Cleanup;

public final class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        String fileName = args[0];
        @Cleanup
        FileInputStream fis = new FileInputStream(fileName);

        Lexer lexer = new Lexer(fis);

        lexer.start();

        var output = lexer.getOutput();

        while (true) {
            var token = output.take();
            System.out.println(token);
            if (token == Token.EOF) {
                break;
            }
        }
    }
}
