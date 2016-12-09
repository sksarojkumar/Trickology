import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class time 
{
    private JLabel label;
	Connection conn=null;
	PreparedStatement t=null,tc=null;
	ResultSet tr=null,tcr=null;
    Timer timer;
	String usern;
    int min=29,sec=60;
	JFrame clbb;
    time(JLabel ptime,String ur,JFrame clb) 
	{
        usern=ur;
		clbb=clb;
		//connection..
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@SAROJ:1521:XE","system","manager");
			//check data exits or not
			tc=conn.prepareStatement("select user_name from user_record where user_name = ?");
			tc.setString(1,usern);
			tcr=tc.executeQuery();
		
			if(!tcr.next())
			{
				//System.out.println("record nahi hai bhai");
			}
			else
			{
				if(usern.equals(tcr.getString(1)))
				{
					t=conn.prepareStatement("select ftime from user_record where user_name = ?");
					t.setString(1,usern);
					tr=t.executeQuery();
					tr.next();
					String ft = tr.getString(1);
					if(ft == null)
					{
						//System.out.println("time naikhe ho");
					}
					else
					{
						String ut=tr.getString(1);
						String[] utime=ut.split(":");
						min=Integer.parseInt(utime[0]);
						sec=Integer.parseInt(utime[1]);
					}
				}
			}
			t.close();
			tc.close();
			tcr.close();
			tr.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		timer = new Timer(1000, new timeraction());
        this.label=ptime;
        timer.start();
    }

		class timeraction implements ActionListener 
		{
			public void actionPerformed(ActionEvent ae) 
			{ 
				try
				{
					if (min>=0) 
					{
						sec--;
						if(min>10 && sec<10)
							label.setText(String.valueOf(min)+":"+"0"+String.valueOf(sec));
						else if(min==10 && sec<10)
							label.setText(String.valueOf(min)+":"+"0"+String.valueOf(sec));
						else if(min<10 && sec>10)
							label.setText("0"+String.valueOf(min)+":"+String.valueOf(sec));
						else if(min<10 && sec==10)
							label.setText("0"+String.valueOf(min)+":"+String.valueOf(sec));
						else if(min<10 && sec<10)
							label.setText("0"+String.valueOf(min)+":"+"0"+String.valueOf(sec));
						else
							label.setText(String.valueOf(min)+":"+String.valueOf(sec));
						t=conn.prepareStatement("update user_record set ftime = ? where user_name = ?");
						String tm=label.getText();
						t.setString(1,tm);
						t.setString(2,usern);
						t.executeUpdate();
						t.close();
						if(sec==0)
						{
							min--;
							sec=60;
						}
					}
					else
					{
						t.close();
						timer.stop();
						label.setText("Stop!");
						conn.close();
						clbb.dispose();
						result ls=new result(usern);
					}
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		}
}