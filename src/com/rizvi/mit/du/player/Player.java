package com.rizvi.mit.du.player;

import com.rizvi.mit.du.console.ConsoleIO;

public class Player {
	private String name;
	private boolean isFirstPlayer;
	private boolean isWinner;
	private String selectedSymbol; 
	
	public Player(String name, String selectedSymbol) {
		super();
		this.name = name;
		this.selectedSymbol = selectedSymbol;
	}

	public String getName() {
		return name;
	}

	public boolean isFirstPlayer() {
		return isFirstPlayer;
	}

	public void setFirstPlayer(boolean isFirstPlayer) {
		this.isFirstPlayer = isFirstPlayer;
	}

	public boolean isWinner() {
		return isWinner;
	}

	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}

	public int move(ConsoleIO userInterface) {
		userInterface.showMessage("Please enter a move (enter cell numbers such as 1, 2, 3....[1 to 9]): ");
		int move = userInterface.takePlayerInputInt();
		return move;
	}

	public String getSelectedSymbol() {
		return selectedSymbol;
	}
}
