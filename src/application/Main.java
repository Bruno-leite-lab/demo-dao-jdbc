package application;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("===== Teste 1: seller findByID =======");
        Seller vendedor = sellerDao.findById(3);
        System.out.println(vendedor);

        System.out.println("===== Teste 2: seller findByDepartment =======");
        Department dep = new Department(2,null);
        List<Seller> lista = sellerDao.findByDepartment(dep);
        for(Seller seller : lista){
            System.out.println(seller);
        }

        System.out.println("===== Teste 3: seller findAll =======");
        lista = sellerDao.findAll();
        for(Seller seller : lista){
            System.out.println(seller);
        }

        System.out.println("===== Teste 4: seller findAll =======");
        Seller vendedor2 = new Seller(null,"john","john@gmail.com",new Date(),4000.0, dep);
        sellerDao.insert(vendedor2);
        System.out.println("Inserido ! Novo Id = "+ vendedor2.getId());

        System.out.println("===== Teste 5: seller update =======");
        Seller vendedor3 = sellerDao.findById(1);
        vendedor3.setName("Martha kant");
        sellerDao.update(vendedor3);
        System.out.println("Atualizacao de dados completa!");
    }
}
