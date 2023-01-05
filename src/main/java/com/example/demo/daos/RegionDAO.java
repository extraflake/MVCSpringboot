package com.example.demo.daos;

import java.sql.*;
import java.util.*;

import com.example.demo.models.Region;

public class RegionDAO {
    private Connection con;
    
    public RegionDAO(Connection connection){
        this.con = connection;
    }

    public List<Region> getAll(){
        List<Region> regions = new ArrayList<>();
        String query = "SELECT * FROM tb_m_region";
        try {
            ResultSet resultSet = con.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                Region region = new Region();
                region.setId(resultSet.getInt(1));
                region.setName(resultSet.getString(2));
                regions.add(region);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regions;
    }

    public Region getById(Integer id) {
        Region region = new Region();
        String query = "SELECT * FROM tb_m_region WHERE id = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                region.setId(resultSet.getInt(1));
                region.setName(resultSet.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return region;
    }

    public Boolean insert(Region region) {
        Integer result = 0;
        try {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO tb_m_region (regionName) VALUES (?)");
            preparedStatement.setString(1, region.getName());
            result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    public Boolean update(Region region) {
        Integer result = 0;
        try {
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE tb_m_region SET name = ? WHERE id = ?");
            preparedStatement.setString(1, region.getName());
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
            PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM tb_m_region WHERE id = ?");
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result > 0;
    }
}
