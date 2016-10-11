package homework7;

import java.util.Random;

/**
A class that implements the ADT list by using a chain of
linked nodes that has a head reference.

@author Frank M. Carrano
@author Timothy M. Henry
@version 4.0
*/
public class LinkedlistBasedList<T> implements ListInterface<T>
{
	private Node firstNode;            // Reference to first node of chain
	private int  numberOfEntries;

	public LinkedlistBasedList()
	{
		initializeDataFields();
	} // end default constructor
	
	public void clear()
	{
		initializeDataFields();
	} // end clear

/*  < Implementations of the public methods add, remove, replace, getEntry, contains,
   getLength, isEmpty, and toArray go here. >
. . . */

// Initializes the class's data fields to indicate an empty list.
private void initializeDataFields()
{
		firstNode = null;
		numberOfEntries = 0;
} // end initializeDataFields
// Returns a reference to the node at a given position.
// Precondition: The chain is not empty;
//               1 <= givenPosition <= numberOfEntries.
private Node getNodeAt(int givenPosition)
{
   assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
   Node currentNode = firstNode;
   
   // Traverse the chain to locate the desired node
   // (skipped if givenPosition is 1)
   for (int counter = 1; counter < givenPosition; counter++)
      currentNode = currentNode.getNextNode();
   assert currentNode != null;
   return currentNode;
} // end getNodeAt

	private class Node
	{
   private T    data; // Entry in list
   private Node next; // Link to next node
   
   private Node(T dataPortion)
   {
      data = dataPortion;
      next = null;
   } // end constructor
   
   private Node(T dataPortion, Node nextNode)
   {
      data = dataPortion;
      next = nextNode;
   } // end constructor
   
   private T getData()
   {
      return data;
   } // end getData
   
   private void setData(T newData)
   {
      data = newData;
   } // end setData
   
   private Node getNextNode()
   {
      return next;
   } // end getNextNode
   
   private void setNextNode(Node nextNode)
   {
      next = nextNode;
   } // end setNextNode
	} // end Node

	public void add(T newEntry)
	{
	   Node newNode = new Node(newEntry);

	   if (isEmpty())
	      firstNode = newNode;
	   else                              // Add to end of non-empty list
	   {
	      Node lastNode = getNodeAt(numberOfEntries);
	      lastNode.setNextNode(newNode); // Make last node reference new node
	   } // end if	
	   
	   numberOfEntries++;
	} // end add
	// Version 4.0


	public void add(int newPosition, T newEntry)
	{
	   if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))
	   {
	      Node newNode = new Node(newEntry);
	      if (newPosition == 1)                  // Case 1
	      {
	         newNode.setNextNode(firstNode);
	         firstNode = newNode;
	      }
	      else									         // Case 2: list is not empty
	      {                                      // and newPosition > 1
	         Node nodeBefore = getNodeAt(newPosition - 1);
	         Node nodeAfter = nodeBefore.getNextNode();
	         newNode.setNextNode(nodeAfter);
	         nodeBefore.setNextNode(newNode);
	      } // end if
	      numberOfEntries++;
	   }
	   else
	      throw new IndexOutOfBoundsException(
	                "Illegal position given to add operation.");
	} // end add
	// Version 4.0


	public T remove(int givenPosition)
	{
	   T result = null;                           // Return value
	   if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
	   {
	      assert !isEmpty();
	      if (givenPosition == 1)                 // Case 1: Remove first entry
	      {
	         result = firstNode.getData();        // Save entry to be removed
	         firstNode = firstNode.getNextNode(); // Remove entry
	      }
	      else                                    // Case 2: Not first entry
	      {
	         Node nodeBefore = getNodeAt(givenPosition - 1);
	         Node nodeToRemove = nodeBefore.getNextNode();
	         result = nodeToRemove.getData();    // Save entry to be removed
	         Node nodeAfter = nodeToRemove.getNextNode();
	         nodeBefore.setNextNode(nodeAfter);  // Remove entry
	      } // end if
	      numberOfEntries--;                     // Update count
	      return result;                         // Return removed entry
	   }
	   else
	      throw new IndexOutOfBoundsException(
	                "Illegal position given to remove operation.");
	} // end remove
	// Version 4.0

	public T replace(int givenPosition, T newEntry)
	{
	   if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
	   {
	      assert !isEmpty();
	      Node desiredNode = getNodeAt(givenPosition);
	      T originalEntry = desiredNode.getData();
	      desiredNode.setData(newEntry);
	      return originalEntry;
	   }
	   else
	      throw new IndexOutOfBoundsException(
	                "Illegal position given to replace operation. at position" +givenPosition);
	} // end replace
	// Version 4.0


	public T getEntry(int givenPosition)
	{
	   if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
	   {
	      assert !isEmpty();
	      return getNodeAt(givenPosition).getData();
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
	   T[] result = (T[])new Object[numberOfEntries];
	   
	   int index = 0;
	   Node currentNode = firstNode;
	   while ((index < numberOfEntries) && (currentNode != null))
	   {
	      result[index] = currentNode.getData();
	      currentNode = currentNode.getNextNode();
	      index++;
	   } // end while
	   
	   return result;
	} // end toArray
	// Version 4.0

	
	@Override
	public boolean contains(T anEntry) {
		// TODO Auto-generated method stub
		return false;
	}

	public int randomIndex(ListInterface rand){	
		Random random = new Random();
		int a = random.nextInt(getLength()) + 1;
		return a;//returns a random int from 1 to numberOfEntries
	}

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return numberOfEntries;
	}

	
	public boolean isEmpty()
	{
	   boolean result;
	      
	   if (numberOfEntries == 0) // Or getLength() == 0
	   {
//	      assert firstNode == null;
	      result = true;
	   }
	   else
	   {
//	      assert firstNode != null;
	      result = false;
	   } // end if
	      
	   return result;
	} // end isEmpty
	// Version 4.0


	@Override
	public void display() {
		Node currentNode = firstNode;
		while(currentNode != null){
			System.out.println(currentNode.getData());
			currentNode = currentNode.next;
		}	
	}
	
} // end LList