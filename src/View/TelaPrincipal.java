package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class TelaPrincipal extends JFrame {

    //algumas variaveis criada para testes, depois do programa integrado apagar
    String testenome = "Limirio Correia Guimaraes";
    Double testeSalario1 = 5241.34;
    //fim das variaveis para teste
    ImageIcon fundo = new ImageIcon(Objects.requireNonNull(getClass().getResource("Imagens/menu.png")));
    public TelaPrincipal(){
        super();
        JFrame telaPrincipal = new JFrame();
        telaPrincipal.setTitle("Controle Financeiro");
        telaPrincipal.setSize(800,600);
        telaPrincipal.getContentPane().setBackground(new Color(95, 159, 159));
        telaPrincipal.setResizable(false);
        telaPrincipal.setLayout(null);
        telaPrincipal.setLocationRelativeTo(null);

        //Painel para mostrar o nome do usuario e o saldo
        JPanel painel = new JPanel();
        painel.setSize(800,100);
        painel.setBackground(new Color(95, 159, 159));
        painel.setLayout(null);
        telaPrincipal.add(painel);
        painel.setVisible(true);

        //Configurações do nome do usuário
        JLabel nomePainel = new JLabel();
        nomePainel.setText(testenome);
        nomePainel.setForeground(new Color(255, 255, 255));
        nomePainel.setBounds(5, 0, 600,100);
        nomePainel.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        nomePainel.setVisible(true);
        painel.add(nomePainel);

        //Definição do valor em conta do usuário
        JLabel valorPainel = new JLabel();
        //valorPainel.setText("R$ " + String.valueOf(testeSalario1));
        valorPainel.setText(""
                + "<html>"
                + "<font color=\"white\">R$</font> "
                + testeSalario1
                + "</html>");
        valorPainel.setForeground(new Color(0, 0, 0));
        valorPainel.setBounds(560, 0, 600,100);
        valorPainel.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        valorPainel.setVisible(true);
        if (testeSalario1 > 0) {
            valorPainel.setForeground(new Color(27, 236, 62));
        }else{
            valorPainel.setForeground(new Color(246, 106, 106));
        }
        painel.add(valorPainel);

        //Jpanel para acoplar os botoes de interacao do usuario
        JPanel botoes = new JPanel();
        botoes.setBounds(0,100,300,500);
        botoes.setBackground(new Color(0, 0, 0));
        botoes.setVisible(true);
        botoes.setLayout(null);
        telaPrincipal.add(botoes);

        //Botoes de interacao
        //Botao da conta bancaria
        JButton contaBancaria = new JButton();
        contaBancaria.setText("Conta Bancária");
        contaBancaria.setFont(new Font("Times New Roman",Font.PLAIN,23));
        contaBancaria.setBounds(0,0,300,100);
        contaBancaria.setBackground(new Color(95, 159, 159));
        contaBancaria.setForeground(new Color(255, 255, 255));
        contaBancaria.addActionListener(this::abrirConta);
        botoes.add(contaBancaria);

        //Botao de despesas
        JButton despesas = new JButton();
        despesas.setText("Despesas");
        despesas.setFont(new Font("Times New Roman",Font.PLAIN,23));
        despesas.setBounds(0,100,300,100);
        despesas.setBackground(new Color(95, 159, 159));
        despesas.setForeground(new Color(255, 255, 255));
        despesas.addActionListener(this::abrirDespesas);
        botoes.add(despesas);

        //Botao de Controle de financas
        JButton controle = new JButton();
        controle.setText("Controle");
        controle.setFont(new Font("Times New Roman",Font.PLAIN,23));
        controle.setBounds(0,200,300,100);
        controle.setBackground(new Color(95, 159, 159));
        controle.setForeground(new Color(255, 255, 255));
        controle.addActionListener(this::abrirControle);
        botoes.add(controle);

        //botao
        JButton meuPerfil = new JButton();
        meuPerfil.setText("Meu Perfil");
        meuPerfil.setFont(new Font("Times New Roman",Font.PLAIN,23));
        meuPerfil.setBounds(0,300,300,100);
        meuPerfil.setBackground(new Color(95, 159, 159));
        meuPerfil.setForeground(new Color(255, 255, 255));
        meuPerfil.addActionListener(this::abrirPerfil);
        botoes.add(meuPerfil);

        //botao para fazer Logout
        JButton sair = new JButton();
        sair.setText("Desconectar");
        sair.setFont(new Font("Times New Roman",Font.PLAIN,23));
        sair.setBounds(0,400,300,63);
        sair.setBackground(new Color(246, 106, 106));
        sair.setForeground(new Color(253, 253, 253));
        botoes.add(sair);
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaPrincipal.dispose();
                //depois que agregar com o login do back-end dar um "new login;"//
            }
        });

        Imagem imagem = new Imagem();
        imagem.setBounds(300,100, 500,500);
        imagem.setBackground(new Color(0, 0, 0));
        imagem.setVisible(true);
        telaPrincipal.add(imagem);

        telaPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaPrincipal.setVisible(true);
    }

    private void abrirConta(ActionEvent actionEvent) {
        new ContaBancaria();
    }

    private void abrirDespesas(ActionEvent actionEvent) {
    }

    private void abrirPerfil(ActionEvent actionEvent) {
    }

    private void abrirControle(ActionEvent actionEvent) {
    }

    //criando um JPanel e adicionando a imagem no menu
    public class Imagem extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Image img = fundo.getImage();
            g.drawImage(img,0,0,this);
        }
    }


}
