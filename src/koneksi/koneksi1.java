package koneksi;
import java.sql.*;

public class koneksi1 {
    public Connection cc;
    public Statement ss;
    public ResultSet rr;
    
    
    public void Class (){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cc = DriverManager.getConnection("jdbc:mysql://localhost/Crud-java2","root","");
            System.out.println("Koneksi database sukses");
        } catch (Exception e) {
            System.out.println(e);
        }
    
}
    public static void main(String[] args) {
        new koneksi1().Class();
    }
}
