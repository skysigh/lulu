package org.skysigh.lulu.admin.learn;

import org.junit.Assert;
import org.junit.Test;

public class UriSplitTest {
	@Test
	public void splitUri_test() {
		String uri = "UserServlet/getUserById?id=1";
		String[] split = uri.split("/");
		Assert.assertEquals(2, split.length);
		Assert.assertEquals("getUserById?id=1", split[1]);
		Assert.assertEquals("UserServlet", split[0]);
		
	}

}
