package model;

import View.ContaBancariaView;
import View.LoginView;
import conexoes.ConexaoSQLite;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RendaExtra {

    private String origem;
    private double valor;

    public static void addRendaExtra(int id, JTextField origemTextField,
    JTextField valorRenda, JFrame contaBancaria){

        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        CriarBancoSQLite criarBancoSQLite = new CriarBancoSQLite(conexaoSQLite);
        PreparedStatement preparedStatement = null;
        criarBancoSQLite.criarTabelaSalario();

        conexaoSQLite.conectar();

        String sql = "INSERT INTO tbl_rendaExtra ("
                + "   id, "
                + "   origem, "
                + "   valor "
                + ") VALUES(?,?,?)"
                + ";";

        String sql2 = "UPDATE tbl_contaBancaria"
                + " SET "
                + " saldo = ? "
                + " WHERE id = ?";

        try{

            preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2, origemTextField.getText());
            preparedStatement.setDouble(3, Double.parseDouble(valorRenda.getText()));

            preparedStatement.executeUpdate();

            double novoSaldo = RetornaInfoConta.retornaSaldo();
            novoSaldo = novoSaldo + Double.parseDouble(valorRenda.getText());

            preparedStatement = null;

            preparedStatement = conexaoSQLite.criarPreparedStatement(sql2);
            preparedStatement.setDouble(1, novoSaldo);
            preparedStatement.setInt(2, LoginView.getIdUsuario());
            preparedStatement.executeUpdate();

            contaBancaria.dispose();
            new ContaBancariaView();

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getMessage());

        }finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            conexaoSQLite.desconectar();
        }
    }

    public static void criarRenda(){
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        CriarBancoSQLite criarBancoSQLite = new CriarBancoSQLite(conexaoSQLite);
        criarBancoSQLite.criarTabelaRendaExtra();
    }

}
