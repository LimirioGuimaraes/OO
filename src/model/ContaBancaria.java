package model;

import View.ContaBancariaView;
import View.LoginView;
import conexoes.ConexaoSQLite;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContaBancaria {
    private String banco = "não informado";
    private int numAgencia = 0;
    private int numConta = 0;
    private int digitoConta = 0;
    private double saldo =0.0;

    public ContaBancaria(String banco, int numAgencia, int numConta, int digitoConta, double saldo) {
        this.banco = banco;
        this.numAgencia = numAgencia;
        this.numConta = numConta;
        this.digitoConta = digitoConta;
        this.saldo = saldo;
    }

    public ContaBancaria(){}

    public static void salvarAtualizacao( JTextField bancoTextField, JTextField agenciaTextField, JTextField numContaTextField,
    JTextField digitoContaTextField, JTextField saldoTextField, JFrame contaBancaria){

        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE tbl_contaBancaria"
                + " SET "
                + " banco = ?, "
                + " numAgencia = ?, "
                + " numConta = ?, "
                + " digitoConta = ?, "
                + " saldo = ? "
                + " WHERE id = ?";
        try{

            preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            preparedStatement.setString(1, bancoTextField.getText());
            preparedStatement.setInt(2, Integer.parseInt(agenciaTextField.getText()));
            preparedStatement.setInt(3, Integer.parseInt(numContaTextField.getText()));
            preparedStatement.setInt(4, Integer.parseInt(digitoContaTextField.getText()));
            preparedStatement.setDouble(5, Double.parseDouble(saldoTextField.getText()));
            preparedStatement.setInt(6, LoginView.getIdUsuario());
            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Dados atualizados", "Informativo", JOptionPane.INFORMATION_MESSAGE);

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
    }

    public static void criarConta(int id){

            ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
            ContaBancaria contaBancaria = new ContaBancaria();

            conexaoSQLite.conectar();

            String sqlInsert = "INSERT INTO tbl_contaBancaria ("
                    + "   id, "
                    + "   banco, "
                    + "   numAgencia, "
                    + "   numConta, "
                    + "   digitoConta, "
                    + "   saldo"
                    + ") VALUES(?,?,?,?,?,?)"
                    + ";";

            PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(sqlInsert);

            try{
                preparedStatement.setInt(1,id);
                preparedStatement.setString(2, contaBancaria.getBanco());
                preparedStatement.setInt(3,contaBancaria.getNumAgencia());
                preparedStatement.setInt(4,contaBancaria.getNumConta());
                preparedStatement.setInt(5,contaBancaria.getDigitoConta());
                preparedStatement.setDouble(6,contaBancaria.getSaldo());

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

    public String getBanco() {
        return banco;
    }

    public int getNumAgencia() {
        return numAgencia;
    }

    public int getNumConta() {
        return numConta;
    }

    public int getDigitoConta() {
        return digitoConta;
    }

    public double getSaldo() {
        return saldo;
    }
}
