package library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class AddUser extends JFrame {

    private JTextField txtName, txtUserId;

    public AddUser() {
        setTitle("Add User");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblName = new JLabel("Name:");
        lblName.setPreferredSize(new Dimension(50, 25));
        JLabel lblUserId = new JLabel("User ID:");
        lblUserId.setPreferredSize(new Dimension(50, 25));

        txtName = new JTextField();
        txtName.setPreferredSize(new Dimension(150, 25));
        txtUserId = new JTextField();
        txtUserId.setPreferredSize(new Dimension(150, 25));

        JButton btnSave = new JButton("Save");
        btnSave.setPreferredSize(new Dimension(100, 30));

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUser();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblUserId, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtUserId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnSave, gbc);

        add(panel);

        setVisible(true);
    }

    private void saveUser() {
        String name = txtName.getText();
        String userId = txtUserId.getText();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","a1b2c3d4")) {
            String sql = "INSERT INTO users (name, user_id) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, userId);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "User added successfully!");
                setVisible(false);
                new MainDashboard();
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(this, "User ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            setVisible(false);
            new MainDashboard();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AddUser();
            }
        });
    }
}
