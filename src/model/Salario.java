package model;

import View.ContaBancariaView;
import View.LoginView;
import conexoes.ConexaoSQLite;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Salario {

    private static double salario = 0;

    public static double getSalario() {
        return salario;
    }

    public static void criarSalario(int id){

        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        CriarBancoSQLite criarBancoSQLite = new CriarBancoSQLite(conexaoSQLite);
        criarBancoSQLite.criarTabelaSalario();

        conexaoSQLite.conectar();

        String sql = "INSERT INTO tbl_salario ("
                + "   id, "
                + "   salario "
                + ") VALUES(?,?)"
                + ";";

        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(sql);

        try{
            preparedStatement.setInt(1,id);
            preparedStatement.setDouble(2, Salario.getSalario());


            preparedStatement.executeUpdate();

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

    public static void buscarInfoSalario(){

        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * "
                +" FROM tbl_salario"
                +" WHERE id = ?";

        try{

            preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            preparedStatement.setInt(1, LoginView.getIdUsuario());

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){

                salario = resultSet.getDouble("salario");
            }

        }catch (
                SQLException e){
            System.out.println(e.getMessage());
        }finally {

            try{
                assert resultSet != null;
                resultSet.close();
                preparedStatement.close();
                conexaoSQLite.desconectar();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void atualizarSalario( JTextField salarioTextField, JFrame contaBancaria){

        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE tbl_salario"
                + " SET "
                + " salario = ? "
                + " WHERE id = ?";

        String sql2 = "UPDATE tbl_contaBancaria"
                + " SET "
                + " saldo = ? "
                + " WHERE id = ?";
        try{

            preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            preparedStatement.setDouble(1, Double.parseDouble(salarioTextField.getText()));
            preparedStatement.setInt(2, LoginView.getIdUsuario());
            preparedStatement.executeUpdate();

            double novoSaldo = RetornaInfoConta.retornaSaldo();
            novoSaldo = novoSaldo + Double.parseDouble(salarioTextField.getText());

            preparedStatement = null;

            preparedStatement = conexaoSQLite.criarPreparedStatement(sql2);
            preparedStatement.setDouble(1, novoSaldo);
            preparedStatement.setInt(2, LoginView.getIdUsuario());
            preparedStatement.executeUpdate();


            JOptionPane.showMessageDialog(null,
                    "Salario e saldo atualizado", "Informativo", JOptionPane.INFORMATION_MESSAGE);


            contaBancaria.dispose();
            new ContaBancariaView();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
                conexaoSQLite.desconectar();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        RetornaInfoConta.buscarInformacoesConta();




    }
    public static Double retornaSalario() {
        return salario;
    }
}
