package crawling;

public class Product {
	private String company;
	private String name;
	private String price;
	private String event;

	public Product(String company, String name, String price, String event)
	{
		this.company = company;
		this.name = name;
		this.price = price;
		this.event = event;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPrice() {
		return price;
	}
	
	public String getEvent() {
		return event;
	}
	
	@Override
	public String toString() {
		return "제품 [회사명 :  " + company + ", 제품명 : " + name + ", 가격 : " + price + ",행사 정보 : " + event + "]";
	}

}
