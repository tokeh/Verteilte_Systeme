package blatt3.aufgabe;

public class GlobalSystem {
	private final Cell[][] board = new Cell[8][8];

	public GlobalSystem() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				board[x][y] = new Cell(x, y);
			}
		}

		CompositeDirectory root = new CompositeDirectory(null);

		int entity = 1;
		for (int x1 = 0; x1 < 2; x1++) {
			for (int y1 = 0; y1 < 2; y1++) {
				CompositeDirectory dir1 = new CompositeDirectory(root);
				for (int x2 = 0; x2 < 2; x2++) {
					for (int y2 = 0; y2 < 2; y2++) {
						CompositeDirectory dir2 = new CompositeDirectory(dir1);
						for (int x3 = 0; x3 < 2; x3++) {
							for (int y3 = 0; y3 < 2; y3++) {
								LeafDirectory leaf = new LeafDirectory(board[4
										* x1 + 2 * x2 + x3][4 * y1 + 2 * y2
										+ y3], dir2);
								leaf.insert(entity);
								if (entity > 1)
									System.out.println("Entity " + (entity - 1)
											+ " is in cell "
											+ leaf.lookup(entity - 1));
								entity++;
							}
						}
					}
				}
			}
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new GlobalSystem();
	}
}