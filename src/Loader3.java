import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Loader3 {
    public static void main(String[] args) {
        Logger logger = logFile();
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO, "Введите адрес каталога");
        String direction = scanner.nextLine();
        logger.log(Level.INFO, "direction = " + direction);
        logger.log(Level.INFO, "Введите адрес, куда нужно скопировать");
        String receiver = scanner.nextLine();
        logger.log(Level.INFO, "receiver = " + receiver);
        try {
            copyDirectory(new File(direction), new File(receiver + direction.substring(direction.lastIndexOf("\\"))));
            logger.log(Level.INFO, "Копирование...");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка");
        }
    }
    public static Logger logFile() {
        Logger logger = Logger.getLogger(Loader3.class.getName());

        FileHandler handler;
        try {
            handler = new FileHandler("log\\log.txt");
            logger.addHandler(handler);
            return logger;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    private static void copyDirectory(File direction, File receiver) throws IOException {
        if (direction.isDirectory()) {
            if (!receiver.exists())
                receiver.mkdir();
            String[] files = direction.list();
            if (files == null)
                return;
            for (String file : files) {
                File dirFile = new File(direction, file);
                File recFile = new File(receiver, file);
                copyDirectory(dirFile, recFile);
            }
        } else {
            try {
                FileInputStream in = new FileInputStream(direction);
                FileOutputStream out = new FileOutputStream(receiver);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0)
                    out.write(buffer, 0, length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
