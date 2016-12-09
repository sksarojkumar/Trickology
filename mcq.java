import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

class mcq extends JFrame implements ActionListener, KeyListener
{ 
	JLabel h,name,timer,pass,cap,clock;
	JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21,b22,b23,b24,b25,b26,b27,b28,b29,b30;
	JRadioButton rb1,rb2,rb3,rb4;
	ButtonGroup bg,qg;
	JTextArea question;
	JPasswordField txtpass;
	JButton prev,lock,next;
	JPanel submit;
	PreparedStatement st=null,sl=null,ss=null,ssp=null,cs=null,ca=null;
	ResultSet rs=null,rs1=null,rsc=null,rscp=null,rca=null;
	Connection conn=null;
	String un;
	int m=1,m1=2,cans=0,wans=0;
	float score=0;
	static  int n=1,bl=0,uc=0,bc1=0,bc2=0,bc3=0,bc4=0,bc5=0,bc6=0,bc7=0,bc8=0,bc9=0,bc10=0,bc11=0,bc12=0,bc13=0,bc14=0,bc15=0,bc16=0,bc17=0,bc18=0,bc19=0,bc20=0,bc21=0,bc22=0,bc23=0,bc24=0,bc25=0,bc26=0,bc27=0,bc28=0,bc29=0,bc30=0;
	Color[] colors = new Color[]{Color.white, Color.blue, Color.green};
	//To know screensize
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = screenSize.width, height = screenSize.height;
	Font font = null;
	//*********
	//Container c=this.getContentPane();
	mcq(String uname)throws IOException
	{	
		un=uname;
		try{
		InputStream is = this.getClass().getResourceAsStream("font/Cartoon Blocks Christmas.ttf");
		font = Font.createFont(Font.TRUETYPE_FONT,is).deriveFont(Font.BOLD,(width/27));
		}
		catch(Exception e)
		{
			System.out.println("font problem: "+e);
		}
		//h=new JLabel("<html><font color=#D90368>TRICK</font><font color='maroon'>O</font><font color=#820263>LOGY</font></html>");
		h=new JLabel("TRICKOLOGY");
		h.setFont(font);
		h.setBounds((width/6), (height/30)+70, width/3, height/10);
		//h.setBounds((width/2)-133, (height/30), (width/4)-41, (height/12));
		name=new JLabel("Welcome  "+uname);
		name.setFont(new Font("Century",Font.BOLD,(width/68)));
		name.setForeground(new Color(17, 78,95));
		name.setBounds((width-480), (height/30)+50, (width/4)-41, (height/15)-1);
	
		
	
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setOpaque(false);
		jp.setBounds((width/4)+25, (height/12)+50, (width-400), (height-140));
		question=new JTextArea();
		question.setFont(new Font("Century",Font.BOLD,(width/68)));
		question.setOpaque(false);
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		question.setEditable(false);
		question.setBounds((width/15), (height/7)-10, (width/2)-33, (height/5)+90);
		//radiobuttons
		rb1=new JRadioButton();
		rb2=new JRadioButton();
		rb3=new JRadioButton();
		rb4=new JRadioButton();
		bg=new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		bg.add(rb3);
		bg.add(rb4);
		rb1.addActionListener(this);
		rb2.addActionListener(this);
		rb3.addActionListener(this);
		rb4.addActionListener(this);
		//buttons
		ImageIcon p=new ImageIcon(getClass().getResource("pics/prevbt.png"));
		prev=new JButton(p);
		prev.setBackground(Color.yellow);
		prev.setBorderPainted(false);
		prev.setOpaque(false);
		prev.setToolTipText("Previous");
		prev.setEnabled(false);
		prev.addActionListener(this);
		ImageIcon pl=new ImageIcon(getClass().getResource("pics/lock.png"));
		lock=new JButton(pl);
		lock.setBackground(Color.yellow);
		lock.setBorderPainted(false);
		lock.setOpaque(false);
		lock.setToolTipText("Lock");
		lock.setEnabled(false);
		lock.addActionListener(this);
		ImageIcon pn=new ImageIcon(getClass().getResource("pics/nextbt.png"));
		next=new JButton(pn);
		next.setBackground(Color.yellow);
		next.setBorderPainted(false);
		next.setOpaque(false);
		next.setToolTipText("Next");
		next.addActionListener(this);
		
		submit=new JPanel(new GridLayout(3,3));
		submit.setBorder(BorderFactory.createTitledBorder("SUBMIT"));
		submit.setOpaque(false);
		pass=new JLabel("              ***PASSWORD***");
		txtpass=new JPasswordField(20);
		cap=new JLabel();
		cap.setForeground(Color.red);
		
		//buttons location in content pane
		rb1.setBounds((width/15), (height/3)+90, (width/6)+150, (height/25));
		rb1.setFont(new Font("Century",Font.BOLD,(width/68)));
		rb1.setOpaque(false);
		rb1.setBorderPainted(false);
		rb1.setFocusPainted(false);
		rb2.setBounds((width/2)-155, (height/3)+90, (width/6)+150, (height/25));
		rb2.setFont(new Font("Century",Font.BOLD,(width/68)));
		rb2.setOpaque(false);
		rb2.setBorderPainted(false);
		rb2.setFocusPainted(false);
		rb3.setBounds((width/15), (height/2)+40, (width/6)+150, (height/25));
		rb3.setFont(new Font("Century",Font.BOLD,(width/68)));
		rb3.setOpaque(false);
		rb3.setBorderPainted(false);
		rb3.setFocusPainted(false);
		rb4.setBounds((width/2)-155, (height/2)+40, (width/6)+150, (height/25));
		rb4.setFont(new Font("Century",Font.BOLD,(width/68)));
		rb4.setOpaque(false);
		rb4.setBorderPainted(false);
		rb4.setFocusPainted(false);
		
		prev.setBounds((height/4),(height/2)+136,(height/9)-1,(height/9)-3);
		
		lock.setBounds((width/4)+12,(height/2)+136,(height/9)-1,(height/9)-3);
		
		next.setBounds((width/3)+65,(height/2)+136,(height/9)-1,(height/9)-3);
		
		submit.setBounds(width-230,(height/2)+280,(height/4)+30,(height/8)+4);
		submit.add(pass,BorderLayout.CENTER);
		submit.add(txtpass,BorderLayout.CENTER);
		submit.add(cap,BorderLayout.CENTER);
		txtpass.addKeyListener(this);
		
		//connect..
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@SAROJ:1521:XE","system","manager");
			//row created with user name if not exits
			cs=conn.prepareStatement("select user_name from user_record");
			rs=cs.executeQuery();
			while(rs.next())
			{
				if(un.equals(rs.getString(1)))
				{
					uc=1;
					break;
				}
			}
			if(uc==0)
			{
				st=conn.prepareStatement("insert into user_record (user_name) values(?)");
				st.setString(1,un);
				//st.setString(2,"NULL");
				st.executeUpdate();
				st.close();
			}
			cs.close();
			rs.close();
			//time display on screen********
			ImageIcon ti = new ImageIcon(getClass().getResource("pics/clock.png"));
			clock = new JLabel(ti);
			clock.setBounds((width-180), (height/8)+30, (width/7)+5, (height/4)+8);
			timer=new JLabel();
			timer.setFont(new Font("Kristen ITC",Font.BOLD,(width/40)-6));
			timer.setForeground(Color.red);
			timer.setBounds((width-120), (height/8)+30, (width/7)+5, (height/4)+8);
			new time(timer,un,this);
			//***********time
			datapos();
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
		//add in frame
		add(h);
		add(name);
		add(timer);
		add(clock);
		add(jp);
		add(submit);
		jp.add(question);
		jp.add(rb1);
		jp.add(rb2);
		jp.add(rb3);
		jp.add(rb4);
		jp.add(prev);
		jp.add(lock);
		jp.add(next);
		
		numberpan();
		lockbuttons();
		startatbutton();
		//actionPerformed......
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		b10.addActionListener(this);
		b11.addActionListener(this);
		b12.addActionListener(this);
		b13.addActionListener(this);
		b14.addActionListener(this);
		b15.addActionListener(this);
		b16.addActionListener(this);
		b17.addActionListener(this);
		b18.addActionListener(this);
		b19.addActionListener(this);
		b20.addActionListener(this);
		b21.addActionListener(this);
		b22.addActionListener(this);
		b23.addActionListener(this);
		b24.addActionListener(this);
		b25.addActionListener(this);
		b26.addActionListener(this);
		b27.addActionListener(this);
		b28.addActionListener(this);
		b29.addActionListener(this);
		b30.addActionListener(this);
		
		//creation of frame and background..
		displayBackground();
		Container c=this.getContentPane();
		c.setLayout(null);
		setTitle("TECHNOSHINE X.6");
		//setSize(400,400);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setAlwaysOnTop(true);
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

			BufferedImage header = ImageIO.read(getClass().getResource("pics/Contest.jpg"));
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
	 void data()
	{
		try
		{
			st=conn.prepareStatement("select * from mcq where qno = ?");
			st.setInt(1,n);
			rs=st.executeQuery();
			if(rs.next())
			{
				question.setText("Question:"+n+"."+rs.getString(2));
				rb1.setText("A. "+rs.getString(3));
				rb2.setText("B. "+rs.getString(4));
				rb3.setText("C. "+rs.getString(5));
				rb4.setText("D. "+rs.getString(6));
			}
				st.close();
				rs.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	void datapos()
	{
		try
		{
			int ck=1;
			
				ssp=conn.prepareStatement("select * from user_record where user_name = ?");
				ssp.setString(1,un);
				rscp=ssp.executeQuery();
				rscp.next();
			while(ck!=0)
			{
				String temp=rscp.getString(n+1);
				if(rscp.wasNull())
					ck=0;
				else
				{
					n++;
					prev.setEnabled(true);
				}
			}
			if(ck==0)
			{
				ssp.close();
				rscp.close();
				data();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	void nextdata()
	{
		try
		{
			n++;
			ss=conn.prepareStatement("select * from user_record where user_name = ?");
			ss.setString(1,un);
			rsc=ss.executeQuery();
			rsc.next();
			String temp=rsc.getString(n+1);
				if(n==30)
					next.setEnabled(false);
				else 
					next.setEnabled(true);
			if(rsc.wasNull())
			{
				lock.setEnabled(false);
				rb1.setEnabled(true);
				rb2.setEnabled(true);
				rb3.setEnabled(true);
				rb4.setEnabled(true);
				rb1.setForeground(Color.darkGray);
				rb2.setForeground(Color.darkGray);
				rb3.setForeground(Color.darkGray);
				rb4.setForeground(Color.darkGray);
				bg.clearSelection();
				prev.setEnabled(true);
				
				ss.close();
				rsc.close();
				btcolor();
			}
			else
			{
				ss.close();
				rsc.close();
				btcolor();
				lock.setEnabled(false);
				prev.setEnabled(true);
				if(temp.equals("A"))
				{
					rb1.setSelected(true);
					rb1.setEnabled(true);
					rb1.setForeground(Color.green);
					rb2.setEnabled(false);
					rb3.setEnabled(false);
					rb4.setEnabled(false);
				}
				else if(temp.equals("B"))
				{
					rb2.setSelected(true);
					rb2.setEnabled(true);
					rb2.setForeground(Color.green);
					rb1.setEnabled(false);
					rb3.setEnabled(false);
					rb4.setEnabled(false);
				}
				else if(temp.equals("C"))
				{
					rb3.setSelected(true);
					rb3.setEnabled(true);
					rb3.setForeground(Color.green);
					rb1.setEnabled(false);
					rb2.setEnabled(false);
					rb4.setEnabled(false);
				}
				else if(temp.equals("D"))
				{
					rb4.setSelected(true);
					rb4.setEnabled(true);
					rb4.setForeground(Color.green);
					rb1.setEnabled(false);
					rb2.setEnabled(false);
					rb3.setEnabled(false);
				}
			}
			if(n==1)
				prev.setEnabled(false);
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	void prevdata()
	{
		lock.setEnabled(false);
		try
		{
			n--;
			btcolor();
			
			switch(n)
			{
				case 1: st=conn.prepareStatement("select q1 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 2: st=conn.prepareStatement("select q2 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 3: st=conn.prepareStatement("select q3 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 4: st=conn.prepareStatement("select q4 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 5: st=conn.prepareStatement("select q5 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 6: st=conn.prepareStatement("select q6 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 7: st=conn.prepareStatement("select q7 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 8: st=conn.prepareStatement("select q8 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 9: st=conn.prepareStatement("select q9 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 10: st=conn.prepareStatement("select q10 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 11: st=conn.prepareStatement("select q11 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 12: st=conn.prepareStatement("select q12 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 13: st=conn.prepareStatement("select q13 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 14: st=conn.prepareStatement("select q14 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 15: st=conn.prepareStatement("select q15 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 16: st=conn.prepareStatement("select q16 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 17: st=conn.prepareStatement("select q17 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 18: st=conn.prepareStatement("select q18 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 19: st=conn.prepareStatement("select q19 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 20: st=conn.prepareStatement("select q20 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 21: st=conn.prepareStatement("select q21 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 22: st=conn.prepareStatement("select q22 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 23: st=conn.prepareStatement("select q23 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 24: st=conn.prepareStatement("select q24 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 25: st=conn.prepareStatement("select q25 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 26: st=conn.prepareStatement("select q26 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 27: st=conn.prepareStatement("select q27 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 28: st=conn.prepareStatement("select q28 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 29: st=conn.prepareStatement("select q29 from user_record where user_name = ?");
						st.setString(1,un);
						break;
				case 30: st=conn.prepareStatement("select q30 from user_record where user_name = ?");
						st.setString(1,un);
						break;
			}
			rs=st.executeQuery();
			rs.next();
			String temp=rs.getString(1);
			if(rs.wasNull())
			{
				rb1.setEnabled(true);
				rb2.setEnabled(true);
				rb3.setEnabled(true);
				rb4.setEnabled(true);
				rb1.setForeground(Color.darkGray);
				rb2.setForeground(Color.darkGray);
				rb3.setForeground(Color.darkGray);
				rb4.setForeground(Color.darkGray);
				bg.clearSelection();
			}
			else if(temp.equals("A"))
			{
				rb1.setSelected(true);
				rb1.setEnabled(true);
				rb1.setForeground(Color.green);
				rb2.setEnabled(false);
				rb3.setEnabled(false);
				rb4.setEnabled(false);
			}
			else if(temp.equals("B"))
			{
				rb2.setSelected(true);
				rb2.setEnabled(true);
				rb2.setForeground(Color.green);
				rb1.setEnabled(false);
				rb3.setEnabled(false);
				rb4.setEnabled(false);
			}
			else if(temp.equals("C"))
			{
				rb3.setSelected(true);
				rb3.setEnabled(true);
				rb3.setForeground(Color.green);
				rb1.setEnabled(false);
				rb2.setEnabled(false);
				rb4.setEnabled(false);
			}
			else if(temp.equals("D"))
			{
				rb4.setSelected(true);
				rb4.setEnabled(true);
				rb4.setForeground(Color.green);
				rb1.setEnabled(false);
				rb2.setEnabled(false);
				rb3.setEnabled(false);
			}
			//*************************************************************
			if(n==1)
				prev.setEnabled(false);
			else
				next.setEnabled(true);
			
			st.close();
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	void lockdata()
	{
		try
		{
			
			if(rb1.isSelected())
			{
				switch(n)
				{
					case 1: sl=conn.prepareStatement("update user_record set q1 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 2: sl=conn.prepareStatement("update user_record set q2 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 3: sl=conn.prepareStatement("update user_record set q3 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 4: sl=conn.prepareStatement("update user_record set q4 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 5: sl=conn.prepareStatement("update user_record set q5 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 6: sl=conn.prepareStatement("update user_record set q6 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 7: sl=conn.prepareStatement("update user_record set q7 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 8: sl=conn.prepareStatement("update user_record set q8 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 9: sl=conn.prepareStatement("update user_record set q9 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 10: sl=conn.prepareStatement("update user_record set q10 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 11: sl=conn.prepareStatement("update user_record set q11 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 12: sl=conn.prepareStatement("update user_record set q12 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 13: sl=conn.prepareStatement("update user_record set q13 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 14: sl=conn.prepareStatement("update user_record set q14 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 15: sl=conn.prepareStatement("update user_record set q15 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 16: sl=conn.prepareStatement("update user_record set q16 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 17: sl=conn.prepareStatement("update user_record set q17 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 18: sl=conn.prepareStatement("update user_record set q18 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 19: sl=conn.prepareStatement("update user_record set q19 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 20: sl=conn.prepareStatement("update user_record set q20 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 21: sl=conn.prepareStatement("update user_record set q21 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 22: sl=conn.prepareStatement("update user_record set q22 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 23: sl=conn.prepareStatement("update user_record set q23 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 24: sl=conn.prepareStatement("update user_record set q24 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 25: sl=conn.prepareStatement("update user_record set q25 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 26: sl=conn.prepareStatement("update user_record set q26 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 27: sl=conn.prepareStatement("update user_record set q27 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 28: sl=conn.prepareStatement("update user_record set q28 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 29: sl=conn.prepareStatement("update user_record set q29 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 30: sl=conn.prepareStatement("update user_record set q30 = ? where user_name = ?");
							sl.setString(1,"A");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
				}
				
				rb1.setForeground(Color.green);
				rb2.setEnabled(false);
				rb3.setEnabled(false);
				rb4.setEnabled(false);
				sl.close();
			}
			else if(rb2.isSelected())
			{
				switch(n)
				{
					case 1: sl=conn.prepareStatement("update user_record set q1 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 2: sl=conn.prepareStatement("update user_record set q2 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 3: sl=conn.prepareStatement("update user_record set q3 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 4: sl=conn.prepareStatement("update user_record set q4 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 5: sl=conn.prepareStatement("update user_record set q5 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 6: sl=conn.prepareStatement("update user_record set q6 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 7: sl=conn.prepareStatement("update user_record set q7 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 8: sl=conn.prepareStatement("update user_record set q8 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 9: sl=conn.prepareStatement("update user_record set q9 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 10: sl=conn.prepareStatement("update user_record set q10 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 11: sl=conn.prepareStatement("update user_record set q11 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 12: sl=conn.prepareStatement("update user_record set q12 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 13: sl=conn.prepareStatement("update user_record set q13 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 14: sl=conn.prepareStatement("update user_record set q14 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 15: sl=conn.prepareStatement("update user_record set q15 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 16: sl=conn.prepareStatement("update user_record set q16 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 17: sl=conn.prepareStatement("update user_record set q17 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 18: sl=conn.prepareStatement("update user_record set q18 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 19: sl=conn.prepareStatement("update user_record set q19 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 20: sl=conn.prepareStatement("update user_record set q20 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 21: sl=conn.prepareStatement("update user_record set q21 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 22: sl=conn.prepareStatement("update user_record set q22 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 23: sl=conn.prepareStatement("update user_record set q23 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 24: sl=conn.prepareStatement("update user_record set q24 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 25: sl=conn.prepareStatement("update user_record set q25 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 26: sl=conn.prepareStatement("update user_record set q26 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 27: sl=conn.prepareStatement("update user_record set q27 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 28: sl=conn.prepareStatement("update user_record set q28 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 29: sl=conn.prepareStatement("update user_record set q29 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 30: sl=conn.prepareStatement("update user_record set q30 = ? where user_name = ?");
							sl.setString(1,"B");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
				}
				
				rb2.setForeground(Color.green);
				rb1.setEnabled(false);
				rb3.setEnabled(false);
				rb4.setEnabled(false);
				sl.close();
			}
			else if(rb3.isSelected())
			{
				switch(n)
				{
					case 1: sl=conn.prepareStatement("update user_record set q1 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 2: sl=conn.prepareStatement("update user_record set q2 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 3: sl=conn.prepareStatement("update user_record set q3 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 4: sl=conn.prepareStatement("update user_record set q4 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 5: sl=conn.prepareStatement("update user_record set q5 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 6: sl=conn.prepareStatement("update user_record set q6 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 7: sl=conn.prepareStatement("update user_record set q7 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 8: sl=conn.prepareStatement("update user_record set q8 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 9: sl=conn.prepareStatement("update user_record set q9 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 10: sl=conn.prepareStatement("update user_record set q10 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 11: sl=conn.prepareStatement("update user_record set q11 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 12: sl=conn.prepareStatement("update user_record set q12 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 13: sl=conn.prepareStatement("update user_record set q13 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 14: sl=conn.prepareStatement("update user_record set q14 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 15: sl=conn.prepareStatement("update user_record set q15 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 16: sl=conn.prepareStatement("update user_record set q16 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 17: sl=conn.prepareStatement("update user_record set q17 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 18: sl=conn.prepareStatement("update user_record set q18 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 19: sl=conn.prepareStatement("update user_record set q19 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 20: sl=conn.prepareStatement("update user_record set q20 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 21: sl=conn.prepareStatement("update user_record set q21 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 22: sl=conn.prepareStatement("update user_record set q22 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 23: sl=conn.prepareStatement("update user_record set q23 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							break;
					case 24: sl=conn.prepareStatement("update user_record set q24 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 25: sl=conn.prepareStatement("update user_record set q25 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 26: sl=conn.prepareStatement("update user_record set q26 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 27: sl=conn.prepareStatement("update user_record set q27 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 28: sl=conn.prepareStatement("update user_record set q28 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 29: sl=conn.prepareStatement("update user_record set q29 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 30: sl=conn.prepareStatement("update user_record set q30 = ? where user_name = ?");
							sl.setString(1,"C");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
				}
				
				rb3.setForeground(Color.green);
				rb1.setEnabled(false);
				rb2.setEnabled(false);
				rb4.setEnabled(false);
				sl.close();
			}
			else if(rb4.isSelected())
			{
				switch(n)
				{
					case 1: sl=conn.prepareStatement("update user_record set q1 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 2: sl=conn.prepareStatement("update user_record set q2 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 3: sl=conn.prepareStatement("update user_record set q3 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 4: sl=conn.prepareStatement("update user_record set q4 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 5: sl=conn.prepareStatement("update user_record set q5 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 6: sl=conn.prepareStatement("update user_record set q6 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 7: sl=conn.prepareStatement("update user_record set q7 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 8: sl=conn.prepareStatement("update user_record set q8 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 9: sl=conn.prepareStatement("update user_record set q9 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 10: sl=conn.prepareStatement("update user_record set q10 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 11: sl=conn.prepareStatement("update user_record set q11 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 12: sl=conn.prepareStatement("update user_record set q12 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 13: sl=conn.prepareStatement("update user_record set q13 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 14: sl=conn.prepareStatement("update user_record set q14 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 15: sl=conn.prepareStatement("update user_record set q15 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 16: sl=conn.prepareStatement("update user_record set q16 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 17: sl=conn.prepareStatement("update user_record set q17 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 18: sl=conn.prepareStatement("update user_record set q18 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 19: sl=conn.prepareStatement("update user_record set q19 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 20: sl=conn.prepareStatement("update user_record set q20 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 21: sl=conn.prepareStatement("update user_record set q21 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 22: sl=conn.prepareStatement("update user_record set q22 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 23: sl=conn.prepareStatement("update user_record set q23 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 24: sl=conn.prepareStatement("update user_record set q24 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 25: sl=conn.prepareStatement("update user_record set q25 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 26: sl=conn.prepareStatement("update user_record set q26 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 27: sl=conn.prepareStatement("update user_record set q27 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 28: sl=conn.prepareStatement("update user_record set q28 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 29: sl=conn.prepareStatement("update user_record set q29 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
					case 30: sl=conn.prepareStatement("update user_record set q30 = ? where user_name = ?");
							sl.setString(1,"D");
							sl.setString(2,un);
							sl.executeUpdate();
							break;
				}
				
				rb4.setForeground(Color.green);
				rb1.setEnabled(false);
				rb2.setEnabled(false);
				rb3.setEnabled(false);
				sl.close();
			}
			bl=5;
			btcolor();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		lock.setEnabled(false);
	}
	public void actionPerformed(ActionEvent ae)
	{
		//some actions
		try{
			
		if(rb1.isSelected())
			lock.setEnabled(true);
		else if(rb2.isSelected())
			lock.setEnabled(true);
		else if(rb3.isSelected())
			lock.setEnabled(true);
		else if(rb4.isSelected())
			lock.setEnabled(true);
		if(ae.getSource()==next)
		{
			nextdata();
		}
		else if(ae.getSource()==prev)
		{
			prevdata();
		}
		else if(ae.getSource()==lock)
		{
			lockdata();
		}
		else if(ae.getSource()==b1)
		{
			n=0;
			nextdata();
		}
		else if(ae.getSource()==b2)
		{
			n=1;
			nextdata();
		}
		else if(ae.getSource()==b3)
		{
			n=2;
			nextdata();
		}
		else if(ae.getSource()==b4)
		{
			n=3;
			nextdata();
		}
		else if(ae.getSource()==b5)
		{
			n=4;
			nextdata();
		}
		else if(ae.getSource()==b6)
		{
			n=5;
			nextdata();
		}
		else if(ae.getSource()==b7)
		{
			n=6;
			nextdata();;
		}
		else if(ae.getSource()==b8)
		{
			n=7;
			nextdata();
		}
		else if(ae.getSource()==b9)
		{
			n=8;
			nextdata();
		}
		else if(ae.getSource()==b10)
		{
			n=9;
			nextdata();
		}
		else if(ae.getSource()==b11)
		{
			n=10;
			nextdata();
		}
		else if(ae.getSource()==b12)
		{
			n=11;
			nextdata();
		}
		else if(ae.getSource()==b13)
		{
			n=12;
			nextdata();
		}
		else if(ae.getSource()==b14)
		{
			n=13;
			nextdata();
		}
		else if(ae.getSource()==b15)
		{
			n=14;
			nextdata();
		}
		else if(ae.getSource()==b16)
		{
			n=15;
			nextdata();
		}
		else if(ae.getSource()==b17)
		{
			n=16;
			nextdata();
		}
		else if(ae.getSource()==b18)
		{
			n=17;
			nextdata();
		}
		else if(ae.getSource()==b19)
		{
			n=18;
			nextdata();
		}
		else if(ae.getSource()==b20)
		{
			n=19;
			nextdata();
		}
		else if(ae.getSource()==b21)
		{
			n=20;
			nextdata();
		}
		else if(ae.getSource()==b22)
		{
			n=21;
			nextdata();
		}
		else if(ae.getSource()==b23)
		{
			n=22;
			nextdata();
		}
		else if(ae.getSource()==b24)
		{
			n=23;
			nextdata();
		}
		else if(ae.getSource()==b25)
		{
			n=24;
			nextdata();
		}
		else if(ae.getSource()==b26)
		{
			n=25;
			nextdata();
		}
		else if(ae.getSource()==b27)
		{
			n=26;
			nextdata();;
		}
		else if(ae.getSource()==b28)
		{
			n=27;
			nextdata();
		}
		else if(ae.getSource()==b29)
		{
			n=28;
			nextdata();
		}
		else if(ae.getSource()==b30)
		{
			n=29;
			nextdata();
		}
		
		}catch(Exception e)
		{}
		
	}
	
	public void keyPressed(KeyEvent ke)
	{
		int keycode = ke.getKeyCode();
		if(keycode == KeyEvent.VK_ENTER)
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
				conn=DriverManager.getConnection("jdbc:oracle:thin:@SAROJ:1521:XE","system","manager");
				
				
				ca=conn.prepareStatement("select password from login where username = ?");
				ca.setString(1,un);
				String ps=new String(txtpass.getPassword());
				rca=ca.executeQuery();
				rca.next();
				if(ps.equals(rca.getString(1)))
				{
					ca.close();
					rca.close();
					conn.close();
					//calculation();
					result l=new result(un);
					this.dispose();
				}
				else
				{
					cap.setText("Invalid Password");
					txtpass.setText("");
					conn.close();
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
	
	public void numberpan()
	{
		JPanel numpan = new JPanel();
		numpan.setLayout(null);
		numpan.setOpaque(false);
		numpan.setBounds(height/40,height-650,height-350,height-150);
		add(numpan);
		ImageIcon bi = new ImageIcon(getClass().getResource("pics/numbt.png"));
		//knowing the buttons...
		JLabel visited=new JLabel("Visited:");
		visited.setFont(new Font("Century",Font.BOLD,(width/90)));
		visited.setBounds((height/85),(height/4)-12,(height/12),(height/13)-4);
		JLabel vis=new JLabel(bi);
		vis.setOpaque(true);
		vis.setBackground(Color.blue);
		vis.setBounds((height/11)+10,(height/4)-12,(height/12)-4,(height/13)-4);
		numpan.add(visited);
		numpan.add(vis);
		JLabel lock=new JLabel("Lock:");
		lock.setFont(new Font("Century",Font.BOLD,(width/90)));
		lock.setBounds((height/6)+32,(height/4)-12,(height/12)-4,(height/13)-4);
		JLabel loc=new JLabel(bi);
		loc.setOpaque(true);
		loc.setBackground(Color.green);
		loc.setBounds((height/4)+18,(height/4)-12,(height/12)-4,(height/13)-4);
		numpan.add(lock);
		numpan.add(loc);
		
			//question  buttons..
			
			b1=new JButton("1",bi);
			b1.setFont(new Font("Century",Font.BOLD,(width/68)));
			b1.setBorderPainted(false);
			//b1.setOpaque(false);
			b1.setHorizontalTextPosition(SwingConstants.CENTER);
			b1.setBackground(Color.white);
			b1.setBounds((height/85)+1,(height/3)+4,(height/12)-4,(height/13)-4);
			b2=new JButton("2",bi);
			b2.setFont(new Font("Century",Font.BOLD,(width/68)));
			b2.setBorderPainted(false);
			//b2.setOpaque(false);
			b2.setHorizontalTextPosition(SwingConstants.CENTER);
			b2.setBackground(Color.white);
			b2.setBounds((height/12)+11,(height/3)+4,(height/12)-4,(height/13)-4);
			b3=new JButton("3",bi);
			b3.setFont(new Font("Century",Font.BOLD,(width/68)));
			b3.setBorderPainted(false);
			//b3.setOpaque(false);
			b3.setHorizontalTextPosition(SwingConstants.CENTER);
			b3.setBackground(Color.white);
			b3.setBounds((height/6)+12,(height/3)+4,(height/12)-4,(height/13)-4);
			b4=new JButton("4",bi);
			b4.setFont(new Font("Century",Font.BOLD,(width/68)));
			b4.setBorderPainted(false);
			//b4.setOpaque(false);
			b4.setHorizontalTextPosition(SwingConstants.CENTER);
			b4.setBackground(Color.white);
			b4.setBounds((height/4)+13,(height/3)+4,(height/12)-4,(height/13)-4);
			b5=new JButton("5",bi);
			b5.setFont(new Font("Century",Font.BOLD,(width/68)));
			b5.setBorderPainted(false);
			//b5.setOpaque(false);
			b5.setHorizontalTextPosition(SwingConstants.CENTER);
			b5.setBackground(Color.white);
			b5.setBounds((height/3)+14,(height/3)+4,(height/12)-4,(height/13)-4);
			
			b6=new JButton("6",bi);
			b6.setFont(new Font("Century",Font.BOLD,(width/68)));
			b6.setBorderPainted(false);
			//b6.setOpaque(false);
			b6.setHorizontalTextPosition(SwingConstants.CENTER);
			b6.setBackground(Color.white);
			b6.setBounds((height/85)+1,(height/2)-64,(height/12)-4,(height/13)-4);
			b7=new JButton("7",bi);
			b7.setFont(new Font("Century",Font.BOLD,(width/68)));
			b7.setBorderPainted(false);
			//b7.setOpaque(false);
			b7.setHorizontalTextPosition(SwingConstants.CENTER);
			b7.setBackground(Color.white);
			b7.setBounds((height/12)+11,(height/2)-64,(height/12)-4,(height/13)-4);
			b8=new JButton("8",bi);
			b8.setFont(new Font("Century",Font.BOLD,(width/68)));
			b8.setBorderPainted(false);
			//b8.setOpaque(false);
			b8.setHorizontalTextPosition(SwingConstants.CENTER);
			b8.setBackground(Color.white);
			b8.setBounds((height/6)+12,(height/2)-64,(height/12)-4,(height/13)-4);
			b9=new JButton("9",bi);
			b9.setFont(new Font("Century",Font.BOLD,(width/68)));
			b9.setBorderPainted(false);
			//b9.setOpaque(false);
			b9.setHorizontalTextPosition(SwingConstants.CENTER);
			b9.setBackground(Color.white);
			b9.setBounds((height/4)+13,(height/2)-64,(height/12)-4,(height/13)-4);
			b10=new JButton("10",bi);
			b10.setFont(new Font("Century",Font.BOLD,(width/68)));
			b10.setBorderPainted(false);
			//b10.setOpaque(false);
			b10.setHorizontalTextPosition(SwingConstants.CENTER);
			b10.setBackground(Color.white);
			b10.setBounds((height/3)+14,(height/2)-64,(height/12)-4,(height/13)-4);
			
			b11=new JButton("11",bi);
			b11.setFont(new Font("Century",Font.BOLD,(width/68)));
			b11.setBorderPainted(false);
			//b11.setOpaque(false);
			b11.setHorizontalTextPosition(SwingConstants.CENTER);
			b11.setBackground(Color.white);
			b11.setBounds((height/85)+1,(height/2)-4,(height/12)-4,(height/13)-4);
			b12=new JButton("12",bi);
			b12.setFont(new Font("Century",Font.BOLD,(width/68)));
			b12.setBorderPainted(false);
			//b12.setOpaque(false);
			b12.setHorizontalTextPosition(SwingConstants.CENTER);
			b12.setBackground(Color.white);
			b12.setBounds((height/12)+11,(height/2)-4,(height/12)-4,(height/13)-4);
			b13=new JButton("13",bi);
			b13.setFont(new Font("Century",Font.BOLD,(width/68)));
			b13.setBorderPainted(false);
			//b13.setOpaque(false);
			b13.setHorizontalTextPosition(SwingConstants.CENTER);
			b13.setBackground(Color.white);
			b13.setBounds((height/6)+12,(height/2)-4,(height/12)-4,(height/13)-4);
			b14=new JButton("14",bi);
			b14.setFont(new Font("Century",Font.BOLD,(width/68)));
			b14.setBorderPainted(false);
			//b14.setOpaque(false);
			b14.setHorizontalTextPosition(SwingConstants.CENTER);
			b14.setBackground(Color.white);
			b14.setBounds((height/4)+13,(height/2)-4,(height/12)-4,(height/13)-4);
			b15=new JButton("15",bi);
			b15.setFont(new Font("Century",Font.BOLD,(width/68)));
			b15.setBorderPainted(false);
			//b15.setOpaque(false);
			b15.setHorizontalTextPosition(SwingConstants.CENTER);
			b15.setBackground(Color.white);
			b15.setBounds((height/3)+14,(height/2)-4,(height/12)-4,(height/13)-4);
			
			b16=new JButton("16",bi);
			b16.setFont(new Font("Century",Font.BOLD,(width/68)));
			b16.setBorderPainted(false);
			//b16.setOpaque(false);
			b16.setHorizontalTextPosition(SwingConstants.CENTER);
			b16.setBackground(Color.white);
			b16.setBounds((height/85)+1,(height/2)+56,(height/12)-4,(height/13)-4);
			b17=new JButton("17",bi);
			b17.setFont(new Font("Century",Font.BOLD,(width/68)));
			b17.setBorderPainted(false);
			//b17.setOpaque(false);
			b17.setHorizontalTextPosition(SwingConstants.CENTER);
			b17.setBackground(Color.white);
			b17.setBounds((height/12)+11,(height/2)+56,(height/12)-4,(height/13)-4);
			b18=new JButton("18",bi);
			b18.setFont(new Font("Century",Font.BOLD,(width/68)));
			b18.setBorderPainted(false);
			//b18.setOpaque(false);
			b18.setHorizontalTextPosition(SwingConstants.CENTER);
			b18.setBackground(Color.white);
			b18.setBounds((height/6)+12,(height/2)+56,(height/12)-4,(height/13)-4);
			b19=new JButton("19",bi);
			b19.setFont(new Font("Century",Font.BOLD,(width/68)));
			b19.setBorderPainted(false);
			//b19.setOpaque(false);
			b19.setHorizontalTextPosition(SwingConstants.CENTER);
			b19.setBackground(Color.white);
			b19.setBounds((height/4)+13,(height/2)+56,(height/12)-4,(height/13)-4);
			b20=new JButton("20",bi);
			b20.setFont(new Font("Century",Font.BOLD,(width/68)));
			b20.setBorderPainted(false);
			//b20.setOpaque(false);
			b20.setHorizontalTextPosition(SwingConstants.CENTER);
			b20.setBackground(Color.white);
			b20.setBounds((height/3)+14,(height/2)+56,(height/12)-4,(height/13)-4);
			
			b21=new JButton("21",bi);
			b21.setFont(new Font("Century",Font.BOLD,(width/68)));
			b21.setBorderPainted(false);
			//b21.setOpaque(false);
			b21.setHorizontalTextPosition(SwingConstants.CENTER);
			b21.setBackground(Color.white);
			b21.setBounds((height/85)+1,(height/2)+116,(height/12)-4,(height/13)-4);
			b22=new JButton("22",bi);
			b22.setFont(new Font("Century",Font.BOLD,(width/68)));
			b22.setBorderPainted(false);
			//b22.setOpaque(false);
			b22.setHorizontalTextPosition(SwingConstants.CENTER);
			b22.setBackground(Color.white);
			b22.setBounds((height/12)+11,(height/2)+116,(height/12)-4,(height/13)-4);
			b23=new JButton("23",bi);
			b23.setFont(new Font("Century",Font.BOLD,(width/68)));
			b23.setBorderPainted(false);
			//b23.setOpaque(false);
			b23.setHorizontalTextPosition(SwingConstants.CENTER);
			b23.setBackground(Color.white);
			b23.setBounds((height/6)+12,(height/2)+116,(height/12)-4,(height/13)-4);
			b24=new JButton("24",bi);
			b24.setFont(new Font("Century",Font.BOLD,(width/68)));
			b24.setBorderPainted(false);
			//b24.setOpaque(false);
			b24.setHorizontalTextPosition(SwingConstants.CENTER);
			b24.setBackground(Color.white);
			b24.setBounds((height/4)+13,(height/2)+116,(height/12)-4,(height/13)-4);
			b25=new JButton("25",bi);
			b25.setFont(new Font("Century",Font.BOLD,(width/68)));
			b25.setBorderPainted(false);
			//b25.setOpaque(false);
			b25.setHorizontalTextPosition(SwingConstants.CENTER);
			b25.setBackground(Color.white);
			b25.setBounds((height/3)+14,(height/2)+116,(height/12)-4,(height/13)-4);
			
			b26=new JButton("26",bi);
			b26.setFont(new Font("Century",Font.BOLD,(width/68)));
			b26.setBorderPainted(false);
			//b26.setOpaque(false);
			b26.setHorizontalTextPosition(SwingConstants.CENTER);
			b26.setBackground(Color.white);
			b26.setBounds((height/85)+1,(height/2)+176,(height/12)-4,(height/13)-4);
			b27=new JButton("27",bi);
			b27.setFont(new Font("Century",Font.BOLD,(width/68)));
			b27.setBorderPainted(false);
			//b27.setOpaque(false);
			b27.setHorizontalTextPosition(SwingConstants.CENTER);
			b27.setBackground(Color.white);
			b27.setBounds((height/12)+11,(height/2)+176,(height/12)-4,(height/13)-4);
			b28=new JButton("28",bi);
			b28.setFont(new Font("Century",Font.BOLD,(width/68)));
			b28.setBorderPainted(false);
			//b28.setOpaque(false);
			b28.setHorizontalTextPosition(SwingConstants.CENTER);
			b28.setBackground(Color.white);
			b28.setBounds((height/6)+12,(height/2)+176,(height/12)-4,(height/13)-4);
			b29=new JButton("29",bi);
			b29.setFont(new Font("Century",Font.BOLD,(width/68)));
			b29.setBorderPainted(false);
			//b29.setOpaque(false);
			b29.setHorizontalTextPosition(SwingConstants.CENTER);
			b29.setBackground(Color.white);
			b29.setBounds((height/4)+13,(height/2)+176,(height/12)-4,(height/13)-4);
			b30=new JButton("30",bi);
			b30.setFont(new Font("Century",Font.BOLD,(width/68)));
			b30.setBorderPainted(false);
			//b30.setOpaque(false);
			b30.setHorizontalTextPosition(SwingConstants.CENTER);
			b30.setBackground(Color.white);
			b30.setBounds((height/3)+14,(height/2)+176,(height/12)-4,(height/13)-4);
			qg=new ButtonGroup();
			qg.add(b1);
			qg.add(b2);
			qg.add(b3);
			qg.add(b4);
			qg.add(b5);
			qg.add(b6);
			qg.add(b7);
			qg.add(b8);
			qg.add(b9);
			qg.add(b10);
			qg.add(b11);
			qg.add(b12);
			qg.add(b13);
			qg.add(b14);
			qg.add(b15);
			qg.add(b16);
			qg.add(b17);
			qg.add(b18);
			qg.add(b19);
			qg.add(b20);
			qg.add(b21);
			qg.add(b22);
			qg.add(b23);
			qg.add(b24);
			qg.add(b25);
			qg.add(b26);
			qg.add(b27);
			qg.add(b28);
			qg.add(b29);
			qg.add(b30);
			numpan.add(b1);
			numpan.add(b2);
			numpan.add(b3);
			numpan.add(b4);
			numpan.add(b5);
			numpan.add(b6);
			numpan.add(b7);
			numpan.add(b8);
			numpan.add(b9);
			numpan.add(b10);
			numpan.add(b11);
			numpan.add(b12);
			numpan.add(b13);
			numpan.add(b14);
			numpan.add(b15);
			numpan.add(b16);
			numpan.add(b17);
			numpan.add(b18);
			numpan.add(b19);
			numpan.add(b20);
			numpan.add(b21);
			numpan.add(b22);
			numpan.add(b23);
			numpan.add(b24);
			numpan.add(b25);
			numpan.add(b26);
			numpan.add(b27);
			numpan.add(b28);
			numpan.add(b29);
			numpan.add(b30);
			//********************
			//btcolor(n);
	}
	public void btcolor()
	{
		int i;
		
			i=n;
			switch(i)
			{
				case 1: if(bc1==0)
						{
							bc1=1;
							b1.setBackground(colors[bc1]);
						}
						else if(bl==5)
						{
							bc1=2;
							b1.setBackground(colors[bc1]);
							bl=0;
						}
						break;
				case 2: if(bc2==0)
						{
							bc2=1;
							b2.setBackground(colors[bc2]);
						}
						else if(bl==5)
						{
							bc2=2;
							b2.setBackground(colors[bc2]);
							bl=0;
						}
						break;
				case 3: if(bc3==0)
						{
							bc3=1;
							b3.setBackground(colors[bc3]);
						}
						else if(bl==5)
						{
							bc3=2;
							b3.setBackground(colors[bc3]);
							bl=0;
						}
						break;
				case 4: if(bc4==0)
						{
							bc4=1;
							b4.setBackground(colors[bc4]);
						}
						else if(bl==5)
						{
							bc4=2;
							b4.setBackground(colors[bc4]);
							bl=0;
						}
						break;
				case 5: if(bc5==0)
						{
							bc5=1;
							b5.setBackground(colors[bc5]);
						}
						else if(bl==5)
						{
							bc5=2;
							b5.setBackground(colors[bc5]);
							bl=0;
						}
						break;
				case 6: if(bc6==0)
						{
							bc6=1;
							b6.setBackground(colors[bc6]);
						}
						else if(bl==5)
						{
							bc6=2;
							b6.setBackground(colors[bc6]);
							bl=0;
						}
						break;
				case 7: if(bc7==0)
						{
							bc7=1;
							b7.setBackground(colors[bc7]);
						}
						else if(bl==5)
						{
							bc7=2;
							b7.setBackground(colors[bc7]);
							bl=0;
						}
						break;
				case 8: if(bc8==0)
						{
							bc8=1;
							b8.setBackground(colors[bc8]);
						}
						else if(bl==5)
						{
							bc8=2;
							b8.setBackground(colors[bc8]);
							bl=0;
						}
						break;
				case 9: if(bc9==0)
						{
							bc9=1;
							b9.setBackground(colors[bc9]);
						}
						else if(bl==5)
						{
							bc9=2;
							b9.setBackground(colors[bc9]);
							bl=0;
						}
						break;
				case 10:if(bc10==0)
						{
							bc10=1;
							b10.setBackground(colors[bc10]);
						}
						else if(bl==5)
						{
							bc10=2;	
							b10.setBackground(colors[bc10]);
							bl=0;
						}							
						break;
				case 11:if(bc11==0)
						{
							bc11=1;
							b11.setBackground(colors[bc11]);
						}
						else if(bl==5)
						{
							bc11=2;
							b11.setBackground(colors[bc11]);
							bl=0;
						}
						break;
				case 12:if(bc12==0)
						{
							bc12=1;
							b12.setBackground(colors[bc12]);
						} 
						else if(bl==5)
						{
							bc12=2;
							b12.setBackground(colors[bc12]);
							bl=0;
						}
						break;
				case 13:if(bc13==0)
						{
							bc13=1;
							b13.setBackground(colors[bc13]);
						} 
						else if(bl==5)
						{
							bc13=2;
							b13.setBackground(colors[bc13]);
							bl=0;
						}
						break;
				case 14:if(bc14==0)
						{
							bc14=1;
							b14.setBackground(colors[bc14]);
						} 
						else if(bl==5)
						{
							bc14=2;
							b14.setBackground(colors[bc14]);
							bl=0;
						}
						break;
				case 15:if(bc15==0)
						{
							bc15=1;
							b15.setBackground(colors[bc15]);
						} 
						else if(bl==5)
						{
							bc15=2;
							b15.setBackground(colors[bc15]);
							bl=0;
						}
						break;
				case 16:if(bc16==0)
						{
							bc16=1;
							b16.setBackground(colors[bc16]);
						} 
						else if(bl==5)
						{
							bc16=2;
							b16.setBackground(colors[bc16]);
							bl=0;
						}
						break;
				case 17:if(bc17==0)
						{
							bc17=1;
							b17.setBackground(colors[bc17]);
						} 
						else if(bl==5)
						{
							bc17=2;
							b17.setBackground(colors[bc17]);
							bl=0;
						}
						break;
				case 18:if(bc18==0)
						{
							bc18=1;
							b18.setBackground(colors[bc18]);
						} 
						else if(bl==5)
						{
							bc18=2;
							b18.setBackground(colors[bc18]);
							bl=0;
						}
						break;
				case 19:if(bc19==0)
						{
							bc19=1;
							b19.setBackground(colors[bc19]);
						}
						else if(bl==5)
						{
							bc19=2;
							b19.setBackground(colors[bc19]);
							bl=0;
						}
						break;
				case 20:if(bc20==0)
						{
							bc20=1;
							b20.setBackground(colors[bc20]);
						} 
						else if(bl==5)
						{
							bc20=2;
							b20.setBackground(colors[bc20]);
							bl=0;
						}
						break;
				case 21:if(bc21==0)
						{
							bc21=1;
							b21.setBackground(colors[bc21]);
						} 
						else if(bl==5)
						{
							bc21=2;
							b21.setBackground(colors[bc21]);
							bl=0;
						}
						break;
				case 22:if(bc22==0)
						{
							bc22=1;
							b22.setBackground(colors[bc22]);
						} 
						else if(bl==5)
						{
							bc22=2;
							b22.setBackground(colors[bc22]);
							bl=0;
						}
						break;
				case 23:if(bc23==0)
						{
							bc23=1;
							b23.setBackground(colors[bc23]);
						} 
						else if(bl==5)
						{
							bc23=2;
							b23.setBackground(colors[bc23]);
							bl=0;
						}
						break;
				case 24:if(bc24==0)
						{
							bc24=1;
							b24.setBackground(colors[bc24]);
						} 
						else if(bl==5)
						{
							bc24=2;
							b24.setBackground(colors[bc24]);
							bl=0;
						}
						break;
				case 25:if(bc25==0)
						{
							bc25=1;
								b25.setBackground(colors[bc25]);
						} 
						else if(bl==5)
						{
							bc25=2;
							b25.setBackground(colors[bc25]);
							bl=0;
						}
						break;
				case 26:if(bc26==0)
						{
							bc26=1;
							b26.setBackground(colors[bc26]);
						} 
						else if(bl==5)
						{
							bc26=2;
							b26.setBackground(colors[bc26]);
							bl=0;
						}
						break;
				case 27:if(bc27==0)
						{
							bc27=1;
							b27.setBackground(colors[bc27]);
						}
						else if(bl==5)
						{
							bc27=2;
							b27.setBackground(colors[bc27]);
							bl=0;
						}
						break;
				case 28:if(bc28==0)
						{
							bc28=1;
							b28.setBackground(colors[bc28]);
						} 
						else if(bl==5)
						{
							bc28=2;
							b28.setBackground(colors[bc28]);
							bl=0;
						}
						break;
				case 29:if(bc29==0)
						{
							bc29=1;
							b29.setBackground(colors[bc29]);
						} 
						else if(bl==5)
						{
							bc29=2;
							b29.setBackground(colors[bc29]);
							bl=0;
						}
						break;
				case 30:if(bc30==0)
						{
							bc30=1;
							b30.setBackground(colors[bc30]);
						} 
						else if(bl==5)
						{
							bc30=2;
							b30.setBackground(colors[bc30]);
							bl=0;
						}
						break;
				default : System.out.println("good red");
			}
			data();
	}
	public void lockbuttons()
	{
		try
		{
			int j,k=2;
			cs=conn.prepareStatement("select * from user_record where user_name = ?");
			cs.setString(1,un);
			rs=cs.executeQuery();
			rs.next();
			while(k<32)
			{
				String temp=rs.getString(k);
				if(rs.wasNull())
				{
					//nothing
				}
				else
				{
					j=k-1;
					switch(j)
					{
						case 1: bc1=2;
								b1.setBackground(colors[bc1]);
								break;
						case 2: bc2=2;
								b2.setBackground(colors[bc2]);
								break;
						case 3: bc3=2;
								b3.setBackground(colors[bc3]);
								break;
						case 4: bc4=2;
								b4.setBackground(colors[bc4]);
								break;
						case 5: bc5=2;
								b5.setBackground(colors[bc5]);
								break;
						case 6: bc6=2;
								b6.setBackground(colors[bc6]);
								break;
						case 7: bc7=2;
								b7.setBackground(colors[bc7]);
								break;
						case 8: bc8=2;
								b8.setBackground(colors[bc8]);
								break;
						case 9: bc9=2;
								b9.setBackground(colors[bc9]);
								break;
						case 10:bc10=2; 
								b10.setBackground(colors[bc10]);
								break;
						case 11:bc11=2; 
								b11.setBackground(colors[bc11]);
								break;
						case 12:bc12=2; 
								b12.setBackground(colors[bc12]);
								break;
						case 13:bc13=2;
								b13.setBackground(colors[bc13]);
								break;
						case 14:bc14=2; 
								b14.setBackground(colors[bc14]);
								break;
						case 15:bc15=2; 
								b15.setBackground(colors[bc15]);
								break;
						case 16:bc16=2;
								b16.setBackground(colors[bc16]);
								break;
						case 17:bc17=2; 
								b17.setBackground(colors[bc17]);
								break;
						case 18:bc18=2; 
								b18.setBackground(colors[bc18]);
								break;
						case 19:bc19=2; 
								b19.setBackground(colors[bc19]);
								break;
						case 20:bc20=2; 
								b20.setBackground(colors[bc20]);
								break;
						case 21:bc21=2; 
								b21.setBackground(colors[bc21]);
								break;
						case 22:bc22=2; 
								b22.setBackground(colors[bc22]);
								break;
						case 23:bc23=2; 
								b23.setBackground(colors[bc23]);
								break;
						case 24:bc24=2; 
								b24.setBackground(colors[bc24]);
								break;
						case 25:bc25=2; 
								b25.setBackground(colors[bc25]);
								break;
						case 26:bc26=2; 
								b26.setBackground(colors[bc26]);
								break;
						case 27:bc27=2; 
								b27.setBackground(colors[bc27]);
								break;
						case 28:bc28=2; 
								b28.setBackground(colors[bc28]);
								break;
						case 29:bc29=2;
								b29.setBackground(colors[bc29]);
								break;
						case 30:bc30=2; 
								b30.setBackground(colors[bc30]);
								break;
						default : System.out.println("good switch");
					}
				}
				k++;
			}
			cs.close();
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void startatbutton()
	{
		int j = n;
		switch(j)
		{
			case 1: bc1=1;
					b1.setBackground(colors[bc1]);
					break;
			case 2: bc2=1;
					b2.setBackground(colors[bc2]);
					break;
			case 3: bc3=1;
					b3.setBackground(colors[bc3]);
					break;
			case 4: bc4=1;
					b4.setBackground(colors[bc4]);
					break;
			case 5: bc5=1;
					b5.setBackground(colors[bc5]);
					break;
			case 6: bc6=1;
					b6.setBackground(colors[bc6]);
					break;
			case 7: bc7=1;
					b7.setBackground(colors[bc7]);
					break;
			case 8: bc8=1;
					b8.setBackground(colors[bc8]);
					break;
			case 9: bc9=1;
					b9.setBackground(colors[bc9]);
					break;
			case 10:bc10=1; 
					b10.setBackground(colors[bc10]);
					break;
			case 11:bc11=1; 
					b11.setBackground(colors[bc11]);
					break;
			case 12:bc12=1; 
					b12.setBackground(colors[bc12]);
					break;
			case 13:bc13=1;
					b13.setBackground(colors[bc13]);
					break;
			case 14:bc14=1; 
					b14.setBackground(colors[bc14]);
					break;
			case 15:bc15=1; 
					b15.setBackground(colors[bc15]);
					break;
			case 16:bc16=1;
					b16.setBackground(colors[bc16]);
					break;
			case 17:bc17=1; 
					b17.setBackground(colors[bc17]);
					break;
			case 18:bc18=1; 
					b18.setBackground(colors[bc18]);
					break;
			case 19:bc19=1; 
					b19.setBackground(colors[bc19]);
					break;
			case 20:bc20=1; 
					b20.setBackground(colors[bc20]);
					break;
			case 21:bc21=1; 
					b21.setBackground(colors[bc21]);
					break;
			case 22:bc22=1; 
					b22.setBackground(colors[bc22]);
					break;
			case 23:bc23=1; 
					b23.setBackground(colors[bc23]);
					break;
			case 24:bc24=1; 
					b24.setBackground(colors[bc24]);
					break;
			case 25:bc25=1; 
					b25.setBackground(colors[bc25]);
					break;
			case 26:bc26=1; 
					b26.setBackground(colors[bc26]);
					break;
			case 27:bc27=1; 
					b27.setBackground(colors[bc27]);
					break;
			case 28:bc28=1; 
					b28.setBackground(colors[bc28]);
					break;
			case 29:bc29=1;
					b29.setBackground(colors[bc29]);
					break;
			case 30:bc30=1; 
					b30.setBackground(colors[bc30]);
					break;
			default : System.out.println("good switch");
		}
	}
}