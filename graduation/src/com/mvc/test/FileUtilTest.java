package com.mvc.test;

import org.junit.Test;

import javassist.NotFoundException;

import com.mvc.common.FileUtil;

public class FileUtilTest {

	@Test
	public void openTest() throws NotFoundException
	{
		FileUtil.open("src/com/mvc/test/FileUtilTest.java");
	}
}
