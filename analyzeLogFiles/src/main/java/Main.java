import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Phaser;

public class Main {
    private static final String USERNAME_FILTER = "Please, enter username. If you don't want to filter by username just click Enter.";
    private static final String DATE_FROM_FILTER = "Please, enter date from in format 'yyyy-mm-ddThh:mm:ss'. If you don't want to filter by date just click Enter.";
    private static final String DATE_FROM_TO = "Please, enter date to in format 'yyyy-mm-ddThh:mm:ss'.";
    private static final String MESSAGE_FILTER = "Please, enter text of message. If you don't want to filter by text just click Enter.";
    private static final String USERNAME_GROUPING = "Do you want to group by username :  If you don't want to filter by username just click Enter or enter any symbol.";
    private static final String DATE_GROUPING = "Do you want to group by username : hour/day/month/year/ or if you don't want to filter by date just click Enter.";
    private static final String NO_ONE_ITEM = "At least one parameter should be specified.";
    private static final String INCORRECT_DATE = "Format of date is incorrect, all items were deleted, please, enter everything again";
    private String username = null;
    private String usernameGrouping = null;
    private String dateGrouping = null;
    private String dateFrom = null;
    private String dateTo = null;
    private String message = null;
    private Scanner scanner = new Scanner(System.in);
    private ConcurrentHashMap<String, Integer> list = new ConcurrentHashMap();

    public static void main(String[] args) {
        String path = ".//src//main//resources";
        File directory = new File(path);
        File[] listOfFiles = directory.listFiles();
        Phaser phaser = new Phaser(listOfFiles.length + 1);
        Main main = new Main();
        main.enterFilterItems();
        main.enterGrouping();
        main.startThreads(listOfFiles, phaser);
        phaser.arriveAndAwaitAdvance();
        main.writeResultToFile();
    }

    private void startThreads(File[] listOfFiles, Phaser phaser) {
        for (int i = 0; i < listOfFiles.length; i++) {
            String onePath = listOfFiles[i].toString();
            Reader reader = new Reader(username, dateFrom,
                    dateTo, message, usernameGrouping, dateGrouping, onePath, list, phaser);
            reader.start();
        }
    }

    private void enterFilterItems() {
        while (true) {
            System.out.println(Main.USERNAME_FILTER);
            username = scanner.nextLine().trim();
            System.out.println(Main.DATE_FROM_FILTER);
            dateFrom = scanner.nextLine().trim();
            if (!dateFrom.isEmpty()) {
                System.out.println(Main.DATE_FROM_TO);
                dateTo = scanner.nextLine().trim();
                try {
                    LocalDateTime.parse(dateFrom);
                    LocalDateTime.parse(dateTo);
                } catch (DateTimeException e) {
                    System.out.println(INCORRECT_DATE);
                }
            }
            System.out.println(Main.MESSAGE_FILTER);
            message = scanner.nextLine().trim();
            if (!username.isEmpty() || !dateFrom.isEmpty() || !message.isEmpty()) {
                break;
            } else {
                System.out.println(NO_ONE_ITEM);
            }
        }
    }

    private void enterGrouping() {
        while (true) {
            System.out.println(Main.USERNAME_GROUPING);
            usernameGrouping = scanner.nextLine().trim();
            System.out.println(Main.DATE_GROUPING);
            dateGrouping = scanner.nextLine().trim();
            if (usernameGrouping.isEmpty() && dateGrouping.isEmpty()) {
                System.out.println(NO_ONE_ITEM);
            } else {
                break;
            }
        }
    }

    private void writeResultToFile() {
        Writer outputStream = null;
        try {
            File filePath = new File(".//src//main//result");
            if (!filePath.exists()) {
                filePath.mkdir();
            }
            File file = new File(filePath + String.format("//result%s.txt", LocalDate.now()));
            file.createNewFile();
            outputStream = new BufferedWriter(new FileWriter(file));
            if (!list.isEmpty()) {
                for (Map.Entry<String, Integer> entry : list.entrySet()) {
                    outputStream.write("| " + entry.getKey() + "  -  " + entry.getValue() + " |");
                    outputStream.write(System.lineSeparator());
                }
            } else outputStream.write("There are not coincidences!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                outputStream.close();
            } catch (Exception exp) {
                System.out.println(exp.getMessage());
            }
        }
    }
}
