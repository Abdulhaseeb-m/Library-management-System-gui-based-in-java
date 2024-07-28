package library;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewBooks extends JFrame {

    public ViewBooks() {
        setTitle("View Books");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        StringBuilder bookList = new StringBuilder();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","a1b2c3d4")) {
            String sql = "SELECT title, author, isbn FROM books";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String isbn = resultSet.getString("isbn");
                bookList.append("TITLE: ").append(title)
                        .append(" ,      AUTHOR: ").append(author)
                        .append(" ,      ISBN: ").append(isbn)
                        .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        textArea.setText(bookList.toString());
        add(new JScrollPane(textArea));

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ViewBooks();
            }
        });
    }
}
