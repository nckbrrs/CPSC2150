package cu.cs.cpsc2150.projectec;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestSListImpl1 {
	
	private SListImpl1 mySList;
	
	@Before
	public void setUp() throws Exception {
		/* instantiates new SList and initializes left and right lists */
		mySList = new SListImpl1();
		mySList.initialize(new String("list"), new String("initialized"));
	}
	
	@Test
	public void testAbstractToString() {
		/* test for correctness with initialized instantiation of SList with Strings */
		assertEquals(" - (<list>,<initialized>)\n", mySList.abstractToString());
		
		/* test for correctness with empty instantiation of SList */
		mySList = new SListImpl1();
		assertEquals(" - (<>,<>)\n", mySList.abstractToString());	
		
		/* test for correctness with initialized instantiation of SList with mixed types */
		mySList.initialize(new Integer(6), new String("nick"));
		assertEquals(" - (<6>,<nick>)\n", mySList.abstractToString());
	}
	
	@Test
	public void testAddRight() {
		/* test for correctness with three different parameter types (Integer, String, and Character) */
		mySList.addRight(new Integer(6));
		assertEquals(" - (<list>,<6, initialized>)\n", mySList.abstractToString());
		
		mySList.addRight(new String("nick"));
		assertEquals(" - (<list>,<nick, 6, initialized>)\n", mySList.abstractToString());
		
		mySList.addRight(new Character('x'));
		assertEquals(" - (<list>,<x, nick, 6, initialized>)\n", mySList.abstractToString());
	}
	
	@Test
	public void testClear() {
		/* test for correctness if left and right lists are both occupied */
		mySList.clear();
		assertEquals(" - (<>,<>)\n", mySList.abstractToString());
		
		/* test for correctness if right is occupied and left is empty */
		mySList.addRight(new String("right full, left empty"));
		mySList.clear();
		assertEquals(" - (<>,<>)\n", mySList.abstractToString());
		
		/* test for correctness if left is occupied and right is empty */
		mySList.addRight(new String("left full, right empty"));
		mySList.advance();
		mySList.clear();
		assertEquals(" - (<>,<>)\n", mySList.abstractToString());
		
		/* test for correctness if left and right lists are both empty */
		mySList = new SListImpl1();
		mySList.clear();
		assertEquals(" - (<>,<>)\n", mySList.abstractToString());
	}
	
	@Test
	public void testAdvance() {
		/* test for correctness if left list is empty and right is occupied */
		mySList.clear();
		mySList.addRight(new String("testing advance"));
		mySList.advance();
		assertEquals(" - (<testing advance>,<>)\n", mySList.abstractToString());
		
		/* test for correctness if left and right lists are both occupied */
		mySList.addRight(new String("again"));
		mySList.advance();
		assertEquals(" - (<testing advance, again>,<>)\n", mySList.abstractToString());
		
		/* test for correctness if right list has more than one element */
		mySList.addRight(new String("right2"));
		mySList.addRight(new String("right1"));
		mySList.advance();
		assertEquals(" - (<testing advance, again, right1>,<right2>)\n", mySList.abstractToString());
	}

	@Test
	public void testRemoveRight() {
		/* test for correctness if right list has more than one element */
		mySList.addRight(new String("testing removeRight()"));
		assertEquals("testing removeRight()", mySList.removeRight());
		assertEquals(" - (<list>,<initialized>)\n", mySList.abstractToString());
		
		/* test for correctness if right list has one element */
		assertEquals("initialized", mySList.removeRight());
		assertEquals(" - (<list>,<>)\n",  mySList.abstractToString());
		
		/* test for correctness if right list is empty */
		/* this should never happen, but ideally the method should work anyway */
		assertNull(mySList.removeRight());
		assertEquals(" - (<list>,<>)\n", mySList.abstractToString());	
	}
	
	@Test
	public void testMoveToStart() {
		/* test for correctness if left list is empty and right list has multiple arguments */
		mySList.clear();
		mySList.addRight(new Integer(4));
		mySList.addRight(new Integer(3));
		mySList.addRight(new Integer(2));
		mySList.addRight(new Integer(1));
		mySList.moveToStart();
		assertEquals(" - (<>,<1, 2, 3, 4>)\n", mySList.abstractToString());
		
		/* test for correctness if left and right lists are both occupied */
		mySList.advance();
		mySList.advance();
		mySList.moveToStart();
		assertEquals(" - (<>,<1, 2, 3, 4>)\n", mySList.abstractToString());
		
		/* test for correctness if left list has multiple elements and right list is empty */
		mySList.advance();
		mySList.advance();
		mySList.moveToStart();
		assertEquals(" - (<>,<1, 2, 3, 4>)\n", mySList.abstractToString());
	}
	
	@Test
	public void testMoveToFinish() {
		/* test for correctness if left list is empty and right list has multiple arguments */
		mySList.clear();
		mySList.addRight(new Integer(4));
		mySList.addRight(new Integer(3));
		mySList.addRight(new Integer(2));
		mySList.addRight(new Integer(1));
		mySList.moveToFinish();
		assertEquals(" - (<1, 2, 3, 4>,<>)\n", mySList.abstractToString());
		
		/* test for correctness if left and right lists are both occupied */
		mySList.addRight(new Integer(6));
		mySList.addRight(new Integer(5));
		mySList.moveToFinish();
		assertEquals(" - (<1, 2, 3, 4, 5, 6>,<>)\n", mySList.abstractToString());
		
		/* test for correctness if left list has multiple elements and right list is empty */
		mySList.moveToFinish();
		assertEquals(" - (<1, 2, 3, 4, 5, 6>,<>)\n", mySList.abstractToString());
	}
	
	@Test
	public void testGetRightLength() {
		/* test for correctness when right list has more than one element */
		mySList.addRight(new String("testing getRightLength()"));
		assertEquals(2, mySList.getRightLength());
		
		/* test for correctness when right list has only one element */
		mySList.removeRight();
		assertEquals(1, mySList.getRightLength());
		
		/* test for correctness when right list is empty */
		mySList.removeRight();
		assertEquals(0, mySList.getRightLength());
	}
	
	@Test
	public void testGetLeftLength() {
		mySList.clear();
		mySList.addRight(new String("testing"));
		mySList.addRight(new String("getLeftLength()"));
		
		/* test for correctness when left list is empty */
		assertEquals(0, mySList.getLeftLength());
		
		/* test for correctness when left list has only one element */
		mySList.advance();
		assertEquals(1, mySList.getLeftLength());
		
		/* test for correctness when left list has more than one element */
		mySList.advance();
		assertEquals(2, mySList.getLeftLength());
	}
	
	@Test
	public void testGetElementAt() {
		mySList.clear();
		mySList.addRight(new Integer(3));
		mySList.addRight(new Integer(2));
		mySList.addRight(new Integer(1));
		
		/* test for correctness when getting first element in right list */
		assertEquals(1, mySList.getElementAt(0));
		
		/* test for correctness when getting an element in the middle of the right list */
		assertEquals(2, mySList.getElementAt(1));
		
		/* test for correctness when getting last element in right list */
		assertEquals(3, mySList.getElementAt(mySList.getRightLength()-1));
	}
}
