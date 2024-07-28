package library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveUser extends JFrame {
    private JTextField txtUserId;

    public RemoveUser() {
        setTitle("Remove User");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblUserId = new JLabel("User ID:");
        txtUserId = new JTextField();
        txtUserId.setPreferredSize(new Dimension(150, 25));

        JButton btnRemove = new JButton("Remove");
        btnRemove.setPreferredSize(new Dimension(100, 30));

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeUser();
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblUserId, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(txtUserId, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnRemove, gbc);

        add(panel);

        setVisible(true);
    }

    private void removeUser() {
        String userId = txtUserId.getText();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","a1b2c3d4")) {
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userId);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "User removed successfully!");
                setVisible(false);
                new MainDashboard();
            } else {
                JOptionPane.showMessageDialog(this, "User not found!");
                setVisible(false);
                new MainDashboard();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RemoveUser();
            }
        });
    }
}
