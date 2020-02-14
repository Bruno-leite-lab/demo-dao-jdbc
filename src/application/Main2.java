package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("====== Teste 1 : find By id ======= ");
        Department departamento = departmentDao.findById(2);
        System.out.println(departamento);

        System.out.println("===== Teste 2 : delete by id ======");
        departmentDao.deleteById(5);
        System.out.println("departamento apagado");

        System.out.println("===== Teste 3 : find all =======");
        List<Department> listaDepartamentos = departmentDao.findAll();
        for(Department department : listaDepartamentos){
            System.out.println(department);
        }

        System.out.println("===== Teste 4 : insert ======");
        Department departamentoArtesanato = new Department(7,"Artesanatos");
        departmentDao.insert(departamentoArtesanato);
        System.out.println("Departamento adicionado");

        System.out.println("===== Teste 5 : update =====");
        departamentoArtesanato.setName("Artes");
        departamentoArtesanato.setId(6);
        departmentDao.update(departamentoArtesanato);
        System.out.println("departamento atualizado.");

    }
}
