package ch.fhnw.oop2.test.collections;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ch.fhnw.oop2.collections.ComparableVector;

public class ComparableVectorTest {

	private ComparableVector<Integer> testVector;

	@Before
	public void init(){
		testVector = new ComparableVector<Integer>();
	}

	@Test
	public void testMinMax(){
		testVector.pushBack(15);
		testVector.pushBack(17);
		testVector.pushBack(25);
		testVector.pushBack(9);
		testVector.pushBack(89);
		testVector.pushBack(253);
		testVector.pushBack(5);
		testVector.pushBack(20);
		Assert.assertEquals(new Integer(5), testVector.getMin());
		Assert.assertEquals(new Integer(253), testVector.getMax());
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testException01(){
		exception.expect(NoSuchElementException.class);
		testVector.getMax();
	}
	@Test
	public void testException02(){
		exception.expect(NoSuchElementException.class);
		testVector.getMin();
	}
	@Test
	public void testException03(){
		testVector.pushBack(5);
		testVector.getMax();
		testVector.popFront();
		exception.expect(NoSuchElementException.class);
		testVector.getMax();
	}
	@Test
	public void testException04(){
		testVector.pushBack(5);
		testVector.getMin();
		testVector.popFront();
		exception.expect(NoSuchElementException.class);
		testVector.getMin();
	}
}
