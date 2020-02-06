package application;

import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Department departamento = new Department(1,"livros");
        Seller vendedor = new Seller(21,"Bob","bob@gmail.com",new Date(),3000.0,departamento);
        System.out.println(vendedor);
    }
}
