package com.my.servlet;

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
    public static User selectOne(int id) {

        User user = null;
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "SELECT * FROM users WHERE id=?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){

                        int userId = resultSet.getInt(1);
                        String u_login = resultSet.getString(2);
                        String u_password = resultSet.getString(3);
                        user = new User(userId, u_login, u_password);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return user;
    }
    public static int insert(User user) {

        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "INSERT INTO users (u_login, u_password) Values (?, ?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, user.getU_login());
                    preparedStatement.setString(2, user.getU_password());

                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }

    public static int update(User user) {

        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "UPDATE users SET u_login = ?, u_password = ? WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, user.getU_login());
                    preparedStatement.setString(2, user.getU_password());
                    preparedStatement.setInt(3, user.getId());

                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
    public static int delete(int id) {

        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "DELETE FROM users WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);

                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }

    public static User selectOne(String login) {

        User user = null;
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String sql = "SELECT * FROM users WHERE u_login=?";
                try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                    preparedStatement.setString(1, login);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){

                        int id = resultSet.getInt(1);
                        String u_login = resultSet.getString(2);
                        String u_password = resultSet.getString(3);
                        user = new User(id, u_login, u_password);
                    }
                }
            }
        } catch(Exception ex){
            System.out.println(ex);
        }

        return user;
    }
}
