package model;

import conexoes.ConexaoSQLite;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaBancaria {
    private String banco = "não informado";
    private int numAgencia = 0000;
    private int numConta = 0000000000;
    private int digitoConta = 000;
    private double saldo =0.0;

    public ContaBancaria(String banco, int numAgencia, int numConta, int digitoConta, double saldo) {
        this.banco = banco;
        this.numAgencia = numAgencia;
        this.numConta = numConta;
        this.digitoConta = digitoConta;
        this.saldo = saldo;
    }

    public ContaBancaria(){}

    public static void criarConta(int id){

            ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
            ResultSet resultSet = null;
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

                int resultado = preparedStatement.executeUpdate();

                if(resultado == 1 ){

                    System.out.println("conta criada!");

                }else{
                    System.out.println("erro ao criar conta");
                }

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

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public int getNumAgencia() {
        return numAgencia;
    }

    public void setNumAgencia(int numAgencia) {
        this.numAgencia = numAgencia;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public int getDigitoConta() {
        return digitoConta;
    }

    public void setDigitoConta(int digitoConta) {
        this.digitoConta = digitoConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
