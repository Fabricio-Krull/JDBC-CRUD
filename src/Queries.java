import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Queries {
    @SuppressWarnings("CallToPrintStackTrace")
    public static int contar(String table){

        String sql = "SELECT COUNT(*) FROM " + table;

        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            ResultSet result = stmt.executeQuery(sql);
            if(result.next()){
                return result.getInt(1);
            }
            result.close();
            stmt.close();
            conn.close();
            return 0;
            
        }

        

        catch (SQLException e){
            e.printStackTrace();
            return 0;
        }

    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static void limparBanco(){

        String catSql = "TRUNCATE TABLE categorias";
        String prodSql = "TRUNCATE TABLE produtos";

            try (Connection conn = Conexao.conectar();
            PreparedStatement stmt1 = conn.prepareStatement(catSql);
            PreparedStatement stmt2 = conn.prepareStatement(prodSql)){

            System.out.println("Banco de dados limpo com sucesso!");

            stmt1.executeUpdate();
            stmt2.executeUpdate();
            // stmt1.close();
            // stmt2.close();
            // conn.close();
            
        }

        catch (SQLException e){
            e.printStackTrace();
        }

    }
}