package com.zombietank.guava.throwables;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doThrow;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Throwables;

@RunWith(MockitoJUnitRunner.class)
public class ThrowablesTest {

	@Mock
	private Exceptional exceptional;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 
	
	@Test
	public void propogateExceptions() throws Exception  {
		SQLException toBeThrown = new SQLException();
		thrown.expect(sameInstance(toBeThrown));
		doThrow(toBeThrown).when(exceptional).doSomethingDangerous();
		
		try {
			exceptional.doSomethingDangerous();
		} catch(Throwable t) {
			 Throwables.propagateIfInstanceOf(t, IOException.class);
		     Throwables.propagateIfInstanceOf(t, SQLException.class);
		     // Throws if already runtimeException, else wraps in runtimeException as last resort
		     throw Throwables.propagate(t); 
		}
	}

	@Test
	public void doesNotCompile()  {
		try {
			exceptional.doSomethingDangerous();
		} catch(Throwable t) {
//			throw t;
		}
	}
	
	@Test
	public void doesCompile()  {
		try {
			exceptional.doSomethingDangerous();
		} catch(Throwable t) {
			throw Throwables.propagate(t); // Throws if same runtimeException, else wraps in runtimeException
		}
	}
	
	private interface Exceptional {
		public void doSomethingDangerous() throws SQLException, IOException;
	}
}
