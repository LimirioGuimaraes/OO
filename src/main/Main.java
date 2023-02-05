package main;

import View.CadastrarUsuario;
import View.Login;
import conexoes.ConexaoSQLite;
import model.CriarBancoSQLite;
import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        CriarBancoSQLite criarBancoSQLite = new CriarBancoSQLite(conexaoSQLite);
        criarBancoSQLite.criarTabelaUsuario();
        new Login();
          //new CadastrarUsuario();
    }
}