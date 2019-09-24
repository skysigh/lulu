package org.skysigh.lulu.admin.util;

import java.util.ListIterator;

import org.skysigh.lulu.admin.exception.ArgsException;

public interface ArgumentMarshaler {
	default void set(ListIterator<String> currentArgument) throws ArgsException {
		
	};
}
