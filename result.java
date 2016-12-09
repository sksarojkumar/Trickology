import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

class result extends JFrame implements KeyListener 
{
	JLabel hed,msg,right,wrong,winname,ecap,epass;
	JPasswordField etxtpass;
	JPanel exit;
	PreparedStatement st=null,ss=null;
	ResultSet rs=null,rsc=null;
	Connection conn=null;
	String un;
	int m=1,m1=2,cans=0,wans=0;
	float score=0;
	//To know screensize
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = screenSize.width, height = screenSize.height;
	Font fontt = null, fontb2 = null, fontb3 = null, fontd = null;
	//*********
	
	result(String u)throws IOException
	{
		un=u;
		calculation();
		try{
		InputStream si = this.getClass().getResourceAsStream("font/DK Cool Crayon.ttf");
		fontt = Font.createFont(Font.TRUETYPE_FONT,si).deriveFont(Font.BOLD,(width/27));
		
		InputStream iss = this.getClass().getResourceAsStream("font/DK Cool Crayon.ttf");
		fontd = Font.createFont(Font.TRUETYPE_FONT,iss).deriveFont(Font.BOLD,(height/32));
		
		InputStream isss = this.getClass().getResourceAsStream("font/Black Crayon.ttf");
		fontb2 = Font.createFont(Font.TRUETYPE_FONT,isss).deriveFont(Font.BOLD,(height/26));
		
		InputStream issss = this.getClass().getResourceAsStream("font/Black Crayon.ttf");
		fontb3 = Font.createFont(Font.TRUETYPE_FONT,issss).deriveFont(Font.BOLD,(height/32));
		}
		catch(Exception e)
		{
			System.out.println("font problem1: "+e);
		}
		
		winname=new JLabel("<html><font color=#9BE564>TRICK</font><font color=#23B5D3>O</font><font color=#D7F75B>LOGY</font></html>");
		winname.setFont(fontt);
		winname.setBounds((width/3)+85, (height/13)+60, width/3, height/13);
		
		hed=new JLabel(un);
		hed.setFont(fontd);
		hed.setHorizontalAlignment(SwingConstants.CENTER);
		hed.setForeground(Color.white);
		hed.setBounds((height/2)+170,(height/4), (width/5),(height/12)-24);
		
		exit=new JPanel(new GridLayout(3,3));
		exit.setBorder(BorderFactory.createTitledBorder("ENTER PASSWORD TO EXIT"));
		exit.setBackground(new Color(163,154,146));
		//exit.setOpaque(false);
		epass=new JLabel("              ***PASSWORD***");
		epass.setForeground(Color.blue);
		etxtpass=new JPasswordField(20);
		ecap=new JLabel();
		ecap.setForeground(Color.red);
		
		exit.setBounds(width-220,(height/2)+280,(height/4)+8,(height/8)+4);
		exit.add(epass,BorderLayout.CENTER);
		exit.add(etxtpass,BorderLayout.CENTER);
		exit.add(ecap,BorderLayout.CENTER);
		etxtpass.addKeyListener(this);
		
		
			//total score..
			msg=new JLabel("Your total Score: "+score);
			msg.setFont(fontb2);
			msg.setForeground(Color.white);
			msg.setBounds((height/2)+156,(height/3)+4,(height+132),(height/12)-4);	
			
			right=new JLabel("Right Answers: "+cans);
			right.setFont(fontb3);
			right.setForeground(Color.white);
			right.setBounds((height/6)+300,(height/2)-20,(height+32),(height/12)-4);
			
			wrong=new JLabel("Wrong Answers: "+wans);
			wrong.setFont(fontb3);
			wrong.setForeground(Color.white);
			wrong.setBounds((height-60),(height/2)-20,(height+32),(height/12)-4);
		add(winname);
		add(hed);
		add(msg);
		add(right);
		add(wrong);
		add(exit);
		
		
		//creation of frame and background..
		displayBackground();
		Container c=this.getContentPane();
		c.setLayout(null);
		setTitle("TECHNOSHINE X.6");
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setAlwaysOnTop(true);
		//setSize(width,height);
		pack();
		setVisible(true);
	}
	//open fullscreen background image************
	
		public static BufferedImage resize(BufferedImage image, int width, int height) 
		{

			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
			Graphics2D g2d = (Graphics2D) bi.createGraphics();
			g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
			g2d.drawImage(image, 0, 0, width, height, null);
			g2d.dispose();
			return bi;

		}

		public void displayBackground() throws IOException
		{
			try{
			JPanel pBackground = new JPanel();
			pBackground.setSize(screenSize);
			pBackground.setLayout(new FlowLayout());
			add(pBackground);

			BufferedImage header = ImageIO.read(getClass().getResource("pics/Scoreboard.jpg"));
			BufferedImage resizedImage = resize(header,width,height);
			ImageIcon image = new ImageIcon(resizedImage);
			JLabel lblheader = new JLabel(image, JLabel.CENTER);
			pBackground.add(lblheader);
			}
			catch(Exception e)
			{
				System.out.println("pics not found: "+e);
			}
		}
		//close fullscreen background image**************
		
		public void keyPressed(KeyEvent ke)
		{
			int keycode = ke.getKeyCode();
			if(keycode == KeyEvent.VK_ENTER)
			{
				try
				{
					/*Class.forName("oracle.jdbc.driver.OracleDriver");
					//conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
					conn=DriverManager.getConnection("jdbc:oracle:thin:@SAROJ:1521:XE","system","manager");
					
					eca=conn.prepareStatement("select password from login where username = ?");
					eca.setString(1,un);*/
					String ps=new String(etxtpass.getPassword());
					//reca=eca.executeQuery();
					//reca.next();
					String adminps="cad614";
					if(ps.equals(adminps))
					{
						//eca.close();
						//reca.close();
						//conn.close();
						System.exit(0);
					}
					else
					{
						ecap.setText("Invalid Password");
						etxtpass.setText("");
						//conn.close();
					}	
				}
				catch(Exception e)
				{}
			}
		}
		public void keyReleased(KeyEvent ke)
		{}
		public void keyTyped(KeyEvent ke)
		{}
		public void calculation()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@SAROJ:1521:XE","system","manager");
			while(m<=30)
			{
				st=conn.prepareStatement("select answer from mcq where qno = ?");
				st.setInt(1,m);
				rs=st.executeQuery();
				rs.next();
				st=conn.prepareStatement("select * from user_record where user_name = ?");				
				st.setString(1,un);
				rsc=st.executeQuery();
				rsc.next();
				if(rs.getString(1).equals(rsc.getString(m1)))
				{
					score+=5;
					cans++;
				}
				else if(rsc.wasNull())
				{
					//nothing to do....
				}
				else
				{
					score-=2;
					wans++;
				}
					m++;
					m1++;
			}
		
				ss=conn.prepareStatement("update user_record set score = ? where user_name = ?");;
				ss.setFloat(1,score);
				ss.setString(2,un);
				ss.executeUpdate();
				
				ss=conn.prepareStatement("update user_record set cans = ? where user_name = ?");;
				ss.setFloat(1,cans);
				ss.setString(2,un);
				ss.executeUpdate();
				
				ss=conn.prepareStatement("update user_record set wans = ? where user_name = ?");;
				ss.setFloat(1,wans);
				ss.setString(2,un);
				ss.executeUpdate();
			
				st.close();
				ss.close();
				rs.close();
				rsc.close();
				conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}