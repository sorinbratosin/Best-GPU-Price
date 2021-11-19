package com.sorinbratosin.BestGPUPrice.Database;

import java.util.List;

public interface GPUDAO {

    List<GPU> findAll();
    List<GPU> findByName(String name);
    void saveGPU(GPU gpu);
    void deleteGPU(int id);
    void truncateTable();
    int countAll();
    int countAllWhereNameContains(String name);
    List<GPU> findAllOrderByPrice();
    List<GPU> findAllAvailable();
    List<GPU> findAllAvailableOrderByPrice();
    List<GPU> findAllAvailableThatContainOrderByPrice(String name);
    List<GPU> findAllAvailableThatContain(String name);
    List<GPU> findAllThatContainOrderByPrice(String name);
}
