package blatt3.aufgabe;

public final class Cell {
	final int column;
	final int row;

	public Cell(int column, int row) {
		this.column = 'a' + column;
		this.row = row + 1;
	}

	@Override
	public String toString() {
		return "" + (char) column + row;
	}
}