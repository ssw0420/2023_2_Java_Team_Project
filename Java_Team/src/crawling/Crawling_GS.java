package crawling;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import java.time.Duration;
import org.openqa.selenium.By;
import java.util.Scanner;
import org.openqa.selenium.JavascriptExecutor;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;

public class Crawling_GS {

	private static WebDriver driver;
//	private WebElement element;
	private String url;
	// 크롬 드라이버 위치
	// static => 모든 객체가 공유 => 다른 페이지 크롤링을 해당 클래스에서 한다면 사용
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver_win32\\chromedriver.exe";
	private final String GS25_company = "GS25";
	
    public Crawling_GS() {
    	try {
    		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--start-maximized");            // 전체화면으로 실행
        options.addArguments("--disable-popup-blocking");    // 팝업 무시
        options.addArguments("--remote-allow-origins=*");

    	driver = new ChromeDriver(options);
    }
    
    public int init() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.print("(GS25 - 1번) 링크 번호 입력 : ");
    	int url_num = scanner.nextInt();
    	if (url_num == 1)
    	{
    		url = "http://gs25.gsretail.com/gscvs/ko/products/event-goods";
    	}
    	// GS25 행사 상품 url
    	// http://gs25.gsretail.com/gscvs/ko/products/event-goods
    	
        driver.get(url); // 지정된 URL로 이동
        // implicitly wait - 페이지 로딩까지 기다림
        // explicitly wait - 명시한 부분이 화면에 표시될 때 까지 기다림
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("시작 페이지 로딩 완료");
        
        JavascriptExecutor js = (JavascriptExecutor)driver;
        
        
        
        System.out.print("행사 선택 : ");
    	int eventmenu = scanner.nextInt(); // 어떤 행사를 선택할 지 정함
    	
		if (eventmenu == 0) // 2+1 행사 탭으로 이동
		{
			System.out.println("2 + 1 행사 제품");
			
			js.executeScript("window.scrollBy(0,700)");
			
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
			driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[3]/div/div/ul/li[2]/span/a")).click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		else
		{
			System.out.println("1 + 1 행사 제품");
			eventmenu = 1;
		}
		
    	scanner.close();
    	return eventmenu;
    }
    
    public JSONObject findproduct(int menu, FileWriter writer) {
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	
    	ArrayList<Product> product_data = new ArrayList<>();	
    	
    	WebElement product_container = driver.findElement(By.cssSelector(".prod_list"));
    	List<WebElement> product_detail = product_container.findElements(By.cssSelector(".prod_box"));
    	String previous_temp_name = "이전 페이지 1번 제품"; // 마지막 페이지 목록에서 10개의 페이지가 있을 경우에는 별도로 예외처리를 해줘야 함 => 버튼의 개수가 똑같기 때문에 오류를 받을 수 없기 때문
    	String next_temp_name = "다음 페이지 1번 제품";
    	String name = "제품 명 초기화";
    	JSONObject all_products = new JSONObject();
    	JSONArray productArray = new JSONArray();
    	int next_page_count = 0;
    	int page_num = 1;
    	
    	if (menu == 0) // 2 + 1 행사
    	{
        	js.executeScript("window.scrollBy(0,500)");
    		try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    		while(true) {
//    			JSONArray productArray = new JSONArray();
            	for(int i = 1; i < product_detail.size() + 1; i++)
            	{	
            		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            		String company = GS25_company;
            		next_temp_name = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[2]/ul/li[1]/div/p[2]")).getText();
//            		System.out.println("이전 페이지 제품 명: " + previous_temp_name);
//            		System.out.println("다음 페이지 제품 명: " + next_temp_name);
            		if(previous_temp_name == next_temp_name)
            		{
            			next_page_count = 1;
            			System.out.println("데이터 수집 종료");
            			break;
            		}
            		
               		try
               		{
               			name = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[2]/ul/li[" + (i) + "]/div/p[2]")).getText();
               		}
               		catch(Exception e)
               		{
               			next_page_count = 1;
               			System.out.println("데이터 수집 종료");
            			break;
               		}

        			
        			String price = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[2]/ul/li[" + (i) + "]/div/p[3]/span")).getText();
        			String event = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[2]/ul/li[" + (i) + "]/div/div/p/span")).getText();

        			previous_temp_name = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[2]/ul/li[1]/div/p[2]")).getText();
//        			System.out.println("이전 페이지 제품 명: " + previous_temp_name);
        			
        			Product product = new Product(company, name, price, event);
//        			productArray = product.JSONWriter(writer);
        			productArray.add(product.JSONWriter(writer));
//        			all_products.put("전체 제품", product.JSONWriter(writer));
        			System.out.println(product);
        			System.out.println(page_num +  "번 페이지 - " + (i)+". "+ name + " (" + price + ")" + " (" + event + " 행사)");
        			
        			String jsonInfo = productArray.toJSONString();
               	 
//                    System.out.println(jsonInfo);
        			product_data.add(product);
            	}
            	
            	
        		if (next_page_count == 1)
        		{
        			all_products.put("전체 제품", productArray);
        			return all_products;
        		}
    			page_num += 1;
    			System.out.println();
    			
    			next_page(menu);
    		}

    	}
    	else if(menu == 1) // 1 + 1 행사
    	{
    		js.executeScript("window.scrollBy(0,1400)");
    		try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        	while(true)
        	{
        		for(int i = 1; i < product_detail.size() + 1; i++)
            	{	
//        			JSONArray productArray = new JSONArray();
            		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            		String company = GS25_company;
            		next_temp_name = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[1]/ul/li[1]/div/p[2]")).getText();
               		if(previous_temp_name == next_temp_name)
            		{
            			next_page_count = 1;
            			System.out.println("데이터 수집 종료");
            			break;
            		}
               		try
               		{
               			name = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[1]/ul/li[" + (i) + "]/div/p[2]")).getText();
               		}
               		catch(Exception e)
               		{
               			next_page_count = 1;
               			System.out.println("데이터 수집 종료");
            			break;
               		}
        			String price = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[1]/ul/li[" + (i) + "]/div/p[3]/span")).getText();
        			String event = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[1]/ul/li[" + (i) + "]/div/div/p/span")).getText();
        			
        			previous_temp_name = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[1]/ul/li[1]/div/p[2]")).getText();
        			
        			Product product = new Product(company, name, price, event);
        			
//        			productArray = product.JSONWriter(writer);
        			productArray.add(product.JSONWriter(writer));
//        			all_products.put("전체 제품", product.JSONWriter(writer));
        			System.out.println(product);
        			System.out.println(page_num +  "번 페이지 - " + (i)+". "+ name + " (" + price + ")" + " (" + event + " 행사)");
        			
        			String jsonInfo = productArray.toJSONString();
        			product_data.add(product);
//        			System.out.println(jsonInfo);
        			
            	}
        		
        		
        		if (next_page_count == 1)
        		{
        			all_products.put("전체 제품", productArray);
        			return all_products;
        		}
    			page_num += 1;
    			System.out.println();
    			
    			next_page(menu);
        	}
    	}
    	return all_products;
    }
    
    public void next_page(int menu) {
    	if(menu == 0)
    	{
        	try
        	{
        		driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[2]/div/a[3]")).click();
        	}
        	catch(Exception e)
        	{
        		e.printStackTrace();
        		System.out.println("데이터 수집 종료");
        	}
    	}
    	else if(menu == 1)
    	{
        	try
        	{
        		driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[1]/div/a[3]")).click();
        	}
        	catch(Exception e)
        	{
        		e.printStackTrace();
        		System.out.println("데이터 수집 종료");
        	}
    	}
    	
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	
    }
//    
//    public void JSONWriter(String name, String company, String price, String event, FileWriter writer) {
//    	JSONObject product_info = new JSONObject();
//		product_info.put("제품 명", name);
//		product_info.put("회사 명", company);
//		product_info.put("가격", price);
//		product_info.put("행사 정보", event);
//		try(writer.write(product_info.toJSONString()))
//		{
//			System.out.println(product_info.toJSONString());
//		}
//		catch(IOException e) {
//			e.printStackTrace();
//		}
//		writer.write(product_info.toJSONString());
//		System.out.println(product_info.toJSONString());
//
//    }
    

	public static void main(String[] args) {
		Crawling_GS crawl = new Crawling_GS(); // 드라이버 세팅
		int menu = crawl.init(); // url 입력 함수
		if(menu == 0)
		{
			try(FileWriter writer = new FileWriter("src/crawling/gs25data_event2+1.json"))
			{
//				JSONArray product_Array = crawl.findproduct(menu, writer);
				JSONObject all_products = crawl.findproduct(menu, writer);
				writer.write(all_products.toJSONString());
				writer.flush();
				writer.close();
				System.out.println(all_products.toJSONString());
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(menu == 1)
		{
			try(FileWriter writer = new FileWriter("src/crawling/gs25data_event1+1.json"))
			{
//				JSONArray product_Array = crawl.findproduct(menu, writer);
				JSONObject all_products = crawl.findproduct(menu, writer);
				writer.write(all_products.toJSONString());
				writer.flush();
				writer.close();
				System.out.println(all_products.toJSONString());
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
//		try(FileWriter writer = new FileWriter("src/crawling/gs25data_event1.json"))
//		{
////			JSONArray product_Array = crawl.findproduct(menu, writer);
//			JSONObject all_products = crawl.findproduct(menu, writer);
//			writer.write(all_products.toJSONString());
//			writer.flush();
//			writer.close();
//			System.out.println(all_products.toJSONString());
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		driver.quit(); // 모든 탭 종료
		// close()는 여러 탭을 쓸 때 하나의 탭만 종료 - close로 모든 탭을 닫아도 process는 살아있음
	}
}


