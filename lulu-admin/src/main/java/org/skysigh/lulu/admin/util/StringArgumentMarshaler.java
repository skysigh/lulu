package org.skysigh.lulu.admin.util;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.skysigh.lulu.admin.exception.ArgsException;

public class StringArgumentMarshaler implements ArgumentMarshaler {

	private String stringValue = "";
	
	@Override
	public void set(ListIterator<String> currentArgument) throws ArgsException {
		try {
			stringValue = currentArgument.next();
		} catch (NoSuchElementException e) {
			throw new ArgsException("jj");
		}
	}

	public static String getValue(ArgumentMarshaler am) {
		if(am != null && am instanceof StringArgumentMarshaler) {
			return ((StringArgumentMarshaler) am).stringValue;
		}
		return "";
	}

}
