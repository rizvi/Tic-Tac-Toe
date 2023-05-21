package com.rizvi.mit.du.board;

import com.rizvi.mit.du.console.ConsoleIO;

import java.util.Arrays;
import java.util.Collections;

public class Board {
	private static final int NUMBER_OF_ROWS = 3;
	private static final int NUMBER_OF_COLUMNS = 3;
	private static final String BOARD_HORIZONTAL_BORDER = "_";
	private static final String BOARD_VERTICAL_BORDER = "|";
	private static final int BOARD_PADDING_LENGTH = 1;
	private String[][] boardContents;
	private ConsoleIO userInterface;

	public Board(ConsoleIO userInterface) {
		initializeBoard();
		this.userInterface = userInterface;
	}

	private void initializeBoard() {
		// TODO Auto-generated method stub
		boardContents = new String[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
		for (int rowIndex = 0; rowIndex < NUMBER_OF_ROWS; rowIndex++) {
			for (int columnIndex = 0; columnIndex < NUMBER_OF_COLUMNS; columnIndex++) {
				boardContents[rowIndex][columnIndex] = BoardStates.BOARD_STATE_EMPTY;
			}
		}
	}

	public void printBoard() {
		for (int rowIndex = 0; rowIndex < boardContents.length; rowIndex++) {
			String[] row = boardContents[rowIndex];
			drawRowWithSeperator(row, " ");
			userInterface.showMessage("");
			if (rowIndex == boardContents.length - 1) {
				drawRowWithSeperator(" ", row);
			} else {
				drawRowWithSeperator(BOARD_HORIZONTAL_BORDER, row);
			}
			userInterface.showMessage("");
		}
	}

	private void drawRowWithSeperator(String[] row, String padWith) {
		for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {
			String seperator = columnIndex == (row.length - 1) ? "" : BOARD_VERTICAL_BORDER;
			String cell = row[columnIndex];
			draw(cell, padWith, seperator);

		}
	}

	private void drawRowWithSeperator(String drawFixedContent, String[] row) {
		String[] newFixedContentRow = new String[row.length];
		Arrays.fill(newFixedContentRow, drawFixedContent);
		drawRowWithSeperator(newFixedContentRow, drawFixedContent);

	}

	private void draw(String content, String padWith, String seperator) {
		content = padAround(content, BOARD_PADDING_LENGTH, " ");
		userInterface.showMessage(content, false);
		userInterface.showMessage(seperator, false);
		// userInterface.showMesssage(cell, false);
		// userInterface.showMesssage(BOARD_VERTICAL_BORDER, false);
	}

	private String padAround(String cell, int length, String padWith) {
		// Using Java 11
//		cell = padWith.repeat(length) + cell + padWith.repeat(length);
		// Using Java 8
//		cell = padWith.repeat(length) + cell + padWith.repeat(length);
		cell = String.join("", Collections.nCopies(length, padWith)) + cell + String.join("", Collections.nCopies(length, padWith));
		return cell;
	}

	public void setBoardCell(int abstractIndex, String value) {
		int[] realIndex = convertAbstractToRealIndex(abstractIndex);
		int rowIndex = realIndex[0];
		int columnIndex = realIndex[1];
		boardContents[rowIndex][columnIndex] = value;
	}

	public boolean isOccupiedAt(int abstractIndex) {
		int[] realIndex = convertAbstractToRealIndex(abstractIndex);
		int rowIndex = realIndex[0];
		int columnIndex = realIndex[1];
		return isOccupiedAt(rowIndex, columnIndex);
	}

	private boolean isOccupiedAt(int rowIndex, int columnIndex) {
		if (!isOutOfBound(rowIndex, columnIndex)) {
			String elementAtIndex = boardContents[rowIndex][columnIndex];
			// System.err.println(!elementAtIndex.contentEquals(com.rizvi.mit.du.board.BoardStates.BOARD_STATE_EMPTY));
			return !elementAtIndex.contentEquals(BoardStates.BOARD_STATE_EMPTY);
		}
		return true;
	}

	public boolean isFull() {
		for (int rowIndex = 0; rowIndex < NUMBER_OF_ROWS; rowIndex++) {
			for (int columnIndex = 0; columnIndex < NUMBER_OF_COLUMNS; columnIndex++) {
				if (!isOccupiedAt(rowIndex, columnIndex)) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isOutOfBound(int rowIndex, int columnIndex) {
		return rowIndex >= NUMBER_OF_ROWS || columnIndex >= NUMBER_OF_COLUMNS || rowIndex < 0 || columnIndex < 0;
	}

	private int[] convertAbstractToRealIndex(int abstractIndex) {
		int rowIndex = (abstractIndex - 1) / NUMBER_OF_COLUMNS;
		int columnIndex = abstractIndex - (1 + rowIndex * NUMBER_OF_COLUMNS);
		return new int[] { rowIndex, columnIndex };
	}

	public String[] getRow(int abstractIndex) {
		int[] realIndex = convertAbstractToRealIndex(abstractIndex);
		int rowNum = realIndex[0];
		return boardContents[rowNum];
	}

	public String[] getColumn(int abstractIndex) {
		int[] realIndex = convertAbstractToRealIndex(abstractIndex);
		int columnNum = realIndex[1];
		String[] columnContents = new String[NUMBER_OF_ROWS];
		int index = 0;
		for (String[] row : boardContents) {
			columnContents[index] = row[columnNum];
			index++;
		}
		return columnContents;
	}

	public String[][] getDiagonal() {
		String[][] diagonalContents = new String[2][Math.min(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS)];
		int primaryDiagonalIndex = 0, secondaryDiagonalIndex = 0;
		for (int rowIndex = 0; rowIndex < NUMBER_OF_ROWS; rowIndex++) {
			
			for (int columnIndex = 0; columnIndex < NUMBER_OF_COLUMNS; columnIndex++) {
				if (rowIndex == columnIndex) {
					diagonalContents[0][primaryDiagonalIndex] = boardContents[rowIndex][columnIndex];
					primaryDiagonalIndex++;

				} if (rowIndex + 1 == (NUMBER_OF_COLUMNS - columnIndex)) {
					diagonalContents[1][secondaryDiagonalIndex] = boardContents[rowIndex][columnIndex];
					secondaryDiagonalIndex++;
				}
			}
		}
		return diagonalContents;
	}
}
