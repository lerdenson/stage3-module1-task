package com.mjc.school;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CommandExecutor executor = new CommandExecutor();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            executor.printMenu();
            String input = scanner.nextLine();
            try {
                Commands command = Commands.getCommand(Integer.parseInt(input));
                if (command == null) {
                    System.out.println("There is no such operation");
                } else {
                    executor.executeCommand(command, scanner);
                }
                if (command != null && command.getCommandNumber() == 0) {
                    System.exit(0);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please write only operation number");
            }
        }
    }
}
