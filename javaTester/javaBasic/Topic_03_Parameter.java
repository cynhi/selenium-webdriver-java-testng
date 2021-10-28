package javaBasic;

public class Topic_03_Parameter {
	String carName = "Honda";
	String carColor = "Black";

	public static void main(String[] args) {
		Topic_03_Parameter topic = new Topic_03_Parameter();
		topic.showCarName();
		topic.showCarName("Toyota");
		System.out.println(topic.getCarName());
		
		topic.showCarName("Honda", "Black");
		System.out.println(topic.getCarName());
		
	}
	
	public void showCarName() {
		System.out.println("No param");
	}
	
	public void showCarName(String carName) {
		this.carName = carName;
	}
	
	public void showCarName(String carName, String carColor) {
		this.carName = carName;
		this.carColor = carColor;
	}
	
	public String getCarName() {
		return this.carName;
	}
	

}
