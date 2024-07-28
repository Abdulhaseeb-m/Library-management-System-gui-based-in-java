package library;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainDashboard extends JFrame {

    public MainDashboard() {
        setTitle("Library Management System");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Library Management System", SwingConstants.CENTER);
        JButton btnAddBook = new JButton("Add Book");
        JButton btnRemoveBook = new JButton("Remove Book");
        JButton btnViewBooks = new JButton("View Books");
        JButton btnAddUser = new JButton("Add User");
        JButton btnRemoveUser = new JButton("Remove User");
        JButton btnViewUsers = new JButton("View Users");
        Dimension buttonSize = new Dimension(150, 30);
        btnAddBook.setPreferredSize(buttonSize);
        btnRemoveBook.setPreferredSize(buttonSize);
        btnViewBooks.setPreferredSize(buttonSize);
        btnAddUser.setPreferredSize(buttonSize);
        btnRemoveUser.setPreferredSize(buttonSize);
        btnViewUsers.setPreferredSize(buttonSize);
        btnAddBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddBook();
            }
        });

        btnRemoveBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemoveBook();
            }
        });

        btnViewBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewBooks();
            }
        });

        btnAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddUser();
            }
        });
        btnRemoveUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemoveUser();
            }
        });
        btnViewUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewUsers();
            }
        });
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 10, 10, 10);
        g.gridx = 0;
        g.gridy = 0;
        buttonPanel.add(titleLabel, g);g.gridy++;
        buttonPanel.add(btnAddBook, g);g.gridy++;
        buttonPanel.add(btnRemoveBook, g);g.gridy++;
        buttonPanel.add(btnViewBooks, g);g.gridy++;
        buttonPanel.add(btnAddUser, g);g.gridy++;
        buttonPanel.add(btnRemoveUser, g);g.gridy++;
        buttonPanel.add(btnViewUsers, g);

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainDashboard();
            }
        });
    }
}
