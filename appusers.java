import javax.swing.*;
import java.io.*;
import java.net.*;

class appusers
{
	public static void main(String args[])
	{
		try
		{
			Socket s = new Socket("SAROJ",7500);
			
			new login();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Server is not Active \nPlease Try after sometimes", "Alert", JOptionPane.ERROR_MESSAGE);
		}
	}
}


