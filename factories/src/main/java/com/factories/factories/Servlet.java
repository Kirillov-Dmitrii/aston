package com.factories.factories;

import java.io.*;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;


@WebServlet("/factory")
public class Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName(Properties.driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection connection = DriverManager.getConnection(Properties.url, Properties.login, Properties.password);
            System.out.println("Connection successful");

            JSONObject jsonObject = new JSONObject(parser(req));

            PreparedStatement statement = connection.prepareStatement("INSERT INTO factory values (?, ?, ?)");
            statement.setInt(1, jsonObject.getInt("id"));
            statement.setString(2, jsonObject.getString("name"));
            statement.setString(3, jsonObject.getString("city"));

            statement.executeUpdate();
            System.out.println("New factory added to DB");

            statement.close();
            connection.close();

            resp.setStatus(200);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName(Properties.driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection connection = DriverManager.getConnection(Properties.url, Properties.login, Properties.password);

            JSONObject jsonObject = new JSONObject(parser(req));
            PreparedStatement st = connection.prepareStatement("DELETE FROM factory WHERE id=?");
            st.setInt(1, jsonObject.getInt("id"));

            st.executeUpdate();

            st.close();
            connection.close();

            resp.setStatus(200);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName(Properties.driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection connection = DriverManager.getConnection(Properties.url, Properties.login, Properties.password);

            JSONObject jsonReq = new JSONObject(parser(req));
            PreparedStatement st = connection.prepareStatement("SELECT * FROM factory WHERE id=?");
            st.setInt(1, jsonReq.getInt("id"));
            ResultSet resultSet = st.executeQuery();
            Factory factory = new Factory();
            while (resultSet.next()) {
                factory.setId(resultSet.getInt(1));
                factory.setName(resultSet.getString(2));
                factory.setCity(resultSet.getString(3));
            }
            JSONObject jsonResp = new JSONObject(factory);

            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print(jsonResp);
            out.flush();

            st.close();
            connection.close();


            resp.setStatus(200);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String parser(HttpServletRequest req) throws IOException {
        StringBuilder body = new StringBuilder();
        String line = req.getReader().readLine();
        while (line != null) {
            body.append(line);
            line = req.getReader().readLine();
        }
        return body.toString();
    }
}