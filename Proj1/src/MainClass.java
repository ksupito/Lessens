import java.io.IOException;

public class MainClass {
    public static void main(String[] args) {
        File file = new File();
        try {
            file.readFile(".//src//files//doc1.txt");
        } catch (IOException e) {
        }
        file.checkString();
        System.out.println(file.countValue());
    }
}
