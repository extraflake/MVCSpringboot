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
}
