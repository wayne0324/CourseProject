/**
 * Created by songwei on 10/07/2017.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

@SuppressWarnings("serial")
public class CheckoutGUI extends JFrame implements ActionListener
{
    public JFrame frame;
    public JTextField textBookID, textCardID;
    public JLabel lblBookID, lblCardID, lblInfo;
    public JButton btnCheckout, btnMain;

    public static String bookTitile, cardName, cardID, bookID;
    public static int noCopies, copiesAvailable;

    public static Date date = new Date();

    public CheckoutGUI(String header)
    {
        super(header);
        setSize(500,380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lblBookID = new JLabel("Book ID");
        lblBookID.setBounds(37, 73, 131, 14);
        getContentPane().add(lblBookID);

        lblCardID = new JLabel("Card No.");
        lblCardID.setBounds(37, 120, 131, 14);
        getContentPane().add(lblCardID);

        textBookID = new JTextField();
        textBookID.setBounds(262, 70, 150, 20);
        getContentPane().add(textBookID);
        textBookID.setColumns(10);

        textCardID = new JTextField();
        textCardID.setBounds(262, 120, 150, 20);
        getContentPane().add(textCardID);
        textCardID.setColumns(10);

        btnCheckout = new JButton("CheckOut");
        btnCheckout.setBounds(30, 180, 131, 23);
        getContentPane().add(btnCheckout);
        btnCheckout.addActionListener(this);

        btnMain = new JButton("Return");
        btnMain.setBounds(350, 300, 131, 23);
        getContentPane().add(btnMain);
        btnMain.addActionListener(this);

        lblInfo = new JLabel("Info: ");
        lblInfo.setBounds(37, 260, 486, 33);
        getContentPane().add(lblInfo);

        if(SearchGUI.indicate == 1)
        {
            textBookID.setText(bookID);
            System.out.println("Book is available");
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btnMain)
        {
            setVisible(false);
            SearchGUI.frame.setVisible(true);
        }
        else if(e.getSource() == btnCheckout)
        {
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.DATE, 14);
            String date = df.format(c.getTime());
            System.out.println(date);

            cardID = textCardID.getText();

            Checkout.check();
            if(Checkout.flag == 0)
            {
                if(noCopies == 3)
                {
                    lblInfo.setText("Sorry, not allowed to checkout more than 3 books");

                }

                else if(copiesAvailable == 0)
                {
                    lblInfo.setText("Sorry, no copy is available");
                }

            }
            else
            {
                lblInfo.setText("Thank you.");
            }
        }

    }
}
