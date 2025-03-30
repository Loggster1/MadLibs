/**
 * Logan Wallarab
 * www.loggster.dev
 */
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class MadLibs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File folder = new File("MadLibsFiles");

        File[] MadLibFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        System.out.println("Let's play MadLibs");

        for (int i = 0; i < MadLibFiles.length; i++) {
            System.out.println((i + 1) + ". " + MadLibFiles[i].getName());
        }

        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice >= 1 && choice <= MadLibFiles.length) {
            String selectedMadLibFile = MadLibFiles[choice - 1].getAbsolutePath();
            String story = readMadLibStory(selectedMadLibFile);

            if (story != null) {
                System.out.println(story);

                String[] storySections = story.split("\\*\\*");
                if (storySections.length == 3) {
                    String title = storySections[0].trim();
                    String prompts = storySections[1].trim();
                    String storyText = storySections[2].trim();

                    String[] promptArray = prompts.split("\n");
                    ArrayList<String> userInput = new ArrayList<>();
                    LinkedList<String> userInputLinkedList = new LinkedList<>();

                    for (String prompt : promptArray) {
                        System.out.print("Enter a " + prompt.trim() + ": ");
                        String input = scanner.nextLine();
                        userInput.add(input);
                        userInputLinkedList.add(input);
                    }

                    for (String input : userInput) {
                        storyText = storyText.replaceFirst("\\[blank\\]", input);
                    }

                    System.out.println("Your New Story:");
                    System.out.println(title);
                    for (String prompt : promptArray) {
                        System.out.println(prompt.trim() + ": " + userInput.remove(0));
                    }

                    System.out.println(storyText);
                } else {
                    System.out.println("Error");
                }
            } 
        }
    }

    public static String readMadLibStory(String filePath) {
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            StringBuilder story = new StringBuilder();
            while (fileScanner.hasNextLine()) {
                story.append(fileScanner.nextLine()).append("\n");
            }
            return story.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
