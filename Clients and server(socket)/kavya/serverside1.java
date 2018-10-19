

import java.io.*;
import java.util.Date ;

import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Semaphore;

import java.net.InetAddress;
import java.net.UnknownHostException;

//==================================================================

class ClientWorker implements Runnable 
{
	private Socket client;
	private String name="";

	ClientWorker(Socket client) 
	{
		this.client = client;
	}
	public void run()
	{
		String line;
		String g;
		BufferedReader in = null;
		PrintWriter out = null;
		try 
		{
			//creating input and output streams to read and write data
			InputStreamReader isr=new InputStreamReader(client.getInputStream());
			in=new BufferedReader(isr);
			out = new PrintWriter(client.getOutputStream(), true);
		} 
		catch (IOException e) 
		{
			System.out.println("in or out failed");
			System.exit(-1);
		}

		try 
		{
			// Receive text from client
			line = in.readLine();
			this.name=line;

			//check if the client is a known user or not
			//updating the known and logged in user lists
			serverside1.mutex1.acquire();
			
			//check if a user is using the existing name or not 
          if(serverside1.loggedin_users.contains(name))
          {

				try 
				{
					
					client.close();
					
				}
				catch(IOException e)
				{ 
					System.out.println("system close failed"); 
				System.exit(-1);
				}
          	
          }
          
          //if the name entered is new, add it to the known users, loggedin users and create a hashmap entry for it
			if(!serverside1.known_users.contains(name))
			 {

				serverside1.user_msgs.put(name,new ArrayList<String >());
				serverside1.known_users.add(name);
				serverside1.loggedin_users.add(name);

				
				//send acknowledgement to the client indicating the connetcion was successfull
				line = "Hi " + line;
				out.println(line);
			  }
		
			else
			{
				serverside1.loggedin_users.add(name);
				//send acknowledgement to the client indicating the connetcion was successfull
				line = "Hi " + line;
					out.println(line);
			}

			serverside1.mutex1.release();
	   // release the mutex
		} 
       // catch the exceptions
		catch (IOException e) 
		{
			System.out.println("Read failed");
			System.exit(-1);
		}
		catch (InterruptedException e)
		{
			System.out.println("mutex acquire failed");
			System.exit(-1);
		}

		
		try{
			
			while(true)
			 {
				//take input from the user so as to perform a function
			
			g=in.readLine();
			if(g==null)
			{
				return;
			}
			int option= Integer.parseInt(g);
			switch(option)
			{   
			case 1:
				System.out.println("At "+findtime()+" client " +name+" displays all known users");
				
				 StringBuilder b=new StringBuilder();
				
				//get the names of all known users 
				for(int f=0; f<serverside1.known_users.size();f++)
				{
					b.append(serverside1.known_users.get(f));
					 b.append("-");
					
				}
			//send the names to the client
				out.println(b);
			//	System.out.println(b);

				break;
				
			case 2 :
				
				System.out.println("At "+findtime()+" client " +name+" displays all logged in users");
				StringBuilder a=new StringBuilder();
				//get the names of all logged in users 
				for(int f=0; f<serverside1.loggedin_users.size();f++)
				{
					a.append(serverside1.loggedin_users.get(f));
					a.append("-");
				}
				
				//send the names to the client
				out.println(a.toString());

				break;
				
			case 3 :
				//System.out.println("entered the case 3 of  switch");
				
				line = in.readLine();
				String sendto=line;
				System.out.println("At "+findtime()+" client " +name+"  is sending a message to "+sendto);
				line = in.readLine();
				String msg=line;
              //check if the user to whom the message is being sent to exists in the known user list or not
				if(serverside1.user_msgs.containsKey(sendto))
				{
					String p=  new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
					//System.out.println("the client to whom the msg has to be sent is already existing");
					send_specific_user(sendto,msg,name,p);
				}
				else{
					
					String p=findtime();
					//System.out.println("The client to whom the message has to be sent is an unknown user");
					serverside1.user_msgs.put(sendto,new ArrayList<String >());
					serverside1.known_users.add(sendto);
					send_specific_user(sendto,msg,name,p);
				}
				break;

			case 4 :
				System.out.println("At "+findtime() +" client "+ name+" is sending message to all logged in users");
				//reading message input to send to all logged  users
				line = in.readLine();
				String message4=line;
				String timestamp4=findtime();

				sendto_all_loggedin(name,timestamp4,message4);
				
				for(int i=0;i<serverside1.loggedin_users.size();i++)
				{
					
					String d=serverside1.loggedin_users.get(i);
					
				}

				break;

			case 5:
				System.out.println("AT "+ findtime()+" client "+ name +" is sending message to all known users");
				//reading message input to send to all known users
				line = in.readLine();
				String message=line;
				String timestamp=findtime();
				sendto_all(name,timestamp,message);
				break;


			case 6 :
				System.out.println(findtime()+"client "+name+ "is requsting to see its messages");
				String s="";
				s=retrivemsgs(name);
				//System.out.println(s);
				out.println(s);
				break;


			case 7: 

				serverside1.loggedin_users.remove(name);
				System.out.println("AT "+findtime()+" client  " +name+" is exiting");
				try 
				{
					client.close();
					
				}
				catch(IOException e)
				{ 
					System.out.println("system close failed"); 
				System.exit(-1);
				}
			//	break;
				return;

			}//switch close
		}//try block close
		}
		catch (IOException e) 
		{
			System.out.println("Read failed");
			System.exit(-1);
		}

	/*
		{
			client.close();
		serverside1.loggedin_users.remove(name);
			//System.out.println("client"+name+"is exiting");

		} 
		catch (IOException e) 
		{
			System.out.println("Close failed");
			System.exit(-1);
		}  */
	}
	
