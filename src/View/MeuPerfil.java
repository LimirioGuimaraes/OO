package View;

import model.RetornaInfoConta;
import model.RetornaInfoUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeuPerfil extends JFrame {

    JFrame meuPerfil;

    public MeuPerfil(){
        super();
        meuPerfil = new JFrame();
        meuPerfil.setTitle("Meu Perfil");
        meuPerfil.setSize(800,600);
        meuPerfil.getContentPane().setBackground(new Color(95, 159, 159));
        meuPerfil.setResizable(false);
        meuPerfil.setLayout(null);
        meuPerfil.setLocationRelativeTo(null);
        meuPerfil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        RetornaInfoUsuario.buscarInformacoesUsuario();

        JLabel titulo = new JLabel("Dados do perfil");
        titulo.setBounds(280, -20, 300,80);
        titulo.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        meuPerfil.add(titulo);

        JLabel nomeLabel = new JLabel("Nome: " + RetornaInfoUsuario.retornaNome());
        nomeLabel.setBounds(5,50,600,50);
        nomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        nomeLabel.setVisible(true);
        meuPerfil.add(nomeLabel);

        JLabel telefoneLabel = new JLabel("Telefone: " + RetornaInfoUsuario.retornaTelefone() );
        telefoneLabel.setBounds(5,100,600,50);
        telefoneLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        telefoneLabel.setVisible(true);
        meuPerfil.add(telefoneLabel);

        JLabel cpfLabel = new JLabel("CPF: " + RetornaInfoUsuario.retornaCpf());
        cpfLabel.setBounds(5,150,600,50);
        cpfLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        cpfLabel.setVisible(true);
        meuPerfil.add(cpfLabel);

        JLabel idadeLabel = new JLabel("Idade: " + RetornaInfoUsuario.retornaIdade());
        idadeLabel.setBounds(5,200,600,50);
        idadeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        idadeLabel.setVisible(true);
        meuPerfil.add(idadeLabel);

        JButton alterar = new JButton();
        alterar.setText("Alterar");
        alterar.setFont(new Font("Times New Roman",Font.PLAIN,23));
        alterar.setBounds(255,450,140,35);
        alterar.addActionListener(this::alterarInfo);
        meuPerfil.add(alterar);


        JButton voltar = new JButton();
        voltar.setText("Voltar");
        voltar.setFont(new Font("Times New Roman",Font.PLAIN,23));
        voltar.setBounds(400,450,140,35);
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                meuPerfil.dispose();

            }
        });
        meuPerfil.add(voltar);

        meuPerfil.setVisible(true);
    }

    private void alterarInfo(ActionEvent actionEvent) {

        JPanel painelAlteracao = new JPanel();
        painelAlteracao.setBounds(400,0,400,600);
        painelAlteracao.setBackground(new Color(0, 0, 0, 205));
        painelAlteracao.setLayout(null);
        painelAlteracao.setVisible(true);
        meuPerfil.add(painelAlteracao);

        JLabel titulo = new JLabel("Dados do perfil");
        titulo.setBounds(280, -20, 300,80);
        titulo.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        meuPerfil.add(titulo);

        JLabel nomeLabel = new JLabel("Nome: " + RetornaInfoUsuario.retornaNome());
        nomeLabel.setBounds(5,50,600,50);
        nomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        nomeLabel.setVisible(true);
        meuPerfil.add(nomeLabel);

        JLabel telefoneLabel = new JLabel("Telefone: " + RetornaInfoUsuario.retornaTelefone() );
        telefoneLabel.setBounds(5,100,600,50);
        telefoneLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        telefoneLabel.setVisible(true);
        meuPerfil.add(telefoneLabel);

        JLabel cpfLabel = new JLabel("CPF: " + RetornaInfoUsuario.retornaCpf());
        cpfLabel.setBounds(5,150,600,50);
        cpfLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        cpfLabel.setVisible(true);
        meuPerfil.add(cpfLabel);

        JLabel idadeLabel = new JLabel("Idade: " + RetornaInfoUsuario.retornaIdade());
        idadeLabel.setBounds(5,200,600,50);
        idadeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        idadeLabel.setVisible(true);
        meuPerfil.add(idadeLabel);


    }

}
