package org.skysigh.lulu.admin.util;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.skysigh.lulu.admin.exception.ArgsException;

public class IntegerArgumentMarshaler implements ArgumentMarshaler {

	private int intValue = 0;
	@Override
	public void set(ListIterator<String> currentArgument) throws ArgsException {
		String parameter = null;
		try {
			parameter = currentArgument.next();
			intValue = Integer.parseInt(parameter);
		} catch (NoSuchElementException e) {
			throw new ArgsException("miss");
		} catch (NumberFormatException e) {
			throw new ArgsException("NumberFormatException");
		}
	}

	public static int getValue(ArgumentMarshaler am) {
		if(am != null && am instanceof IntegerArgumentMarshaler) {
			return ((IntegerArgumentMarshaler) am).intValue;
		}
		return 0;
	}

}
