package conexoes;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class ConexaoSQLite {

    private Connection conexao;

    public boolean conectar() {
        try {
            String url = "jdbc:sqlite:banco_de_dados/banco_sqlite.db";
            this.conexao = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        System.out.println("Conectou");

        return true;
    }
    public boolean desconectar(){
        try {
            if(!this.conexao.isClosed()){
                this.conexao.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        System.out.println("desconectou");
        return true;
    }

    //criar statements para os sql's serem executados
    public Statement criarStatement(){
        try{
            return this.conexao.createStatement();
        }catch (SQLException e){
            return null;
        }
    }

    public PreparedStatement criarPreparedStatement(String sql){
        try {
            return this.conexao.prepareStatement(sql);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public Connection getConexao(){
        return this.conexao;
    }

}


