package testNG;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_03_Group {
  @BeforeClass (alwaysRun= true)
  public void beforeClass () {
	  
  }
  
  @Test (groups="user")	
  public void TC_01_Assert() {
	  
  }
  
  @Test (groups={"super", "user"})	
  public void TC_02() {
	  
  }
  

}
