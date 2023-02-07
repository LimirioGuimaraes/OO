package View;

import conexoes.ConexaoSQLite;
import model.RetornaInfoConta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContaBancariaView extends JFrame {

    JPanel painelInfo;
    JFrame contaBancaria;
    JTextField bancoTextField;
    JTextField agenciaTextField;
    JTextField numContaTextField;
    JTextField digitoContaTextField;
    JTextField saldoTextField;
    JPanel painelEdicao;
    JLabel agenciaLabel;
    public ContaBancariaView(){
        super();
        contaBancaria = new JFrame();
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
        editar.addActionListener(this::editarInfoConta);
        painelBotoes.add(editar);


        //Painel para mostrar informacoes
        painelInfo = new JPanel();
        painelInfo.setBounds(300,0,500,600);
        painelInfo.setBackground(new Color(180, 220, 209));
        painelInfo.setLayout(null);
        painelInfo.setVisible(true);

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


    private void salvarAtualizacao(ActionEvent actionEvent) {

        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE tbl_contaBancaria"
                    + " SET "
                    + " banco = ?, "
                    + " numAgencia = ?, "
                    + " numConta = ?, "
                    + " digitoConta = ?, "
                    + " saldo = ? "
                    + " WHERE id = ?";
        try{

            preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            preparedStatement.setString(1, bancoTextField.getText());
            preparedStatement.setInt(2, Integer.parseInt(agenciaTextField.getText()));
            preparedStatement.setInt(3, Integer.parseInt(numContaTextField.getText()));
            preparedStatement.setInt(4, Integer.parseInt(digitoContaTextField.getText()));
            preparedStatement.setDouble(5, Double.parseDouble(saldoTextField.getText()));
            preparedStatement.setInt(6, LoginView.getIdUsuario());
            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Dados atualizados", "Informativo", JOptionPane.PLAIN_MESSAGE);

            contaBancaria.dispose();
            new ContaBancariaView();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
                conexaoSQLite.desconectar();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }

    }

    private void editarInfoConta(ActionEvent actionEvent) {

        painelEdicao = new JPanel();
        painelEdicao.setBounds(300,0,500,600);
        painelEdicao.setBackground(new Color(180, 220, 209));
        painelEdicao.setLayout(null);
        contaBancaria.add(painelEdicao);

        painelInfo.setVisible(false);
        painelEdicao.setVisible(true);

        RetornaInfoConta.buscarInformacoesConta();

        JLabel titulo = new JLabel("Alterar infomações da conta");
        titulo.setBounds(50, -20, 600,80);
        titulo.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        painelEdicao.add(titulo);

        JLabel bancoLabel = new JLabel("Banco:");
        bancoLabel.setBounds(5, 75, 200,30);
        bancoLabel.setFont(new Font("Arial", Font.PLAIN, 21));
        bancoLabel.setForeground(new Color(0, 0, 0));
        painelEdicao.add(bancoLabel);

        bancoTextField = new JTextField();
        bancoTextField.setText(RetornaInfoConta.retornaBanco());
        bancoTextField.setBounds(72, 75, 140, 30);
        bancoTextField.setVisible(true);
        painelEdicao.add(bancoTextField);

        JLabel agenciaLabel = new JLabel("Número da agência:");
        agenciaLabel.setBounds(5, 125, 200,30);
        agenciaLabel.setFont(new Font("Arial", Font.PLAIN, 21));
        agenciaLabel.setForeground(new Color(0, 0, 0));
        painelEdicao.add(agenciaLabel);

        agenciaTextField = new JTextField();
        agenciaTextField.setText(String.valueOf(RetornaInfoConta.retornaNumAgencia()));
        agenciaTextField.setBounds(193, 125, 140, 30);
        agenciaTextField.setVisible(true);
        painelEdicao.add(agenciaTextField);

        JLabel numContaLabel = new JLabel("Número da conta:");
        numContaLabel.setBounds(5, 175, 200,30);
        numContaLabel.setFont(new Font("Arial", Font.PLAIN, 21));
        numContaLabel.setForeground(new Color(0, 0, 0));
        painelEdicao.add(numContaLabel);

        numContaTextField = new JTextField();
        numContaTextField.setText(String.valueOf(RetornaInfoConta.retornaNumConta()));
        numContaTextField.setBounds(172, 175, 140, 30);
        numContaTextField.setVisible(true);
        painelEdicao.add(numContaTextField);

        JLabel digitoContaLabel = new JLabel("Digito da conta:");
        digitoContaLabel.setBounds(5, 225, 200,30);
        digitoContaLabel.setFont(new Font("Arial", Font.PLAIN, 21));
        digitoContaLabel.setForeground(new Color(0, 0, 0));
        painelEdicao.add(digitoContaLabel);

        digitoContaTextField = new JTextField();
        digitoContaTextField.setText(String.valueOf(RetornaInfoConta.retornaDigitoConta()));
        digitoContaTextField.setBounds(153, 225, 140, 30);
        digitoContaTextField.setVisible(true);
        painelEdicao.add(digitoContaTextField);

        JLabel saldoLabel = new JLabel("Saldo:");
        saldoLabel.setBounds(5, 275, 200,30);
        saldoLabel.setFont(new Font("Arial", Font.PLAIN, 21));
        saldoLabel.setForeground(new Color(0, 0, 0));
        painelEdicao.add(saldoLabel);

        saldoTextField = new JTextField();
        saldoTextField.setText(String.valueOf(RetornaInfoConta.retornaSaldo()));
        saldoTextField.setBounds(65, 275, 140, 30);
        saldoTextField.setVisible(true);
        painelEdicao.add(saldoTextField);

        JButton salvar = new JButton();
        salvar.setText("Salvar");
        salvar.setFont(new Font("Times New Roman",Font.PLAIN,23));
        salvar.setBounds(105,450,140,35);
        salvar.addActionListener(this::salvarAtualizacao);
        painelEdicao.add(salvar);

        bancoTextField.getText();

        JButton cancelar = new JButton();
        cancelar.setText("Cancelar");
        cancelar.setFont(new Font("Times New Roman",Font.PLAIN,23));
        cancelar.setBounds(250,450,140,35);
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painelInfo.setVisible(true);
                painelEdicao.setVisible(false);

            }
        });
        painelEdicao.add(cancelar);

    }


    private void infoPanel(JPanel painelInfo){

        JLabel titulo = new JLabel("Dados da conta");
        titulo.setBounds(painelInfo.getX()/2, -20, 600,80);
        titulo.setFont(new Font("Times New Roman", Font.PLAIN, 35));

        RetornaInfoConta.buscarInformacoesConta();

        JLabel bancoLabel = new JLabel("Banco: " + RetornaInfoConta.retornaBanco());
        bancoLabel.setBounds(5,50,600,50);
        bancoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        bancoLabel.setVisible(true);
        painelInfo.add(bancoLabel);

        agenciaLabel = new JLabel("Número da agência: " + RetornaInfoConta.retornaNumAgencia());
        agenciaLabel.setBounds(5,100,600,50);
        agenciaLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        agenciaLabel.setVisible(true);
        painelInfo.add(agenciaLabel);

        JLabel numContaLabel = new JLabel("Número da conta: " + RetornaInfoConta.retornaNumConta() +" - "+ RetornaInfoConta.retornaDigitoConta());
        numContaLabel.setBounds(5,150,600,50);
        numContaLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        numContaLabel.setVisible(true);
        painelInfo.add(numContaLabel);

        JLabel saldoLabel = new JLabel("Saldo: R$ " + RetornaInfoConta.retornaSaldo());
        saldoLabel.setBounds(5,200,600,50);
        saldoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        saldoLabel.setVisible(true);
        painelInfo.add(saldoLabel);

        painelInfo.add(titulo);
        titulo.setVisible(true);
    }

}
