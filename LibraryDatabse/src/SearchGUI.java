/**
 * Created by songwei on 09/07/2017.
 */
import sun.jvm.hotspot.ui.table.LongCellRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class SearchGUI extends JFrame implements ActionListener
{
    public static String fullname;
    public static String isbn;
    public static String booktitle;

    public static Object row[][] = new Object[5000][5000];
    public static int indicate;
    Object column[] = {"ISBN", "Title", "Author(s)","No_Of_Copies_Owned","No_of_Copies_Avialable"};

    public static SearchGUI frame;
    public JTextField textISBN;
    public JTextField textBookTitle;
    public JTextField textAuthor;

    Object selected[];
    public JLabel lbISBN;
    public JLabel lbBookTitle;
    public JLabel lbAuthor;
    public JLabel lbInfo;

    Checkbox chckISBN = new Checkbox();
    Checkbox chckBookTitle = new Checkbox();
    Checkbox chckAutohr = new Checkbox();
    public JButton btnSearch;
    public JButton btnNewUser;
    public JButton btnCheckout;
    public JButton btnCheckin;
    public JButton btnFine;

    public static String BookID;
    public static int NoCopies;

    public static CheckoutGUI c;
    public static CheckinGUI cin;
    public static FineGUI f;
    public static NewGUI n;

    public final JTable table;

    public SearchGUI(String header)
    {
        super(header);
        setSize(1000,650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(null);


        JLabel lblNewLabel = new JLabel();
        lblNewLabel.setText("CHOOSE ANY ONE OR TWO OPTIONS, Enter VALUE: ");
        lblNewLabel.setBounds(10, 34, 500, 17);
        getContentPane().add(lblNewLabel);

        lbISBN = new JLabel("ISBN");
        lbISBN.setBounds(48,68,46,14);
        getContentPane().add(lbISBN);

        lbBookTitle = new JLabel("Book Title");
        lbBookTitle.setBounds(48,102,77,14);
        getContentPane().add(lbBookTitle);

        lbAuthor = new JLabel("Author");
        lbAuthor.setBounds(48, 143, 77, 14);
        getContentPane().add(lbAuthor);

        lbInfo = new JLabel("");
        lbInfo.setBounds(10, 206, 800, 78);
        getContentPane().add(lbInfo);

        textISBN = new JTextField(20);
        textISBN.setBounds(180,68,197,20);
        getContentPane().add(textISBN);

        textBookTitle = new JTextField(20);
        textBookTitle.setBounds(180,102,197,20);
        getContentPane().add(textBookTitle);

        textAuthor = new JTextField(20);
        textAuthor.setBounds(180,143,197,20);
        getContentPane().add(textAuthor);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(10,186,127,23);
        getContentPane().add(btnSearch);
        btnSearch.addActionListener(this);

        btnNewUser = new JButton("New User");
        btnNewUser.setBounds(10,11,127,20);
        getContentPane().add(btnNewUser);
        btnNewUser.addActionListener(this);

        btnCheckout = new JButton("Check Out");
        btnCheckout.setBounds(217, 186, 127, 23);
        getContentPane().add(btnCheckout);
        btnCheckout.addActionListener(this);

        btnCheckin = new JButton("Check In");
        btnCheckin.setBounds(442, 186, 127, 23);
        getContentPane().add(btnCheckin);
        btnCheckin.addActionListener(this);

        btnFine = new JButton("Fine Details");
        btnFine.setBounds(659, 186, 127, 23);
        getContentPane().add(btnFine);
        btnFine.addActionListener(this);

        table = new JTable(row, column);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(23, 300,940,270);
        getContentPane().add(scrollPane);

        chckISBN.setBounds(20,65,20,23);
        getContentPane().add(chckISBN);
        chckBookTitle.setBounds(20,100,20,23);
        getContentPane().add(chckBookTitle);
        chckAutohr.setBounds(20, 140,20,23);
        getContentPane().add(chckAutohr);

        // scrollPane.setViewportView(table);

    }

    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource() == btnNewUser)
        {

            setVisible(false);
            n = new NewGUI("Fine");
            n.setVisible(true);
        }

        if(e.getSource() == btnSearch)
        {
            if(chckAutohr.getState() && chckISBN.getState())
            {
                lbInfo.setText("");
                fullname = textAuthor.getText();
                isbn = textISBN.getText();
                Search.nameIsbn();

            }

            else if(chckAutohr.getState() && chckBookTitle.getState())
            {
                lbInfo.setText("");
                fullname = textAuthor.getText();
                booktitle = textBookTitle.getText();
                Search.nameTitle();
            }

            else if(chckISBN.getState() && chckBookTitle.getState())
            {
                lbInfo.setText("");
                isbn = textISBN.getText();
                booktitle = textBookTitle.getText();
                Search.isbnTitle();
            }

            else if(chckISBN.getState())
            {
                lbInfo.setText("");
                isbn = textISBN.getText();
                Search.isbn();
            }

            else if(chckBookTitle.getState())
            {
                lbInfo.setText("");
                booktitle = textBookTitle.getText();
                Search.bookTitle();
            }

            else if(chckAutohr.getState())
            {
                lbInfo.setText("");
                fullname = textAuthor.getText();
                Search.fullname();
            }

            else
            {
                lbInfo.setText("Error: Enter BOOK ID, TITLE OR AUTHOR NAME,  OR CHOOSE ANY TWO OF THEM!");
            }
        }

        else if(e.getSource() == btnCheckin)
        {
            int row = 0;
            row = table.getSelectedRow();
            CheckinGUI.book_search = (String)table.getValueAt(row, 0);

            indicate = 1;
            lbInfo.setText("");
            dispose();
            cin = new CheckinGUI("Checkin");
            cin.setVisible(true);
        }

        else if(e.getSource() == btnCheckout)
        {
            int row = 0;
            row = table.getSelectedRow();
            CheckoutGUI.bookID = (String)table.getValueAt(row,0);
            System.out.println("id:" + CheckoutGUI.bookID);
            Search.check();
            //if(NoCopies <= Search.checkedOut)
            if(NoCopies <= 0)
            {
                lbInfo.setText("All available books have been checked out.");
            }
            else
            {
                indicate = 1;
                lbInfo.setText("");
                dispose();
                c = new CheckoutGUI("Checkout");
                c.setVisible(true);
            }
        }

        if(e.getSource() == btnFine)
        {
            dispose();
            f = new FineGUI("Fine");
            f.setVisible(true);
        }
    }

    public static void main(String[] args)
    {
        frame = new SearchGUI("Search");
        frame.setVisible(true);

        //n = new NewGUI("New User");
    }








    
}



