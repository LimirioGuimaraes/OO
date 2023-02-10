package View;

import conexoes.ConexaoSQLite;
import model.RetornaInfoUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MeuPerfilView extends JFrame {

    JFrame meuPerfil;
    JPanel panelPerfil;
    JPanel painelAlteracao;

    JTextField nome;
    JTextField telefone;
    JTextField cpf;
    JTextField idade;

    public MeuPerfilView(){
        super();
        meuPerfil = new JFrame();
        meuPerfil.setTitle("Meu Perfil");
        meuPerfil.setSize(800,600);
        meuPerfil.getContentPane().setBackground(new Color(95, 159, 159));
        meuPerfil.setResizable(false);
        meuPerfil.setLayout(null);
        meuPerfil.setLocationRelativeTo(null);
        meuPerfil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panelPerfil = new JPanel();
        panelPerfil.setBounds(0,65,400,600);
        panelPerfil.setBackground(new Color(95, 159, 159));
        panelPerfil.setLayout(null);

        RetornaInfoUsuario.buscarInformacoesUsuario();

        JLabel titulo = new JLabel("Dados do perfil");
        titulo.setBounds(280, -20, 300,80);
        titulo.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        meuPerfil.add(titulo);

        JLabel nomeLabel = new JLabel("Nome: " + RetornaInfoUsuario.retornaNome());
        nomeLabel.setBounds(5,0,600,50);
        nomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        nomeLabel.setVisible(true);
        panelPerfil.add(nomeLabel);

        JLabel telefoneLabel = new JLabel("Telefone: " + RetornaInfoUsuario.retornaTelefone() );
        telefoneLabel.setBounds(5,50,600,50);
        telefoneLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        telefoneLabel.setVisible(true);
        panelPerfil.add(telefoneLabel);

        JLabel cpfLabel = new JLabel("CPF: " + RetornaInfoUsuario.retornaCpf());
        cpfLabel.setBounds(5,100,600,50);
        cpfLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        cpfLabel.setVisible(true);
        panelPerfil.add(cpfLabel);

        JLabel idadeLabel = new JLabel("Idade: " + RetornaInfoUsuario.retornaIdade());
        idadeLabel.setBounds(5,150,600,50);
        idadeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        idadeLabel.setVisible(true);
        panelPerfil.add(idadeLabel);

        JButton alterar = new JButton();
        alterar.setText("Alterar");
        alterar.setFont(new Font("Times New Roman",Font.PLAIN,23));
        alterar.setBounds(135,500,170,35);
        alterar.addActionListener(this::alterarInfo);
        meuPerfil.add(alterar);

        JButton voltar = new JButton();
        voltar.setText("Voltar");
        voltar.setFont(new Font("Times New Roman",Font.PLAIN,23));
        voltar.setBounds(315,500,170,35);

        voltar.addActionListener((event) -> meuPerfil.dispose());

        meuPerfil.add(voltar);

        JButton apagarConta = new JButton();
        apagarConta.setText("Apagar Perfil");
        apagarConta.setFont(new Font("Times New Roman",Font.PLAIN,23));
        apagarConta.setBounds(495,500,170,35);
        apagarConta.addActionListener(this::apagarConta);
        meuPerfil.add(apagarConta);

        painelAlteracao = new JPanel();
        painelAlteracao.setBounds(400,65,400,600);
        painelAlteracao.setBackground(new Color(95, 159, 159, 255));
        painelAlteracao.setLayout(null);
        painelAlteracao.setVisible(false);
        meuPerfil.add(painelAlteracao);

        meuPerfil.add(panelPerfil);
        panelPerfil.setVisible(true);
        meuPerfil.setVisible(true);
    }

    private void apagarConta(ActionEvent actionEvent) {

        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM tbl_usuarios"
                + " WHERE id = ?;";

        String sql2 = "DELETE FROM tbl_contaBancaria"
                + " WHERE id = ?;";

        String sql3 = "DELETE FROM tbl_salario"
                + " WHERE id = ?;";

        String sql4 = "DELETE FROM tbl_rendaExtra"
                + " WHERE id = ?;";

        String sql5 = "DELETE FROM tbl_acoes"
                + " WHERE id = ?;";


        try {

            preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            preparedStatement.setInt(1, LoginView.getIdUsuario());
            preparedStatement.executeUpdate();

            preparedStatement = conexaoSQLite.criarPreparedStatement(sql2);
            preparedStatement.setInt(1, LoginView.getIdUsuario());
            preparedStatement.executeUpdate();

            preparedStatement = conexaoSQLite.criarPreparedStatement(sql3);
            preparedStatement.setInt(1, LoginView.getIdUsuario());
            preparedStatement.executeUpdate();

            preparedStatement = conexaoSQLite.criarPreparedStatement(sql4);
            preparedStatement.setInt(1, LoginView.getIdUsuario());
            preparedStatement.executeUpdate();

            preparedStatement = conexaoSQLite.criarPreparedStatement(sql5);
            preparedStatement.setInt(1, LoginView.getIdUsuario());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }

            }
        System.exit(0);
        }
    private void alterarInfo (ActionEvent actionEvent){

            //tem que arrumar essa classe

            JLabel alterarNomeLabel = new JLabel("Nome:");
            alterarNomeLabel.setBounds(5, 0, 600, 50);
            alterarNomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            alterarNomeLabel.setVisible(true);
            painelAlteracao.add(alterarNomeLabel);

            nome = new JTextField();
            nome.setText(RetornaInfoUsuario.retornaNome());
            nome.setBounds(63, 0, 600, 40);
            nome.setVisible(true);
            painelAlteracao.add(nome);

            JLabel alterarTelefoneLabel = new JLabel("Telefone:");
            alterarTelefoneLabel.setBounds(5, 50, 600, 50);
            alterarTelefoneLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            alterarTelefoneLabel.setVisible(true);
            painelAlteracao.add(alterarTelefoneLabel);

            telefone = new JTextField();
            telefone.setText(RetornaInfoUsuario.retornaTelefone());
            telefone.setBounds(87, 50, 600, 40);
            telefone.setVisible(true);
            painelAlteracao.add(telefone);

            JLabel alterarCpfLabel = new JLabel("CPF:");
            alterarCpfLabel.setBounds(5, 100, 600, 50);
            alterarCpfLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            alterarCpfLabel.setVisible(true);
            painelAlteracao.add(alterarCpfLabel);

            cpf = new JTextField();
            cpf.setText(RetornaInfoUsuario.retornaCpf());
            cpf.setBounds(54, 100, 600, 40);
            cpf.setVisible(true);
            painelAlteracao.add(cpf);

            JLabel alteraIdadeLabel = new JLabel("Idade:");
            alteraIdadeLabel.setBounds(5, 150, 600, 50);
            alteraIdadeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            alteraIdadeLabel.setVisible(true);
            painelAlteracao.add(alteraIdadeLabel);

            idade = new JTextField();
            idade.setText(String.valueOf(RetornaInfoUsuario.retornaIdade()));
            idade.setBounds(63, 150, 600, 40);
            idade.setVisible(true);
            painelAlteracao.add(idade);

            JButton salvar = new JButton();
            salvar.setText("Salvar");
            salvar.setFont(new Font("Times New Roman", Font.PLAIN, 23));
            salvar.setBounds(130, 220, 140, 35);
            salvar.addActionListener(this::alterarDadosUsuario);
            painelAlteracao.add(salvar);

            painelAlteracao.setVisible(true);


        }
    private void alterarDadosUsuario (ActionEvent actionEvent){

            ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
            conexaoSQLite.conectar();
            PreparedStatement preparedStatement = null;

            String sql = "UPDATE tbl_usuarios"
                    + " SET "
                    + " nome = ?, "
                    + " telefone = ?, "
                    + " idade = ?, "
                    + " cpf = ? "
                    + " WHERE id = ?";
            try {

                preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
                preparedStatement.setString(1, nome.getText());
                preparedStatement.setString(2, telefone.getText());
                preparedStatement.setInt(3, Integer.parseInt(idade.getText()));
                preparedStatement.setString(4, cpf.getText());
                preparedStatement.setInt(5, LoginView.getIdUsuario());
                preparedStatement.executeUpdate();

                JOptionPane.showMessageDialog(null,
                        "Dados atualizados", "Informativo", JOptionPane.PLAIN_MESSAGE);

                meuPerfil.dispose();
                new MeuPerfilView();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    assert preparedStatement != null;
                    preparedStatement.close();
                    conexaoSQLite.desconectar();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }


        }

}

