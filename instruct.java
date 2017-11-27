import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

class instruct extends JFrame implements ActionListener , MouseListener
{
	JLabel hed,msg;
	JButton go;
	String u;
	Font font = null;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = screenSize.width, height = screenSize.height;
	instruct(String usr)throws IOException
	{
		u=usr;
		try{
		InputStream is = this.getClass().getResourceAsStream("font/Fine College.ttf");
		font = Font.createFont(Font.TRUETYPE_FONT,is).deriveFont(Font.BOLD,(width/23));
		
		}
		catch(Exception e)
		{
			System.out.println("font problem: "+e);
		}
		
		hed=new JLabel("INSTRUCTIONS");
		hed.setFont(font);
		hed.setForeground(Color.yellow);
		hed.setBounds((width/3), (height/30), (width/3)+20, (height/8)-5);
		msg=new JLabel("Welcome  "+u);
		msg.setForeground(new Color(243, 66, 0));
		msg.setFont(new Font("Kristen ITC",Font.BOLD,(width/68)));
		msg.setBounds(width-390, (height/30)+70, (width/4)-41, (height/15)-1);
		ImageIcon ic = new ImageIcon(getClass().getResource("pics/insbt.png"));
		go=new JButton(ic);
		go.setBackground(Color.red);
		go.setOpaque(false);
		go.setBorderPainted(false);
		go.setFocusPainted(false);
		go.addMouseListener(this);
		go.setBounds((width/2)+1, height-250, (width/12), (height/8)+5);
		go.setCursor(new Cursor(Cursor.HAND_CURSOR));
		go.addActionListener(this);
	
		add(go);
		add(hed);
		add(msg);
		
		mPanel mp=new mPanel(width);
		mp.setLayout(null);
		mp.setOpaque(false);
		mp.setSize(width,height);
		add(mp);
		//creation of frame and background...
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
			BufferedImage header = ImageIO.read(getClass().getResource("pics/Ins.jpg"));
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
		if(ae.getSource()==go)
		{
			try
			{
				mcq f2=new mcq(u);
				this.dispose();
			}
			catch(Exception e)
			{}
			
		}
	}
	public void mouseEntered(MouseEvent me)
	{
		go.setSize((width/12)-10, (height/8)-5);
	}
	public void mouseExited(MouseEvent me)
	{
		go.setSize((width/12), (height/8)+5);
	}
	public void mouseReleased(MouseEvent me){}
	public void mousePressed(MouseEvent me){}
	public void mouseClicked(MouseEvent me){}
}
class mPanel extends JPanel
{
	int width;
	Font fontt = null;
	mPanel(int w)
	{
		width=w;
		try{
			
		InputStream iss = this.getClass().getResourceAsStream("font/Montserrat-Regular.ttf");
		fontt = Font.createFont(Font.TRUETYPE_FONT,iss).deriveFont(Font.BOLD,(width/48));
		
		}
		catch(Exception e)
		{
			System.out.println("font problem: "+e);
		}
		//this.setBackground(Color.red);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setFont(fontt);
		g.setColor(Color.yellow);
		g.drawString("1. Every question has 4 option in which only one is correct.",(width/9)+40,(width/7)+5);
		g.drawString("2. To save your answer click on LOCK button.",(width/9)+40,(width/6)+23);
		g.drawString("3. After LOCK you cannot modify your answer.",(width/9)+40,(width/5)+27);
		g.drawString("4. Get 5 marks on right answer and -2 for wrong.",(width/9)+40,(width/4)+9);
		g.drawString("5. You have only 30 minutes to solve 30 questions",(width/9)+40,(width/4)+59);
		g.drawString("6. After SUBMITION you cannot play again.",(width/9)+40,(width/4)+109);
	}
}