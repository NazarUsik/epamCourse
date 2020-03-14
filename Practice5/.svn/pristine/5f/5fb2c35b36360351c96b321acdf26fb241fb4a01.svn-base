package ua.nure.usik.practice5;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class InputConsole extends InputStream {
    private static final ByteArrayInputStream bis = new ByteArrayInputStream("\n".getBytes());

    @Override
    public int read(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bis.read();
    }
}
