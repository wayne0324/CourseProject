/**
 * Created by songwei on 10/07/2017.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;



@SuppressWarnings("serial")
public class CheckinGUI extends JFrame implements ActionListener
{
    public JFrame frame;
    public static String bookID, borrowerName, cardID, book_search;
    public JTextField textBookID, textName, textCardID;
    public JLabel lblBookID, lblName, lblCardID, lblInfo;
    public JButton btnCheckin, btnSearch, btnMain;
    static public Object row[][] = new Object[5000][5000];
    Object column[] = { "LOAN_ID","ISBN","CARD_ID","DATE_OUT","DUE_DATE","DATE_IN" };
    Object data[];
    public final JTable table_1;
    Checkbox chckBookID = new Checkbox();
    Checkbox chckBorrower = new Checkbox();
    Checkbox chckCardID = new Checkbox();

    public CheckinGUI(String header)
    {
        super(header);
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);


        chckBookID.setBounds(6, 55, 22, 23);
        getContentPane().add(chckBookID);

        chckBorrower.setBounds(6, 85, 22, 23);
        getContentPane().add(chckBorrower);

        chckCardID.setBounds(6, 110, 22, 23);
        getContentPane().add(chckCardID);


        lblBookID = new JLabel("Book ID");
        lblBookID.setBounds(34, 59, 103, 14);
        getContentPane().add(lblBookID);

        lblName = new JLabel("FirstName");
        lblName.setBounds(34, 89, 103, 14);
        getContentPane().add(lblName);

        lblCardID = new JLabel("Card No.");
        lblCardID.setBounds(34, 115, 103, 14);
        getContentPane().add(lblCardID);

        lblInfo = new JLabel("Info: ");
        lblInfo.setBounds(36, 500, 46, 14);
        getContentPane().add(lblInfo);

        textBookID = new JTextField();
        textBookID.setBounds(226, 59, 192, 20);
        getContentPane().add(textBookID);
        textBookID.setColumns(10);

        textName = new JTextField();
        textName.setBounds(226, 88, 192, 20);
        getContentPane().add(textName);
        textName.setColumns(10);

        textCardID = new JTextField();
        textCardID.setBounds(226, 114, 192, 20);
        getContentPane().add(textCardID);
        textCardID.setColumns(10);

        btnCheckin = new JButton("Check IN");
        btnCheckin.setBounds(500, 59, 89, 23);
        getContentPane().add(btnCheckin);
        btnCheckin.addActionListener(this);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(500, 85, 89, 23);
        getContentPane().add(btnSearch);
        btnSearch.addActionListener(this);

        btnMain = new JButton("Return");
        btnMain.setBounds(700, 550, 89, 23);
        getContentPane().add(btnMain);
        btnMain.addActionListener(this);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(42, 164, 712, 295);
        getContentPane().add(scrollPane);

        table_1 = new JTable(row,column);
        scrollPane.setViewportView(table_1);



        if(SearchGUI.indicate==1)
        {
            textBookID.setText(book_search);
            //System.out.println("print");
        }

    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btnMain)
        {
            setVisible(false);
            SearchGUI.frame.setVisible(true);
        }

        else if(e.getSource() == btnCheckin)
        {
            int row = 0;
            row = table_1.getSelectedRow();
            Checkin.BOOK_ID = (String) table_1.getValueAt(row,1);
            Checkin.Card_ID=(String) table_1.getValueAt(row,2);
            Checkin.update();
        }

        else if(e.getSource() == btnSearch)
        {
            borrowerName = textName.getText();
            bookID = textBookID.getText();
            cardID = textCardID.getText();

            if(chckBorrower.getState())
            {
                for(int i=0;i<10;i++)
                {
                    CheckinGUI.row[i][0] = "";
                    CheckinGUI.row[i][1] = "";
                    CheckinGUI.row[i][2] = "";
                    CheckinGUI.row[i][3] = "";
                    CheckinGUI.row[i][4] = "";
                    CheckinGUI.row[i][5] = "";
                }

                lblInfo.setText(" ");
                Checkin.borrower();
            }
            else if(chckBookID.getState())
            {
                for(int i=0;i<10;i++)
                {
                    CheckinGUI.row[i][0] = "";
                    CheckinGUI.row[i][1] = "";
                    CheckinGUI.row[i][2] = "";
                    CheckinGUI.row[i][3] = "";
                    CheckinGUI.row[i][4] = "";
                    CheckinGUI.row[i][5] = "";
                }
                lblInfo.setText(" ");

                //System.out.println("bookID");
                Checkin.bookID();
            }
            else if(chckCardID.getState())
            {
                for(int i=0;i<10;i++)
                {
                    CheckinGUI.row[i][0] = "";
                    CheckinGUI.row[i][1] = "";
                    CheckinGUI.row[i][2] = "";
                    CheckinGUI.row[i][3] = "";
                    CheckinGUI.row[i][4] = "";
                    CheckinGUI.row[i][5] = "";
                }

                lblInfo.setText(" ");
                Checkin.cardID();
            }
            else if(chckBookID.getState() && chckBorrower.getState())
            {
                for(int i=0;i<10;i++)
                {
                    CheckinGUI.row[i][0] = "";
                    CheckinGUI.row[i][1] = "";
                    CheckinGUI.row[i][2] = "";
                    CheckinGUI.row[i][3] = "";
                    CheckinGUI.row[i][4] = "";
                    CheckinGUI.row[i][5] = "";
                }
                lblInfo.setText(" ");
                //System.out.println("bookID");
                Checkin.bookBorrower();


            }
            else if(chckBookID.getState() && chckCardID.getState())
            {
                for(int i=0;i<10;i++)
                {
                    CheckinGUI.row[i][0] = "";
                    CheckinGUI.row[i][1] = "";
                    CheckinGUI.row[i][2] = "";
                    CheckinGUI.row[i][3] = "";
                    CheckinGUI.row[i][4] = "";
                    CheckinGUI.row[i][5] = "";
                }
                lblInfo.setText(" ");
                //System.out.println("bookID");
                Checkin.bookCard();


            }

            else if(chckBookID.getState() && chckCardID.getState() && chckBorrower.getState())
            {
                for(int i=0;i<10;i++)
                {
                    CheckinGUI.row[i][0] = "";
                    CheckinGUI.row[i][1] = "";
                    CheckinGUI.row[i][2] = "";
                    CheckinGUI.row[i][3] = "";
                    CheckinGUI.row[i][4] = "";
                    CheckinGUI.row[i][5] = "";
                }
                lblInfo.setText(" ");
                Checkin.bookCardName();


            }
            else
            {
                lblInfo.setText("Please enter Book ID, Title and/or Author Name.");
            }

        }

    }

}


