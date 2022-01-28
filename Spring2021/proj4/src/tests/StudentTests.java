package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import listClass.BasicLinkedList;

public class StudentTests {
	
	@Test
	public void testRemoveAllInstances() {
		BasicLinkedList<String> list = new BasicLinkedList<String>();
		list.addToEnd("YELLOW_");
		list.addToEnd("GREEN_");
		list.addToEnd("BLUE_");
		list.addToFront("ORANGE_");
		list.addToFront("RED_");
		list.addToFront("BLACK");
		list.addToEnd("PINK");
		
		list.retrieveFirstElement();
		list.retrieveLastElement();		
		
		list.addToEnd("PURPLE");
		list.addToEnd("PINK");
		
		list.removeAllInstances("PINK");
		
		String result = "";
		for(String element: list) {
			result+= element;
		}
		
		System.out.println("\n" + "testRemoveAllInstances: ");
		System.out.print("results: " + result);
		assertEquals(result, "RED_ORANGE_YELLOW_GREEN_BLUE_PURPLE");
	}
	
	@Test
	public void testRetrieve() {
		BasicLinkedList<String> list = new BasicLinkedList<String>();

		list.addToEnd("YELLOW_");
		list.addToEnd("GREEN_");
		list.addToEnd("BLUE_");
		list.addToFront("ORANGE_");
		list.addToFront("RED_");
		list.addToEnd("PURPLE");
		
		String first = list.retrieveFirstElement();
		String last = list.retrieveLastElement();
		
		String result = "";
		for(String element: list) {
			result += element;
		}
		
		
		System.out.println("\n" + "testRetrieve: ");
		System.out.println("First element: "+ first);
		System.out.println("First element should be: RED_");
		System.out.println("Last element: " + last);
		System.out.println("Last element should be: PURPLE");
		System.out.println("List: " + result);
		assertEquals(first, "RED_");
		assertEquals(last, "PURPLE");
		assertEquals(result, "ORANGE_YELLOW_GREEN_BLUE_");
	}
	
	@Test
	public void testGetFirst() {
		BasicLinkedList<String> list = new BasicLinkedList<String>();
		list.addToEnd("YELLOW_");
		list.addToEnd("GREEN_");
		list.addToEnd("BLUE_");
		list.addToFront("ORANGE_");
		list.addToFront("RED_");
		list.addToEnd("PURPLE");
		
		assertEquals(list.getFirst(), "RED_");
		list.retrieveFirstElement();
		assertEquals(list.getFirst(), "ORANGE_");
		list.retrieveFirstElement();
		assertEquals(list.getFirst(), "YELLOW_");
		
		String first = list.retrieveFirstElement();
		System.out.println("\n" + "testGetFirst: ");
		System.out.println("First element: " + first);
	}

	@Test
	public void testGetLast() {
		BasicLinkedList<String> list = new BasicLinkedList<String>();
		list.addToEnd("YELLOW_");
		list.addToEnd("GREEN_");
		list.addToEnd("BLUE_");
		list.addToFront("ORANGE_");
		list.addToFront("RED_");
		list.addToEnd("PURPLE");
		
		assertEquals(list.getLast(), "PURPLE");
		list.retrieveLastElement();
		assertEquals(list.getLast(), "BLUE_");
		list.retrieveLastElement();
		assertEquals(list.getLast(), "GREEN_");
		
		String last = list.retrieveLastElement();
		System.out.println("\n" + "testGetLast: ");
		System.out.println("Last element: " + last);
	}
	
	@Test
	public void testSize() {
		BasicLinkedList<String> list = new BasicLinkedList<String>();
		list.addToEnd("YELLOW_");
		list.addToEnd("GREEN_");
		list.addToEnd("BLUE_");
		list.addToFront("ORANGE_");
		list.addToFront("RED_");
		list.addToEnd("PURPLE");
		
		assertEquals(list.getSize(), 6);
		
		list.addToFront("BLACK");
		list.addToEnd("BROWN");
		list.addToEnd("BLACK");
		
		assertEquals(list.getSize(), 9);
		
		list.removeAllInstances("BLACK");
		
		assertEquals(list.getSize(), 7);
		
		list.retrieveLastElement();
		assertEquals(list.getSize(), 6);
	}

}
