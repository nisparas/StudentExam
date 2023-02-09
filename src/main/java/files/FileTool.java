package files;

import java.io.File;
import java.io.IOException;

public class FileTool {

    public static File createFileIfNotExist(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Cannot create file" +e);
                return null;
            }
        }
        return file;
    }
}