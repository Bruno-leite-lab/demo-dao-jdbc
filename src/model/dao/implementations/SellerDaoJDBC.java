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
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Seller departamento) {

    }

    @Override
    public void update(Seller departamento) {

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
        return null;
    }
}
