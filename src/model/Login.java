package model;

import View.LoginView;
import View.TelaPrincipal;
import conexoes.ConexaoSQLite;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    public static void verificaLogin(String nome_usuario, String senha_usuario, JFrame login){
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * "
                +" FROM tbl_usuarios"
                +" WHERE login = ? AND senha = ?";

        try{

            preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            preparedStatement.setString(1, nome_usuario);
            preparedStatement.setString(2, senha_usuario);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){

                LoginView.setIdUsuario(resultSet.getInt("id"));

                login.dispose();
                new TelaPrincipal();

            }else{
                JOptionPane.showMessageDialog(null,
                        "Usuario ou senha incorretos", "ERRO", JOptionPane.ERROR_MESSAGE);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {

            try{
                resultSet.close();
                preparedStatement.close();
                conexaoSQLite.desconectar();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }

    }

}
