package com.example.blogservlet.dao.impl;

import com.example.blogservlet.dao.IGenericDAO;
import com.example.blogservlet.mapper.IRowMapper;
import com.example.blogservlet.model.News;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class GenericDAOImpl<T> implements IGenericDAO<T> {
    ResourceBundle dbBundle = ResourceBundle.getBundle("application");

    public Connection getConnection() {
        try {
            Class.forName(dbBundle.getString("drive"));
            String url = dbBundle.getString("url");
            String user = dbBundle.getString("user");
            String password = dbBundle.getString("password");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }

    private void setParameter(PreparedStatement statement, Object... parameters) {
        try {
            for (int i = 0; i < parameters.length; i++) {
                Object parameter = parameters[i];
                int index = i + 1;
                if (parameter instanceof Long) {
                    statement.setLong(index, (Long) parameter);
                } else if (parameter instanceof String) {
                    statement.setString(index, (String) parameter);
                } else if (parameter instanceof Integer) {
                    statement.setInt(index, (Integer) parameter);
                } else if (parameter instanceof Timestamp) {
                    statement.setTimestamp(index, (Timestamp) parameter);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public <T> List<T> query(String sql, IRowMapper<T> rowMapper, Object... parameters) {
        List<T> result = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            setParameter(statement, parameters);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet));
            }
            return result;
        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
                if (statement != null) statement.close();
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }
        return null;
    }

    @Override
    public Long insert(String sql, Object... parameters) {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Long id = null;
            connection.setAutoCommit(false);
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParameter(statement, parameters);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            connection.commit(); //luu vao db
            return id;
        } catch (SQLException e) {
            try {
                connection.rollback(); //rollback db neu loi
            }catch (SQLException eRollback) {
                eRollback.getStackTrace();
            }
            e.getStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
                if (statement != null) statement.close();
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }
        return null;
    }

    @Override
    public void update(String sql, Object... parameters) {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = getConnection().prepareStatement(sql);
            setParameter(statement, parameters);
            statement.executeUpdate();
            connection.commit(); //luu vao db
        } catch (SQLException e) {
            try {
                connection.rollback(); //rollback db neu method loi
            }catch (SQLException eRollback) {
                eRollback.getStackTrace();
            }
            e.getStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }
    }

    @Override
    public void deleteById(String sql, Object... parameters) {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = getConnection().prepareStatement(sql);
            setParameter(statement, parameters);
            statement.executeUpdate();
            connection.commit(); //luu vao db
        } catch (SQLException e) {
            try {
                connection.rollback(); //rollback db neu method loi
            }catch (SQLException eRollback) {
                eRollback.getStackTrace();
            }
            e.getStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }
    }

    @Override
    public int count(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            int count = 0;
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            // Set parameter
            setParameter(statement, parameters);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            return 0;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                return 0;
            }
        }
    }
}
