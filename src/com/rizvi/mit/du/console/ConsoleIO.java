package com.rizvi.mit.du.console;

import java.util.Scanner;

public class ConsoleIO {

	public String takePlayerInputString() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		return input;
	}

	public int takePlayerInputInt() {
		Scanner scanner = new Scanner(System.in);
		int input = scanner.nextInt();
		return input;
	}

	public void showMessage(String message) {
		System.out.println(message);
	}

	public void showMessage(String message, boolean newLine) {
		if (newLine) {
			showMessage(message);
		} else {
			System.out.print(message);
		}
	}

	public void showErrorMessage(String message) {
		System.err.println(message);
	}
}
