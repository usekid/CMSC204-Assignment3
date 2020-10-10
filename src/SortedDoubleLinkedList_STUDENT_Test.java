/***********************************************************************
 Class: CMSC204 CRN 22378
 Program: Assignment # 3
 Instructor: Professor Alexander
 Description: Sorted Double Linked List Student Test
 Due: 10/14/2020
 I pledge that I have completed the programming assignment independently.
 I have not copied the code from a student or any source.
 Anthony Liu
************************************************************************/
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortedDoubleLinkedList_STUDENT_Test {
	SortedDoubleLinkedList<String> sortedLinkedString;
	SortedDoubleLinkedList<Double> sortedLinkedDouble;
	SortedDoubleLinkedList<Patient> sortedLinkedPatient;
	StringComparator comparator;
	DoubleComparator comparatorD;
	PatientComparator comparatorPatient;

	public Patient a=new Patient("Fred", 12, 2345);
	public Patient b=new Patient("Jerry", 23, 4563);
	public Patient c=new Patient("Harry", 45, 6542);
	public Patient d=new Patient("Sarah", 50, 2562);
	public Patient e=new Patient("Chet", 22, 5476);
	public Patient f=new Patient("Chris", 23, 5672);
	
	@Before
	public void setUp() throws Exception {
		comparator = new StringComparator();
		sortedLinkedString = new SortedDoubleLinkedList<String>(comparator);

		comparatorD = new DoubleComparator();
		sortedLinkedDouble = new SortedDoubleLinkedList<Double>(comparatorD);

		comparatorPatient = new PatientComparator();
		sortedLinkedPatient = new SortedDoubleLinkedList<Patient>(comparatorPatient);

	}

	@After
	public void tearDown() throws Exception {
		comparator = null;
		comparatorD = null;
		comparatorPatient = null;
		sortedLinkedString = null;
		sortedLinkedDouble = null;
		sortedLinkedPatient = null;
	}

	@Test
	public void testAddToEnd() {
		try {
			sortedLinkedString.addToEnd("Hello");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		} catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		} catch (Exception e) {
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddToFront() {
		try {
			sortedLinkedString.addToFront("Hello");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		} catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		} catch (Exception e) {
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testIteratorSuccessfulNext() {
		sortedLinkedPatient.add(a);
		sortedLinkedPatient.add(b);
		sortedLinkedPatient.add(c);
		sortedLinkedPatient.add(d);
		ListIterator<Patient> iterator = sortedLinkedPatient.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(a, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(true, iterator.hasNext());

	}

	@Test
	public void testIteratorSuccessfulStringPrevious() {
		sortedLinkedString.add("Begin");
		sortedLinkedString.add("World");
		sortedLinkedString.add("Hello");
		sortedLinkedString.add("End");
		ListIterator<String> iterator = sortedLinkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Begin", iterator.next());
		assertEquals("End", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals("World", iterator.previous());
		assertEquals("Hello", iterator.previous());
		assertEquals("End", iterator.previous());
	}

	@Test
	public void testIteratorSuccessfulPatientPrevious() {
		sortedLinkedPatient.add(e);
		sortedLinkedPatient.add(c);
		sortedLinkedPatient.add(b);
		sortedLinkedPatient.add(d);
		// Alphabetic order: e f a c b d
		ListIterator<Patient> iterator = sortedLinkedPatient.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(e, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(d, iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(d, iterator.previous());
		assertEquals(b, iterator.previous());
		assertEquals(c, iterator.previous());
	}

	@Test
	public void testIteratorSuccessfulDoubleNext() {
		sortedLinkedDouble.add(new Double(8));
		sortedLinkedDouble.add(new Double(6));
		sortedLinkedDouble.add(new Double(1));
		sortedLinkedDouble.add(new Double(2));
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(new Double(1), iterator.next());
		assertEquals(new Double(2), iterator.next());
		assertEquals(new Double(6), iterator.next());
		assertEquals(true, iterator.hasNext());
	}

	@Test
	public void testIteratorSuccessfulDoublePrevious() {
		sortedLinkedDouble.add(new Double(5));
		sortedLinkedDouble.add(new Double(10));
		sortedLinkedDouble.add(new Double(6));
		sortedLinkedDouble.add(new Double(2));
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		assertEquals(new Double(2), iterator.next());
		assertEquals(new Double(5), iterator.next());
		assertEquals(new Double(6), iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(new Double(8), iterator.previous());
		assertEquals(true, iterator.hasPrevious());
	}

	@Test
	public void testIteratorNoSuchElementException() {
		sortedLinkedPatient.add(e);
		sortedLinkedPatient.add(c);
		sortedLinkedPatient.add(b);
		sortedLinkedPatient.add(d);
		// Alphabetic order: e f a c b d
		ListIterator<Patient> iterator = sortedLinkedPatient.iterator();

		assertEquals(true, iterator.hasNext());
		assertEquals(e, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(d, iterator.next());
		try {
			//Empty list, should throw exception
			iterator.next();
			assertTrue("Did not throw a NoSuchElementException", false);
		} catch (NoSuchElementException e) {
			assertTrue("Successfully threw a NoSuchElementException", true);
		} catch (Exception e) {
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}

	@Test
	public void testIteratorUnsupportedOperationExceptionString() {
		sortedLinkedPatient.add(e);
		sortedLinkedPatient.add(c);
		sortedLinkedPatient.add(b);
		sortedLinkedPatient.add(d);
		// ArrayList<Patient> PatientList = sortedLinkedPatient.toArrayList();
		// Alphabetic order: e f a c b d
		ListIterator<Patient> iterator = sortedLinkedPatient.iterator();

		try {
			//Remove is not supported for the iterator, should throw unsupported exception
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException", false);
		} catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw a UnsupportedOperationException", true);
		} catch (Exception e) {
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddPatient() {
		// Alphabetic order: e f a c b d
		sortedLinkedPatient.add(a);
		sortedLinkedPatient.add(b);
		sortedLinkedPatient.add(c);
		assertEquals(a, sortedLinkedPatient.getFirst());
		assertEquals(b, sortedLinkedPatient.getLast());
		sortedLinkedPatient.add(d);
		sortedLinkedPatient.add(e);
		assertEquals(e, sortedLinkedPatient.getFirst());
		assertEquals(d, sortedLinkedPatient.getLast());
		// Deletes last element
		assertEquals(d, sortedLinkedPatient.retrieveLastElement());
		assertEquals(b, sortedLinkedPatient.getLast());
	}

	@Test
	public void testRemoveFirstPatient() {
		// Alphabetic order: e f a c b d
		sortedLinkedPatient.add(b);
		sortedLinkedPatient.add(c);
		assertEquals(c, sortedLinkedPatient.getFirst());
		assertEquals(b, sortedLinkedPatient.getLast());
		sortedLinkedPatient.add(a);
		assertEquals(a, sortedLinkedPatient.getFirst());
		// Remove first item from thel ist
		sortedLinkedPatient.remove(a, comparatorPatient);
		assertEquals(c, sortedLinkedPatient.getFirst());
	}

	@Test
	public void testRemoveEndPatient() {
		// Alphabetic order: e f a c b d
		sortedLinkedPatient.add(b);
		sortedLinkedPatient.add(f);
		assertEquals(f, sortedLinkedPatient.getFirst());
		assertEquals(b, sortedLinkedPatient.getLast());
		sortedLinkedPatient.add(d);
		assertEquals(d, sortedLinkedPatient.getLast());
		// Removes from the end of the list
		sortedLinkedPatient.remove(d, comparatorPatient);
		assertEquals(b, sortedLinkedPatient.getLast());
	}

	@Test
	public void testRemoveMiddlePatient() {
		// Alphabetic order: e f a c b d
		sortedLinkedPatient.add(a);
		sortedLinkedPatient.add(b);
		assertEquals(a, sortedLinkedPatient.getFirst());
		assertEquals(b, sortedLinkedPatient.getLast());
		sortedLinkedPatient.add(f);
		assertEquals(f, sortedLinkedPatient.getFirst());
		assertEquals(b, sortedLinkedPatient.getLast());
		assertEquals(3, sortedLinkedPatient.getSize());
		// Remove from middle of list
		sortedLinkedPatient.remove(a, comparatorPatient);
		assertEquals(f, sortedLinkedPatient.getFirst());
		assertEquals(b, sortedLinkedPatient.getLast());
		assertEquals(2, sortedLinkedPatient.getSize());
	}

	private class StringComparator implements Comparator<String> {

		@Override
		public int compare(String arg0, String arg1) {
			return arg0.compareTo(arg1);
		}

	}

	private class DoubleComparator implements Comparator<Double> {

		@Override
		public int compare(Double arg0, Double arg1) {

			return arg0.compareTo(arg1);
		}

	}

	private class PatientComparator implements Comparator<Patient> {

		@Override
		public int compare(Patient arg0, Patient arg1) {
			// Just put Patients in alphabetic order by name
			return arg0.getName().compareTo(arg1.getName());
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