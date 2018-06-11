import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader {
    public synchronized String fromConsole(String name) throws IOException, PathOfFileIsIncorrect {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path;
        System.out.println("Enter the path for " + name);
        path = reader.readLine();
        if (path.trim().isEmpty()) {
            throw new PathOfFileIsIncorrect("Path is empty");
        }
        return path;
    }
}
