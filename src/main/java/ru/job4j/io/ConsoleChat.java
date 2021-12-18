package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConsoleChat {
    private final String pathLog;
    private final String botAnswers;
    private final String out = "закончить";
    private final String stop = "стоп";
    private final String goOn = "продолжить";
    private final Charset charset = StandardCharsets.UTF_8;

    public ConsoleChat(String pathLog, String botAnswers) {
        this.pathLog = pathLog;
        this.botAnswers = botAnswers;
    }

    public void run() {
       Scanner scanner = new Scanner(System.in);
       System.out.println("Ask any question to the bot:");
       System.out.println("To control the bot, use the commands:"
               + System.lineSeparator() + out
               + System.lineSeparator() + stop
               + System.lineSeparator() + goOn);
       String question;
       boolean pause = false;
       do {
           question = scanner.nextLine();
           saveLog(question);
           if (!stop.equals(question) || goOn.equals(question)) {
               String botAnswer = getAnswer();
               System.out.println(botAnswer);
               saveLog(botAnswer);
           } else if (stop.equals(question)) {
               question = scanner.nextLine();
               saveLog(question);
           }
       } while (!out.equals(question));
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, charset))) {
            in.lines().forEach(rsl::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private String getAnswer() {
        Random random = new Random();
        List<String> rsl = readPhrases();
        return rsl.get(random.nextInt(rsl.size()));
    }

    private void saveLog(String string) {
        List<String> log = List.of(string.split(" "));
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(pathLog, charset, true))
        ) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/fileLog.txt", "./data/botAnswers.txt");
        cc.run();
    }
}
