package com.rizvi.mit.du.service;

import com.rizvi.mit.du.console.ConsoleIO;
import com.rizvi.mit.du.board.Board;
import com.rizvi.mit.du.board.BoardStates;
import com.rizvi.mit.du.player.Player;

public class GameLogic {
	private Player firstPlayer;
	private Player secondPlayer;
	private Board board;
	private ConsoleIO userInterface;

	public GameLogic(ConsoleIO userInterface) {
		this.userInterface = userInterface;
		board = new Board(this.userInterface);
		setPlayers();
	}

	public void play() {
		int move;
		Player currentPlayer = secondPlayer;
		do {
			currentPlayer = changeTurn(currentPlayer);
			board.printBoard();
			move = getMove(currentPlayer);
			board.setBoardCell(move, currentPlayer.getSelectedSymbol());
		} while (!hasWon(currentPlayer, move) && !hasDrawn());
		
		board.printBoard();
		if (currentPlayer.isWinner()) {
			userInterface.showMessage(currentPlayer.getName() + " is the winner!!");
		} else {
			userInterface.showMessage("This game ended up in a draw!!");
		}

	}

	private int getMove(Player currentPlayer) {
		int move;
		move = currentPlayer.move(userInterface);
		while (board.isOccupiedAt(move)) {
			userInterface.showErrorMessage("Please enter a valid move!!");
			move = currentPlayer.move(userInterface);
		}
		return move;
	}

	private Player changeTurn(Player currentPlayer) {
		if (currentPlayer.equals(firstPlayer)) {
			currentPlayer = secondPlayer;
		} else {
			currentPlayer = firstPlayer;
		}
		return currentPlayer;
	}

	private boolean hasDrawn() {
		// TODO Auto-generated method stub
		return board.isFull();
	}

	private boolean hasWon(Player currentPlayer, int lastCell) {
		// TODO Auto-generated method stub
		String[] rows = board.getRow(lastCell);
		String[] cols = board.getColumn(lastCell);
		String[][] diagonals = board.getDiagonal();
		if (haveSameElements(rows) || haveSameElements(cols) || haveSameElements(diagonals[0])
				|| haveSameElements(diagonals[1])) {
			currentPlayer.setWinner(true);
			return true;
		}
		return false;

	}

	private boolean haveSameElements(String[] items) {
		// TODO Auto-generated method stub
		//System.out.println(Arrays.toString(items));
		for (int index = 0; index < items.length-1; index++) {
			String item = items[index];
			String nextItem = items[index + 1];
			if (item.contentEquals(BoardStates.BOARD_STATE_EMPTY)||!item.contentEquals(nextItem)) {
				return false;
			}
		}
		return true;
	}

	private void setPlayers() {
		userInterface.showMessage("Enter the first player's name: ");
		String firstPlayerName = userInterface.takePlayerInputString();
		firstPlayer = new Player(firstPlayerName,BoardStates.BOARD_STATE_X);
		userInterface.showMessage("Enter the second player's name: ");
		String secondPlayerName = userInterface.takePlayerInputString();
		secondPlayer = new Player(secondPlayerName,BoardStates.BOARD_STATE_O);
	}
}
