package library;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewUsers extends JFrame {

    public ViewUsers() {
        setTitle("View Users");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        StringBuilder userList = new StringBuilder();


        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","a1b2c3d4")) {
            String sql = "SELECT name, user_id FROM users";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String userId = resultSet.getString("user_id");
                userList.append("NAME: ").append(name)
                        .append(" ,      USER ID: ").append(userId)
                        .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        textArea.setText(userList.toString());
        add(new JScrollPane(textArea));

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ViewUsers();
            }
        });
    }
}
