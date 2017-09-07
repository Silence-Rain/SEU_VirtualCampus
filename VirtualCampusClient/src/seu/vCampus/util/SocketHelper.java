package seu.vCampus.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketHelper {
	private Socket server;
	private BufferedReader in;
	private PrintWriter out;

	public void getConnection()
	  {
	    try
	    {
	      this.server = new Socket(InetAddress.getLocalHost(),5678); 
	      this.in = new BufferedReader(new InputStreamReader(server.getInputStream())); 
	      this.out = new PrintWriter(server.getOutputStream());
	      while(true){ 
	    	  String str=in.readLine(); 
	    	  out.println(str); 
	    	  out.flush(); 
	    	  if(str.equals("end"))  break;  
	    	  System.out.println(in.readLine()); 
		}
	
	      server.close(); 
	    }
	    catch (UnknownHostException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }
	

public PrintWriter getout()
{
  return this.out;
}

public void setout(PrintWriter out)
{
  this.out = out;
}

public BufferedReader getin()
{
	return this.in;
}
public void setin(BufferedReader in)
{
	this.in = in;
}
}



