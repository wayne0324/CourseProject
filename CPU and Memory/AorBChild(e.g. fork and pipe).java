// Ozbirn, 02/02/17
// Reads A or B and returns value of a or b

import java.util.Scanner;

public class AorBChild 
{
   public static void main(String args[])
   {
      int a = 5;
      int b = 10;

      Scanner sc = new Scanner(System.in);
 
      String input = null;
      if (sc.hasNext())
         input = sc.nextLine(); 
     
      if (input.charAt(0) == 'A')
          System.out.println(a);
      else
          System.out.println(b);
   }
}
