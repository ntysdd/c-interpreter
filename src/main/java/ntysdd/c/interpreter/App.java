package ntysdd.c.interpreter;

import java.io.FileInputStream;
import java.io.IOException;

import lombok.Cleanup;

public final class App {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        @Cleanup
        FileInputStream fis = new FileInputStream(fileName);
    }
}
