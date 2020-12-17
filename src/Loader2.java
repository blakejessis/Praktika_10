import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Loader2 {
    public static void main(String[] args) {
        Logger logger = logFile();
        Scanner scanner = new Scanner(System.in);
        long size = 0;
        logger.log(Level.INFO, "Введите путь до папки");

        String path = scanner.nextLine();
        logger.log(Level.INFO, path);

        try {
            File file = new File(path);
            if (file.exists()) {
                size = folderSize(file);

                String answer;
                if (size < 1024) {
                    answer = String.valueOf(size) + " Байт";
                } else if (size < 1024*1024) {
                    answer = String.valueOf(size/1024) + " Кб";
                } else if (size < 1024*1024*1024) {
                    answer = String.valueOf(size/(1024*1024)) + " Мб";
                } else if (size < (long)1024*1024*1024*1024) {
                    answer = String.valueOf(size/(1024*1024*1024)) + " Гб";
                } else {
                    answer = String.valueOf(size/((long)1024*1024*1024*1024)) + " Тб";
                }

                logger.log(Level.INFO, "Размер папки " + path + " " + answer);
            } else
                logger.log(Level.WARNING, "Каталога с таким адресом не существует");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Logger logFile() {
        Logger logger = Logger.getLogger(Loader2.class.getName());
        FileHandler handler;

        try {
            handler = new FileHandler("log\\log.txt");
            logger.addHandler(handler);
            return logger;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static long folderSize(File directory) {
        long length = 0;
        for (File file : directory.listFiles()) {
            if (file.isFile())
                length += file.length();
            else if (file.isDirectory())
                length += folderSize(file);
        }
        return length;
    }

}
