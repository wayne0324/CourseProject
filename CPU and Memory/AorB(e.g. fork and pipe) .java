// Ozbirn 02/02/17
//
import java.io.*;
import java.util.Scanner;
import java.lang.Runtime;

public class AorB 
{
   public static void main(String args[])
   {
      try
      {            
	 int x;
	 Runtime rt = Runtime.getRuntime();

	 Process proc = rt.exec("java AorBChild");

	 InputStream is = proc.getInputStream();
	 OutputStream os = proc.getOutputStream();

         PrintWriter pw = new PrintWriter(os);
	 pw.printf("B\n");
         pw.flush();

	 Scanner sc = new Scanner(is);
	 String line = sc.nextLine();

         System.out.println(line);
	      
	 proc.waitFor();

         int exitVal = proc.exitValue();

         System.out.println("Process exited: " + exitVal);
      } 
      catch (Throwable t)
      {
	 t.printStackTrace();
      }
   }
}

