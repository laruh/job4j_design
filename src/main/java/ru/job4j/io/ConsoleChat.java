package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String pathLog;
    private final String botAnswers;
    private boolean pause;

    public ConsoleChat(String pathLog, String botAnswers) {
        this.pathLog = pathLog;
        this.botAnswers = botAnswers;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    private void switchPause(String question) {
        switch (question) {
            case STOP -> setPause(true);
            case CONTINUE -> setPause(false);
        }
    }

    public void run() {
       Scanner scanner = new Scanner(System.in);
       System.out.println("To control the bot, use the commands:"
               + System.lineSeparator() + OUT
               + System.lineSeparator() + STOP
               + System.lineSeparator() + CONTINUE);
        System.out.println("Ask any question to the bot:");
       String question = scanner.nextLine();
       while (!OUT.equals(question)) {
           switchPause(question);
           if (!isPause()) {
               String botAnswer = getAnswer();
               System.out.println(botAnswer);
               saveLog(question);
               saveLog(botAnswer);
           } else {
               saveLog(question);
           }
           question = scanner.nextLine();
           if (OUT.equals(question)) {
               saveLog(question);
           }
       }
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(
                new FileReader(botAnswers, StandardCharsets.UTF_8))) {
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
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(pathLog, StandardCharsets.UTF_8, true))
        ) {
            pw.println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/fileLog.txt", "./data/botAnswers.txt");
        cc.run();
    }
}
