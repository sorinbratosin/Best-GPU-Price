package com.sorinbratosin.BestGPUPrice.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GPUMySqlDAO implements GPUDAO {

    private ConnectMySQL connectMySQL;
    private ResultSet resultSet;

    public GPUMySqlDAO() {
        connectMySQL = new ConnectMySQL();
    }

    @Override
    public List<GPU> findAll() {
        if(!connectMySQL.isConnected()) {
            connectMySQL.connectToDB();
        }
        List<GPU> gpuList = new ArrayList<>();
        try {
            resultSet = connectMySQL.getStatement().executeQuery("select * from gpu_price.gpus");
            while (resultSet.next()) {
                gpuList.add(new GPURowMapper().mapRow(resultSet));
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                if(resultSet != null) {resultSet.close();}
                if(connectMySQL.getStatement() != null) {connectMySQL.closeStatement();}
                if(connectMySQL.getConnection() != null) {connectMySQL.closeConnection();}
            } catch (SQLException sqlEx) {sqlEx.printStackTrace();}
        }
        return gpuList;
    }

    @Override
    public List<GPU> findByName(String name) {
        if(!connectMySQL.isConnected()) {
            connectMySQL.connectToDB();
        }
        List<GPU> gpuList = new ArrayList<>();
        try {
            resultSet = connectMySQL.getStatement().executeQuery("select * from gpu_price.gpus where name LIKE '%" + name + "%'");
            while (resultSet.next()) {
                gpuList.add(new GPURowMapper().mapRow(resultSet));
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                if(resultSet != null) {resultSet.close();}
                if(connectMySQL.getStatement() != null) {connectMySQL.closeStatement();}
                if(connectMySQL.getConnection() != null) {connectMySQL.closeConnection();}
            } catch (SQLException sqlEx) {sqlEx.printStackTrace();}
        }
        return gpuList;
    }

    @Override
    public void saveGPU(GPU gpu) {
        if(!connectMySQL.isConnected()) {
            connectMySQL.connectToDB();
        }
        try {
            connectMySQL.getStatement().executeUpdate("insert into gpu_price.gpus (id, name, price, availability, url) values(null,\"" + gpu.getName() + "\",\"" + gpu.getPrice() + "\",\"" + gpu.isAvailable() + "\",\"" + gpu.getUrl() + "\");");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                if(resultSet != null) {resultSet.close();}
                if(connectMySQL.getStatement() != null) {connectMySQL.closeStatement();}
                if(connectMySQL.getConnection() != null) {connectMySQL.closeConnection();}
            } catch (SQLException sqlEx) {sqlEx.printStackTrace();}
        }

    }

    @Override
    public void deleteGPU(int id) {
        if(!connectMySQL.isConnected()) {
            connectMySQL.connectToDB();
        }
        try {
            connectMySQL.getStatement().executeUpdate("delete from gpu_price.gpus where id=" + id + ";");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                if(resultSet != null) {resultSet.close();}
                if(connectMySQL.getStatement() != null) {connectMySQL.closeStatement();}
                if(connectMySQL.getConnection() != null) {connectMySQL.closeConnection();}
            } catch (SQLException sqlEx) {sqlEx.printStackTrace();}
        }
    }

    @Override
    public void truncateTable() {
        if(!connectMySQL.isConnected()) {
            connectMySQL.connectToDB();
        }
        try {
            connectMySQL.getStatement().executeUpdate("truncate table gpu_price.gpus");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                if(resultSet != null) {resultSet.close();}
                if(connectMySQL.getStatement() != null) {connectMySQL.closeStatement();}
                if(connectMySQL.getConnection() != null) {connectMySQL.closeConnection();}
            } catch (SQLException sqlEx) {sqlEx.printStackTrace();}
        }
    }

    @Override
    public int countAll() {
        if(!connectMySQL.isConnected()) {
            connectMySQL.connectToDB();
        }
        int count = 0;
        try {
            resultSet = connectMySQL.getStatement().executeQuery("select count(*) from gpu_price.gpus");
            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                if(resultSet != null) {resultSet.close();}
                if(connectMySQL.getStatement() != null) {connectMySQL.closeStatement();}
                if(connectMySQL.getConnection() != null) {connectMySQL.closeConnection();}
            } catch (SQLException sqlEx) {sqlEx.printStackTrace();}
        }
        return count;
    }

    @Override
    public int countAllWhereNameContains(String name) {
        if(!connectMySQL.isConnected()) {
            connectMySQL.connectToDB();
        }
        int count = 0;
        try {
            resultSet = connectMySQL.getStatement().executeQuery("select count(id) from gpu_price.gpus where name LIKE '%" + name + "%'");
            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                if(resultSet != null) {resultSet.close();}
                if(connectMySQL.getStatement() != null) {connectMySQL.closeStatement();}
                if(connectMySQL.getConnection() != null) {connectMySQL.closeConnection();}
            } catch (SQLException sqlEx) {sqlEx.printStackTrace();}
        }
        return count;
    }

    @Override
    public List<GPU> findAllOrderByPrice() {
        if(!connectMySQL.isConnected()) {
            connectMySQL.connectToDB();
        }
        List<GPU> gpuList = new ArrayList<>();
        try {
            resultSet = connectMySQL.getStatement().executeQuery("select * from gpu_price.gpus order by price ASC");
            while (resultSet.next()) {
                gpuList.add(new GPURowMapper().mapRow(resultSet));
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                if(resultSet != null) {resultSet.close();}
                if(connectMySQL.getStatement() != null) {connectMySQL.closeStatement();}
                if(connectMySQL.getConnection() != null) {connectMySQL.closeConnection();}
            } catch (SQLException sqlEx) {sqlEx.printStackTrace();}
        }
        return gpuList;
    }

    @Override
    public List<GPU> findAllAvailable() {
        if(!connectMySQL.isConnected()) {
            connectMySQL.connectToDB();
        }
        List<GPU> gpuList = new ArrayList<>();
        try {
            resultSet = connectMySQL.getStatement().executeQuery("select * from gpu_price.gpus where availability = 'true'");
            while (resultSet.next()) {
                gpuList.add(new GPURowMapper().mapRow(resultSet));
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                if(resultSet != null) {resultSet.close();}
                if(connectMySQL.getStatement() != null) {connectMySQL.closeStatement();}
                if(connectMySQL.getConnection() != null) {connectMySQL.closeConnection();}
            } catch (SQLException sqlEx) {sqlEx.printStackTrace();}
        }
        return gpuList;
    }

    @Override
    public List<GPU> findAllAvailableOrderByPrice() {
        if(!connectMySQL.isConnected()) {
            connectMySQL.connectToDB();
        }
        List<GPU> gpuList = new ArrayList<>();
        try {
            resultSet = connectMySQL.getStatement().executeQuery("select * from gpu_price.gpus where availability = 'true' order by price asc");
            while (resultSet.next()) {
                gpuList.add(new GPURowMapper().mapRow(resultSet));
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                if(resultSet != null) {resultSet.close();}
                if(connectMySQL.getStatement() != null) {connectMySQL.closeStatement();}
                if(connectMySQL.getConnection() != null) {connectMySQL.closeConnection();}
            } catch (SQLException sqlEx) {sqlEx.printStackTrace();}
        }
        return gpuList;
    }

    @Override
    public List<GPU> findAllAvailableThatContainOrderByPrice(String name) {
        if(!connectMySQL.isConnected()) {
            connectMySQL.connectToDB();
        }
        List<GPU> gpuList = new ArrayList<>();
        try {
            resultSet = connectMySQL.getStatement().executeQuery("select * from gpu_price.gpus where name LIKE '%" + name + "%' and availability = 'true' order by price asc");
            while (resultSet.next()) {
                gpuList.add(new GPURowMapper().mapRow(resultSet));
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                if(resultSet != null) {resultSet.close();}
                if(connectMySQL.getStatement() != null) {connectMySQL.closeStatement();}
                if(connectMySQL.getConnection() != null) {connectMySQL.closeConnection();}
            } catch (SQLException sqlEx) {sqlEx.printStackTrace();}
        }
        return gpuList;
    }

    @Override
    public List<GPU> findAllAvailableThatContain(String name) {
        if(!connectMySQL.isConnected()) {
            connectMySQL.connectToDB();
        }
        List<GPU> gpuList = new ArrayList<>();
        try {
            resultSet = connectMySQL.getStatement().executeQuery("select * from gpu_price.gpus where name LIKE '%" + name + "%' and availability = 'true'");
            while (resultSet.next()) {
                gpuList.add(new GPURowMapper().mapRow(resultSet));
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                if(resultSet != null) {resultSet.close();}
                if(connectMySQL.getStatement() != null) {connectMySQL.closeStatement();}
                if(connectMySQL.getConnection() != null) {connectMySQL.closeConnection();}
            } catch (SQLException sqlEx) {sqlEx.printStackTrace();}
        }
        return gpuList;
    }

    @Override
    public List<GPU> findAllThatContainOrderByPrice(String name) {
        if(!connectMySQL.isConnected()) {
            connectMySQL.connectToDB();
        }
        List<GPU> gpuList = new ArrayList<>();
        try {
            resultSet = connectMySQL.getStatement().executeQuery("select * from gpu_price.gpus where name LIKE '%" + name + "%' order by price asc");
            while (resultSet.next()) {
                gpuList.add(new GPURowMapper().mapRow(resultSet));
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                if(resultSet != null) {resultSet.close();}
                if(connectMySQL.getStatement() != null) {connectMySQL.closeStatement();}
                if(connectMySQL.getConnection() != null) {connectMySQL.closeConnection();}
            } catch (SQLException sqlEx) {sqlEx.printStackTrace();}
        }
        return gpuList;
    }
}
