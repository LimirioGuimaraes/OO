package model;

import View.LoginView;
import conexoes.ConexaoSQLite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetornaInfoUsuario {
    private static String nome;
    private static String telefone;
    private static String cpf;
    private static int idade;
    public static void buscarInformacoesUsuario() {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * "
                + " FROM tbl_usuarios"
                + " WHERE id = ?";

        try {

            preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            preparedStatement.setInt(1, LoginView.getIdUsuario());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                nome = resultSet.getString("nome");
                telefone = resultSet.getString("telefone");
                cpf = resultSet.getString("cpf");
                idade = resultSet.getInt("idade");
            }

        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            try {
                resultSet.close();
                preparedStatement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static String retornaNome(){return nome;}
    public static String retornaTelefone(){return telefone;}
    public static String retornaCpf(){return cpf;}
    public static int retornaIdade(){return idade;}
}
