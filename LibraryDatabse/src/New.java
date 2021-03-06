import java.sql.*;
import java.text.DecimalFormat;


public class New
{

    static Connection conn = null;

    public static Object data[][]=new Object[1000][1000];
    public static int CardID;

    public static String cardResult;

    public static void select()
    {

        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "songwei1986");


            Statement stmt = conn.createStatement();

            stmt.execute("use LIBRARY;");

            int i =0;
            ResultSet rs = stmt.executeQuery("SELECT Ssn, fname, lname, Address FROM BORROWER WHERE Ssn='"+NewGUI.ssn+"' AND fname LIKE '%"+NewGUI.fname+"%' AND lname LIKE '%"+NewGUI.lname+"%' AND Address LIKE '%"+ NewGUI.address+"%';");



            while (rs.next()) {

                data[i][0]=rs.getString("Ssn");
                data[i][1]=rs.getString("fname");
                data[i][2]=rs.getString("lname");
                data[i][3]=rs.getString("Address");

                i++;
            }

            rs.close();
            conn.close();

            if(i>0)
            {
                NewGUI.remove=1;
            }
            else NewGUI.remove=0;

            System.out.println("Success");
        }
        catch(SQLException ex) {
            System.out.println("Error in connection: " + ex.getMessage());
        }


    }

    public static void create()
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "songwei1986");

            Statement stmt = conn.createStatement();


            stmt.execute("use library;");

            ResultSet rs = stmt.executeQuery("SELECT MAX(Card_id) as Card FROM BORROWER;");
            rs.next();

            String cardString = rs.getString("Card");
            String cardID = cardString.substring(0, 2);
            CardID = Integer.parseInt(cardString.substring(2));
            CardID++;
            DecimalFormat df = new DecimalFormat("000000");
            cardResult = cardID.concat(df.format(CardID));

            stmt.executeUpdate("INSERT INTO BORROWER(Card_id, Ssn, fname, lname, Address, Phone) VALUES" + "('" +cardResult+"','"+ NewGUI.ssn+"','"+NewGUI.fname + "','"+NewGUI.lname+"','"+NewGUI.address+"','"+NewGUI.phone+"');");
            conn.close();

        }
        catch(SQLException ex)
        {
            System.out.println("Error in connection: " + ex.getMessage());
        }
    }

    public static void main(String args[])
    {

    }
}

