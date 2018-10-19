/**
 * Created by songwei on 17/07/2017.
 */
import com.sun.org.apache.regexp.internal.RE;

import javax.xml.transform.Result;
import java.sql.*;

public class Checkin {

    static Connection conn = null;

    public static String BOOK_ID;
    public static String Card_ID;


    static public void update()
    {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "songwei1986";

        try {
            conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("use LIBRARY;");
            System.out.println("hey");
            stmt.executeUpdate("UPDATE Book_Loans SET Date_in=SYSDATE() WHERE ISBN LIKE '%"+BOOK_ID+"%' AND Card_ID LIKE '%"+Card_ID+"%';");
            stmt.executeUpdate("UPDATE book_copies SET copies_available = copies_available + 1 where Book_id LIKE '%"+ BOOK_ID +"%';");
            CheckoutGUI.noCopies--;

            conn.close();
            System.out.println("Success!!");

        }

        catch(SQLException ex)
        {
            System.out.println("Error in connection: " + ex.getMessage());
        }


    }

    static public void borrower()
    {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "songwei1986";

        int i = 0;
        try {
            conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("use LIBRARY;");
            ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK_LOANS,BORROWER WHERE BORROWER.fname LIKE '%"+CheckinGUI.borrowerName+"%' AND BORROWER.CARD_ID=BOOK_LOANS.CARD_ID;");

            while (rs.next()) {


                CheckinGUI.row[i][0] = rs.getString("Loan_ID");
                CheckinGUI.row[i][1] = rs.getString("ISBN");
                CheckinGUI.row[i][2] = rs.getString("Card_ID");
                CheckinGUI.row[i][3] = rs.getDate("Date_out");
                CheckinGUI.row[i][4] = rs.getDate("Due_date");
                CheckinGUI.row[i][5] = rs.getDate("Date_in");

                i++;
            }


            rs.close();
            conn.close();
            System.out.println("Success!!");

        }

        catch(SQLException ex)
        {
            System.out.println("Error in connection: " + ex.getMessage());
        }


    }

    static public void bookID()
    {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "songwei1986";


        int i = 0;
        try {
            conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("use LIBRARY;");
            ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK_LOANS,BORROWER WHERE BOOK_LOANS.ISBN LIKE '%"+CheckinGUI.bookID+"%' AND BORROWER.CARD_ID=BOOK_LOANS.CARD_ID;");

            while (rs.next()) {

                CheckinGUI.row[i][0] = rs.getString("Loan_ID");
                CheckinGUI.row[i][1] = rs.getString("Isbn");
                CheckinGUI.row[i][2] = rs.getString("Card_ID");
                CheckinGUI.row[i][3] = rs.getDate("Date_out");
                CheckinGUI.row[i][4] = rs.getDate("Due_date");
                CheckinGUI.row[i][5] = rs.getDate("Date_in");

                i++;
            }


            rs.close();
            conn.close();
            System.out.println("Success!!");

        }

        catch(SQLException ex)
        {
            System.out.println("Error in connection: " + ex.getMessage());
        }


    }
    static public void bookBorrower()
    {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "songwei1986";

        int i = 0;

        try {
            conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("use LIBRARY;");
            ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK_LOANS,BORROWER WHERE BOOK_LOANS.ISBN LIKE '%"+CheckinGUI.bookID+"%' AND Borrower.Fname LIKE '%"+CheckinGUI.borrowerName+"%' AND BORROWER.CARD_ID=BOOK_LOANS.Card_ID;");

            while (rs.next()) {



                CheckinGUI.row[i][0] = rs.getString("Loan_ID");
                CheckinGUI.row[i][1] = rs.getString("ISBN");
                //CheckinGUI.row[i][2] = rs.getString("Branch_id");
                CheckinGUI.row[i][3] = rs.getString("Card_ID");
                CheckinGUI.row[i][4] = rs.getDate("Date_out");
                CheckinGUI.row[i][5] = rs.getDate("Due_date");
                CheckinGUI.row[i][6] = rs.getDate("Date_in");

                i++;
            }


            rs.close();
            conn.close();
            //System.out.println("Success!!");

        }

        catch(SQLException ex)
        {
            System.out.println("Error in connection: " + ex.getMessage());
        }


    }

    static public void bookCard()
    {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "songwei1986";


        int i = 0;



        try {
            conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("use LIBRARY;");
            ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK_LOANS,BORROWER WHERE BOOK_LOANS.ISBN LIKE '%"+CheckinGUI.bookID+"%' AND Book_Loans.Card_ID LIKE '%"+CheckinGUI.cardID+"%' AND BORROWER.CARD_ID=BOOK_LOANS.Card_ID;");

            while (rs.next())
            {


                CheckinGUI.row[i][0] = rs.getString("Loan_ID");
                CheckinGUI.row[i][1] = rs.getString("ISBN");
                CheckinGUI.row[i][2] = rs.getString("Card_ID");
                CheckinGUI.row[i][3] = rs.getDate("Date_out");
                CheckinGUI.row[i][4] = rs.getDate("Due_date");
                CheckinGUI.row[i][5] = rs.getDate("Date_in");

                i++;
            }


            rs.close();
            conn.close();
            //System.out.println("Success!!");

        }

        catch(SQLException ex)
        {
            System.out.println("Error in connection: " + ex.getMessage());
        }


    }

    static public void bookCardName()
    {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "songwei1986";


        int i = 0;



        try {
            conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("use LIBRARY;");
            ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK_LOANS,BORROWER WHERE BOOK_LOANS.ISBN LIKE '%"+CheckinGUI.bookID+"%' AND Book_Loans.Card_ID LIKE '%"+CheckinGUI.cardID+"%' AND Borrower.Fname LIKE '%"+CheckinGUI.borrowerName+"%' AND BORROWER.CARD_ID = BOOK_LOANS.Card_ID;");

            while (rs.next()) {


                CheckinGUI.row[i][0] = rs.getString("Loan_id");
                CheckinGUI.row[i][1] = rs.getString("Isbn");
                CheckinGUI.row[i][2] = rs.getString("Card_ID");
                CheckinGUI.row[i][3] = rs.getDate("Date_out");
                CheckinGUI.row[i][4] = rs.getDate("Due_date");
                CheckinGUI.row[i][5] = rs.getDate("Date_in");

                i++;
            }


            rs.close();
            conn.close();
            //System.out.println("Success!!");

        }

        catch(SQLException ex)
        {
            System.out.println("Error in connection: " + ex.getMessage());
        }


    }



    static public void cardID()
    {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "songwei1986";


        int i = 0;



        try {
            conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("use LIBRARY;");
            String query = "SELECT * FROM BOOK_LOANS,BORROWER WHERE BOOK_LOANS.CARD_ID LIKE '%"+CheckinGUI.cardID+"%' AND BORROWER.CARD_ID=BOOK_LOANS.Card_ID;";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {


                CheckinGUI.row[i][0] = rs.getString("Loan_id");
                CheckinGUI.row[i][1] = rs.getString("Isbn");
                CheckinGUI.row[i][2] = rs.getString("Card_ID");
                CheckinGUI.row[i][3] = rs.getDate("Date_out");
                CheckinGUI.row[i][4] = rs.getDate("Due_date");
                CheckinGUI.row[i][5] = rs.getDate("Date_in");

                i++;
            }


            rs.close();
            conn.close();
            //System.out.println("Success!!");

        }

        catch(SQLException ex)
        {
            System.out.println("Error in connection: " + ex.getMessage());
        }


    }
}
