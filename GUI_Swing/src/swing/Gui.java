package swing;


import java.awt.*; // 그래픽 처리를 위한 클래스들의 경로명
import java.awt.event.*; // AWT 이벤트 사용을 위한 경로명
import javax.swing.*; // 스윙 컴포넌트 클래스들의 경로명
import javax.swing.event.*; // 스윙 이벤트를 위한 경로명


public class Gui extends JFrame{
		private JTextField searchField;

		public Gui() { 
			setTitle("편의점 행사 알리미");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//프레임 윈도우 닫으면 프로그램 종료
			setLayout(null); // 레이아웃 매니저 해제			
			
			//Container contentPane = getContentPane();//컨텐트팬을 알아낸다.`

			// 레이블 추가
			JLabel label = new JLabel("편의점 할인 행사 알림이");
			label.setBounds(250, 10, 200, 30);
	        add(label);
			
	        
	        searchField = new JTextField();
	        searchField.setBounds(50, 50, 350, 30);
	        add(searchField);

	        JButton searchButton = new JButton("검색");
	        searchButton.setBounds(410, 50, 80, 30);
	        add(searchButton);
	        
	        // 사진 경로 설정
	        ImageIcon gsIcon = new ImageIcon("C:\\Users\\alscj\\OneDrive\\바탕 화면\\GUI_Swing\\GUI_Swing\\Icon\\GS1.png");
	        ImageIcon sevenIcon = new ImageIcon("C:\\Users\\alscj\\OneDrive\\바탕 화면\\GUI_Swing\\GUI_Swing\\Icon\\SevenEleven1.png");
	        ImageIcon cuIcon = new ImageIcon("C:\\Users\\alscj\\OneDrive\\바탕 화면\\GUI_Swing\\GUI_Swing\\Icon\\CU1.png");
	        
	        // 버튼 생성
	        JButton GS_button = new JButton(gsIcon);
	        JButton Seven_button = new JButton(sevenIcon);
	        JButton CU_button = new JButton(cuIcon);
	        
	        
	        // 버튼 크기랑 위치 설정(x좌표, y좌표, 넓이, 높이)
	        GS_button.setBounds(50, 100, 150, 50);
	        Seven_button.setBounds(250, 100, 150, 50);
	        CU_button.setBounds(450, 100, 150, 50);

	        //버튼 생성
	        add(GS_button);
	        add(Seven_button);
	        add(CU_button);
	        
	     // 버튼에 액션 리스너 추가 (버튼 클릭 동작 설정)
	        GS_button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	// 이전 창 숨기기
	                setVisible(false);
	            	GS_Event gs_event = new GS_Event();
	            	gs_event.setVisible(true);
	            
	            }
	        });

	        Seven_button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	// 이전 창 숨기기
	                setVisible(false);
	            	Seven_Event seven_event = new Seven_Event();
	            	seven_event.setVisible(true);
	            }
	        });

	        CU_button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	// 이전 창 숨기기
	                setVisible(false);
	            	CU_Event cu_event = new CU_Event();
	            	cu_event.setVisible(true);
	            }
	        });
	        
	        
	        searchButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // 검색 버튼 눌렀을 때 여기에 검색 기능을 추가할 코드 넣어야 함
	                String searchQuery = searchField.getText();
	                // 여기에 검색 기능을 구현해야함. 검색 결과에 따른 새 창을 열거임

	            }
	        });


	        // 창 사이즈 설정
			setSize(650,300);
			
			setVisible(true);
		}
	
		
		class GS_Event extends JFrame {
		    public GS_Event() {
		        setTitle("GS25 할인행사 목록");
		        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창을 닫을 때만 닫히도록 설정
		        setLayout(null);

		        // 원하는 내용을 새 창에 추가
		        JLabel newLabel = new JLabel("GS25");
		        newLabel.setBounds(10, 10, 200, 30);
		        add(newLabel);
		        
		        JButton backButton = new JButton("뒤로");
	            backButton.setBounds(10, 50, 60, 30);
	            add(backButton);

	            backButton.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    setVisible(false);
	                    Gui.this.setVisible(true); // 이전 페이지로 돌아가기
	                }
	            });

		        setSize(650, 300);
		    }
		}
		
		class Seven_Event extends JFrame {
		    public Seven_Event() {
		        setTitle("7_Eleven 할인행사 목록");
		        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창을 닫을 때만 닫히도록 설정
		        setLayout(null);

		        // 원하는 내용을 새 창에 추가
		        JLabel newLabel = new JLabel("7_Eleven");
		        newLabel.setBounds(10, 10, 200, 30);
		        add(newLabel);
		        
		        JButton backButton = new JButton("뒤로");
	            backButton.setBounds(10, 50, 60, 30);
	            add(backButton);

	            backButton.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    setVisible(false);
	                    Gui.this.setVisible(true); // 이전 페이지로 돌아가기
	                }
	            });

		        setSize(650,300);
		    }
		}
		
		class CU_Event extends JFrame {
		    public CU_Event() {
		        setTitle("CU 할인행사 목록");
		        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창을 닫을 때만 닫히도록 설정
		        setLayout(null);

		        // 원하는 내용을 새 창에 추가
		        JLabel newLabel = new JLabel("CU");
		        newLabel.setBounds(10, 10, 200, 30);
		        add(newLabel);
		        
		        JButton backButton = new JButton("뒤로");
	            backButton.setBounds(10, 50, 60, 30);
	            add(backButton);

	            backButton.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    setVisible(false);
	                    Gui.this.setVisible(true); // 뒤로가기
	                }
	            });
	            
		        setSize(650,300);
		    }
		}
		
	/*	
		class Back_Button extends JFrame {
		    public Back_Button() {
		    	
		            }

		}
	*/
	public static void main(String[] args) {
		Gui app = new Gui();

		}

}

