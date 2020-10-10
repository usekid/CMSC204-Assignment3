/***********************************************************************
 Class: CMSC204 CRN 22378
 Program: Assignment # 3
 Instructor: Professor Alexander
 Description: Basic Double Linked List Student Tests
 Due: 10/14/2020
 I pledge that I have completed the programming assignment independently.
 I have not copied the code from a student or any source.
 Anthony Liu
************************************************************************/
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BasicDoubleLinkedList_STUDENT_Test {
	BasicDoubleLinkedList<String> linkedString;
	BasicDoubleLinkedList<Double> linkedDouble;
	BasicDoubleLinkedList<Patient> linkedPatient;
	StringComparator comparator;
	DoubleComparator comparatorD;
	PatientComparator comparatorPatient;
	
	public Patient a=new Patient("Fred", 12, 2345);
	public Patient b=new Patient("Jerry", 23, 4563);
	public Patient c=new Patient("Harry", 45, 6542);
	public Patient d=new Patient("Sarah", 50, 2562);
	public Patient e=new Patient("Chet", 22, 5476);
	public Patient f=new Patient("Chris", 23, 5672);

	public ArrayList<Patient> fill = new ArrayList<Patient>();
	

	@Before
	public void setUp() throws Exception {
		linkedString = new BasicDoubleLinkedList<String>();
		linkedString.addToEnd("Hello");
		linkedString.addToEnd("World");
		comparator = new StringComparator();
		
		linkedDouble = new BasicDoubleLinkedList<Double>();
		linkedDouble.addToEnd(15.0);
		linkedDouble.addToEnd(100.0);
		comparatorD = new DoubleComparator();
		
		linkedPatient= new BasicDoubleLinkedList<Patient>();
		linkedPatient.addToEnd(b);
		linkedPatient.addToEnd(c);
		comparatorPatient = new PatientComparator();
	}

	@After
	public void tearDown() throws Exception {
		linkedString = null;
		linkedDouble = null;
		linkedPatient = null;
		comparatorD = null;
		comparator = null;
	}

	@Test
	public void testGetSize() {
		assertEquals(2,linkedString.getSize());
		assertEquals(2,linkedDouble.getSize());
		assertEquals(2,linkedPatient.getSize());
	}
	
	@Test
	public void testAddToLast() {
		assertEquals("World", linkedString.getLast());
		linkedString.addToEnd("Last");
		assertEquals("Last", linkedString.getLast());
		
		assertEquals(c,linkedPatient.getLast());
		linkedPatient.addToEnd(d);
		assertEquals(d,linkedPatient.getLast());
	}
	
	@Test
	public void testAddToFront() {
		assertEquals("Hello", linkedString.getFirst());
		linkedString.addToFront("Front");
		assertEquals("Front", linkedString.getFirst());
		
		assertEquals(b,linkedPatient.getFirst());
		linkedPatient.addToFront(a);
		assertEquals(a,linkedPatient.getFirst());
	}
	
	@Test
	public void testGetFirst() {
		assertEquals("Hello", linkedString.getFirst());
		linkedString.addToFront("New");
		assertEquals("New", linkedString.getFirst());
		
		assertEquals(b,linkedPatient.getFirst());
		linkedPatient.addToFront(a);
		assertEquals(a,linkedPatient.getFirst());
	}

	@Test
	public void testGetLast() {
		assertEquals("World", linkedString.getLast());
		linkedString.addToEnd("New");
		assertEquals("New", linkedString.getLast());
		
		assertEquals(c,linkedPatient.getLast());
		linkedPatient.addToEnd(d);
		assertEquals(d,linkedPatient.getLast());
	}
	
	@Test
	public void testToArrayList()
	{
		ArrayList<Patient> list;
		linkedPatient.addToFront(a);
		linkedPatient.addToEnd(d);
		list = linkedPatient.toArrayList();
		assertEquals(a,list.get(0));
		assertEquals(b,list.get(1));
		assertEquals(c,list.get(2));
		assertEquals(d,list.get(3));
	}
	
	@Test
	public void testIteratorSuccessfulNext() {
		linkedString.addToFront("Front");
		linkedString.addToEnd("Last");
		ListIterator<String> iterator = linkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Front", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals("Last", iterator.next());
		
		linkedPatient.addToFront(a);
		linkedPatient.addToEnd(d);
		ListIterator<Patient> iteratorPatient = linkedPatient.iterator();
		assertEquals(true, iteratorPatient.hasNext());
		assertEquals(a, iteratorPatient.next());
		assertEquals(b, iteratorPatient.next());
		assertEquals(c, iteratorPatient.next());
		assertEquals(true, iteratorPatient.hasNext());
		assertEquals(d, iteratorPatient.next());
	}
	
	@Test
	public void testIteratorSuccessfulPrevious() {
		linkedPatient.addToFront(a);
		linkedPatient.addToEnd(d);
		ListIterator<Patient> iteratorPatient = linkedPatient.iterator();
		assertEquals(true, iteratorPatient.hasNext());
		assertEquals(a, iteratorPatient.next());
		assertEquals(b, iteratorPatient.next());
		assertEquals(c, iteratorPatient.next());
		assertEquals(d, iteratorPatient.next());
		assertEquals(true, iteratorPatient.hasPrevious());
		assertEquals(d, iteratorPatient.previous());
		assertEquals(c, iteratorPatient.previous());
		assertEquals(b, iteratorPatient.previous());
		assertEquals(a, iteratorPatient.previous());
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionNext() {
		linkedPatient.addToFront(a);
		linkedPatient.addToEnd(d);
		ListIterator<Patient> iteratorPatient = linkedPatient.iterator();		
		assertEquals(true, iteratorPatient.hasNext());
		assertEquals(a, iteratorPatient.next());
		assertEquals(b, iteratorPatient.next());
		assertEquals(c, iteratorPatient.next());
		assertEquals(true, iteratorPatient.hasNext());
		assertEquals(d, iteratorPatient.next());
		
		try{
			//Empty list, should throw exception
			iteratorPatient.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionPrevious() {
		linkedPatient.addToFront(a);
		linkedPatient.addToEnd(d);
		ListIterator<Patient> iteratorPatient = linkedPatient.iterator();		
		assertEquals(true, iteratorPatient.hasNext());
		assertEquals(a, iteratorPatient.next());
		assertEquals(b, iteratorPatient.next());
		assertEquals(c, iteratorPatient.next());
		assertEquals(d, iteratorPatient.next());
		assertEquals(true, iteratorPatient.hasPrevious());
		assertEquals(d, iteratorPatient.previous());
		assertEquals(c, iteratorPatient.previous());
		assertEquals(b, iteratorPatient.previous());
		assertEquals(a, iteratorPatient.previous());
		
		try{
			//Empty list, should throw excpetion
			iteratorPatient.previous();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
	
	@Test
	public void testIteratorUnsupportedOperationException() {
		linkedPatient.addToFront(a);
		linkedPatient.addToEnd(d);
		ListIterator<Patient> iteratorPatient = linkedPatient.iterator();		
		assertEquals(true, iteratorPatient.hasNext());
		assertEquals(a, iteratorPatient.next());
		assertEquals(b, iteratorPatient.next());
		assertEquals(c, iteratorPatient.next());
		assertEquals(true, iteratorPatient.hasNext());
		assertEquals(d, iteratorPatient.next());
		
		try{
			//Remove not supported, should throw exception
			iteratorPatient.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
		
	}
	
	@Test
	public void testRemove() {
		// Removes first item
		assertEquals(b, linkedPatient.getFirst());
		assertEquals(c, linkedPatient.getLast());
		linkedPatient.addToFront(a);
		assertEquals(a, linkedPatient.getFirst());
		linkedPatient.remove(a, comparatorPatient);
		assertEquals(b, linkedPatient.getFirst());
		//Removes from end of the list
		linkedPatient.addToEnd(d);
		assertEquals(d, linkedPatient.getLast());
		linkedPatient.remove(d, comparatorPatient);
		assertEquals(c, linkedPatient.getLast());
		//Removes from middle of list
		linkedPatient.addToFront(a);
		assertEquals(a, linkedPatient.getFirst());
		assertEquals(c, linkedPatient.getLast());
		linkedPatient.remove(b, comparatorPatient);
		assertEquals(a, linkedPatient.getFirst());
		assertEquals(c, linkedPatient.getLast());
		
	}

	@Test
	public void testRetrieveFirstElement() {
		assertEquals(b, linkedPatient.getFirst());
		linkedPatient.addToFront(a);
		assertEquals(a, linkedPatient.getFirst());
		assertEquals(a, linkedPatient.retrieveFirstElement());
		assertEquals(b,linkedPatient.getFirst());
		assertEquals(b, linkedPatient.retrieveFirstElement());
		assertEquals(c,linkedPatient.getFirst());
		//Gets first item in list
		assertEquals("Hello", linkedString.getFirst());
		linkedString.addToFront("New");
		assertEquals("New", linkedString.getFirst());
		assertEquals("New", linkedString.retrieveFirstElement());
		assertEquals("Hello",linkedString.getFirst());
		assertEquals("Hello", linkedString.retrieveFirstElement());
		assertEquals("World",linkedString.getFirst());
		
	}

	@Test
	public void testRetrieveLastElement() {
		assertEquals(c, linkedPatient.getLast());
		linkedPatient.addToEnd(d);
		assertEquals(d, linkedPatient.getLast());
		assertEquals(d, linkedPatient.retrieveLastElement());
		assertEquals(c,linkedPatient.getLast());
		//Gets last item in list
		assertEquals("World", linkedString.getLast());
		linkedString.addToEnd("New");
		assertEquals("New", linkedString.getLast());
		assertEquals("New", linkedString.retrieveLastElement());
		assertEquals("World",linkedString.getLast());
	}

	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class PatientComparator implements Comparator<Patient>
	{

		@Override
		public int compare(Patient arg0, Patient arg1) {
			return arg0.toString().compareTo(arg1.toString());
		}
		
	}
	
	private class Patient{
		String name;
		int age;
		int patientNumber;
		
		public Patient(String name, int age, int patientNumber){
			this.name = name;
			this.age = age;
			this.patientNumber = patientNumber;
		}
		
		public String getName(){
			return name;
		}
		public int getAge(){
			return age;
		}
		public int getPatientNumber(){
			return patientNumber;
		}
		
		public String toString() {
			return (getName()+" "+getAge()+" "+getPatientNumber());
		}
	}
}