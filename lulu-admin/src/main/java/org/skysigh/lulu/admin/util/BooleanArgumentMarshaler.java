package org.skysigh.lulu.admin.util;

import java.util.ListIterator;

import org.skysigh.lulu.admin.exception.ArgsException;

public class BooleanArgumentMarshaler implements ArgumentMarshaler {

	private boolean booleanValue = false;

	@Override
	public void set(ListIterator<String> currentArgument) throws ArgsException {
		booleanValue = true;
	}

	public static boolean getValue(ArgumentMarshaler am) {
		if (am != null && am instanceof BooleanArgumentMarshaler) {
			return ((BooleanArgumentMarshaler) am).booleanValue;
		} else {
			return false;
		}
	}

}
