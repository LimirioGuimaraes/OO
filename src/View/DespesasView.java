package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DespesasView extends JFrame {
    public DespesasView(){

        super();
        JFrame despesas = new JFrame();
        despesas.setTitle("Despesas");
        despesas.setSize(800,600);
        despesas.getContentPane().setBackground(new Color(95, 159, 159));
        despesas.setResizable(false);
        despesas.setLayout(null);
        despesas.setLocationRelativeTo(null);
        despesas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Painel para adicionar os botoes de acoes
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBounds(0,0,300,600);
        painelBotoes.setBackground(new Color(95, 159, 159));
        painelBotoes.setLayout(null);
        despesas.add(painelBotoes);


        JButton cartoes = new JButton();
        cartoes.setLayout(null);
        cartoes.setText("Cartões");
        cartoes.setFont(new Font("Times New Roman",Font.PLAIN,23));
        cartoes.setBounds(0,0,300,100);
        cartoes.setBackground(new Color(95, 159, 159));
        cartoes.setForeground(new Color(255, 255, 255));
        painelBotoes.add(cartoes);

        JButton gastosFixos = new JButton();
        gastosFixos.setText("Gastos Fixos");
        gastosFixos.setFont(new Font("Times New Roman",Font.PLAIN,23));
        gastosFixos.setBounds(0,100,300,100);
        gastosFixos.setBackground(new Color(95, 159, 159));
        gastosFixos.setForeground(new Color(255, 255, 255));
        painelBotoes.add(gastosFixos);

        JButton gastosVariaveis = new JButton();
        gastosVariaveis.setText("Gastos Variáveis");
        gastosVariaveis.setFont(new Font("Times New Roman",Font.PLAIN,23));
        gastosVariaveis.setBounds(0,200,300,100);
        gastosVariaveis.setBackground(new Color(95, 159, 159));
        gastosVariaveis.setForeground(new Color(255, 255, 255));
        painelBotoes.add(gastosVariaveis);


        //Painel para mostrar informacoes
        JPanel painelInfo = new JPanel();
        painelInfo.setBounds(300,0,500,600);
        painelInfo.setBackground(new Color(180, 220, 209));
        painelInfo.setLayout(null);
        despesas.add(painelInfo);

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
                despesas.dispose();
            }
        });

        despesas.setVisible(true);
    }

}
