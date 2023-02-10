package model;

import View.ContaBancariaView;
import View.LoginView;
import conexoes.ConexaoSQLite;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Acoes {
    private String nomeAcao;
    private double valorAcao;

    public static void apagarAcao(JFrame contaBancaria, JTextField removeAcao){
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM tbl_acoes"
                + " WHERE idAcao = ?;";

        try {

            preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(removeAcao.getText()));
            preparedStatement.executeUpdate();

            contaBancaria.dispose();
            new ContaBancariaView();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }

        }
    }

    public static String listarAcoes() {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        int idAcao = 0 ;
        String nomeAcao = " ";
        double valor = 0;

        String sql = "SELECT * "
                + " FROM tbl_acoes"
                + " WHERE id = ?";

        try {

            preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            preparedStatement.setInt(1, LoginView.getIdUsuario());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                idAcao = resultSet.getInt("idAcao");
                nomeAcao = resultSet.getString("nomeAcao");
                valor = resultSet.getDouble("valor");
            }

        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            try {
                assert resultSet != null;
                resultSet.close();
                preparedStatement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return (idAcao + " - " + nomeAcao + " - " + valor + "R$");
    }

    public static void criarAcoes(){

        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        CriarBancoSQLite criarBancoSQLite = new CriarBancoSQLite(conexaoSQLite);
        criarBancoSQLite.criarTabelaAcoes();
    }

    public static void addAcao(int id, JFrame contaBancaria,  JTextField nomeAcaoField, JTextField valorAcao){

        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        CriarBancoSQLite criarBancoSQLite = new CriarBancoSQLite(conexaoSQLite);
        criarBancoSQLite.criarTabelaSalario();

        conexaoSQLite.conectar();

        String sql = "INSERT INTO tbl_acoes ("
                + "   id, "
                + "   nomeAcao, "
                + "   valor "
                + ") VALUES(?,?,?)"
                + ";";

        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(sql);

        try{
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2, nomeAcaoField.getText());
            preparedStatement.setDouble(3, Double.parseDouble(valorAcao.getText()));

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Ação Adicionada", "Informativo", JOptionPane.INFORMATION_MESSAGE);

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

}
