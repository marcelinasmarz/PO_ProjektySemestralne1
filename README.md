# PO_ProjektySemestralne1
Odczyty plików z svg

z Files.readString

import java.nio.file.*;

public class SvgReader {
    public static void main(String[] args) throws Exception {
        Path path = Paths.get("path/to/file.svg");
        String content = Files.readString(path);
        System.out.println(content);
    }
}
z bufferedreader
import java.io.*;

public class SvgReader {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("path/to/file.svg"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



