import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class Loader2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long size = 0;
        System.out.println("Введите путь до папки");

        String path = scanner.nextLine();

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

                System.out.println("Размер папки с адресом " + path + " составляет " + answer);
            } else {
                System.out.println("Такого каталога нет");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static long folderSize(File directory) {
        long length = 0;
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isFile())
                length += file.length();
            else if (file.isDirectory())
                length += folderSize(file);
        }
        return length;
    }

}