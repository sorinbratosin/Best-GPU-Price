package com.sorinbratosin.BestGPUPrice.Service;

import com.sorinbratosin.BestGPUPrice.Database.GPU;
import com.sorinbratosin.BestGPUPrice.Database.GPUMySqlDAO;
import com.sorinbratosin.BestGPUPrice.Database.IllegalCharactersException;

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

    public List<GPU> findByName(String name) throws IllegalCharactersException {
        if(isNameContainsEmpty(name)) {
            return findAllAvailableOrderByPrice();
        } else if (doesNameContainIllegalCharacters(name)) {
            throw new IllegalCharactersException("Illegal Characters used");
        }
        return gpuMySqlDAO.findByName(name);
    }

    public int countAllWhereNameContains(String name) {
        if(isNameContainsEmpty(name)) {
            return countAll();
        }
        return gpuMySqlDAO.countAllWhereNameContains(name);
    }

    public List<GPU> findAllOrderByPrice() {
        return gpuMySqlDAO.findAllOrderByPrice();
    }

    public List<GPU> findAllAvailable() {
        return gpuMySqlDAO.findAllAvailable();
    }

    public List<GPU> findAllAvailableOrderByPrice() {
        return gpuMySqlDAO.findAllAvailableOrderByPrice();
    }

    public List<GPU> findAllAvailableThatContainOrderByPrice(String name) throws IllegalCharactersException {
        if(isNameContainsEmpty(name)) {
            return findAllAvailableOrderByPrice();
        } else if (doesNameContainIllegalCharacters(name)) {
            throw new IllegalCharactersException("Illegal Characters used");
        }
        return gpuMySqlDAO.findAllAvailableThatContainOrderByPrice(name);
    }

    public List<GPU> findAllThatContainOrderByPrice(String name) throws IllegalCharactersException {
        if(isNameContainsEmpty(name)) {
            return findAllOrderByPrice();
        } else if (doesNameContainIllegalCharacters(name)) {
            throw new IllegalCharactersException("Illegal Characters used");
        }
        return gpuMySqlDAO.findAllThatContainOrderByPrice(name);
    }

    public List<GPU> findAllAvailableThatContain(String name) throws IllegalCharactersException {
        if(isNameContainsEmpty(name)) {
            return findAllAvailable();
        } else if (doesNameContainIllegalCharacters(name)) {
            throw new IllegalCharactersException("Illegal Characters used");
        }
        return gpuMySqlDAO.findAllAvailableThatContain(name);
    }

    private boolean isNameContainsEmpty(String name) {
        return name.isEmpty();
    }

    private boolean doesNameContainIllegalCharacters(String name) {
        return name.contains("--") || name.contains("#") || name.contains("/*") || name.contains("*/") || name.contains("(");
    }
}
