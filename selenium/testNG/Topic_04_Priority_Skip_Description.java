package testNG;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_04_Priority_Skip_Description {
  
  @Test (priority=2)	
  public void TC_01_Assert() {
	  
  }
  
  @Test ()	
  public void TC_02() {
	  
  }
  
  //Skipped TC using 'enabled' flag
  @Test (enabled= false)	
  public void TC_03() {
	  
  }
  
  @Test (description = "Jira ID: 12345 - Create user...")	
  public void TC_04() {
	  
  }
  
  @Test (priority=1)	
  public void TC_05() {
	  
  }
}
 