/**
 * Created by songwei on 10/07/2017.
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class NewGUI extends JFrame implements ActionListener
{


    public JFrame frame;
    public JTextField textFirstName,textLastName, textAddress,textPhone,textSsn;
    public JLabel lblFirstName, lblLastName, lblAddress, lblPhone, lblSsn, lblError;
    public JButton btnSignup, btnMain;
    public static String ssn, fname, lname, address, phone, state, city;
    public static int remove;



    public NewGUI(String header)
    {

        super(header);
        setSize(800,400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lblFirstName = new JLabel("First Name");
        lblFirstName.setBounds(22, 64, 80, 14);
        getContentPane().add(lblFirstName);

        lblLastName = new JLabel("Last Name");
        lblLastName.setBounds(22, 103, 80, 14);
        getContentPane().add(lblLastName);

        lblAddress = new JLabel("Address");
        lblAddress.setBounds(22, 141, 80, 14);
        getContentPane().add(lblAddress);

        lblPhone = new JLabel("Phone");
        lblPhone.setBounds(22, 213, 80, 14);
        getContentPane().add(lblPhone);

        lblError = new JLabel("error");
        lblError.setBounds(22, 300, 397, 65);
        getContentPane().add(lblError);

        lblSsn = new JLabel("SSN");
        lblSsn.setBounds(22, 31, 80, 14);
        getContentPane().add(lblSsn);

        textSsn = new JTextField();
        textSsn.setBounds(149, 28, 209, 20);
        getContentPane().add(textSsn);
        textSsn.setColumns(10);

        textFirstName = new JTextField();
        textFirstName.setBounds(149, 61, 209, 20);
        getContentPane().add(textFirstName);
        textFirstName.setColumns(10);

        textLastName = new JTextField();
        textLastName.setBounds(149, 100, 209, 20);
        getContentPane().add(textLastName);
        textLastName.setColumns(10);

        textAddress = new JTextField();
        textAddress.setBounds(149, 138, 209, 47);
        getContentPane().add(textAddress);
        textAddress.setColumns(10);

        textPhone = new JTextField();
        textPhone.setBounds(149, 210, 209, 20);
        getContentPane().add(textPhone);
        textPhone.setColumns(10);

        btnSignup = new JButton("Sign Up");
        btnSignup.setBounds(506, 77, 209, 23);
        getContentPane().add(btnSignup);
        btnSignup.addActionListener(this);

        btnMain = new JButton("Return");
        btnMain.setBounds(700, 350, 100, 23);
        getContentPane().add(btnMain);
        btnMain.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btnMain)
        {
            setVisible(false);
            SearchGUI.frame.setVisible(true);
        }
        else if(e.getSource() == btnSignup)
        {
            ssn = textSsn.getText();
            fname = textFirstName.getText();
            lname = textLastName.getText();
            address = textAddress.getText();
            phone = textPhone.getText();
            New.select();

            if(ssn.isEmpty() || fname.isEmpty() || lname.isEmpty() || address.isEmpty() || phone.isEmpty())
            {
                lblError.setText("Please fill in all the information");
            }
            else
            {
                if (!phone.matches("[0-9]+"))
                {
                    lblError.setText("Invalid Phone Number: Only Digits Allowed");
                }
                else if (remove == 1)
                {
                    System.out.println("Reject");
                    lblError.setText("User already exists");
                }
                else
                {
                    New.create();
                   // lblError.setText("Your Card Number is: " + New.cardResult());
                }

            }
        }
    }

}
