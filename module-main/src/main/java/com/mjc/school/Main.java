package com.mjc.school;

import com.mjc.school.service.exceptions.ValidationException;
import com.mjc.school.utils.CommandExecutor;
import com.mjc.school.utils.Commands;

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
                executor.executeCommand(command, scanner);
                if (command.getCommandNumber() == 0) break;
            } catch (NumberFormatException | ValidationException e) {
                System.out.println("Command not found");
            }
        }
    }
}
