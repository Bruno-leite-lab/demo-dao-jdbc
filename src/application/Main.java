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
        for(Seller vende : lista){
            System.out.println(vende);
        }
    }
}
