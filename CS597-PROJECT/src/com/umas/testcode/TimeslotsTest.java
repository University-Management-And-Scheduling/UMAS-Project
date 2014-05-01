package com.umas.testcode;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import com.umas.code.*;

public class TimeslotsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testTimeslotsInt() {
		/*
		 * Initializing existing time slot
		 */
		Timeslots t = new Timeslots(16);
		assertNotNull(t);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testTimeslotsInt2() {
		/*
		 * initializing non existing time slot
		 */
		Timeslots t = new Timeslots(1454309);
	}

	@Test
	public final void testTimeslotsIntInt() {
		/*
		 * Initialize a time slot using start and end hours
		 * If the time slot does not exist error is throw
		 * Here passing valid arguments and testing
		 */
		Timeslots t = new Timeslots(10, 11);
		assertNotNull(t);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testTimeslotsIntInt2() {
		/*
		 * Initialize a time slot using start and end hours
		 * If the time slot does not exist error is throw
		 * Here passing invalid arguments and testing
		 */
		Timeslots t = new Timeslots(10, 16);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testAddTimeSlot() {
		/*
		 * Adding a new time slot
		 * The code would not add duplicate time slots
		 * Hence will fail if same slot is added multiple times
		 * Therefore testing that the duplicate must not be added
		 * Hence the test should throw a illegal argument exception
		 */
		Timeslots.addTimeSlot(13, 15, 2);
	}

	@Test
	public final void testAreHoursCorrect() {
		/*
		 * Checking for one hour and two hour time slots which are valid
		 */
		boolean check = Timeslots.areHoursCorrect(10, 11);
		boolean check1 = Timeslots.areHoursCorrect(10, 12);
		
		assertTrue(check && check1);
	}
	
	@Test
	public final void testAreHoursCorrect2() {
		/*
		 * Checking for invalid hours i.e more than two hours time slots
		 */
		boolean check = Timeslots.areHoursCorrect(10, 20);
		
		assertTrue(!check);
	}

	@Test
	public final void testIsConflict() {
		/*
		 * Entering non conflicting times
		 */
		boolean check = Timeslots.isConflict(new Timeslots(16), new Timeslots(17));
		assertTrue(!check);
	}
	
	
	@Test
	public final void testIsConflict2() {
		/*
		 * Entering conflicting times, checking if the time slot conflicts with self
		 */
		boolean check = Timeslots.isConflict(new Timeslots(16), new Timeslots(16));
		assertTrue(check);
	}

	@Test
	public final void testIsInBetween() {
		/*
		 * Entering time slot which is in between the other values (Conflicting)
		 * Here 11 is between 10 and 12 is checked
		 */
		boolean check = Timeslots.isInBetween(10, 12, 11);
		assertTrue(check);
	}
	
	@Test
	public final void testIsInBetween2() {
		/*
		 * Entering time slot which is in between the other values (Conflicting)
		 * Here 13 is not between 10 and 12 is checked
		 */
		boolean check = Timeslots.isInBetween(10, 12, 13);
		assertTrue(!check);
	}

	@Test
	public final void testIsTypeCorrect() {
		/*
		 * Testing if returns true for time slot types 1 and 2 which are valid time slots
		 * One hour time slot is type 1
		 * Two hour time slot is type 2
		 * Valid entries are passed into functions and checked
		 */
		boolean check = Timeslots.isTypeCorrect(10, 11, 1);
		boolean check1 = Timeslots.isTypeCorrect(10, 12, 2);
		assertTrue(check && check1);
	}
	
	
	@Test
	public final void testIsTypeCorrect2() {
		/*
		 * Testing if returns true for time slot types 1 and 2 which are valid time slots
		 * One hour time slot is passed as type 2 (returns false)
		 * Two hour time slot is type as type 1 (returns false)
		 * Invalid entries are passed into functions and checked
		 */
		boolean check = Timeslots.isTypeCorrect(10, 11, 2);
		boolean check1 = Timeslots.isTypeCorrect(10, 12, 1);
		assertTrue(!check && !check1);
	}

}
