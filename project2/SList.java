package cu.cs.cpsc2150.projectec;

public interface SList {
/*!
 **	modeled_by: (left: string of Object, right: string of Object)
 **	initial_value: (<>, <>)
!*/
	
	void clear ();
	/*!
	 **	requires: true
	 **	ensures: self = (<>, <>)
	!*/
	
	void addRight(Object x);
	/*!
	 **	requires: true
	 **	preserves: x, self.left
	 **	ensures: self.right = <x> * #self.right
	!*/
	
	Object removeRight();
	/*!
	 **	requires: |self.right| > 0
	 **	preserves: self.left
	 **	ensures: #self.right = <removeRight()> * self.right 
	!*/
	
	Object getElementAt(int pos);
	/*!
	 **	requires: 0 <= pos <= |self.right|
	 **	preserves: pos, self
	 **	ensures:
	 **		there exists l, r: string of Object
	 **		s.t.
	 **		|l| = pos and
	 ** 	self.right = l * getElementAt(pos) * r
	!*/
	
	void advance();
	/*!
	 **	requires: |self.right| > 0
	 **	ensures:
	 **		left = #left * <removeRight()> and
	 **		#self.right = <removeRight()> * self.right
	!*/
	
	void moveToStart();
	/*!
	 **	requires: true
	 **	ensures:
	 **		self.right = #self.left * #self.right and
	 **		self.left = <>
	!*/
	
	void moveToFinish();
	/*!
	 **	requires: true
	 **	ensures:
	 **		self.left = #self.left * #self.right and
	 **		self.right = <>
	!*/
	
	int getLeftLength();
	/*!
	 ** requires: true
	 ** preserves: self
	 ** ensures: getLeftLength() = |self.left|
	!*/
	
	int getRightLength();
	/*!
	 ** requires: true
	 ** preserves: self
	 ** ensures: getRightLength() = |self.right|
	!*/
	
	String abstractToString();
}
