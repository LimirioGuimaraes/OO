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

    public void criarTabelaContaBancaria(){
        String sql = ("CREATE TABLE IF NOT EXISTS tbl_contaBancaria"
                + " ( "
                + "   id INTEGER, "
                + "   banco VARCHAR(255), "
                + "   numAgencia INTEGER, "
                + "   numConta INTEGER, "
                + "   digitoConta INTEGER, "
                + "   saldo DOUBLE"
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
    public void criarTabelaSalario(){

        String sql = ("CREATE TABLE IF NOT EXISTS tbl_salario"
                + " ( "
                + "   id INTEGER, "
                + "   salario DOUBLE"
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
    public void criarTabelaRendaExtra(){

        String sql = ("CREATE TABLE IF NOT EXISTS tbl_rendaExtra"
                + " ( "
                + "   id INTEGER, "
                + "   origem VARCHAR(255), "
                + "   valor DOUBLE"
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

    public void criarTabelaAcoes(){

        String sql = ("CREATE TABLE IF NOT EXISTS tbl_acoes"
                + " ( "
                + "   idAcao INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "   id INTEGER, "
                + "   nomeAcao VARCHAR(255), "
                + "   valor DOUBLE"
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
