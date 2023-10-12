/*
 * package crawling;
 * 
 * import org.openqa.selenium.WebDriver; import
 * org.openqa.selenium.chrome.ChromeDriver; import
 * org.openqa.selenium.chrome.ChromeOptions; import
 * org.openqa.selenium.WebElement; import org.openqa.selenium.Keys; import
 * java.time.Duration; import org.openqa.selenium.By; import java.util.Scanner;
 * import org.openqa.selenium.JavascriptExecutor; import java.util.ArrayList;
 * import java.util.List; //import org.openqa.selenium.interactions.Actions;
 * //import org.openqa.selenium.support.ui.ExpectedConditions;
 * 
 * public class Crawling_GS {
 * 
 * private static WebDriver driver; // private WebElement element; private
 * String url; // 크롬 드라이버 위치 // static => 모든 객체가 공유 => 다른 페이지 크롤링을 해당 클래스에서 한다면
 * 사용 public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
 * public static final String WEB_DRIVER_PATH =
 * "C:\\chromedriver_win32\\chromedriver.exe";
 * 
 * private final String GS25_company = "GS25";
 * 
 * public Crawling_GS() { try { System.setProperty(WEB_DRIVER_ID,
 * WEB_DRIVER_PATH); } catch (Exception e) { e.printStackTrace(); }
 * 
 * ChromeOptions options = new ChromeOptions();
 * options.addArguments("--start-maximized"); // 전체화면으로 실행
 * options.addArguments("--disable-popup-blocking"); // 팝업 무시
 * options.addArguments("--remote-allow-origins=*");
 * 
 * driver = new ChromeDriver(options); }
 * 
 * public int init() { Scanner scanner = new Scanner(System.in);
 * System.out.print("(GS25 - 1번) 링크 번호 입력 : "); int url_num = scanner.nextInt();
 * if (url_num == 1) { url =
 * "http://gs25.gsretail.com/gscvs/ko/products/event-goods"; } // GS25 행사 상품 url
 * // http://gs25.gsretail.com/gscvs/ko/products/event-goods
 * 
 * driver.get(url); // 지정된 URL로 이동 // implicitly wait - 페이지 로딩까지 기다림 //
 * explicitly wait - 명시한 부분이 화면에 표시될 때 까지 기다림
 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
 * System.out.println("시작 페이지 로딩 완료");
 * 
 * JavascriptExecutor js = (JavascriptExecutor)driver;
 * 
 * 
 * 
 * System.out.print("행사 선택 : "); int eventmenu = scanner.nextInt(); // 어떤 행사를
 * 선택할 지 정함
 * 
 * if (eventmenu == 0) // 2+1 행사 탭으로 이동 { System.out.println("2 + 1 행사 제품");
 * 
 * js.executeScript("window.scrollBy(0,700)");
 * 
 * try { Thread.sleep(1000); } catch (InterruptedException e) {
 * e.printStackTrace(); }
 * 
 * driver.findElement(By.xpath(
 * "/html/body/div[1]/div[4]/div[2]/div[3]/div/div/ul/li[2]/span/a")).click();
 * try { Thread.sleep(5000); } catch (InterruptedException e) {
 * e.printStackTrace(); }
 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); } else {
 * System.out.println("1 + 1 행사 제품"); eventmenu = 1; }
 * 
 * scanner.close(); return eventmenu; }
 * 
 * public void findproduct(int menu) { JavascriptExecutor js =
 * (JavascriptExecutor)driver;
 * 
 * ArrayList<Product> product_data = new ArrayList<>();
 * 
 * WebElement product_container =
 * driver.findElement(By.cssSelector(".prod_list")); List<WebElement>
 * product_detail = product_container.findElements(By.cssSelector(".prod_box"));
 * String previous_temp_name = "이전 페이지 1번 제품"; // 마지막 페이지 목록에서 10개의 페이지가 있을 경우에는
 * 별도로 예외처리를 해줘야 함 => 버튼의 개수가 똑같기 때문에 오류를 받을 수 없기 때문 String next_temp_name =
 * "다음 페이지 1번 제품"; String name = "제품 명 초기화"; int next_page_count = 0; int
 * page_num = 1;
 * 
 * if (menu == 0) // 2 + 1 행사 { js.executeScript("window.scrollBy(0,500)"); try
 * { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace();
 * } while(true) { for(int i = 1; i < product_detail.size() + 1; i++) {
 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); String
 * company = GS25_company; next_temp_name = driver.findElement(By.xpath(
 * "/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[2]/ul/li[1]/div/p[2]")).
 * getText(); System.out.println("이전 페이지 제품 명: " + previous_temp_name);
 * System.out.println("다음 페이지 제품 명: " + next_temp_name); if(previous_temp_name
 * == next_temp_name) { next_page_count = 1; System.out.println("데이터 수집 종료");
 * break; }
 * 
 * try { name = driver.findElement(By.xpath(
 * "/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[2]/ul/li[" + (i) +
 * "]/div/p[2]")).getText(); } catch(Exception e) { next_page_count = 1;
 * System.out.println("데이터 수집 종료"); break; }
 * 
 * 
 * String price = driver.findElement(By.xpath(
 * "/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[2]/ul/li[" + (i) +
 * "]/div/p[3]/span")).getText(); String event = driver.findElement(By.xpath(
 * "/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[2]/ul/li[" + (i) +
 * "]/div/div/p/span")).getText();
 * 
 * previous_temp_name = driver.findElement(By.xpath(
 * "/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[2]/ul/li[1]/div/p[2]")).
 * getText(); System.out.println("이전 페이지 제품 명: " + previous_temp_name);
 * 
 * Product product = new Product(company, name, price, event);
 * 
 * System.out.println(page_num + "번 페이지 - " + (i)+". "+ name + " (" + price +
 * ")" + " (" + event + " 행사)");
 * 
 * product_data.add(product); }
 * 
 * if (next_page_count == 1) { break; } page_num += 1; System.out.println();
 * 
 * next_page(menu); }
 * 
 * } else if(menu == 1) // 1 + 1 행사 {
 * js.executeScript("window.scrollBy(0,1400)"); try { Thread.sleep(2000); }
 * catch (InterruptedException e) { e.printStackTrace(); } while(true) { for(int
 * i = 1; i < product_detail.size() + 1; i++) {
 * 
 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); String
 * company = GS25_company; next_temp_name = driver.findElement(By.xpath(
 * "/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[1]/ul/li[1]/div/p[2]")).
 * getText(); if(previous_temp_name == next_temp_name) { next_page_count = 1;
 * System.out.println("데이터 수집 종료"); break; } try { name =
 * driver.findElement(By.xpath(
 * "/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[1]/ul/li[" + (i) +
 * "]/div/p[2]")).getText(); } catch(Exception e) { next_page_count = 1;
 * System.out.println("데이터 수집 종료"); break; } String price =
 * driver.findElement(By.xpath(
 * "/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[1]/ul/li[" + (i) +
 * "]/div/p[3]/span")).getText(); String event = driver.findElement(By.xpath(
 * "/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[1]/ul/li[" + (i) +
 * "]/div/div/p/span")).getText();
 * 
 * previous_temp_name = driver.findElement(By.xpath(
 * "/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[1]/ul/li[1]/div/p[2]")).
 * getText();
 * 
 * Product product = new Product(company, name, price, event);
 * 
 * System.out.println(page_num + "번 페이지 - " + (i)+". "+ name + " (" + price +
 * ")" + " (" + event + " 행사)"); product_data.add(product);
 * 
 * }
 * 
 * if (next_page_count == 1) { break; } page_num += 1; System.out.println();
 * 
 * next_page(menu); } } }
 * 
 * public void next_page(int menu) { if(menu == 0) { try {
 * driver.findElement(By.xpath(
 * "/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[2]/div/a[3]")).click(); }
 * catch(Exception e) { e.printStackTrace(); System.out.println("데이터 수집 종료"); }
 * } else if(menu == 1) { try { driver.findElement(By.xpath(
 * "/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[1]/div/a[3]")).click(); }
 * catch(Exception e) { e.printStackTrace(); System.out.println("데이터 수집 종료"); }
 * }
 * 
 * try { Thread.sleep(2000); } catch (InterruptedException e) {
 * e.printStackTrace(); }
 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
 * 
 * }
 * 
 * 
 * public static void main(String[] args) { Crawling_GS crawl = new
 * Crawling_GS(); // 드라이버 세팅 int menu = crawl.init(); // url 입력 함수
 * crawl.findproduct(menu);
 * 
 * driver.quit(); // 모든 탭 종료 // close()는 여러 탭을 쓸 때 하나의 탭만 종료 - close로 모든 탭을 닫아도
 * process는 살아있음 } }
 * 
 * 
 */

