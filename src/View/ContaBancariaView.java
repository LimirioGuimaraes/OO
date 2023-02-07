package View;

import model.RetornaInfoConta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContaBancariaView extends JFrame {

    JPanel painelInfo;
    JFrame contaBancaria;
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

    private void editarInfoConta(ActionEvent actionEvent) {

        JPanel painelEdicao = new JPanel();
        painelEdicao.setBounds(300,0,500,600);
        painelEdicao.setBackground(new Color(180, 220, 209));
        painelEdicao.setLayout(null);
        painelInfo.setVisible(false);
        painelEdicao.setVisible(true);
        contaBancaria.add(painelEdicao);

        RetornaInfoConta.buscarInformacoesConta();

        JLabel titulo = new JLabel("Alterar infomações da conta");
        titulo.setBounds(50, -20, 600,80);
        titulo.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        painelEdicao.add(titulo);

        JLabel bancoLabel = new JLabel("Banco:");
        bancoLabel.setBounds(5, 50, 120,30);
        bancoLabel.setFont(new Font("Arial", Font.PLAIN, 21));
        bancoLabel.setForeground(new Color(0, 0, 0));
        painelEdicao.add(bancoLabel);

        JTextField bancoTextField = new JTextField();
        bancoTextField.setText("");
        bancoTextField.setBounds(85, 50, 140, 30);
        bancoTextField.setVisible(true);
        painelEdicao.add(bancoTextField);

        JButton salvar = new JButton();
        salvar.setText("Salvar");
        salvar.setFont(new Font("Times New Roman",Font.PLAIN,23));
        salvar.setBounds(105,450,140,35);
        painelEdicao.add(salvar);

        JButton cancelar = new JButton();
        cancelar.setText("Cancelar");
        cancelar.setFont(new Font("Times New Roman",Font.PLAIN,23));
        cancelar.setBounds(250,450,140,35);
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

        JLabel agenciaLabel = new JLabel("Número da agência: " + RetornaInfoConta.retornaNumAgencia());
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
