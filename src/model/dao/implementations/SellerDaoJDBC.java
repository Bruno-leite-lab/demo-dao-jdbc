package model.dao.implementations;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Seller vendedor) {

    }

    @Override
    public void update(Seller vendedor) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try{
            comando = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?"
            );
            comando.setInt(1, id);
            resultado = comando.executeQuery();
            if(resultado.next()){
                Department departamento = instanciarDepartamento(resultado);
                Seller vendedor = instaciarVendedor(resultado, departamento);
                return vendedor;
            }
            return null;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.fecharcomando(comando);
            DB.fecharResultado(resultado);
        }
    }

    private Seller instaciarVendedor(ResultSet resultado, Department departamento) throws SQLException{
        Seller vendedor = new Seller();
        vendedor.setId(resultado.getInt("Id"));
        vendedor.setName(resultado.getString("Email"));
        vendedor.setEmail(resultado.getString("Email"));
        vendedor.setBaseSalary(resultado.getDouble("BaseSalary"));
        vendedor.setBirthDate(resultado.getDate("BirthDate"));
        vendedor.setDepartment(departamento);
        return vendedor;
    }

    private Department instanciarDepartamento(ResultSet resultado) throws SQLException {
        Department departamento = new Department();
        departamento.setId(resultado.getInt("DepartmentId"));
        departamento.setName(resultado.getString("DepName"));
        return departamento;
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement comando = null;
        ResultSet resultado = null;

        try{
            comando = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "+
                            "FROM seller INNER JOIN department "+
                            "ON seller.DepartmentId = department.Id "+
                            "ORDER BY Name"
            );
            resultado = comando.executeQuery();
            List<Seller> vendedores = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (resultado.next()){
                Department departamento = map.get(resultado.getInt("DepartmentId"));
                if(departamento == null){
                    departamento = instanciarDepartamento(resultado);
                    map.put(resultado.getInt("DepartmentId"),departamento);
                }
                Seller vendedor = instaciarVendedor(resultado, departamento);
                vendedores.add(vendedor);
            }

            return vendedores;

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.fecharResultado(resultado);
            DB.fecharcomando(comando);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department departmento) {
        PreparedStatement comando = null;
        ResultSet resultado = null;

        try{
            comando = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "+
                            "FROM seller INNER JOIN department "+
                            "ON seller.DepartmentId = department.Id "+
                            "WHERE DepartmentId = ? "+
                            "ORDER BY Name"
            );
            comando.setInt(1, departmento.getId());
            resultado = comando.executeQuery();
            List<Seller> vendedores = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();
            while (resultado.next()){
                Department departamento = map.get(resultado.getInt("DepartmentId"));
                if(departamento == null){
                    departamento = instanciarDepartamento(resultado);
                    map.put(resultado.getInt("DepartmentId"),departamento);
                }
                Seller vendedor = instaciarVendedor(resultado, departamento);
                vendedores.add(vendedor);
            }

            return vendedores;

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.fecharResultado(resultado);
            DB.fecharcomando(comando);
        }
    }
}
