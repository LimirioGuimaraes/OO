package main;

import View.CadastrarUsuario;
import View.Login;
import conexoes.ConexaoSQLite;
import model.CriarBancoSQLite;
import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {

        new Login();

    }
}