import java.sql.*;
public class Checkout {

    public static int flag=0;
    static Connection conn = null;


    public static void check()
    {

        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "songwei1986");

            Statement stmt = conn.createStatement();

            stmt.execute("use LIBRARY;");


            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS NUM_OF_CHECKOUTS FROM BOOK_LOANS WHERE Card_ID LIKE '%" +CheckoutGUI.cardID+"%';");


            while (rs.next()) {

                CheckoutGUI.noCopies = rs.getInt("NUM_OF_CHECKOUTS");
                System.out.println("No of Copies"+ CheckoutGUI.noCopies);  //Check out times of a certain people
            }
            ResultSet rs1 = stmt.executeQuery("SELECT COPIES_AVAILABLE FROM BOOK_COPIES WHERE Book_id LIKE '%"+CheckoutGUI.bookID+"%';");
            while(rs1.next())
            {
                CheckoutGUI.copiesAvailable = rs1.getInt("COPIES_AVAILABLE");
                System.out.println("No of Copies"+ CheckoutGUI.copiesAvailable);
            }
            conn.close();
            System.out.println("Success!!");
        }
        catch(SQLException ex) {
            System.out.println("Error in connection: " + ex.getMessage());
        }

        if(CheckoutGUI.noCopies<3 && CheckoutGUI.copiesAvailable>0)
        {
            try
            {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "songwei1986");
                Statement stmt = conn.createStatement();


                stmt.execute("use library;");

                stmt.executeQuery("set foreign_key_checks = 0;");
                stmt.executeUpdate("INSERT INTO BOOK_LOANS (ISBN,CARD_ID,DATE_OUT, DUE_DATE) VALUES ('"+CheckoutGUI.bookID+"','"+CheckoutGUI.cardID+"',SYSDATE(), ADDDATE(SYSDATE(),INTERVAL 14 DAY));");
                stmt.executeQuery("set foreign_key_checks = 1;");

                stmt.executeUpdate("INSERT INTO FINES(LOAN_ID, FINE_AMT, PAID) VALUES (  (SELECT LOAN_ID FROM BOOK_LOANS WHERE ISBN = '"+CheckoutGUI.bookID+"'),(0.00),(FALSE)  );");

                //stmt.executeUpdate("UPDATE BOOK_LOANS, FINES SET FINES.LOAN_ID = BOOK_LOANS.LOAN_ID;  ");
                //stmt.executeUpdate("UPDATE FINES SET FINE_AMT = 0.00, PAID = FALSE WHERE ;");

                ResultSet rs2=stmt.executeQuery("SELECT Copies_Available FROM BOOK_Copies WHERE Book_Id LIKE '%"+CheckoutGUI.bookID+"%';");
                rs2.next();
                int copies = rs2.getInt("COPIES_AVAILABLE")-1;
                stmt.executeUpdate("UPDATE BOOK_COPIES SET COPIES_AVAILABLE = "+copies+" WHERE Book_Id LIKE '%"+CheckoutGUI.bookID+"%';");
                conn.close();
                flag=1;
                System.out.println("Flag =" + flag);

                //System.out.println("Success!");
            }

            catch(SQLException ex)
            {
                System.out.println("Error:"+ex.getMessage());
            }
        }
    }



}
