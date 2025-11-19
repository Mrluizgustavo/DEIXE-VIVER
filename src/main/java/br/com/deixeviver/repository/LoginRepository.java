package br.com.deixeviver.repository;

import br.com.deixeviver.model.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginRepository {

    private static final String INSERT_SQL = "INSERT INTO login (nome, senha, tipo_usuario) VALUES (?, ?, ?)";

    public boolean salvar(Login login) {

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {

            // 1. Define os parâmetros da query (evita injeção de SQL)
            stmt.setString(1, login.getNome()); // O primeiro '?' será o nome
            stmt.setString(2, login.getTipo_usuario()); // O segundo '?' será o email
            stmt.setString(3, login.getSenha()); // O terceiro '?' será a senha

            // 2. Executa a instrução SQL
            int linhasAfetadas = stmt.executeUpdate();

            // 3. Verifica se a inserção foi bem-sucedida
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao salvar usuário no banco de dados: " + e.getMessage());
            // Em projetos reais, você logaria o erro.
            return false;
        }
    }
}
