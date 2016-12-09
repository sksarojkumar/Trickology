import java.sql.*;
import java.awt.*;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

class login extends JFrame implements ActionListener , KeyListener
{
	JPanel pan,pan1,abpan;
	JTextArea aboutmsg;
	JLabel username,password,cuname,cpass,rpass,em,im,iclogin,icregister;
	JTextField txtname,txtcuname;
	JPasswordField txtpassword,txtcpass,txtrpass;
	JButton log,sign,ck,change,change1,about,aboutl,exabout;
	JRadioButton rb;
	float total=0;
	int uc=0,cans=0,wans=0,logck=0,ab=0;
	Connection conn=null;
	PreparedStatement st=null,sl=null,tc=null;
	ResultSet rs=null,rl=null,rtc=null;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = screenSize.width, height = screenSize.height;
	
	login()throws IOException
	{
		
		ImageIcon iclog = new ImageIcon(getClass().getResource("pics/tkloginic.png"));
		ImageIcon icreg = new ImageIcon(getClass().getResource("pics/tkregisteric.png"));
		//ii.getImage().flush();

		pan=new JPanel();
		pan.setLayout(null);
		pan.setOpaque(false);
		pan.setBounds((width/2)+60, (height/2)-100, width/3, height/2);
		pan.setVisible(false);
		
		//ImageIcon lgs=new ImageIcon(getClass().getResource("pics/creg.png"));
		change=new JButton("Need an account?");
		change.setHorizontalAlignment(SwingConstants.LEFT);
		change.setBackground(new Color(0, 100, 0));
		change.setForeground(new Color(16, 152, 247));
		change.setOpaque(false);
		change.setBorderPainted(false);
		change.setBounds((width/4)-10, (height/3)+70, (width/6)-50, (height/25)-15);
		change.setCursor(new Cursor(Cursor.HAND_CURSOR));
		change.addActionListener(this);
		//lgs.getImage().flush();
		pan1=new JPanel();
		pan1.setLayout(null);
		pan1.setOpaque(false);
		pan1.setBounds((width/2)+60, (height/2)-100, width/3, height/2);
		pan1.setVisible(true);
		
		//ImageIcon lgs1=new ImageIcon(getClass().getResource("pics/clog.png"));
		change1=new JButton("Already hava an account?");
		change1.setHorizontalAlignment(SwingConstants.LEFT);
		change1.setBackground(new Color(0, 100,0));
		change1.setForeground(new Color(16, 152, 247));
		change1.setOpaque(false);
		change1.setBorderPainted(false);
		change1.setBounds((width/4)-50, (height/3)+70, (width/6), (height/25)-15);
		change1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		change1.addActionListener(this);
		
		iclogin = new JLabel();
		iclogin.setBounds((width-540),(width/9)-13,(height/3)+50,(height/8));
		iclogin.setIcon(iclog);
		iclogin.setVisible(false);
		icregister = new JLabel();
		icregister.setBounds((width-610),(width/9)-13,(height/3)+160,(height/8));
		icregister.setIcon(icreg);
		icregister.setVisible(true);
		add(iclogin);
		add(icregister);
		
		abpan=new JPanel();
		abpan.setLayout(null);
		abpan.setOpaque(false);
		abpan.setBounds((width/2)+60, (height/2)-30, (width/3)+50, (height/3)+50);
		abpan.setVisible(false);
		add(abpan);
		
		
		aboutmsg=new JTextArea();
		aboutmsg.setFont(new Font("Century",Font.BOLD,(width/75)));
		aboutmsg.setForeground(new Color(16, 152, 247));
		aboutmsg.setOpaque(false);
		//aboutmsg.setLineWrap(true);
		//aboutmsg.setWrapStyleWord(true);
		aboutmsg.setEditable(false);
		aboutmsg.setBounds((width/15)-50, (height/7)-80, (width/3), (height/5)+20);
		aboutmsg.setText("Application is Developed By SAROJ KUMAR\n\nDepartment Of Computer Application\n\nNational Institute Of Technology Durgapur\n\nE-mail: sksaroj2016@outlook.com\n\nContack No.: +91-7551856943");
		abpan.add(aboutmsg);
		
		
		aboutl=new JButton("About..");
		aboutl.setHorizontalAlignment(SwingConstants.LEFT);
		aboutl.setBackground(new Color(0, 100,0));
		aboutl.setForeground(new Color(16, 152, 247));
		aboutl.setOpaque(false);
		aboutl.setBorderPainted(false);
		aboutl.setBounds((width/20), (height/3)+70, (width/20)+5, (height/25)-15);
		aboutl.setCursor(new Cursor(Cursor.HAND_CURSOR));
		aboutl.addActionListener(this);
		pan.add(aboutl);
		
		
		about=new JButton("About..");
		about.setHorizontalAlignment(SwingConstants.LEFT);
		about.setBackground(new Color(0, 100,0));
		about.setForeground(new Color(16, 152, 247));
		about.setOpaque(false);
		about.setBorderPainted(false);
		about.setBounds((width/20), (height/3)+70, (width/20)+5, (height/25)-15);
		about.setCursor(new Cursor(Cursor.HAND_CURSOR));
		about.addActionListener(this);
		pan1.add(about);
		
		
		exabout=new JButton("<-Back");
		exabout.setHorizontalAlignment(SwingConstants.LEFT);
		exabout.setBackground(new Color(0, 100,0));
		exabout.setForeground(new Color(16, 152, 247));
		exabout.setOpaque(false);
		exabout.setBorderPainted(false);
		exabout.setBounds((width/6)-30, (height/3), (width/20)+10, (height/25)-15);
		exabout.setCursor(new Cursor(Cursor.HAND_CURSOR));
		exabout.addActionListener(this);
		abpan.add(exabout);
		
		
		//login...
		im=new JLabel();
		im.setFont(new Font("Century",Font.BOLD,(width/90)));
		im.setBounds((width/15)+50, (height/10), (width/3)-55, height/30);
		im.setForeground(Color.red);
		im.setVisible(false);
		username=new JLabel("UserName:");
		username.setFont(new Font("Century",Font.BOLD,(width/90)));
		username.setBounds((width/14)+3, (height/8)+4, (width/14)+3, height/30);
		username.setForeground(new Color( 0, 176, 196, 222));
		password=new JLabel("Password:");
		password.setFont(new Font("Century",Font.BOLD,(width/90)));
		password.setBounds((width/14)+3, (height/5)-13, (width/14)+3, height/30);
		password.setForeground(new Color( 0, 176, 196, 222));
		txtname=new JTextField(20);
		txtname.setBounds((width/7)+5, (height/8)+4, (width/7)-45, height/30);
		txtpassword=new JPasswordField(20);
		txtpassword.setFont(new Font("Century",Font.PLAIN,(width/90)));
		txtpassword.setBounds((width/7)+5, (height/5)-13, (width/7)-45, height/30);
		txtpassword.addKeyListener(this);
		ImageIcon li=new ImageIcon(getClass().getResource("pics/loginbt.png"));
		log=new JButton(li);
		log.setBackground(Color.pink);
		log.setOpaque(false);
		log.setBorderPainted(false);
		log.setCursor(new Cursor(Cursor.HAND_CURSOR));
		log.setBounds((width/6)-7, (height/4)+8, (width/15)+20, (height/20));
		li.getImage().flush();
		rb=new JRadioButton("Show Password");
		rb.setBounds((width/7)+5, (height/5)+17, (width/11)+4, height/55);
		rb.setForeground(Color.blue);
		rb.setOpaque(false);
		//signup..
		em=new JLabel();
		em.setFont(new Font("Century",Font.BOLD,(width/90)));
		em.setBounds((width/15)+50, (height/10), (width/3)-55, height/30);
		em.setForeground(Color.red);
		em.setVisible(false);
		cuname=new JLabel("UserName:");
		cuname.setFont(new Font("Century",Font.BOLD,(width/90)));
		cuname.setBounds((width/24)-6, (height/8)+4, (width/14)+3, height/25);
		cuname.setForeground(new Color( 0, 176, 196, 222));
		cpass=new JLabel("Enter Password:");
		cpass.setFont(new Font("Century",Font.BOLD,(width/90)));
		//cpass.setBounds(50,140,200,30);
		cpass.setBounds((width/24)-6, (height/5)-13, (width/7)+5, height/25);
		cpass.setForeground(new Color( 0, 176, 196, 222));
		rpass=new JLabel("Re-enter Password:");
		rpass.setFont(new Font("Century",Font.BOLD,(width/90)));
		rpass.setBounds((width/24)-6, (height/4)-12, (width/5)-23, height/25);
		rpass.setForeground(new Color( 0, 176, 196, 222));
		txtcuname=new JTextField(20);
		txtcuname.setBounds((width/6)-17, (height/8)+4, (width/9)-1, height/30);
		txtcpass=new JPasswordField(20);
		txtcpass.setBounds((width/6)-17, (height/5)-13, (width/9)-1, height/30);
		txtrpass=new JPasswordField(20);
		txtrpass.setBounds((width/6)-12, (height/4)-12, (width/9)-1, height/30);
		txtrpass.addKeyListener(this);
		ImageIcon s=new ImageIcon(getClass().getResource("pics/signupbt.png"));
		sign=new JButton(s);
		sign.setBackground(Color.pink);
		sign.setOpaque(false);
		sign.setBorderPainted(false);
		sign.setCursor(new Cursor(Cursor.HAND_CURSOR));
		sign.setBounds((width/6)-7, (height/3)-26, (width/15)+20, (height/20));
		s.getImage().flush();

		
		add(pan);
		pan.add(im);
		pan.add(username);
		pan.add(txtname);
		pan.add(password);
		pan.add(txtpassword);
		pan.add(log);
		pan.add(rb);
		pan.add(change);
		add(pan1);
		pan1.add(change1);
		pan1.add(em);
		pan1.add(cuname);
		pan1.add(cpass);
		pan1.add(rpass);
		pan1.add(txtcuname);
		pan1.add(txtcpass);
		pan1.add(txtrpass);
		pan1.add(sign);
		//action..
		txtname.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent kee){
				if(txtname.getText().length()>=8){
					getToolkit().beep();
					kee.consume();
					im.setText("max 8 character in UserName");
				}
			}
		});
		txtpassword.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent kee){
				String pp=new String(txtpassword.getPassword());
				if(pp.length()>=8){
					getToolkit().beep();
					kee.consume();
					im.setText("max 8 character in Password");
				}
			}
		});
		txtcuname.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent kee){
				if(txtcuname.getText().length()>=8){
					getToolkit().beep();
					kee.consume();
					em.setText("max 8 character in UserName");
				}
			}
		});
		txtcpass.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent kee){
				String cp=new String(txtcpass.getPassword());
				if(cp.length()>=8){
					getToolkit().beep();
					kee.consume();
					em.setText("max 8 character in Password");
				}
			}
		});
		txtrpass.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent kee){
				String rp=new String(txtrpass.getPassword());
				if(rp.length()>=8){
					getToolkit().beep();
					kee.consume();
					em.setText("max 8 character in Password");
				}
			}
		});
		
		log.addActionListener(this);
		rb.addActionListener(this);
		sign.addActionListener(this);
		
		//creation of frame and background...
		displayBackground();
		Container c=this.getContentPane();
		c.setLayout(null);
		setTitle("TECHNOSHINE X.6");
		//setSize(400,400);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);			//for fullscreen window like video
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setAlwaysOnTop(true);

		pack();
		setVisible(true);
	}
	//open fullscreen background image************
		//setContentPane(new JLabel(new ImageIcon("tklogin1.jpg")));
		public static BufferedImage resize(BufferedImage image, int width, int height) 
		{

			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
			Graphics2D g = (Graphics2D) bi.createGraphics();
			g.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
			g.drawImage(image, 0, 0, width, height, null);
			g.dispose();
			return bi;
		}

		public void displayBackground() throws IOException
		{
			try
			{
			JPanel pBackground = new JPanel();
			pBackground.setSize(screenSize);
			pBackground.setLayout(new FlowLayout());
			add(pBackground);
			BufferedImage header = ImageIO.read(getClass().getResource("pics/tklogin.jpg"));
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
		
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==log)
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
				conn=DriverManager.getConnection("jdbc:oracle:thin:@SAROJ:1521:XE","system","manager");
				
				st=conn.prepareStatement("select * from login");
				String uname=txtname.getText();
				String p=new String(txtpassword.getPassword());
				rs=st.executeQuery();
				sl=conn.prepareStatement("select ftime,score from user_record where user_name = ?");
				sl.setString(1,uname);
				String u="0:0";
				float s=0;
				rl=sl.executeQuery();
				if(!rl.next())
				{}
				else
				{
					if((u.equals(rl.getString(1))) || (s!=(rl.getFloat(2))))
					{
						//total = rl.getFloat(2);
						uc=1;
						st.close();
						sl.close();
						rs.close();
						rl.close();
						conn.close();
						result ur=new result(uname);
						this.dispose();
					}
				}
				if(uc==0)
				{
					while(rs.next())
					{
						if(uname.equals(rs.getString(1)) && p.equals(rs.getString(2)))
						{
							logck = 1;
							st.close();
							sl.close();
							rs.close();
							rl.close();
							conn.close();
							instruct f1=new instruct(uname);
							this.dispose();
							break;
						}
					}
					if(logck == 0)
					{
						em.setVisible(false);
						im.setVisible(true);
						im.setText("Invalid Username/Password");
					}
				}
				else
					this.dispose();
				txtname.setText("");
				txtname.requestFocus();
				txtpassword.setText("");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else if(ae.getSource()==sign)
		{
			try
			{
				String ps=new String(txtcpass.getPassword());
				String rps=new String(txtrpass.getPassword());
				if(txtcuname.getText().equals(""))
				{
					em.setVisible(true);
					em.setText("The UserName field is Empty");
					txtcuname.requestFocus();
				}
				else if(ps.equals(""))
				{
					em.setVisible(true);
					em.setText("The Password field is Empty");
					txtcpass.requestFocus();
				}
				else if(rps.equals(""))
				{
					em.setVisible(true);
					em.setText("The Re-Enter Password field is Empty");
					txtrpass.requestFocus();
				}
				else
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					//conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
					conn=DriverManager.getConnection("jdbc:oracle:thin:@SAROJ:1521:XE","system","manager");
					
					int flag=0;
					tc=conn.prepareStatement("select * from login");
					rtc=tc.executeQuery();
					while(rtc.next())
					{
						if(txtcuname.getText().equals(rtc.getString(1)))
						{
							txtcuname.setText("");
							txtcuname.requestFocus();
							em.setVisible(true);
							em.setText("The user name is already exits!!!");
							flag=1;
						}
					}
						//inserting password in database login
						if(flag==0)
						{
							if(ps.equals(rps))
							{
								st=conn.prepareStatement("insert into login values(?,?)");
								st.setString(1,txtcuname.getText());
								st.setString(2,ps);
								st.executeUpdate();
								//em.setText("SignUp Successfull!!");
								st.close();
								tc.close();
								rtc.close();
								conn.close();
								icregister.setVisible(false);
								iclogin.setVisible(true);
								pan.setVisible(true);
								pan1.setVisible(false);
								txtname.setText("");
								txtpassword.setText("");
								txtname.requestFocus();
							}
							else
							{
								txtcpass.setText("");
								txtrpass.setText("");
								txtcpass.requestFocus();
								im.setVisible(false);
								em.setVisible(true);
								em.setText("Password not match");
							}
						}
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, e, "Alert", JOptionPane.ERROR_MESSAGE);
				System.out.println(e);
			}
		}
		else if(ae.getSource()==change)
		{
			pan.setVisible(false);
			pan1.setVisible(true);
			im.setVisible(false);
			em.setVisible(false);
			iclogin.setVisible(false);
			icregister.setVisible(true);
			txtcuname.setText("");
			txtcpass.setText("");
			txtrpass.setText("");
			txtcuname.requestFocus();
		}
		else if(ae.getSource()==change1)
		{
			pan1.setVisible(false);
			pan.setVisible(true);
			im.setVisible(false);
			em.setVisible(false);
			icregister.setVisible(false);
			iclogin.setVisible(true);
			txtname.setText("");
			txtpassword.setText("");
			txtname.requestFocus();
		}
		else if(ae.getSource()==about)
		{
			ab=1;
			pan1.setVisible(false);
			abpan.setVisible(true);
		}
		else if(ae.getSource()==aboutl)
		{
			ab=2;
			pan.setVisible(false);
			abpan.setVisible(true);
		}
		else if(ae.getSource()==exabout)
		{
			if(ab==1)
				pan1.setVisible(true);
			else
				pan.setVisible(true);
			
			abpan.setVisible(false);
		}
		if(rb.isSelected())
			txtpassword.setEchoChar((char)0);
		else
			txtpassword.setEchoChar('*');
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
				
				st=conn.prepareStatement("select * from login");
				String uname=txtname.getText();
				String p=new String(txtpassword.getPassword());
				rs=st.executeQuery();
				sl=conn.prepareStatement("select ftime,score from user_record where user_name = ?");
				sl.setString(1,uname);
				String u="0:0";
				float s=0;
				rl=sl.executeQuery();
				if(!rl.next()){}
				else
				{
					if((u.equals(rl.getString(1))) || (s!=(rl.getFloat(2))))
					{
						/*total=rl.getFloat(2);
						cans = rl.getInt(3);
						wans = rl.getInt(4);*/
						uc=1;
						st.close();
						sl.close();
						rs.close();
						rl.close();
						conn.close();
						result ur=new result(uname);
						this.dispose();
					}
				}
				if(uc==0)
				{
					while(rs.next())
					{
						if(uname.equals(rs.getString(1)) && p.equals(rs.getString(2)))
						{
							logck = 1;
							st.close();
							sl.close();
							rs.close();
							rl.close();
							conn.close();
							instruct f1=new instruct(uname);
							this.dispose();
							break;
						}
					}
					if(logck == 0)
					{
						em.setVisible(false);
						im.setVisible(true);
						im.setText("Invalid Username/Password");
					}
				}
				else
					this.dispose();
				txtname.setText("");
				txtname.requestFocus();
				txtpassword.setText("");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
	public void keyReleased(KeyEvent ke)
	{}
	public void keyTyped(KeyEvent ke)
	{}
	
	/*public static void main(String arg[])throws IOException
	{
		login f=new login();	
	}*/
}
