import java.util.Scanner;

public class ConsoleInputOutput {

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

	public void showMesssage(String message) {
		System.out.println(message);
	}

	public void showMesssage(String message, boolean newLine) {
		if (newLine) {
			showMesssage(message);
		} else {
			System.out.print(message);
		}
	}

	public void showErrorMesssage(String message) {
		System.err.println(message);
	}
}
