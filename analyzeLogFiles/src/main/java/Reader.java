import java.io.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.Phaser;

public class Reader extends Thread {
    private String username;
    private String dateFrom;
    private String dateTo;
    private String message;
    private String nameGrouping;
    private String dateGrouping;
    private String path;
    private Phaser phaser;
    private Map<String, Integer> list;

    public Reader(String username, String dateFrom,
                  String dateTo, String message,
                  String nameGrouping, String dateGrouping,
                  String path, Map<String, Integer> list,
                  Phaser phaser) {
        this.username = username;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.message = message;
        this.nameGrouping = nameGrouping;
        this.dateGrouping = dateGrouping;
        this.path = path;
        this.list = list;
        this.phaser = phaser;
    }

    @Override
    public void run() {
        readFile();
        phaser.arrive();
    }

    public synchronized void readFile() {
        try (FileInputStream file = new FileInputStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(file));) {
            String stringLineTemp;
            String[] subStr;
            while ((stringLineTemp = reader.readLine()) != null) {
                if(!stringLineTemp.trim().isEmpty()){
                subStr = parseString(stringLineTemp);
                if (!username.isEmpty() && username != null) {
                    if (!username.equals(subStr[1])) {
                        continue;
                    }
                }
                if ((!dateFrom.isEmpty() && dateFrom != null) && (!dateTo.isEmpty() && dateTo != null)) {
                    if (!checkDate(dateFrom, dateTo, subStr[0])) {
                        continue;
                    }
                }
                if (!message.isEmpty() && message != null) {
                    if (!subStr[2].contains(message)) {
                        continue;
                    }
                }
                if (nameGrouping != null && !nameGrouping.isEmpty() && dateGrouping == null || dateGrouping.isEmpty()) {
                    addToMap(subStr[1]);
                }
                if (dateGrouping != null && !dateGrouping.isEmpty() && nameGrouping == null || nameGrouping.isEmpty()) {
                    addToMap(groupingByDate(subStr[0], dateGrouping));
                } else if (dateGrouping != null && !dateGrouping.isEmpty() && nameGrouping != null && !nameGrouping.isEmpty()) {
                    String dateNameGroupng = new StringBuffer().append(groupingByDate(subStr[0], dateGrouping)).append(" | ")
                            .append(subStr[1]).toString();
                    addToMap(dateNameGroupng);
                }
            }}

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private synchronized boolean checkDate(String dateFrom, String dateTo, String logDate) {
        LocalDateTime dateTimeFrom = LocalDateTime.parse(dateFrom);
        LocalDateTime dateTimeTo = LocalDateTime.parse(dateTo);
        LocalDateTime dateTimeLog = LocalDateTime.parse(logDate);
        return (dateTimeLog.isAfter(dateTimeFrom) || dateTimeLog.isEqual(dateTimeFrom))
                && (dateTimeLog.isBefore(dateTimeTo) || dateTimeLog.isEqual(dateTimeTo));
    }

    private synchronized String[] parseString(String line) {
        String[] subStr;
        subStr = line.split(" ");
        return subStr;
    }

    private synchronized String groupingByDate(String date, String dateGrouping) {
        LocalDateTime localDateTime = LocalDateTime.parse(date);
        String returnedDate = null;
        if (dateGrouping.equals("day")) {
            returnedDate = localDateTime.toLocalDate().toString();
        } else if (dateGrouping.equals("month")) {
            StringBuffer stringBuffer = new StringBuffer().append(localDateTime.getYear()).append(" ").append(localDateTime.getMonth());
            returnedDate = stringBuffer.toString();
        } else if (dateGrouping.equals("year")) {
            returnedDate = Integer.toString(localDateTime.getYear());
        } else if (dateGrouping.equals("hour")) {
            returnedDate = Integer.toString(localDateTime.getHour());
        }else System.out.println("Your filter for date is incorrect");
        return returnedDate;
    }

    private synchronized void addToMap(String value) {
        if (list.get(value) == null) {
            list.put(value, 1);
        } else {
            list.replace(value,list.get(value), list.get(value)+1);
        }
    }
}
