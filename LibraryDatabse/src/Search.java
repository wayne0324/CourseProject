/**
 * Created by songwei on 16/07/2017.
 */
import java.sql.*;
public class Search {

    static Connection conn = null;

    public static int checkedOut;

    public static void nameIsbn(){



        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "songwei1986");


            Statement stmt = conn.createStatement();

            stmt.execute("use LIBRARY;");

            int i =0;
            ResultSet rs = stmt.executeQuery("SELECT BOOK.ISBN, BOOK.TITLE, AUTHORS.FULLNAME FROM book, authors, book_authors WHERE Book.ISBN LIKE '%"+SearchGUI.isbn+"%'AND Fullname LIKE '%"+SearchGUI.fullname+"%' AND BOOK.Isbn=BOOK_AUTHORS.Isbn AND book_authors.Author_ID=AUTHORS.Author_Id;");

            while (rs.next()) {


                SearchGUI.row[i][0] = rs.getString("Book.ISBN");
                SearchGUI.row[i][1] = rs.getString("BOOK.Title");
                SearchGUI.row[i][2] = rs.getString("Authors.Fullname");
                SearchGUI.row[i][3] = rs.getInt("No_of_copies");
                SearchGUI.row[i][4] = rs.getInt("copies_available");

                i++;
            }

