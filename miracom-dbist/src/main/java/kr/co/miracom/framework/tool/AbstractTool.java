package kr.co.miracom.framework.tool;

import java.util.Scanner;

public abstract class AbstractTool {
    private static final Scanner scanner = new Scanner(System.in);

    protected static String scan(String message) {
        System.out.print(message + ": ");
        String value = scanner.next().trim();
        return "exit".equalsIgnoreCase(value) ? null : value;
    }
}
