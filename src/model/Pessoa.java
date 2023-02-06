package model;

import View.LoginView;
import View.TelaPrincipal;
import conexoes.ConexaoSQLite;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pessoa {
    private String nome;
    private String telefone;
    private String cpf;

    public Pessoa(String nome, String telefone, String cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public Pessoa() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public static void salvarUsuario(String nomeUsuario, String telefoneUsuario, int idadeUsuario,
                              String cpfUsuario, String acessoUsuario, String senhaUsuario, JFrame cadastrarUsuario) throws RuntimeException {

        int resultado = 0;
        int id = 0;

        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        ResultSet resultSet = null;

        Usuario usuario1 = new Usuario();
        usuario1.setNome(nomeUsuario);
        usuario1.setTelefone(telefoneUsuario);
        usuario1.setIdade(Integer.parseInt(String.valueOf(idadeUsuario)));
        usuario1.setCpf(cpfUsuario);
        usuario1.setUser(acessoUsuario);
        usuario1.setSenha(senhaUsuario);

        conexaoSQLite.conectar();

        String sqlInsert = "INSERT INTO tbl_usuarios ("
                + "nome,"
                + "telefone,"
                + "idade,"
                + "cpf,"
                + "login,"
                + "senha"
                + ") VALUES(?,?,?,?,?,?)"
                + ";";

        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(sqlInsert);

        try {
            preparedStatement.setString(1, usuario1.getNome());
            preparedStatement.setString(2, usuario1.getTelefone());
            preparedStatement.setInt(3, usuario1.getIdade());
            preparedStatement.setString(4, usuario1.getCpf());
            preparedStatement.setString(5, usuario1.getUser());
            preparedStatement.setString(6, usuario1.getSenha());

            resultado = preparedStatement.executeUpdate();

            if (resultado == 1) {

                JOptionPane.showMessageDialog(null,
                        "Usuário cadastrado com sucesso!", "Usuário Cadastrado", JOptionPane.PLAIN_MESSAGE);

                String sql = "SELECT * "
                        + " FROM tbl_usuarios"
                        + " WHERE login = ?";

                try {

                    preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
                    preparedStatement.setString(1, usuario1.getUser());
                    resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {

                        id = resultSet.getInt("id");

                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

                cadastrarUsuario.dispose();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Erro ao cadastrar usuário!", "ERRO", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getMessage());

        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            conexaoSQLite.desconectar();

            if (resultado == 1) {
                ContaBancaria.criarConta(id);
            }
        }
    }

}

