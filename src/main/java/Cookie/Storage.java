package Cookie;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {

    public File fetchFile() {
        File file = new File("./data/cookie.txt");
        File directory = new File(file.getParent());

        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            if (!file.exists()) {
                file.createNewFile();
                return file;
            } else {
                loadFile(file);
                return file;
            }
        } catch (IOException | CookieException e) {
            System.out.println(e.getMessage());
        }
        return file;
    }

    public ArrayList<Task> loadFile(File file) throws FileNotFoundException, CookieException {
        Scanner fileScanner = new Scanner(file);
        ArrayList<Task> taskArrayList = new ArrayList<>();

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();;
            taskArrayList.add(parseFileContent(line));
        }

        return taskArrayList;
    }

    public Task parseFileContent(String string) throws CookieException {
        String[] parts = string.split(" \\| ");
        String command = parts[0];
        boolean isDone = parts[1].equals("1");
        String task = parts[2];
        String details = parts.length > 3 ? parts[3] : "";

        switch (command) {
            case "T":
                return new ToDo(isDone, task);
            case "D":
                return new Deadline(isDone, task, details);
            case "E":
                String[] eventDetails = details.split("-");
                return new Event(isDone, task, eventDetails[0], eventDetails[1]);
            default:
                throw new CookieException("File contains unknown command");
        }
    }

    public void saveFile(ArrayList<Task> taskArrayList) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./data/cookie.txt"))) {
            for (Task task : taskArrayList) {
                StringBuilder saveIntoFile = new StringBuilder();

                if (task instanceof ToDo) {
                    saveIntoFile.append("T | ");
                } else if (task instanceof Deadline) {
                    saveIntoFile.append("D | ");
                } else if (task instanceof Event) {
                    saveIntoFile.append("E | ");
                }

                saveIntoFile.append(task.getStatus().equals("X") ? "1 | " : "0 | ");
                saveIntoFile.append(task.getDescription());

                if (task instanceof Deadline) {
                    saveIntoFile.append(" | ").append(((Deadline) task).getBy());
                } else if (task instanceof Event) {
                    saveIntoFile.append(" | ").append(((Event) task).getFrom())
                            .append("-").append(((Event) task).getTo());
                }

                writer.write(saveIntoFile.toString());
                writer.write(System.lineSeparator());
            }
        }
    }


}
