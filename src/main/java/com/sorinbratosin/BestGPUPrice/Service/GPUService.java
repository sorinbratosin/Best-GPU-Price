package com.sorinbratosin.BestGPUPrice.Service;

import com.sorinbratosin.BestGPUPrice.Database.GPU;
import com.sorinbratosin.BestGPUPrice.Database.GPUMySqlDAO;
import java.util.List;

public class GPUService {

    private GPUMySqlDAO gpuMySqlDAO = new GPUMySqlDAO();

    public List<GPU> findAll() {
        return gpuMySqlDAO.findAll();
    }

    public void saveGPU(GPU gpu) {
        gpuMySqlDAO.saveGPU(gpu);
    }

    public void deleteGPU(int id) {
        gpuMySqlDAO.deleteGPU(id);
    }

    public void truncateTable() {
        gpuMySqlDAO.truncateTable();
    }

    public int countAll() {
        return gpuMySqlDAO.countAll();
    }

    public List<GPU> findByName(String name) {
        return gpuMySqlDAO.findByName(name);
    }
}
