package RapidTestSQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostgreSQLJDBCExample {
    public static void main(String[] args) {
        // URL de connexion à la base de données
        String url = "jdbc:postgresql://localhost:5432/databasetest";
        String user = "postgres";
        String password = "";

        Connection conn = null;
        Statement stmt = null;

        try {
            // Établir la connexion
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion à la base de données réussie.");

            // Créer un objet Statement
            stmt = conn.createStatement();

            // Exécuter une requête SQL
            String sql = "SELECT * FROM TestTable WHERE BirthYear < 2000";
            ResultSet rs = stmt.executeQuery(sql);

            // Traiter les résultats
            while (rs.next()) {
                System.out.println("CompleteName: " + rs.getString("CompleteName"));
                System.out.println("BirthYear: " + rs.getInt("BirthYear"));
                System.out.println("Picture: " + rs.getString("Picture"));
            }

            // Fermer les ressources
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
