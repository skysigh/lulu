package org.skysigh.lulu.admin.util;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.skysigh.lulu.admin.exception.ArgsException;

public class DoubleArgumentMarshaler implements ArgumentMarshaler {

	private double doubleValue = 0;

	public static double getValue(ArgumentMarshaler am) {
		if (am != null && am instanceof DoubleArgumentMarshaler) {
			return ((DoubleArgumentMarshaler) am).doubleValue;
		}
		return 0;
	}

	@Override
	public void set(ListIterator<String> currentArgument) throws ArgsException {
		String param = null;
		try {
			param = currentArgument.next();
			doubleValue = Double.parseDouble(param);
		} catch (NoSuchElementException e) {
			throw new ArgsException("miss");
		} catch (NumberFormatException e) {
			throw new ArgsException("NumberFormatException");
		}
	}

}
