public class DoubleLinkedSeq extends DoubleNode implements Cloneable
{

		int manyNodes;
		DoubleNode head;
		DoubleNode tail;
		DoubleNode cursor;
		DoubleNode precursor;
   
    /**
	* Initialize an empty sequence.
	* @param - none
	* <dt><b>Postcondition:</b><dd>
	*   This sequence is empty.
   **/   
   public DoubleLinkedSeq( )
   {
      head = null;
      tail = head;
      cursor = null;
      precursor = null;
      manyNodes = 0;
   }
    
 
   /**
   * Add a new element to this sequence, after the current element. 
   * @param element</CODE>
   *   the new element that is being added
   * <dt><b>Postcondition:</b><dd>
   *   A new copy of the element has been added to this sequence. If there was
   *   a current element, then the new element is placed after the current
   *   element. If there was no current element, then the new element is placed
   *   at the end of the sequence. In all cases, the new element becomes the
   *   new current element of this sequence. 
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for a new node.
   **/
   public void addAfter(double element)
   {
      if(isCurrent() == true)
      {
    	  precursor = cursor;
    	  cursor.addNodeAfter(element);
    	  cursor = cursor.getLink();
    	  if (tail == precursor)
    	  {
    		  tail = cursor;
    	  }
    	  manyNodes++;
      }
      else
      {
    	  precursor = tail;
    	  tail = new DoubleNode(element, null);
    	  precursor.setLink(tail);
    	  manyNodes++;
      }
   }
   
   
   public void addLast(double element)
   {
	   DoubleNode add_Last;
	   for(add_Last = head; add_Last != null; add_Last = add_Last.getLink())
 	  {
 		  if (add_Last.getLink() == null)
 		  {
 			  tail = add_Last;
 			  add_Last = new DoubleNode(element, null);
 			  tail.setLink(add_Last);
 			  tail = add_Last;

 			  manyNodes++;
 			  break;
 		  }
 		  continue;
 	  }
   }


   /**
   * Add a new element to this sequence, before the current element. 
   * @param element</CODE>
   *   the new element that is being added
   * <dt><b>Postcondition:</b><dd>
   *   A new copy of the element has been added to this sequence. If there was
   *   a current element, then the new element is placed before the current
   *   element. If there was no current element, then the new element is placed
   *   at the start of the sequence. In all cases, the new element becomes the
   *   new current element of this sequence. 
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for a new node.
   **/
   public void addBefore(double element)
   {  
	      if(isCurrent() == true)
	      {
	    	  if(cursor == head)
	    	  {
	    		  head = new DoubleNode(element, head);
	    		  cursor = head;
	    		  manyNodes++;
	    	  }
	    	  if(cursor != head)
	    	  {
	    		  precursor.addNodeAfter(element);
	    		  cursor = precursor.getLink();
	    		  manyNodes++;
	    	  }
	      }
	      else
	      {
	    	  head = new DoubleNode(element, head);
	    	  manyNodes++;
	      }
   }
   
   /**
    * Adds an element to the front of the sequence as the head.
    * @param element value of the element
    */
   public void addFront(double element)
   {
	   if(tail == null)
	   {
		   head = new DoubleNode(element, head);
		   tail = head;
		   cursor = head;
	   }
	   else
	   {
		   head = new DoubleNode(element, head);
	   }
	   manyNodes++;
	   
   }
   
   
   /**
   * Place the contents of another sequence at the end of this sequence.
   * @param addend</CODE>
   *   a sequence whose contents will be placed at the end of this sequence
   * <dt><b>Precondition:</b><dd>
   *   The parameter, addend</CODE>, is not null. 
   * <dt><b>Postcondition:</b><dd>
   *   The elements from addend</CODE> have been placed at the end of 
   *   this sequence. The current element of this sequence remains where it 
   *   was, and the addend</CODE> is also unchanged.
   * @exception NullPointerException
   *   Indicates that addend</CODE> is null. 
   * @exception OutOfMemoryError
   *   Indicates insufficient memory to increase the size of this sequence.
   **/
   public void addAll(DoubleLinkedSeq addend)
   {
	   try
	   {
		   for(addend.start(); addend.isCurrent(); addend.advance())
		   {
			   this.addLast(addend.getCurrent());
		   }
			
	   }
	   catch(NullPointerException npe)
	   {
		   System.out.println("The sequence being added is null.");
	   }
	   if(size() >= Integer.MAX_VALUE)
	   {
		   throw new OutOfMemoryError();
	   }
   }   
   
   
   /**
   * Move forward, so that the current element is now the next element in
   * this sequence.
   * @param - none
   * <dt><b>Precondition:</b><dd>
   *   isCurrent()</CODE> returns true. 
   * <dt><b>Postcondition:</b><dd>
   *   If the current element was already the end element of this sequence 
   *   (with nothing after it), then there is no longer any current element. 
   *   Otherwise, the new element is the element immediately after the 
   *   original current element.
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   advance</CODE> may not be called.
   **/
   public void advance( )
   {
	   if(cursor.getLink() != null)
	   {
		   precursor = cursor;
		   cursor = cursor.getLink();
	   }
	   else
	   {
		   cursor = null;
	   }
   }
   
   
   /**
   * Generate a copy of this sequence.
   * @param - none
   * @return
   *   The return value is a copy of this sequence. Subsequent changes to the
   *   copy will not affect the original, nor vice versa. Note that the return
   *   value must be type cast to a DoubleLinkedSeq</CODE> before it can be used.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/ 
   public Object clone( )
   {  // Clone a DoubleLinkedSeq object.
      // Student will replace this return statement with their own code:
      return null;
   }
   

   /**
   * Create a new sequence that contains all the elements from one sequence
   * followed by another.
   * @param s1</CODE>
   *   the first of two sequences
   * @param s2</CODE>
   *   the second of two sequences
   * <dt><b>Precondition:</b><dd>
   *   Neither s1 nor s2 is null.
   * @return
   *   a new sequence that has the elements of s1</CODE> followed by the
   *   elements of s2</CODE> (with no current element)
   * @exception NullPointerException.
   *   Indicates that one of the arguments is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for the new sequence.
   **/   
   public static DoubleLinkedSeq catenation(DoubleLinkedSeq s1, DoubleLinkedSeq s2)
   {
      DoubleLinkedSeq s3 = new DoubleLinkedSeq();
      try
      {
    	  s3.addAll(s1);
    	  s3.addAll(s2);
    	  return s3;
      }
      catch(NullPointerException npe)
      {
    	  if(s1 == null && s2 == null)
    	  {
    		  System.out.println("Both sequences are null");
    		  return null;
    	  }
    	  if(s1 == null)
    	  {
    		  System.out.println("The fist sequence is null.");
    		  return s2;
    	  }
    	  if(s2 == null)
    	  {
    		  System.out.println("The second sequence is null.");
    		  return s1;
    	  }
      }
      if(s3.size() > Integer.MAX_VALUE){
    	  throw new OutOfMemoryError();
      }
      return s3;
   }


