package model.dao.implementations;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDaoJDBC implements DepartmentDao {
    private Connection connection;

    public DepartmentDaoJDBC(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Department departamento) {
        PreparedStatement comando = null;
        ResultSet resultSet = null;
        try{
            comando = connection.prepareStatement("INSERT INTO department (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            comando.setString(1, departamento.getName());
            int linhasAfetadas = comando.executeUpdate();
            if(linhasAfetadas >0){
                resultSet = comando.getGeneratedKeys();
                if(resultSet.next()){
                    int id = resultSet.getInt(1);
                }
            }
            else {
                throw new DbException("Erro doido! nenhuma linha foi afetada");
            }

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.fecharResultado(resultSet);
            DB.fecharcomando(comando);
        }
    }

    @Override
    public void update(Department departamento) {
        PreparedStatement comando = null;
        try{
            comando = connection.prepareStatement("update department set Name = ? where Id = ?");
            comando.setString(1,departamento.getName());
            comando.setInt(2,departamento.getId());
            comando.executeUpdate();
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.fecharcomando(comando);
        }
    }

    @Override
    public void deleteById(Integer id){
        PreparedStatement comando = null;
        try{
            comando = connection.prepareStatement("delete from department where Id = ?");
            comando.setInt(1,id);
            comando.executeUpdate();
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.fecharcomando(comando);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement comando = null;
        ResultSet resultSet = null;
        Department department = new Department();
        try{
            comando = connection.prepareStatement("select department.* from department where Id = ?");
            comando.setInt(1,id);
            resultSet = comando.executeQuery();
            if(resultSet.next()){
                department.setId(resultSet.getInt(1));
                department.setName(resultSet.getString(2));
            }
            else {
                throw new DbException("Este Id não existe no banco de dados.");
            }
            DB.fecharResultado(resultSet);
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.fecharcomando(comando);
        }

        return department;
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement comnando = null;
        List<Department> departamentos = new ArrayList<>();
        try{
            comnando = connection.prepareStatement("select * from department order by Name");
            ResultSet resultSet = comnando.executeQuery();
            while (resultSet.next()){
                Department department = new Department();
                department.setId(resultSet.getInt(1));
                department.setName(resultSet.getString(2));
                departamentos.add(department);
            }
            DB.fecharResultado(resultSet);
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.fecharcomando(comnando);
        }

        return departamentos;
    }
}