	//METHOD TO FIND THE TIME
	private String findtime()
	{
		String d=  new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
		return d;
	}

	// METHOD TO SEND MESSAGE TO SPECIFIC USER
	private void send_specific_user(String sendto, String msg,String sender,String timestamp)
	{
		System.out.println("entered the send specific users method");

		String h="FROM  "+sender + timestamp +" : "+msg;
		serverside1.user_msgs.get(sendto).add(h);
		
		System.out.println("user "+sender+"has sent msgs to " +sendto);
		System.out.println(serverside1.user_msgs.get("kavya"));
	}
	
    //method to retrive all messages
	private static String retrivemsgs(String name)
	{ 
		System.out.println("entered the retrive msg's method");
		String l="";

		if((serverside1.user_msgs.get(name)!=null)&&(!serverside1.user_msgs.get(name).isEmpty()))
		{
			
			for (int j=0;j<serverside1.user_msgs.get(name).size();j++)
			{
				
				String f=(serverside1.user_msgs.get(name)).get(j);
				
				l=l+"-"+f;
			}
			
		// REMOVE THE MESSAGES ONCE THE USER HAS READ IT
				(serverside1.user_msgs.get(name)).clear();
		
			
		}
		else
		{
			//send this if the user has no messages
			l=l+" you have no messages ";
		}

		return l;
	}

	//method to send messages to all known users
	private void sendto_all(String sender,String timestamp,String msg)
	{
		int i;

		String G="FROM  "+sender +"  " +timestamp +" : "+msg;
		for ( i=0;i<serverside1.known_users.size();i++)
		{
			String name_user = serverside1.known_users.get(i);

			(serverside1.user_msgs.get(name_user)).add(G);
			System.out.println(findtime()+"user "+sender+" has finished sending messages to all other clients");

		}
	}

	
	//sending messages to all logged in users
	public  void sendto_all_loggedin(String sender,String timestamp,String msg)
	{

		String G=timestamp+"  : "+ msg +" sent by  "+sender;
		
		for (int i=0;i<serverside1.loggedin_users.size();i++)
		{
			String name_user = serverside1.loggedin_users.get(i);

			(serverside1.user_msgs.get(name_user)).add(G);
			
		}
		System.out.println( findtime()+"user "+sender+" has finished sending messages to all other clients");

	}

}

//====================================================================================================================================================

public class serverside1
{

	public static	ArrayList<String> loggedin_users =new ArrayList<String>();
	public static	ArrayList<String> known_users =new ArrayList<String>();
	public static	Semaphore mutex1=new Semaphore(1,true);

	public static HashMap<String,ArrayList<String>> user_msgs= new HashMap<String,ArrayList<String>>();

	ServerSocket server = null;


	public void listenSocket(int port)
	{
		try
		{
			server = new ServerSocket(port); 
			System.out.println("Server running on port " + port + 
					"," + " use ctrl-C to end");
		} 
		catch (IOException e) 
		{
			System.out.println("Error creating socket");
			System.exit(-1);
		}
		while(true)
		{
			ClientWorker w;
			try
			{
				System.out.println("Waiting for a  client to connect");
				w = new ClientWorker(server.accept());
				Thread t = new Thread(w);
				t.start();
			} 
			catch (IOException e) 
			{
				System.out.println("Accept failed");
				System.exit(-1);
			}
		}
	}

	protected void finalize()
	{
		try
		{
			server.close();
		} 
		catch (IOException e) 
		{
			System.out.println("Could not close socket");
			System.exit(-1);
		}
	}

	public static void main(String[] args)
	{
		
		 InetAddress ip;
	        String hostname;
	        try {
	        	//printing the host name
	            ip = InetAddress.getLocalHost();
	            hostname = ip.getHostName();
	          //  System.out.println("Your current IP address : " + ip);
	            System.out.println("SERVER: " + hostname + "  "+args[0]);
	            System.out.println("SERVER is running on  " + hostname + " : "+args[0]);
	            
	 
	        } catch (UnknownHostException e) {
	 
	            e.printStackTrace();
	        }
		if (args.length != 1)
		{
			System.out.println("Usage: java SocketThrdServer port");
			System.exit(1);
		}
		serverside1 server = new serverside1();
		int port = Integer.valueOf(args[0]);
		server.listenSocket(port);
	}
}