            rs.close();
            conn.close();
        }
        catch(SQLException ex) {
            System.out.println("Error in connection: " + ex.getMessage());
        }
    }

    public static void nameTitle(){

        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "songwei1986");


            Statement stmt = conn.createStatement();

            stmt.execute("use library;");

            int i =0;

            ResultSet rs = stmt.executeQuery("SELECT BOOK.ISBN,BOOK.TITLE,AUTHORS.FULLNAME FROM book,authors,book_authors WHERE Book.Title LIKE '%"+SearchGUI.booktitle+"%' AND Fullname LIKE '%"+SearchGUI.fullname+"%' AND BOOK.ISBN = BOOK_AUTHORS.ISBN AND BOOK_AUTHORS.Author_ID=AUTHORS.Author_ID;");

            while (rs.next()) {


                SearchGUI.row[i][0] = rs.getString("Book.ISBN");
                SearchGUI.row[i][1] = rs.getString("BOOK.Title");
                SearchGUI.row[i][2] = rs.getString("Authors.Fullname");
                SearchGUI.row[i][3] = rs.getInt("No_of_copies");
                SearchGUI.row[i][4] = rs.getInt("copies_available");

                i++;
            }

            rs.close();
            conn.close();
        }
        catch(SQLException ex) {
            System.out.println("Error in connection: " + ex.getMessage());
        }
    }

    public static void isbnTitle(){



        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "songwei1986");


            Statement stmt = conn.createStatement();

            stmt.execute("use library;");

            int i =0;

            ResultSet rs = stmt.executeQuery("SELECT BOOK.ISBN, BOOK.TITLE, AUTHORS.FULLNAME FROM BOOK, AUTHORS, BOOK_AUTHORS WHERE BOOK.TITLE LIKE '%"+SearchGUI.booktitle+"%' AND BOOK.ISBN LIKE '%"+SearchGUI.isbn+"%' AND BOOK.ISBN = BOOK_AUTHORS.ISBN AND BOOK_AUTHORS.AUTHOR_ID = AUTHORS.AUTHOR_ID;");

            while (rs.next()) {


                SearchGUI.row[i][0] = rs.getString("Book.ISBN");
                SearchGUI.row[i][1] = rs.getString("BOOK.Title");
                SearchGUI.row[i][2] = rs.getString("Authors.Fullname");
                SearchGUI.row[i][3] = rs.getInt("No_of_copies");
                SearchGUI.row[i][4] = rs.getInt("copies_available");

                i++;
            }

            rs.close();
            conn.close();
        }
        catch(SQLException ex) {
            System.out.println("Error in connection: " + ex.getMessage());
        }
    }

    public static void fullname(){



        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "songwei1986");


            Statement stmt = conn.createStatement();


            stmt.execute("use LIBRARY;");

            int i =0;

            //ResultSet rs = stmt.executeQuery("SELECT BOOK.ISBN,BOOK.TITLE,AUTHORS.FULLNAME FROM book,authors,book_authors WHERE Fullname LIKE"+"'%"+SearchGUI.fullname+"%' AND BOOK.Isbn=BOOK_AUTHORS.Isbn AND BOOK.Isbn LIKE CONCAT('%',BOOK_COPIES.Book_id,'%') AND Library_Branch.Branch_id=Book_Copies.Branch_Id AND BOOK.Isbn=BOOK_AUTHORS.Isbn AND book_authors.Author_ID=AUTHORS.Author_Id;");
            ResultSet rs = stmt.executeQuery("SELECT AUTHORS.FULLNAME, BOOK.ISBN, BOOK.TITLE FROM authors, book, book_authors WHERE Fullname LIKE '%"+SearchGUI.fullname+"%' AND AUTHORS.AUTHOR_ID = BOOK_AUTHORS.AUTHOR_ID AND BOOK_AUTHORS.ISBN = BOOK.ISBN;");

            while (rs.next()) {


                SearchGUI.row[i][0] = rs.getString("Book.ISBN");
                SearchGUI.row[i][1] = rs.getString("BOOK.Title");
                SearchGUI.row[i][2] = rs.getString("Authors.Fullname");
                SearchGUI.row[i][3] = rs.getInt("No_of_copies");
                SearchGUI.row[i][4] = rs.getInt("copies_available");

                i++;
            }

            rs.close();
            conn.close();
        }
        catch(SQLException ex) {
            System.out.println("Error in connection: " + ex.getMessage());
        }
    }

    public static void isbn(){



        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "songwei1986");

            Statement stmt = conn.createStatement();

            stmt.execute("use library;");

            int i =0;
            System.out.println("hey");
            //ResultSet rs = stmt.executeQuery("SELECT BOOK.ISBN,BOOK.TITLE,AUTHORS.FULLNAME,BOOK_COPIES.BRANCH_ID,library_branch.branch_name,no_of_copies,copies_available FROM book,authors,book_authors,book_copies,library_branch WHERE Book.ISBN LIKE"+"'%"+SearchGUI.isbn+"%'AND BOOK.Isbn=BOOK_AUTHORS.Isbn AND BOOK.Isbn LIKE CONCAT('%',BOOK_COPIES.Book_id,'%') AND Library_Branch.Branch_id=Book_Copies.Branch_Id AND BOOK.Isbn=BOOK_AUTHORS.Isbn AND book_authors.Author_ID=AUTHORS.Author_Id;");
            ResultSet rs = stmt.executeQuery("SELECT AUTHORS.FULLNAME, BOOK.ISBN, BOOK.TITLE, NUM_OF_COPIES, COPIES_AVAILABLE FROM authors, book, book_authors, book_copies WHERE BOOK.ISBN LIKE '%"+SearchGUI.isbn+"%' AND BOOK_AUTHORS.ISBN = BOOK.ISBN AND AUTHORS.AUTHOR_ID = BOOK_AUTHORS.AUTHOR_ID AND BOOK_COPIES.BOOK_ID = BOOK.ISBN;");
            System.out.println("hi");

            while (rs.next()) {

                System.out.println(rs.getString("Book.Title"));
                SearchGUI.row[i][0] = rs.getString("Book.ISBN");
                SearchGUI.row[i][1] = rs.getString("BOOK.Title");
                SearchGUI.row[i][2] = rs.getString("Authors.Fullname");
                SearchGUI.row[i][3] = rs.getInt("NUM_OF_COPIES");
                SearchGUI.row[i][4] = rs.getInt("COPIES_AVAILABLE");

                i++;
            }

            rs.close();
            conn.close();
        }
        catch(SQLException ex) {
            System.out.println("Error in connection: " + ex.getMessage());
        }
    }

    public static void bookTitle(){



        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "songwei1986");

            Statement stmt = conn.createStatement();


            stmt.execute("use library;");

            int i =0;

            //ResultSet rs = stmt.executeQuery("SELECT BOOK.ISBN,BOOK.TITLE,AUTHORS.FULLNAME,BOOK_COPIES.BRANCH_ID,library_branch.branch_name,no_of_copies,copies_available FROM book,authors,book_authors,book_copies,library_branch WHERE Book.Title LIKE"+"'%"+SearchGUI.booktitle+"%' AND BOOK.Isbn=BOOK_AUTHORS.Isbn AND BOOK.Isbn LIKE CONCAT('%',BOOK_COPIES.Book_id,'%') AND Library_Branch.Branch_id=Book_Copies.Branch_Id AND BOOK.Isbn=BOOK_AUTHORS.Isbn AND book_authors.Author_ID=AUTHORS.Author_Id;");
            ResultSet rs = stmt.executeQuery("SELECT AUTHORS.FULLNAME, BOOK.ISBN, BOOK.TITLE FROM authors, book, book_authors WHERE BOOK.TITLE LIKE '%"+SearchGUI.booktitle+"%' AND BOOK_AUTHORS.ISBN = BOOK.ISBN AND AUTHORS.AUTHOR_ID = BOOK_AUTHORS.AUTHOR_ID;");
            while (rs.next()) {

                SearchGUI.row[i][0] = rs.getString("Book.ISBN");
                SearchGUI.row[i][1] = rs.getString("BOOK.Title");
                SearchGUI.row[i][2] = rs.getString("Authors.Fullname");
                SearchGUI.row[i][3] = rs.getInt("No_of_copies");
                SearchGUI.row[i][4] = rs.getInt("copies_available");

                i++;
            }

            rs.close();
            conn.close();
        }
        catch(SQLException ex) {
            System.out.println("Error in connection: " + ex.getMessage());
        }
    }

    public static void check(){

        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "songwei1986");
            Statement stmt = conn.createStatement();
            stmt.execute("use library;");
            ResultSet rs = stmt.executeQuery("SELECT COPIES_AVAILABLE FROM BOOK_COPIES WHERE Book_id LIKE '%"+CheckoutGUI.bookID+"%';");

            while (rs.next()) {

                SearchGUI.NoCopies = rs.getInt("COPIES_AVAILABLE");

                //checkedOut=rs.getInt("CHECKED");

            }

            rs.close();
            conn.close();
            System.out.println("Success");
        }
        catch(SQLException ex) {
            System.out.println("Error in connection: " + ex.getMessage());
        }
    }



    public static void main(String args[])
    {

    }

}

