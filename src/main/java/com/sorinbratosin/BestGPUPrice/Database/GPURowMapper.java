package com.sorinbratosin.BestGPUPrice.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GPURowMapper {

    public GPU mapRow(ResultSet rs) throws SQLException {
        GPU gpu = new GPU();
            gpu.setId(rs.getInt("id"));
            gpu.setName(rs.getString("name"));
            gpu.setPrice(rs.getDouble("price"));
            gpu.setAvailable(rs.getBoolean("availability"));
            gpu.setUrl(rs.getString("url"));
        return gpu;
    }

}
