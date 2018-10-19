/**
 * Created by songwei on 10/07/2017.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FineGUI extends JFrame implements ActionListener
{
    public JFrame frame;
    public JTextField textCardID, textEnterPayment;
    public JLabel lblCardID, lblInfo1, lblInfo2, lblEnterPayment;
    public static String CardID;
    public static double fineAmt, estFineAmt, fineRecord;
    public static JButton btnCheckFine, btnPayFine, btnUpdate, btnMain, btnEnterPayment;

    public FineGUI(String header)
    {
        super(header);
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lblCardID = new JLabel("Card No.");
        lblCardID.setBounds(26, 59, 80, 18);
        getContentPane().add(lblCardID);

        textCardID = new JTextField();
        textCardID.setBounds(120, 56, 165, 20);
        getContentPane().add(textCardID);
        textCardID.setColumns(10);

        lblEnterPayment = new JLabel("Enter Payment: ");
        lblEnterPayment.setBounds(26, 86, 120, 18);
        getContentPane().add(lblEnterPayment);

        textEnterPayment = new JTextField();
        textEnterPayment.setBounds(120, 86, 165,20);
        getContentPane().add(textEnterPayment);
        textEnterPayment.setColumns(10);

        btnEnterPayment = new JButton("Enter");
        btnEnterPayment.setBounds(280,86,60,23);
        btnEnterPayment.setForeground(Color.blue);
        getContentPane().add(btnEnterPayment);
        btnEnterPayment.addActionListener(this);


        btnCheckFine = new JButton("Check Fine");
        btnCheckFine.setBounds(47, 173, 124, 23);
        getContentPane().add(btnCheckFine);
        btnCheckFine.addActionListener(this);

        btnPayFine = new JButton("Pay Fine");
        btnPayFine.setBounds(47, 207, 124, 23);
        getContentPane().add(btnPayFine);
        btnPayFine.addActionListener(this);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(47, 139, 124, 23);
        getContentPane().add(btnUpdate);
        btnUpdate.addActionListener(this);

        btnMain = new JButton("Return");
        btnMain.setBounds(450, 350, 124, 23);
        getContentPane().add(btnMain);
        btnMain.addActionListener(this);

        lblInfo1 = new JLabel("");
        lblInfo1.setBounds(55, 286, 203, 32);
        getContentPane().add(lblInfo1);

        lblInfo2 = new JLabel("");
        lblInfo2.setBounds(47, 329, 187, 34);
        getContentPane().add(lblInfo2);

        JLabel lblNewLabel = new JLabel("***Please click 'Update' before Checking or Paying Fine***");
        lblNewLabel.setBounds(26, 11, 500, 18);
        getContentPane().add(lblNewLabel);


    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btnMain)
        {
            setVisible(false);
            SearchGUI.frame.setVisible(true);
        }
        else if(e.getSource() == btnCheckFine)
        {
            lblInfo2.setText(" ");
            CardID = textCardID.getText();
            Fine.check();
            lblInfo2.setText("Please pay fine: " + "$" + Fine.bd);


        }
        else if(e.getSource() == btnPayFine)
        {
            Fine.payFine();
            lblInfo1.setText("Fine paid");
            lblInfo2.setText("");
        }
        else if(e.getSource() == btnUpdate)
        {
            fineAmt = 0.00;
            estFineAmt = 0.00;
            Fine.update();
            lblInfo1.setText("");
            lblInfo2.setText("");
        }
        else if(e.getSource() == btnEnterPayment)
        {
            fineRecord = Double.parseDouble(textEnterPayment.getText());
            Fine.record();
            lblInfo2.setText("Payment recorded.");

        }



    }
}
