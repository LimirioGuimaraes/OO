package View;

import conexoes.ConexaoSQLite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends JFrame {

    JTextField nome_usuario;
    JTextField senha_usuario;
    JFrame login;
    int idUsuario = 0;

    public Login(){
        super();
        login = new JFrame();
        login.setSize(300, 325);
        login.setTitle("Login");
        login.setResizable(false);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setLocationRelativeTo(null);
        login.getContentPane().setBackground(new Color(95, 159, 159));
        login.setLayout(null);

        JLabel usuario = new JLabel("Usuário:");
        usuario.setBounds(5, 50, 120,30);
        usuario.setFont(new Font("Arial", Font.PLAIN, 21));
        usuario.setForeground(new Color(0,0,0));
        login.add(usuario);

        JLabel senha = new JLabel("Senha:");
        senha.setBounds(5, 115, 120,30);
        senha.setFont(new Font("Arial", Font.PLAIN, 21));
        senha.setForeground(new Color(0, 0, 0));
        login.add(senha);

        //Criação do espaço para o usuário adicionar o nome de usuário
        nome_usuario = new JTextField();
        nome_usuario.setText("");
        nome_usuario.setBounds(85, 50, 140, 30);
        nome_usuario.setVisible(true);
        login.add(nome_usuario);

        //Criação do espaço para o usuário adicionar a senha
        senha_usuario = new JTextField();
        senha_usuario.setText("");
        senha_usuario.setBounds(85, 115, 140, 30);
        senha_usuario.setVisible(true);
        login.add(senha_usuario);

        //Criação e configuração do botão responsável pelo login
        JButton entrar = new JButton("Entrar");
        entrar.setBounds(5, 225, 140, 35);
        entrar.setBackground(new Color(0, 0, 0, 205));
        entrar.setForeground(new Color (255, 255, 255));
        entrar.setVisible(true);
        entrar.addActionListener(this::verificaLogin);
        login.add(entrar);


        //Criação e configuração do botão responsável pelo cadastro de novos perfis
        JButton cadastrar = new JButton("Cadastra-se");
        cadastrar.setBounds(155, 225, 140, 35);
        cadastrar.setBackground(new Color(0, 0, 0, 205));
        cadastrar.setForeground(new Color (255, 255, 255));
        cadastrar.setVisible(true);
        cadastrar.addActionListener(this:: criarUsuario);
        login.add(cadastrar);

        login.setVisible(true);
    }

    private void criarUsuario(ActionEvent actionEvent) {
        new CadastrarUsuario();
    }

    private void verificaLogin(ActionEvent actionEvent) {

        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * "
                +" FROM tbl_usuarios"
                +" WHERE login = ? AND senha = ?";

        try{

            preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            preparedStatement.setString(1, nome_usuario.getText());
            preparedStatement.setString(2, senha_usuario.getText());
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){

                while (resultSet.next()){
                    System.out.println("o id e: " + idUsuario);
                    idUsuario = resultSet.getInt("id");
                    System.out.println("o id e: " + idUsuario);

                }
                login.dispose();
                new TelaPrincipal();

            }else{
                JOptionPane.showMessageDialog(null,
                        "Usuario ou senha incorretos", "ERRO", JOptionPane.ERROR_MESSAGE);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {

            try{
                resultSet.close();
                preparedStatement.close();
                conexaoSQLite.desconectar();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println("o id e: " + idUsuario);
   }


}
