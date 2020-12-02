import java.io.*;


public class Loader1 {


    public static void pr2() {
        try {
            PrintWriter writer = new PrintWriter("log/log.txt");

            writer.write("logiruem soobshenia v otdelniy fail");

            for (int i = 0; i <= 1000; i++) {
                writer.write(i + "\n");
            }
            writer.flush();
            writer.close();

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Андрей\\IdeaProjects\\Praktika_10\\log");
            if (!file.exists()) {
                if (file.mkdir()) {
                    System.out.println("Folder/Directory is created successfully");
                } else {
                    System.out.println("Directory/Folder creation failed!!!");
                }
            }
        pr2();
    }


}
