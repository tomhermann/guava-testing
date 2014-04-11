package com.zombietank.guava.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.Reflection;

public class ReflectionTests {
	
	@Test
	public void testFindClassesRecursive() throws Exception {
		ClassPath classpath = ClassPath.from(getClass().getClassLoader());
		for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClassesRecursive("com.zombietank")) {
			System.out.println(classInfo);
		}
	}
	
	@Test
	public void testProxy() throws Exception {
		SomeInterface instance = Reflection.newProxy(SomeInterface.class, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("Called " + method.getName() + " with args: " + Arrays.toString(args));
				return "Return something good for me now";
			}
		});
		
		instance.thisMethod();
		instance.thatMethod("Hello there");
		System.out.println(instance.returnSometing());
	}
	
	private interface SomeInterface {
		void thisMethod();
		void thatMethod(String myParameter);
		String returnSometing();
	}
}
