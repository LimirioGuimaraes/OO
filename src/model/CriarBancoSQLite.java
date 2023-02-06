package model;

import conexoes.ConexaoSQLite;

import java.sql.SQLException;
import java.sql.Statement;

public class CriarBancoSQLite {

    private final ConexaoSQLite conexaoSQLite;

    public CriarBancoSQLite(ConexaoSQLite pConexaoSQLite){
       this.conexaoSQLite = pConexaoSQLite;
    }
    public void criarTabelaUsuario(){
        String sql = ("CREATE TABLE IF NOT EXISTS tbl_usuarios"
                    + " ( "
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "   nome VARCHAR(255), "
                    + "   telefone VARCHAR(255), "
                    + "   idade INTEGER, "
                    + "   cpf VARCHAR(255), "
                    + "   login VARCHAR(255), "
                    + "   senha VARCHAR(255)"
                    + " );" );
        //Executando o sql de criar tabela

        boolean conectou = false;

        try{
            conectou = this.conexaoSQLite.conectar();

            Statement stmt = this.conexaoSQLite.criarStatement();
            stmt.execute(sql);

        }catch (SQLException e){
            System.err.println(e.getMessage());

        }finally {
            if(conectou){
                this.conexaoSQLite.desconectar();
            }
        }

    }
}