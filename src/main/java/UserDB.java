import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;

public class UserDB {

    private static String url = "jdbc:postgresql://localhost:5432/servlets";
    private static String username = "postgres";
    private static String password = "131997";

    public static ArrayList<User> select() {

        ArrayList<User> users = new ArrayList<User>();
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                while(resultSet.next()){

                    int id = resultSet.getInt(1);
                    String u_login = resultSet.getString(2);
                    String u_password = resultSet.getString(3);
                    User user = new User(id, u_login, u_password);
                    users.add(user);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return users;
    }

    public static void saveDocument(StringBuilder data, String filename, String path) {
        try {
            String str = "";
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String n = filename.replaceAll("\\.", "|");
                if (n.split("\\|")[1].equals("doc")){
                    FileInputStream fis = new FileInputStream(path+ "\\" +filename);
                    HWPFDocument document = new HWPFDocument(fis);
                    WordExtractor extractor = new WordExtractor(document);
                    String[] fileData = extractor.getParagraphText();
                    for (int i = 0; i < fileData.length; i++)
                    {
                        if (fileData[i] != null)
                            str+= fileData[i] +" ";
                    }

                } else if(n.split("\\|")[1].equals("docx")) {
                    FileInputStream fis = new FileInputStream(path+ "\\" +filename);
                    XWPFDocument document = new XWPFDocument(fis);
                    XWPFWordExtractor extractor = new XWPFWordExtractor(document);
                    str = extractor.getText();
                } else {
                    str = data.toString();
                }

                PreparedStatement statement = conn.prepareStatement("insert into documents (data) values (?)");
                statement.setString(1, str);
                statement.executeUpdate();

                statement.close();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}