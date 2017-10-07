package cu.cs.cpsc2150.projectec;

import java.util.*;

public class SListImpl1 implements SList {

	 private List<Object> myLeft, myRight;
	 
	 public SListImpl1() {
		myLeft = new ArrayList<>();
		myRight = new ArrayList<>();
	 }
		 
	public void initialize(Object left, Object right) {
		myLeft.add(left);
		myRight.add(right);
	}
	
	@Override
	public void clear() {
		myLeft.clear();
		myRight.clear();
	}

	@Override
	public void addRight(Object x) {
		myRight.add(0, x);
	}

	@Override
	public Object removeRight() {
		if (myRight.isEmpty()) return null;
		else {
			Object toReturn = myRight.get(0);
			myRight.remove(0);
			return toReturn;
		}
	}

	@Override
	public Object getElementAt(int pos) {
		return myRight.get(pos);
	}

	@Override
	public void advance() {
		Object toMove = myRight.get(0);
		myRight.remove(0);
		myLeft.add(toMove);
	}

	@Override
	public void moveToStart() {
		int pos = 0;
		
		ListIterator<Object> iter = myLeft.listIterator();
		while (iter.hasNext()) {
			myRight.add(pos, iter.next());
			pos++;
		}
		
		myLeft.clear();
	}

	@Override
	public void moveToFinish() {
		ListIterator<Object> iter = myRight.listIterator();
		while (iter.hasNext()) {
			myLeft.add(iter.next());
		}
		myRight.clear();
	}

	@Override
	public int getLeftLength() {
		return myLeft.size();
	}

	@Override
	public int getRightLength() {
		return myRight.size();
	}

	@Override
	public String abstractToString() {
		String str = " - (<";
		ListIterator<Object> leftIter = myLeft.listIterator();
		ListIterator<Object> rightIter = myRight.listIterator();
		
		if (!myLeft.isEmpty()) 
			str += leftIter.next().toString();
		while (leftIter.hasNext()) {
			str += ", ";
			str += leftIter.next().toString();
		}
		
		str += ">,<";
		
		if (!myRight.isEmpty()) 
			str += rightIter.next().toString();
		while (rightIter.hasNext()) {
			str += ", ";
			str += rightIter.next().toString();
		}
		
		str += ">)\n";
		
		return str;
	}

}
