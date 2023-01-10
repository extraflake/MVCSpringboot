package com.example.demo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.config.DBConnection;
import com.example.demo.models.Division;

public class DivisionDAO {
    private Connection con;
    private RegionDAO regionDAO = new RegionDAO(DBConnection.getConnection());

    public DivisionDAO(Connection connection) {
        con = connection;
    }

    public List<Division> getAll() {
        List<Division> divisions = new ArrayList<>();
        String query = "SELECT d.id, d.name, r.id FROM tb_m_division";
        try {
            ResultSet resultSet = con.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                Division division = new Division();
                division.setId(resultSet.getInt(1));
                division.setName(resultSet.getString(2));
                division.setRegion(regionDAO.getById(resultSet.getInt(3)));
                divisions.add(division);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return divisions;
    }

    public com.example.demo.dto.Division getById(Integer id) {
        com.example.demo.dto.Division division = new com.example.demo.dto.Division();
        String query = "SELECT * FROM tb_m_division where id = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                division.setId(resultSet.getInt(1));
                division.setName(resultSet.getString(2));
                division.setRegion(resultSet.getInt(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return division;
    }

    public Boolean insert(com.example.demo.dto.Division division) {
        Integer result = 0;
        try {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO tb_m_division (name, regionId) VALUES (?, ?)");
            preparedStatement.setString(1, division.getName());
            preparedStatement.setInt(2, division.getRegion());
            result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    public Boolean update(com.example.demo.dto.Division division) {
        Integer result = 0;
        try {
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE tb_m_division SET name = ?, regionId = ? WHERE id = ?");
            preparedStatement.setString(1, division.getName());
            preparedStatement.setInt(2, division.getRegion());
            preparedStatement.setInt(3, division.getId());
            result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    public Boolean delete(Integer id) {
        Integer result = 0;
        try {
            PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM tb_m_division WHERE id = ?");
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result > 0;
    }
}
