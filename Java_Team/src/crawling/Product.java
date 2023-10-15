package crawling;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
	
	public JSONArray JSONWriter(FileWriter writer){
		JSONObject product_info = new JSONObject();
		JSONArray productArray = new JSONArray();
		product_info.put("제품 명", this.name);
		product_info.put("회사 명", this.company);
		product_info.put("가격", this.price);
		product_info.put("행사 정보", this.event);
		productArray.add(product_info);
		
		return productArray;
//		all_products.put("전체 제품", productArray);
		
//		
//		try(FileWriter writer = new FileWriter("src/crawling/gs25data.json"))
//		{
//			writer.write(all_products.toJSONString());
//			writer.flush();
//			writer.close();
//			System.out.println(product_info.toJSONString());
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
		
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
		return "제품 [회사명 :  " + company + "], [제품명 : " + name + "], [가격 : " + price + "], [행사 정보 : " + event + "]";
	}

}
