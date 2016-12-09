import java.io.*;
import java.net.*;

class threadhandler extends Thread
{
	Socket newsock;
	int n;
	threadhandler(Socket s, int v)
	{
		newsock = s;
		n = v;
	}
	public void run()
	{
		try
		{
			System.out.println("Running Thread..."+n);
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}

class appserver
{
	public static void main(String arg[])
	{
		int nreq = 1;
		try{
			ServerSocket sock = new ServerSocket(7500);
			while(true)
			{
				Socket newsock = sock.accept();
				System.out.println("creating Thread..."+nreq);
				Thread t = new threadhandler(newsock,nreq);
				t.start();
				nreq++;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
}