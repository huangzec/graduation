package com.mvc.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javassist.NotFoundException;

import org.junit.Before;
import org.junit.Test;

import com.mvc.common.FileUtil;
import com.mvc.exception.VerifyException;

public class FileTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testOpen() throws NotFoundException, FileNotFoundException {
		FileInputStream file = FileUtil.open("FileTest.java");
		assertEquals(new FileInputStream("FileTest.java"), file);
	}

	@Test
	public void testWrite() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsExists() throws NotFoundException, VerifyException {
		FileUtil.isExists("FileUtilTest.java");
	}

	@Test
	public void testFixPath() {
		fail("Not yet implemented");
	}

}
