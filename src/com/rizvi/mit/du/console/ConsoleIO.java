package com.rizvi.mit.du.console;

import com.rizvi.mit.du.exception.GameExceptions;

import java.util.Scanner;

public class ConsoleIO {
GameExceptions exceptions = new GameExceptions();
	public String takePlayerInputString() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		return input;
	}

	public int takePlayerInputInt() {
		Scanner scanner = new Scanner(System.in);
		int input = 0;
		String inputString = scanner.next();
		while(!exceptions.validateInput(inputString)) {
			System.out.println("Invalid Input. Please give input among 1 to 9");
			inputString = scanner.next();
			exceptions.validateInput(inputString);
		}
		return Integer.parseInt(inputString);
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
