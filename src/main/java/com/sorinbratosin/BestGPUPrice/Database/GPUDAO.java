package com.sorinbratosin.BestGPUPrice.Database;

import java.sql.SQLException;
import java.util.List;

public interface GPUDAO {

    List<GPU> findAll();
    List<GPU> findByName(String name);
    void saveGPU(GPU gpu);
    void deleteGPU(int id);
    void truncateTable();
    int countAll() throws SQLException;
}
