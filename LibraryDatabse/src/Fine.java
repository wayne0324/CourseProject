import java.sql.*;
import java.math.BigDecimal;
public class Fine
{
    public static BigDecimal bd;

    static Connection conn = null;

    static public void update()
    {
        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "songwei1986");


            Statement stmt = conn.createStatement();


            stmt.execute("use library;");


            stmt.executeUpdate("UPDATE FINES,BOOK_LOANS SET FINES.fine_amt = Datediff(SYSDATE(),BOOK_LOANS.Due_date)*0.25 WHERE BOOK_LOANS.loan_id=FINES.loan_id AND FINES.paid=0 AND Date_in IS NULL;");
            stmt.executeUpdate("UPDATE FINES,BOOK_LOANS SET FINES.fine_amt = Datediff(BOOK_LOANS.Date_in,BOOK_LOANS.Due_date)*0.25 WHERE BOOK_LOANS.loan_id=FINES.loan_id AND FINES.paid=0 AND Date_in > ADDDATE(Date_out,INTERVAL 14 DAY);");
            stmt.executeUpdate("UPDATE FINES SET fine_amt=0.00 WHERE fine_amt<0.00");

            conn.close();
            //System.out.println("Success");
        }
        catch(SQLException ex) {
            System.out.println("Error in connection: " + ex.getMessage());
        }

    }

    static public void check()
    {


        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "songwei1986");

            Statement stmt = conn.createStatement();

            stmt.execute("use library;");

            ResultSet rs = stmt.executeQuery("SELECT SUM(fine_amt) FROM FINES,BOOK_LOANS WHERE FINES.LOAN_ID=BOOK_LOANS.LOAN_ID AND CARD_ID LIKE '%"+FineGUI.CardID+"%' AND paid=FALSE AND Date_in > ADDDATE(Date_out,INTERVAL 14 DAY) Group By Card_ID;");

            while (rs.next()) {
                FineGUI.fineAmt = rs.getDouble("SUM(fine_amt)");
                //BigDecimal bd = new BigDecimal(FineGUI.fineAmt);
            }
            bd = new BigDecimal(FineGUI.fineAmt);

            bd = bd.setScale(2);

            System.out.println("fineAmt= "+FineGUI.fineAmt + "bd = "+bd);

            rs.close();
            conn.close();
            //System.out.println("Success");
        }
        catch(SQLException ex) {
            System.out.println("Error in connection: " + ex.getMessage());
        }
    }


    static public void payFine()
    {
        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "songwei1986");

            Statement stmt = conn.createStatement();


            stmt.execute("use library;");


            stmt.executeUpdate("UPDATE FINES,BOOK_LOANS SET fine_amt=0.00,paid=1 WHERE Card_ID LIKE '%"+FineGUI.CardID+"%' AND BOOK_LOANS.loan_id=FINES.loan_id AND Date_in IS NOT NULL;");


            conn.close();
            //System.out.println("Success");
        }
        catch(SQLException ex) {
            System.out.println("Error in connection: " + ex.getMessage());
        }

    }

    static public void record()
    {
        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "songwei1986");

            Statement stmt = conn.createStatement();

            stmt.execute("use library;");



            stmt.executeUpdate("UPDATE FINES, BOOK_LOANS SET FINE_RECORD = "+FineGUI.fineRecord+" WHERE FINES.LOAN_ID = BOOK_LOANS.LOAN_ID AND BOOK_LOANS.CARD_ID like '"+FineGUI.CardID+"';" );

            System.out.println("hi");

            conn.close();
        }
        catch(SQLException ex) {
            System.out.println("Error in connection: " + ex.getMessage());
        }
    }


}