package Java_Team;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import java.time.Duration;
import org.openqa.selenium.By;
import java.util.Scanner;
import org.openqa.selenium.JavascriptExecutor;
import java.util.ArrayList;
import java.util.List;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Crawling_GS {

   private static WebDriver driver;
//   private WebElement element;
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
    
    public void findproduct(int menu) {
    	Connection con = null;
        PreparedStatement pstmt = null;
        
        
        String d = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String userid = "csv";
        String passwd = "1234";

        String SQL = "insert into 원플원행사(회사명, 제품명, 가격, 행사구분) values(?, ?, ?, ?)";
    	
       JavascriptExecutor js = (JavascriptExecutor)driver;
       
       ArrayList<Product> product_data = new ArrayList<>();   
//       WebElement product_container = driver.findElement(By.cssSelector(".prod_list"));
//       List<WebElement> product_detail = product_container.findElements(By.cssSelector(".prod_box"));
//       
       if (menu == 0) // 2 + 1 행사
       {
           WebElement product_container = driver.findElement(By.cssSelector(".prod_list"));
           List<WebElement> product_detail = product_container.findElements(By.cssSelector(".prod_box"));
           js.executeScript("window.scrollBy(0,500)");
           for(int i = 1; i < product_detail.size() + 1; i++)
           {   
              driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
              String company = GS25_company;
              
             String name = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[2]/ul/li[" + (i) + "]/div/p[2]")).getText();
             // /html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[2]/ul/li[1]/div/p[2]
             // /html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[2]/ul/li[5]/div/p[2]
             // /html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[2]/ul/li[2]/div/p[2]
             // /html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[2]/ul/li[5]/div/p[2]
             //*[@id="wrap"]/div[4]/div[2]/div[3]/div/div/div[1]/ul/li[1]/div/p[2]
             String price = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[2]/ul/li[" + (i) + "]/div/p[3]/span")).getText();
             //*[@id="wrap"]/div[4]/div[2]/div[3]/div/div/div[1]/ul/li[2]/div/p[3]/span
             String event = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[2]/ul/li[" + (i) + "]/div/div/p/span")).getText();
             //*[@id="wrap"]/div[4]/div[2]/div[3]/div/div/div[1]/ul/li[2]/div/div/p
             
             Product product = new Product(company, name, price, event);
             
             System.out.println((i)+". "+ name + " (" + price + ")" + " (" + event + ")");
             
             product_data.add(product);
             
             try {
                 // 1. JDBC 드라이버 로딩 - MySQL JDBC 드라이버의 Driver Class 로딩
                 Class.forName(d);

                 // 2. Connection 생성 - .getConnection(연결문자열, DB-ID, DB-PW)
                 con = DriverManager.getConnection(url, userid, passwd);

                 // 3. PreParedStatement 객체 생성, 객체 생성시 SQL 문장 저장
                 pstmt = con.prepareStatement(SQL);

                 // 4. pstmt.set<데이터타입>(? 순서, 값) ex).setString(), .setInt ...
                 pstmt.setString(1, company);
                 pstmt.setString(2, name);
                 pstmt.setString(3, price);
                 pstmt.setString(4, event);

                 // 5. SQL 문장을 실행하고 결과를 리턴 - SQL 문장 실행 후, 변경된 row 수 int type 리턴
                 int r = pstmt.executeUpdate();

                 // pstmt.excuteQuery() : select
                 // pstmt.excuteUpdate() : insert, update, delete ..

                 System.out.println("변경된 row : " + r);

             } catch (SQLException e) {

                 System.out.println("[SQL Error : " + e.getMessage() + "]");

             } catch (ClassNotFoundException e1) {

                 System.out.println("[JDBC Connector Driver 오류 : " + e1.getMessage() + "]");

             } finally {

                 //사용순서와 반대로 close 함
                 if (pstmt != null) {
                     try {
                         pstmt.close();
                     } catch (SQLException e) {
                         e.printStackTrace();
                     }
                 }

                 if (con != null) {
                     try {
                         con.close();
                     } catch (SQLException e) {
                         e.printStackTrace();
                     }
                 }
             }
           }
//          // 화면 스크롤
//          js.executeScript("window.scrollBy(0,700)");
//          try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
       }
       else if(menu == 1) // 1 + 1 행사
       {
          js.executeScript("window.scrollBy(0,1400)");
          try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           WebElement product_container = driver.findElement(By.cssSelector(".prod_list"));
           List<WebElement> product_detail = product_container.findElements(By.cssSelector(".prod_box"));
           
           for(int i = 1; i < product_detail.size() + 1; i++)
           {   
              driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
              String company = GS25_company;
             String name = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[1]/ul/li[" + (i) + "]/div/p[2]")).getText();
             String price = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[1]/ul/li[" + (i) + "]/div/p[3]/span")).getText();
             String event = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[3]/div/div/div[1]/ul/li[" + (i) + "]/div/div/p/span")).getText();
             
             Product product = new Product(company, name, price, event);
             
             System.out.println((i)+". "+ name + " (" + price + ")" + " (" + event + ")");
             
             product_data.add(product);
             
             try {
                 // 1. JDBC 드라이버 로딩 - MySQL JDBC 드라이버의 Driver Class 로딩
                 Class.forName(d);

                 // 2. Connection 생성 - .getConnection(연결문자열, DB-ID, DB-PW)
                 con = DriverManager.getConnection(url, userid, passwd);

                 // 3. PreParedStatement 객체 생성, 객체 생성시 SQL 문장 저장
                 pstmt = con.prepareStatement(SQL);

                 // 4. pstmt.set<데이터타입>(? 순서, 값) ex).setString(), .setInt ...
                 pstmt.setString(1, company);
                 pstmt.setString(2, name);
                 pstmt.setString(3, price);
                 pstmt.setString(4, event);

                 // 5. SQL 문장을 실행하고 결과를 리턴 - SQL 문장 실행 후, 변경된 row 수 int type 리턴
                 int r = pstmt.executeUpdate();

                 // pstmt.excuteQuery() : select
                 // pstmt.excuteUpdate() : insert, update, delete ..

                 System.out.println("변경된 row : " + r);

             } catch (SQLException e) {

                 System.out.println("[SQL Error : " + e.getMessage() + "]");

             } catch (ClassNotFoundException e1) {

                 System.out.println("[JDBC Connector Driver 오류 : " + e1.getMessage() + "]");

             } finally {

                 //사용순서와 반대로 close 함
                 if (pstmt != null) {
                     try {
                         pstmt.close();
                     } catch (SQLException e) {
                         e.printStackTrace();
                     }
                 }

                 if (con != null) {
                     try {
                         con.close();
                     } catch (SQLException e) {
                         e.printStackTrace();
                     }
                 }
             }
           }
       }
    }

   public static void main(String[] args) {
	   
	  Crawling_GS crawl = new Crawling_GS(); // 드라이버 세팅
      int menu = crawl.init(); // url 입력 함수
      crawl.findproduct(menu);
      
      driver.quit(); // 모든 탭 종료
      // close()는 여러 탭을 쓸 때 하나의 탭만 종료 - close로 모든 탭을 닫아도 process는 살아있음
   }
}

