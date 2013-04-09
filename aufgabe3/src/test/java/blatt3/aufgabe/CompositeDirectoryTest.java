package blatt3.aufgabe;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class CompositeDirectoryTest {

	private CompositeDirectory rootStub;
	private CompositeDirectory compStub;
	private CompositeDirectory compDir;
	private CompositeDirectory rootDir;

	private Cell testCell;
	private Cell returnCell;

	private int testEntityTrue;
	private int testEntityFalse;

	@Before
	public void setUp() throws Exception {
		this.rootStub = mock(CompositeDirectory.class);
		this.compStub = mock(CompositeDirectory.class);
		this.rootDir = new CompositeDirectory(null);

		this.testEntityTrue = 1;
		this.testEntityFalse = 2;
		this.testCell = new Cell(2, 7);

		when(this.rootStub.lookup(1)).thenReturn(this.testCell);
		when(this.compStub.lookup(1)).thenReturn(this.testCell);
	}

	@Test
	public void lookupMiddleTrue() {
		this.compDir = new CompositeDirectory(this.rootStub);

		this.returnCell = this.compDir.lookup(this.testEntityTrue);

		assertEquals(this.testCell, returnCell);
	}

	@Test
	public void lookupMiddleFalse() {
		this.compDir = new CompositeDirectory(this.rootStub);

		this.returnCell = compDir.lookup(this.testEntityFalse);

		assertNull(returnCell);
	}

	@Test
	public void lookupRootTrue() {
		this.rootDir.addChild(testEntityTrue, this.compStub);

		this.returnCell = this.rootDir.lookup(testEntityTrue);

		assertEquals(this.testCell, returnCell);
	}

	@Test
	public void lookupRootFalse() {
		this.returnCell = this.rootDir.lookup(this.testEntityTrue);

		assertNull(returnCell);
	}

	@Test
	public void testAddChild() {
		this.compDir = new CompositeDirectory(this.rootDir);
		LeafDirectory leafDir = new LeafDirectory(this.testCell, this.compDir);

		leafDir.insert(this.testEntityTrue);

		this.returnCell = this.rootDir.lookup(this.testEntityTrue);

		assertEquals(this.testCell, returnCell);
	}

}
