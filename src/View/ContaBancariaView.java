package View;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ContaBancariaView extends JFrame {

    JPanel painelInfo;
    JFrame contaBancaria;
    JTextField bancoTextField;
    JTextField agenciaTextField;
    JTextField numContaTextField;
    JTextField digitoContaTextField;
    JTextField saldoTextField;
    JTextField salarioTextField;
    JPanel painelEdicao;
    JPanel painelSalario;
    JTextField origemTextField;
    JTextField valorRenda;
    JPanel painelRenda;
    JPanel painelAcoes;
    JTextField nomeAcaoField;
    JTextField valorAcao;
    JPanel painelAddAcao;
    JTextField removeAcao;
    public ContaBancariaView(){
        super();
        painelEdicao = new JPanel();
        painelSalario = new JPanel();
        painelRenda = new JPanel();
        painelAcoes = new JPanel();
        painelAddAcao = new JPanel();
        
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
        salario.addActionListener(this::salarioInfoEditar);
        painelBotoes.add(salario);

        JButton rendaExtra = new JButton();
        rendaExtra.setText("Renda Extra");
        rendaExtra.setFont(new Font("Times New Roman",Font.PLAIN,23));
        rendaExtra.setBounds(0,100,300,100);
        rendaExtra.setBackground(new Color(95, 159, 159));
        rendaExtra.setForeground(new Color(255, 255, 255));
        rendaExtra.addActionListener(this::addRendaExtra);
        painelBotoes.add(rendaExtra);

        JButton acoes = new JButton();
        acoes.setText("Ações");
        acoes.setFont(new Font("Times New Roman",Font.PLAIN,23));
        acoes.setBounds(0,200,300,100);
        acoes.setBackground(new Color(95, 159, 159));
        acoes.setForeground(new Color(255, 255, 255));
        acoes.addActionListener(this::infoAcoes);
        painelBotoes.add(acoes);

        JButton editar = new JButton();
        editar.setText("Editar Informações");
        editar.setFont(new Font("Times New Roman",Font.PLAIN,23));
        editar.setBounds(0,300,300,100);
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

        voltar.addActionListener((event) -> contaBancaria.dispose());
        voltar.addActionListener((event) -> new TelaPrincipalView());

        contaBancaria.setVisible(true);
    }

    private void infoAcoes(ActionEvent actionEvent) {
        
        painelAcoes.setBounds(300,0,500,600);
        painelAcoes.setBackground(new Color(180, 220, 209));
        painelAcoes.setLayout(null);
        contaBancaria.add(painelAcoes);

        painelRenda.setVisible(false);
        painelEdicao.setVisible(false);
        painelInfo.setVisible(false);
        painelSalario.setVisible(false);
        painelAcoes.setVisible(true);

        Salario.buscarInfoSalario();

        JLabel titulo = new JLabel("Ações");
        titulo.setBounds(220, -20, 600,80);
        titulo.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        painelAcoes.add(titulo);

        painelAddAcao.setBounds(0,40,500,220);
        painelAddAcao.setBackground(new Color(180, 220, 209));
        painelAddAcao.setLayout(null);
        painelAddAcao.setVisible(true);
        painelAcoes.add(painelAddAcao);

        JLabel nomeAcao = new JLabel("Nome da ação");
        nomeAcao.setBounds(5, 15, 400,30);
        nomeAcao.setFont(new Font("Arial", Font.PLAIN, 21));
        nomeAcao.setForeground(new Color(0, 0, 0));
        painelAddAcao.add(nomeAcao);

        nomeAcaoField = new JTextField();
        nomeAcaoField.setBounds(145, 15, 300,30);
        nomeAcaoField.setText("Nome ação: ");
        nomeAcaoField.setVisible(true);
        painelAddAcao.add(nomeAcaoField);

        JLabel valorLabel = new JLabel("Valor:");
        valorLabel.setBounds(5, 50, 200,30);
        valorLabel.setFont(new Font("Arial", Font.PLAIN, 21));
        valorLabel.setForeground(new Color(0, 0, 0));
        painelAddAcao.add(valorLabel);

        valorAcao = new JTextField();
        valorAcao.setBounds(60, 50, 140, 30);
        valorAcao.setText("0");
        valorAcao.setVisible(true);
        painelAddAcao.add(valorAcao);

        JButton addAcao = new JButton();
        addAcao.setText("Adicionar ação");
        addAcao.setFont(new Font("Times New Roman",Font.PLAIN,23));
        addAcao.setBounds(150,90,200,35);
        addAcao.addActionListener(this::adicionarAcao);
        painelAddAcao.add(addAcao);

        JLabel labelRemoveAcao = new JLabel("Passe o id da ação q deseja remover: ");
        labelRemoveAcao.setBounds(5, 140, 400,30);
        labelRemoveAcao.setFont(new Font("Arial", Font.PLAIN, 21));
        labelRemoveAcao.setForeground(new Color(0, 0, 0));
        painelAddAcao.add(labelRemoveAcao);

        removeAcao = new JTextField();
        removeAcao.setBounds(365, 140, 30, 30);
        removeAcao.setText("0");
        removeAcao.setVisible(true);
        painelAddAcao.add(removeAcao);

        JLabel listarAcao = new JLabel();
        listarAcao.setBounds(0,220,500,380);
        listarAcao.setFont(new Font("Arial", Font.PLAIN, 21));
        listarAcao.setForeground(new Color(0, 0, 0));
        listarAcao.setText(Acoes.listarAcoes());
        painelAcoes.add(listarAcao);

        JButton removerAcao = new JButton();
        removerAcao.setText("Remover ação");
        removerAcao.setFont(new Font("Times New Roman",Font.PLAIN,23));
        removerAcao.setBounds(150,180,200,35);
        removerAcao.addActionListener((event) -> Acoes.apagarAcao(contaBancaria,removeAcao));
        painelAddAcao.add(removerAcao);
        
        JButton cancelar = new JButton();
        cancelar.setText("Cancelar");
        cancelar.setFont(new Font("Times New Roman",Font.PLAIN,23));
        cancelar.setBounds(175,500,150,35);
        cancelar.addActionListener((event) -> painelInfo.setVisible(true));
        cancelar.addActionListener((event) -> painelAcoes.setVisible(false));

        painelAcoes.add(cancelar);
    }

    private void adicionarAcao(ActionEvent actionEvent) {
        Acoes.addAcao(LoginView.getIdUsuario(), contaBancaria, nomeAcaoField,valorAcao);
    }

    private void addRendaExtra(ActionEvent actionEvent) {

        painelRenda.setBounds(300,0,500,600);
        painelRenda.setBackground(new Color(180, 220, 209));
        painelRenda.setLayout(null);
        contaBancaria.add(painelRenda);

        painelEdicao.setVisible(false);
        painelAcoes.setVisible(false);
        painelInfo.setVisible(false);
        painelSalario.setVisible(false);
        painelRenda.setVisible(true);


        Salario.buscarInfoSalario();

        JLabel titulo = new JLabel("Rendas Extras");
        titulo.setBounds(100, -20, 600,80);
        titulo.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        painelRenda.add(titulo);

        JLabel ultimoSalarioLabel = new JLabel("Origem: ");
        ultimoSalarioLabel.setBounds(5, 60, 400,30);
        ultimoSalarioLabel.setFont(new Font("Arial", Font.PLAIN, 21));
        ultimoSalarioLabel.setForeground(new Color(0, 0, 0));
        painelRenda.add(ultimoSalarioLabel);

        origemTextField = new JTextField();
        origemTextField.setBounds(79, 60, 400,30);
        origemTextField.setText("Origem");
        origemTextField.setVisible(true);
        painelRenda.add(origemTextField);

        JLabel salarioAtualLabel = new JLabel("Valor:");
        salarioAtualLabel.setBounds(5, 110, 200,30);
        salarioAtualLabel.setFont(new Font("Arial", Font.PLAIN, 21));
        salarioAtualLabel.setForeground(new Color(0, 0, 0));
        painelRenda.add(salarioAtualLabel);

        valorRenda = new JTextField();
        valorRenda.setBounds(59, 110, 140, 30);
        valorRenda.setText("0");
        valorRenda.setVisible(true);
        painelRenda.add(valorRenda);

        JButton salvar = new JButton();
        salvar.setText("Adicionar renda");
        salvar.setFont(new Font("Times New Roman",Font.PLAIN,23));
        salvar.setBounds(45,450,200,35);
        salvar.addActionListener(this::addRenda);
        painelRenda.add(salvar);

        JButton cancelar = new JButton();
        cancelar.setText("Cancelar");
        cancelar.setFont(new Font("Times New Roman",Font.PLAIN,23));
        cancelar.setBounds(255,450,200,35);
        cancelar.addActionListener((event) -> painelInfo.setVisible(true));
        cancelar.addActionListener((event) -> painelRenda.setVisible(false));

        painelRenda.add(cancelar);
    }

    private void addRenda(ActionEvent actionEvent) {
        RendaExtra.addRendaExtra(LoginView.getIdUsuario(), origemTextField, valorRenda, contaBancaria);
    }

    private void salarioInfoEditar(ActionEvent actionEvent) {
        
        painelSalario.setBounds(300,0,500,600);
        painelSalario.setBackground(new Color(180, 220, 209));
        painelSalario.setLayout(null);
        contaBancaria.add(painelSalario);

        painelRenda.setVisible(false);
        painelAcoes.setVisible(false);
        painelEdicao.setVisible(false);
        painelInfo.setVisible(false);
        painelSalario.setVisible(true);

        Salario.buscarInfoSalario();

        JLabel titulo = new JLabel("Dados referentes ao salário");
        titulo.setBounds(50, -20, 600,80);
        titulo.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        painelSalario.add(titulo);

        JLabel ultimoSalarioLabel = new JLabel("Último salário: " + Salario.retornaSalario());
        ultimoSalarioLabel.setBounds(5, 75, 400,30);
        ultimoSalarioLabel.setFont(new Font("Arial", Font.PLAIN, 21));
        ultimoSalarioLabel.setForeground(new Color(0, 0, 0));
        painelSalario.add(ultimoSalarioLabel);

        JLabel salarioAtualLabel = new JLabel("Sálario atual:");
        salarioAtualLabel.setBounds(5, 125, 200,30);
        salarioAtualLabel.setFont(new Font("Arial", Font.PLAIN, 21));
        salarioAtualLabel.setForeground(new Color(0, 0, 0));
        painelSalario.add(salarioAtualLabel);

        salarioTextField = new JTextField();
        salarioTextField.setText(String.valueOf(Salario.retornaSalario()));
        salarioTextField.setBounds(130, 125, 140, 30);
        salarioTextField.setVisible(true);
        painelSalario.add(salarioTextField);

        JButton salvar = new JButton();
        salvar.setText("Adicionar salário");
        salvar.setFont(new Font("Times New Roman",Font.PLAIN,23));
        salvar.setBounds(45,450,200,35);
        salvar.addActionListener(this::salvarAtualizacaoSalario);
        painelSalario.add(salvar);

        JButton cancelar = new JButton();
        cancelar.setText("Cancelar");
        cancelar.setFont(new Font("Times New Roman",Font.PLAIN,23));
        cancelar.setBounds(255,450,200,35);
        cancelar.addActionListener((event) -> painelInfo.setVisible(true));
        cancelar.addActionListener((event) -> painelSalario.setVisible(false));

        painelSalario.add(cancelar);
    }

    private void salvarAtualizacaoSalario(ActionEvent actionEvent) {
        Salario.atualizarSalario(salarioTextField, contaBancaria);
    }

    private void salvarAtualizacaoConta(ActionEvent actionEvent) {
       ContaBancaria.salvarAtualizacao(bancoTextField, agenciaTextField, numContaTextField, digitoContaTextField, saldoTextField, contaBancaria);
    }

    private void editarInfoConta(ActionEvent actionEvent) {
        
        painelEdicao.setBounds(300,0,500,600);
        painelEdicao.setBackground(new Color(180, 220, 209));
        painelEdicao.setLayout(null);
        contaBancaria.add(painelEdicao);

        painelRenda.setVisible(false);
        painelAcoes.setVisible(false);
        painelSalario.setVisible(false);
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
        salvar.addActionListener(this::salvarAtualizacaoConta);
        painelEdicao.add(salvar);

        bancoTextField.getText();

        JButton cancelar = new JButton();
        cancelar.setText("Cancelar");
        cancelar.setFont(new Font("Times New Roman",Font.PLAIN,23));
        cancelar.setBounds(250,450,140,35);
        cancelar.addActionListener((event) -> painelInfo.setVisible(true));
        cancelar.addActionListener((event) -> painelEdicao.setVisible(false));
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
