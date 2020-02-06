package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Department departamento = new Department(1,"livros");
        Seller vendedor = new Seller(21,"Bob","bob@gmail.com",new Date(),3000.0,departamento);
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println(vendedor);
    }
}
