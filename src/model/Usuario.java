package model;

import conexoes.ConexaoSQLite;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario extends Pessoa{

    private int idade;
    private String user;
    private String senha;

    public Usuario(String nome, String telefone, String cpf, int idade, String user, String senha) {
        super(nome, telefone, cpf);
        this.idade = idade;
        this.user = user;
        this.senha = senha;
    }

    public Usuario() {
        super();
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