   /**
   * Accessor method to get the current element of this sequence. 
   * @param - none
   * <dt><b>Precondition:</b><dd>
   *   isCurrent()</CODE> returns true.
   * @return
   *   the current capacity of this sequence
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   getCurrent</CODE> may not be called.
   **/
   public double getCurrent()
   {
      if(isCurrent() == true)
      {
    	  return cursor.getData();
      }
      if(isCurrent() == false)
      {
    	  throw new IllegalStateException();
      }
      return 0;
   }


   /**
   * Accessor method to determine whether this sequence has a specified 
   * current element that can be retrieved with the 
   * getCurrent</CODE> method. 
   * @param - none
   * @return
   *   true (there is a current element) or false (there is no current element at the moment)
   **/
   public boolean isCurrent( )
   {
      DoubleNode find;
      for(find = head; find != null; find = find.getLink())
      {
    	  if(find == cursor)
    	  {
    		  return true;
    	  }
      }      return false;
   }
              
   /**
   * Remove the current element from this sequence.
   * @param - none
   * <dt><b>Precondition:</b><dd>
   *   isCurrent()</CODE> returns true.
   * <dt><b>Postcondition:</b><dd>
   *   The current element has been removed from this sequence, and the 
   *   following element (if there is one) is now the new current element. 
   *   If there was no following element, then there is now no current 
   *   element.
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   removeCurrent</CODE> may not be called. 
   **/
   public void removeCurrent( )
   {
	   if(isCurrent() == true)
	      {
	    	  cursor = cursor.getLink();
	    	  manyNodes--;
	      }
	   if(isCurrent() == false)
	      {
	    	  throw new IllegalStateException();
	      }
   }
   
   /**
    * Removes the very first element of the sequence of the sequence
    * 
    * @throws NullPointerExcpetion
    * 	this error should only occur if the sequence has no elements.
    */
   public void removeFront() throws NullPointerException
   {
	   if(head != null)
	   {
		   head = head.getLink();
	   		manyNodes--;
	   }
   }
                 
   
   /**
   * Determine the number of elements in this sequence.
   * @param - none
   * @return
   *   the number of elements in this sequence
   **/ 
   public int size( )
   {
	   return manyNodes;
   }
   
   
   /**
   * Set the current element at the front of this sequence.
   * @param - none
   * <dt><b>Postcondition:</b><dd>
   *   The front element of this sequence is now the current element (but 
   *   if this sequence has no elements at all, then there is no current 
   *   element).
   **/ 
   public void start( )
   {
      cursor = head;
   }
   
   /**
    * finds the value at the requested index
    * @param element element to be retrieved
    * 
    * @return
    * 	if the Sequence is not null, it returns the element value at the requested
    * 	index, else it will return 0.0
    */
   public double getElementAtIndex(int element)
   {
	   if(head != null)
	   {
		   start();
		   for(int i = 0; i < element; i++)
		   {
			   advance();
		   }
		   return cursor.getData();
	   }
	   return 0.0;
   }
   
   
   public void end()
   {
	   
   }
   
   
   public void setCurrent(int element)
   {
	   
   }
   
   
   public String toString()
   {
	   DoubleNode count;
	   String string= "";
	   for(count = head; count != null; count = count.getLink())
	   {
		   string += (count.getData() + "->");
	   }
	   return string;
   }
}