package homework7;

import java.util.Arrays;
import java.util.Random;
/**
   A class that implements the ADT list by using a resizable array.
   Entries in a list have positions that begin with 1.
   Duplicate entries are allowed.
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 4.0
*/
public class ArrayBasedList<T> implements ListInterface<T>
{
	private T[] list;   // Array of list entries; ignore list[0]
	private int numberOfEntries;
   private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 3;
	private static final int MAX_CAPACITY = 15;
   
	public ArrayBasedList()
	{
		this(DEFAULT_CAPACITY);
	} // end default constructor
   
	public ArrayBasedList(int initialCapacity)
	{
      // Is initialCapacity too small?
      if (initialCapacity < DEFAULT_CAPACITY)
         initialCapacity = DEFAULT_CAPACITY;

      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempList = (T[])new Object[initialCapacity + 1];
      list = tempList;
      numberOfEntries = 0;
      initialized = true;
	} // end constructor
	
	public int randomIndex(ListInterface rand){	
		Random random = new Random();
		int a = random.nextInt(getLength() + 1) + 1;
		return a;//returns a random int from 1 to numberOfEntries
	}
	
	public void display(){
		for(int i = 0; i < numberOfEntries; i++){
			System.out.println(list[i]);
		}
	}
	
	public void add(T newEntry)
	{
       list[numberOfEntries + 1] = newEntry;
       numberOfEntries++;
       ensureCapacity();
//     add(numberOfEntries + 1, newEntry);  // ALTERNATE CODE
	} // end add

	
	// Precondition: The array list has room for another entry.
	public void add(int newPosition, T newEntry)
	{
	   if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))
	   {
	      if (newPosition <= numberOfEntries)
	         makeRoom(newPosition);
	      list[newPosition] = newEntry;
	      numberOfEntries++;
	      ensureCapacity(); // Ensure enough room for next add
	   }
	   else
	      throw new IndexOutOfBoundsException(
	                "Given position of add's new entry is out of bounds.");
	} // end add
	// Version 4.0
	
	// Makes room for a new entry at newPosition.
	// Precondition: 1 <= newPosition <= numberOfEntries + 1;
//	               numberOfEntries is list's length before addition;
//	               checkInitialization has been called.
	private void makeRoom(int newPosition)
	{
	   assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);
	   int newIndex = newPosition;
	   int lastIndex = numberOfEntries;
	   // Move each entry to next higher index, starting at end of
	   // list and continuing until the entry at newIndex is moved
	   for (int index = lastIndex; index >= newIndex; index--)
	      list[index + 1] = list[index];
	   
	   //POSSIBLE NEED numberOfEntries++;
	}  // end makeRoom
	// Version 4.0


	public T remove(int givenPosition)
	{
	   if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
	   {
	      assert !isEmpty();
	      T result = list[givenPosition]; // Get entry to be removed
	      // Move subsequent entries towards entry to be removed,
	      // unless it is last in list
	      if (givenPosition < numberOfEntries)
	         removeGap(givenPosition);
	      numberOfEntries--;
	      return result;
	   }
	   else
	      throw new IndexOutOfBoundsException(
	                "Illegal position given to remove operation.");
	} // end remove
	// Version 4.0

	public void removeGap(int givenPosition){
	   assert (givenPosition >= 1) && (givenPosition <= numberOfEntries + 1);
	   int newIndex = givenPosition;
	   int lastIndex = numberOfEntries;

	   for (int index = givenPosition; index <= lastIndex; index++)
	      list[index] = list[index + 1];	
	   list[numberOfEntries] = null;
	}


	public T replace(int givenPosition, T newEntry)
	{
	   if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
	   {
	      assert !isEmpty();
	      T originalEntry = list[givenPosition];
	      list[givenPosition] = newEntry;
	      return originalEntry;
	   }
	   else
	      throw new IndexOutOfBoundsException(
	                "Illegal position given to replace operation.");
	} // end replace
	
	
	public T getEntry(int givenPosition)
	{
	   if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
	   {
	      assert !isEmpty();
	      return list[givenPosition];
	   }
	   else
	      throw new IndexOutOfBoundsException(
	                "Illegal position given to getEntry operation.");
	} // end getEntry
	// Version 4.0


   public T[] toArray()
   {
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast
      for (int index = 0; index < numberOfEntries; index++)
      {
         result[index] = list[index + 1];
      } // end for
      
      return result;
   } // end toArray
   

	public int getLength()
	{
		return numberOfEntries;
	} // end getLength

	public boolean isEmpty()
	{
		return numberOfEntries == 0; // Or getLength() == 0
	} // end isEmpty

   // Doubles the capacity of the array list if it is full.
   // Precondition: checkInitialization has been called.
   private void ensureCapacity()
   {
      int capacity = list.length - 1;
      if (numberOfEntries >= capacity)
      {
         int newCapacity = 2 * capacity;
         list = Arrays.copyOf(list, newCapacity + 1);
      } // end if
   } // end ensureCapacity

@Override
public boolean contains(T anEntry) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public void clear() {
	// TODO Auto-generated method stub
	
}

/* < This class will define checkCapacity, checkInitialization, and two more private methods that will be discussed later. > */
} // end AList