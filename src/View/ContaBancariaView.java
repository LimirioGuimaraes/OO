package View;

import conexoes.ConexaoSQLite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaBancariaView extends JFrame {

    JPanel painelInfo;
    public ContaBancariaView(){
        super();
        JFrame contaBancaria = new JFrame();
        contaBancaria.setTitle("Conta Bancária");
        contaBancaria.setSize(800,600);
        contaBancaria.getContentPane().setBackground(new Color(95, 159, 159));
        contaBancaria.setResizable(false);
        contaBancaria.setLayout(null);
        contaBancaria.setLocationRelativeTo(null);
        contaBancaria.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Painel para adicionar os botoes de acoes
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBounds(0,0,300,600);
        painelBotoes.setBackground(new Color(95, 159, 159));
        painelBotoes.setLayout(null);
        contaBancaria.add(painelBotoes);


        JButton salario = new JButton();
        salario.setLayout(null);
        salario.setText("Salário");
        salario.setFont(new Font("Times New Roman",Font.PLAIN,23));
        salario.setBounds(0,0,300,100);
        salario.setBackground(new Color(95, 159, 159));
        salario.setForeground(new Color(255, 255, 255));
        painelBotoes.add(salario);

        JButton rendaExtra = new JButton();
        rendaExtra.setText("Renda Extra");
        rendaExtra.setFont(new Font("Times New Roman",Font.PLAIN,23));
        rendaExtra.setBounds(0,100,300,100);
        rendaExtra.setBackground(new Color(95, 159, 159));
        rendaExtra.setForeground(new Color(255, 255, 255));
        painelBotoes.add(rendaExtra);

        JButton cripto = new JButton();
        cripto.setText("Criptomoedas");
        cripto.setFont(new Font("Times New Roman",Font.PLAIN,23));
        cripto.setBounds(0,200,300,100);
        cripto.setBackground(new Color(95, 159, 159));
        cripto.setForeground(new Color(255, 255, 255));
        painelBotoes.add(cripto);

        JButton acoes = new JButton();
        acoes.setText("Ações");
        acoes.setFont(new Font("Times New Roman",Font.PLAIN,23));
        acoes.setBounds(0,300,300,100);
        acoes.setBackground(new Color(95, 159, 159));
        acoes.setForeground(new Color(255, 255, 255));
        painelBotoes.add(acoes);

        JButton editar = new JButton();
        editar.setText("Editar Informações");
        editar.setFont(new Font("Times New Roman",Font.PLAIN,23));
        editar.setBounds(0,400,300,100);
        editar.setBackground(new Color(95, 159, 159));
        editar.setForeground(new Color(255, 255, 255));
        painelBotoes.add(editar);


        //Painel para mostrar informacoes
        painelInfo = new JPanel();
        painelInfo.setBounds(300,0,500,600);
        painelInfo.setBackground(new Color(180, 220, 209));
        painelInfo.setLayout(null);
        infoPanel(painelInfo);
        contaBancaria.add(painelInfo);

        JButton voltar = new JButton();
        voltar.setText("Voltar");
        voltar.setFont(new Font("Times New Roman",Font.PLAIN,23));
        voltar.setBounds(0,500,300,63);
        voltar.setBackground(new Color(246, 106, 106));
        voltar.setForeground(new Color(255, 255, 255));
        painelBotoes.add(voltar);

        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contaBancaria.dispose();
            }
        });

        contaBancaria.setVisible(true);
    }

    private void infoPanel(JPanel painelInfo){

        String banco = "nome";
        int numAgencia;
        int numConta;
        int digitoConta;
        double saldo;

        JLabel titulo = new JLabel("Dados da conta");
        titulo.setBounds(painelInfo.getX()/2, -20, 600,80);
        titulo.setFont(new Font("Times New Roman", Font.PLAIN, 35));

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

                System.out.println(banco + numAgencia +numConta+ digitoConta+saldo);
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

        JLabel bancoLabel = new JLabel("Banco: " + banco);
        bancoLabel.setBounds(0,50,600,80);
        bancoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        bancoLabel.setVisible(true);
        painelInfo.add(bancoLabel);


        painelInfo.add(titulo);
        titulo.setVisible(true);
    }

}
