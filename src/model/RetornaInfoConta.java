package model;

import View.LoginView;
import conexoes.ConexaoSQLite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetornaInfoConta {

    static String banco = "nome";
    static int numAgencia = 1;
    static int numConta = 1;
    static int digitoConta = 1;
    static double saldo = 4.0;

    public static void buscarInformacoesConta(){

        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * "
                +" FROM tbl_contaBancaria"
                +" WHERE id = ?";

        try{

            preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            preparedStatement.setInt(1, LoginView.getIdUsuario());

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){

                banco = resultSet.getString("banco");
                numAgencia = resultSet.getInt("numAgencia");
                numConta = resultSet.getInt("numConta");
                digitoConta = resultSet.getInt("digitoConta");
                saldo = resultSet.getDouble("saldo");
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
    public static String retornaBanco() {
        return banco;
    }
    public static int retornaNumAgencia() {return numAgencia;}
    public static int retornaNumConta() {return numConta;}
    public static int retornaDigitoConta() {return digitoConta;}
    public static double retornaSaldo() {return saldo;}



}
