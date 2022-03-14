package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;
public class Topic_02_Assert {
	@Test
  public void TC_01_Assert() {
	  boolean status = 8 < 2;
//	  Assert.assertTrue(status);
	  Assert.assertTrue(status, "8 is greater than 2");
	  Assert.assertFalse(status );
	  Assert.assertEquals("abc", "ABC");
	  Assert.assertNotEquals(8-2, 12-10);
  }
}
