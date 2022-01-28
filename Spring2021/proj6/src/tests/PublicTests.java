package tests;

import java.util.NoSuchElementException;
import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.Test;

import searchTree.EmptyTree;
import searchTree.NonEmptyTree;
import searchTree.SearchTreeMap;
import searchTree.Tree;

public class PublicTests{
	
	@Test
	public void testEasyBSTSearch() {
		Tree<Integer,String> t = EmptyTree.getInstance();
		assertTrue(t.search(0) == null);
		t = t.insert(5, "THIS IS 5");
		assertEquals("THIS IS 5", t.search(5));
		t = t.insert(8, "THIS IS 8");
		assertEquals("THIS IS 8", t.search(8));
	}
	
	@Test
	public void testEmptySearchTreeMap() {
		SearchTreeMap<String, Integer> s = new SearchTreeMap<String, Integer>();
		assertEquals(0, s.size());
		try {
			assertEquals(null, s.getMin());
			fail("Should have thrown NoSuchElementException");
		} catch (NoSuchElementException e) {
			assert true; // as intended
		}
		try {
			assertEquals(null, s.getMax());
			fail("Should have thrown NoSuchElementException");
		} catch (NoSuchElementException e) {
			assert true; // as intended
		}
		assertEquals(null, s.get("a"));
	}
	
	@Test
	public void testBasicSearchTreeMapStuff() {
		SearchTreeMap<Integer,String> s = new SearchTreeMap<Integer,String>();
		assertEquals(0, s.size());
		s.put(2, "Two");
		s.put(1, "One");
		s.put(3, "Three");
		s.put(1, "OneSecondTime");
		assertEquals(3, s.size());
		assertEquals("OneSecondTime", s.get(1));
		assertEquals("Two", s.get(2));
		assertEquals("Three", s.get(3));
		assertEquals(null, s.get(8));
	}
	
	@Test
	public void testDelete() {
		Tree<Integer, String> tree = EmptyTree.getInstance();
		tree = tree.insert(18, "eighteen");
		tree = tree.insert(24, "twenty-four");
		tree = tree.insert(17, "seventeen");
		tree = tree.insert(4, "4");
		tree = tree.insert(19, "nineteen");
		tree = tree.insert(35, "two");
		tree = tree.insert(4, "seven");
		assertEquals(6, tree.size());
		tree = tree.delete(35);
		assertEquals(5, tree.size());
	}
	
	@Test
	public void testAddToCollection() {
		Tree<Integer, String> tree = EmptyTree.getInstance();
		tree = tree.insert(1, "first");
		tree = tree.insert(2, "second");
		tree = tree.insert(3, "third");
		tree = tree.insert(4, "fourth");
		
		ArrayList<Integer> list = new ArrayList<>();
		tree.addKeysToCollection(list);
		
		String keys = "";
		for(Integer i: list) {
			keys += i;
		}
		assertEquals(keys, "1234");
	}
	
	@Test
	public void testMaxAndMin() {
		NonEmptyTree<Integer, String> tree = new NonEmptyTree<Integer, String>(20, "twenty", 
				EmptyTree.getInstance(), EmptyTree.getInstance());
		
		tree = tree.insert(5, "five");
		tree = tree.insert(10, "ten");
		tree = tree.insert(15, "fifteen");
		tree = tree.insert(20, "twenty");
		tree = tree.insert(25, "twentyfive");
		tree = tree.insert(30, "thirty");
		tree = tree.insert(35, "thirtyfive");
		
		assertEquals(Integer.valueOf(5), tree.min());
		assertEquals(Integer.valueOf(35), tree.max());
	}
	
	@Test
	public void testSubTree() {
		Tree<Integer, String> tree = EmptyTree.getInstance();
		tree = tree.insert(2, "two");
		tree = tree.insert(4, "four");
		tree = tree.insert(6, "six");
		tree = tree.insert(8, "eight");
		tree = tree.insert(10, "ten");
		
		Tree<Integer, String> subTree = tree.subTree(6, 10);
		ArrayList<Integer> list = new ArrayList<>();
		subTree.addKeysToCollection(list);
		
		assertEquals(Integer.valueOf(6), list.get(0));
		assertEquals(Integer.valueOf(10), list.get(list.size() - 1));
	}
	
	@Test
	public void testMap() {
		SearchTreeMap<Integer,String> map = new SearchTreeMap<Integer,String>();
		map.put(2, "two");
		map.put(4, "four");
		map.put(6, "six");
		map.put(8, "eight");
		map.put(10, "ten");
		
		Tree<Integer, String> tree = EmptyTree.getInstance();
		tree = tree.insert(2, "two");
		tree = tree.insert(4, "four");
		tree = tree.insert(6, "six");
		tree = tree.insert(8, "eight");
		tree = tree.insert(10, "ten");
		
		Tree<Integer, String> subTree = tree.subTree(6, 10);
		ArrayList<Integer> list = new ArrayList<>();
		subTree.addKeysToCollection(list);
		
		System.out.println(list.get(0));
		assertEquals(Integer.valueOf(6), list.get(0));
	}
}