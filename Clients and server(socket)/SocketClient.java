import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;
import java.net.*;

public class SocketClient
{
    Socket socket = null;
    PrintWriter out = null;
    BufferedReader in = null;


    private void buildMenu()  //Menu
    {
        System.out.println("Choose from the following list of commands:");
        System.out.println("  1. Display the names of all known users.");
        System.out.println("  2. Display the names of all currently connected users.");
        System.out.println("  3. Send a text message to a particular user.");
        System.out.println("  4. Send a text message to all currently connected users.");
        System.out.println("  5. Send a text message to all known users.");
        System.out.println("  6. Get my messages.");
        System.out.println("  7. Exit.");
        System.out.println("Enter choice: ");
    }


    public void communicate()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = sc.nextLine();

        //Send data over socket
        out.println(name);
        //Receive text from server
      /*  try
        {
            String line = in.readLine();
            System.out.println("Text received: " + line);
        }
        catch (IOException e)
        {
            System.out.println("Read failed");
            System.exit(1);
        } */
        try {
         String line1=in.readLine();
       
     if(line1 != null)
     {
    	 System.out.println("Text received: " + line1);
        
        while(true)
        {
            System.out.println(" ");
            buildMenu(); //present menu of choices to user
            String option;
            option = sc.nextLine(); //enter user's option.
            out.println(option);
            String line="";
     
            if(option.equalsIgnoreCase("1"))  // Display the names of all known users
            {
    				
        		try
                {
                    line = in.readLine(); //read from server
                    String [] knownUsers = line.split("-");  //split the String get from server  
                    System.out.println("Known users: ");
                    int i = 0;
                    for(String user:knownUsers) //prient each piece of the name String
                    {
                        i++;
                        System.out.println(" "+i+". "+user);

                    }
                }
                catch (IOException e)
                {
                    System.out.println("Read failed");
                    System.exit(1);
                }
                        
                    }

           else if(option.equalsIgnoreCase("2")) //Display the names of all currently connected users
                {
        	   
            	   try
                    {
                        line = in.readLine();//read from server
                        String [] knownUsers = line.split("-"); //split the String get from server  
                        System.out.println("Connected users: ");
                        int i = 0;
                        for(String user:knownUsers) //prient each piece of the name String
                        {
                            i++;
                            System.out.println(" "+i+" "+user);

                        }
                    }
                    catch (IOException e)
                    {
                        System.out.println("Read failed");
                        System.exit(1);
                    }

                } 
            
            else if(option.equalsIgnoreCase("3")) // Send a text message to a particular user
                {
                    String s;
                    System.out.println("Enter name of the clinet to send message to: ");
                    s = sc.nextLine();
                    out.println(s);
                    System.out.println("Enter message: ");
                    String msg;
                    msg = sc.nextLine();
                    out.println(msg);
                    System.out.println("Message posted to "+s);
                }

            else if(option.equalsIgnoreCase("4")) // Send a text message to all currently connected users
                {
            //	String b="kavya case 4";
            	System.out.println("case 4 entered ");
    			 System.out.println("Type msg  u wanna send to connectd users   ");
            	//out.print(b);
                   
    			
                    String g;
    				g=sc.nextLine();
                   // g=in.readLine();
                    System.out.println(g);
                    out.println(g);		
    				
                    
                }
            else if(option.equalsIgnoreCase("5")) //Send a text message to all known users
                {

                    System.out.println("Type message that you want to send to all known users: ");
                   
                    

                }
                else if(option.equalsIgnoreCase("6")) //Get my messages
                {
                    System.out.println("Your messages: ");
                    try
                    {
                        line=in.readLine();
						//System.out.println("msgs are"+line);
                        String [] messages = line.split("-");  //split the String get from server  
						int i = 0;
						//for(String msg:messages) //prient each piece of the name String
						for(i=1;i<messages.length;i++)
						{
							//i++;
							System.out.println("mesage "+i+". "+messages[i]);

						}
						
                    }
                    catch(IOException e)
                    { 
                        System.out.println("catch exception");
                    }	
                    

                }
                else if(option.equalsIgnoreCase("7")) //exit
                {
                    System.exit(0);
                }
                else
                {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
     }
     
     else
     { 
    	 System.out.println("user is already logged in. Try to relogin with a different name."); //Not allow two or more users online at the same time. 
    	 System.exit(0);
     }
        
        }
        catch (IOException e)
        {
            System.out.println("Read failed");
            System.exit(1);
        }
        
        }


    public void listenSocket(String host, int port)
    {
        //Create socket connection  
        try
        {
            socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (UnknownHostException e)
        {
            System.out.println("Unknown host");
            System.exit(1);
        }
        catch (IOException e)
        {
            System.out.println("No I/O");
            System.exit(1);
        }
    }

    public static void main(String[] args)
    {
        SocketClient client = new SocketClient(); 
        String host = args[0];
        int port = Integer.valueOf(args[1]);
        client.listenSocket(host, port);
        if (args.length != 2)
        {
            System.out.println("Usage:  client hostname port");
            System.exit(1);
        }

        else
        {
            System.out.println(">client cs1 2017");
            System.out.println("Connecting to " + host +"  "+port);
        }
        client.communicate();

    }
}
