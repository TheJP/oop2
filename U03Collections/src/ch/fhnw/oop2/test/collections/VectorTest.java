package ch.fhnw.oop2.test.collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ch.fhnw.oop2.collections.Vector;

/**
 * Testing of Vector<T>
 * @author JP
 *
 */
public class VectorTest {
	
	private Vector<String> testVector;

	@Before
	public void init(){
		testVector = new Vector<>();
	}

	@Test
	public void testNormalAccess(){
		Assert.assertEquals(16, testVector.capacity());
		Assert.assertEquals(0, testVector.size());
		Assert.assertSame(null, testVector.popFront());
		testVector.pushBack("test1");
		Assert.assertEquals(16, testVector.capacity());
		Assert.assertEquals(1, testVector.size());
		Assert.assertEquals("test1", testVector.get(0));
		Assert.assertEquals("test1", testVector.popFront());
		Assert.assertEquals(16, testVector.capacity());
		Assert.assertEquals(0, testVector.size());
	}

	@Test
	public void testGetSetAccess(){
		testVector.pushBack("test");
		Assert.assertEquals("test", testVector.get(0));
		testVector.set(0, "newtest");
		Assert.assertEquals("newtest", testVector.get(0));
	}

	@Test
	public void testResize(){
		for(int i = 0; i < 16; i++){
			testVector.pushBack("test"+i);
			Assert.assertEquals("test"+i, testVector.get(i));
		}
		Assert.assertEquals(16, testVector.capacity());
		Assert.assertEquals(16, testVector.size());
		testVector.pushBack("resize");
		Assert.assertEquals(32, testVector.capacity());
		Assert.assertEquals(17, testVector.size());
		Assert.assertEquals("test0", testVector.popFront());
		Assert.assertEquals(32, testVector.capacity());
		Assert.assertEquals(16, testVector.size());
		Assert.assertEquals("resize", testVector.get(15));
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testException01(){
		exception.expect(IndexOutOfBoundsException.class);
		testVector.get(0);
	}
	@Test
	public void testException02(){
		exception.expect(IndexOutOfBoundsException.class);
		testVector.set(0, "test");
	}
	@Test
	public void testException03(){
		testVector.pushBack("test");
		exception.expect(IndexOutOfBoundsException.class);
		testVector.get(2);
	}
	@Test
	public void testException04(){
		testVector.pushBack("test");
		exception.expect(IndexOutOfBoundsException.class);
		testVector.set(2, "test2");
	}
	@Test
	public void testException05(){
		exception.expect(IndexOutOfBoundsException.class);
		testVector.get(-1);
	}
	@Test
	public void testException06(){
		exception.expect(IndexOutOfBoundsException.class);
		testVector.set(-1, "test");
	}

	@Test
	public void testCopyConstructor(){
		for(int i = 0; i < 16; i++){
			testVector.pushBack("test"+i);
		}
		Vector<String> newVector = new Vector<>(testVector);
		Assert.assertEquals(testVector.capacity(), newVector.capacity());
		Assert.assertEquals(testVector.size(), newVector.size());
		while(testVector.size() > 0){
			Assert.assertSame(testVector.popFront(), newVector.popFront());
		}
	}

	@Test
	public void testIterator(){
		for(int i = 0; i < 20; i++){
			testVector.pushBack("test"+i);
		}
		int count = 0;
		for(String s : testVector){
			Assert.assertEquals("test"+(count++), s);
		}
		Assert.assertEquals(20, count);
	}
}
