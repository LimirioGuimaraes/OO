package View;

import model.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CadastrarUsuarioView extends JFrame {

    public static JTextField nomeUsuario;
    public static JTextField telefoneUsuario;
    public static JTextField idadeUsuario;
    public static JTextField cpfUsuario;
    public static JTextField acessoUsuario;
    public static JTextField senhaUsuario;
    JFrame cadastrarUsuario;
    public CadastrarUsuarioView(){
        super();
        cadastrarUsuario = new JFrame();
        cadastrarUsuario.setSize(800, 600);
        cadastrarUsuario.setTitle("Cadastro de Novo Usuário");
        cadastrarUsuario.setResizable(false);
        //cadastrarUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cadastrarUsuario.setLocationRelativeTo(null);
        cadastrarUsuario.getContentPane().setBackground(new Color(95, 159, 159));
        cadastrarUsuario.setLayout(null);

        //Titulo
        JLabel titulo = new JLabel("Insira Seus Dados");
        titulo.setBounds(275, -125, 300,300);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(new Color(0, 0, 0));
        cadastrarUsuario.add(titulo);

        //Responsável pela inserção do nome
        JLabel nome = new JLabel("Nome:");
        nome.setBounds(5, 110, 120,30);
        nome.setFont(new Font("Arial", Font.PLAIN, 25));
        nome.setForeground(new Color(0,0,0));
        cadastrarUsuario.add(nome);

        nomeUsuario = new JTextField();
        nomeUsuario.setText("");
        nomeUsuario.setBounds(81, 110, 504, 30);
        nomeUsuario.setVisible(true);
        cadastrarUsuario.add(nomeUsuario);

        //Responsável pela inserção do telefone
        JLabel telefone = new JLabel("Telefone:");
        telefone.setBounds(5, 160, 120,30);
        telefone.setFont(new Font("Arial", Font.PLAIN, 25));
        telefone.setForeground(new Color(0,0,0));
        cadastrarUsuario.add(telefone);

        telefoneUsuario = new JTextField();
        telefoneUsuario.setText("");
        telefoneUsuario.setBounds(110, 160, 475, 30);
        telefoneUsuario.setVisible(true);
        cadastrarUsuario.add(telefoneUsuario);

        //Responsável pela inserção da idade
        JLabel idade = new JLabel("Idade:");
        idade.setBounds(5, 210, 120,30);
        idade.setFont(new Font("Arial", Font.PLAIN, 25));
        idade.setForeground(new Color(0,0,0));
        cadastrarUsuario.add(idade);

        idadeUsuario = new JTextField();
        idadeUsuario.setText("");
        idadeUsuario.setBounds(75, 210, 510, 30);
        idadeUsuario.setVisible(true);
        cadastrarUsuario.add(idadeUsuario);

        JLabel cpf = new JLabel("CPF:");
        cpf.setBounds(5, 260, 120,30);
        cpf.setFont(new Font("Arial", Font.PLAIN, 25));
        cpf.setForeground(new Color(0,0,0));
        cadastrarUsuario.add(cpf);

        cpfUsuario = new JTextField();
        cpfUsuario.setText("");
        cpfUsuario.setBounds(62, 260, 523, 30);
        cpfUsuario.setVisible(true);
        cadastrarUsuario.add(cpfUsuario);

        /*String pra informar o salto das informacoes do usuário para
        as informacoes de login*/
        JLabel dados = new JLabel("Dados Para Login do Usuário");
        dados.setBounds(200, 200, 600,300);
        dados.setFont(new Font("Arial", Font.BOLD, 30));
        dados.setForeground(new Color(0,0,0));
        cadastrarUsuario.add(dados);

        //Parte para inserir o nome de usuário
        JLabel usuario = new JLabel("Usuário:");
        usuario.setBounds(5, 400, 120,30);
        usuario.setFont(new Font("Arial", Font.PLAIN, 25));
        usuario.setForeground(new Color(0,0,0));
        cadastrarUsuario.add(usuario);

        acessoUsuario = new JTextField();
        acessoUsuario.setText("");
        acessoUsuario.setBounds(100, 400, 250, 30);
        acessoUsuario.setVisible(true);
        cadastrarUsuario.add(acessoUsuario);

        //Parte para inserir a senha do usuario
        JLabel senha = new JLabel("Senha:");
        senha.setBounds(5, 450, 120,30);
        senha.setFont(new Font("Arial", Font.PLAIN, 25));
        senha.setForeground(new Color(0,0,0));
        cadastrarUsuario.add(senha);

        senhaUsuario = new JTextField();
        senhaUsuario.setText("");
        senhaUsuario.setBounds(85, 450, 265, 30);
        senhaUsuario.setVisible(true);
        cadastrarUsuario.add(senhaUsuario);

        JButton salvar = new JButton("Salvar");
        salvar.setFont(new Font("Arial",Font.PLAIN,25));
        salvar.setBounds(325, 500, 150, 40);
        salvar.setBackground(new Color(0, 0, 0, 205));
        salvar.setForeground(new Color (255, 255, 255));
        salvar.setVisible(true);
        salvar.addActionListener(this::salvar);

        cadastrarUsuario.add(salvar);

        cadastrarUsuario.setVisible(true);
    }

    private void salvar(ActionEvent actionEvent) throws RuntimeException {

        Usuario.salvarUsuario(nomeUsuario.getText(),telefoneUsuario.getText(), Integer.parseInt(idadeUsuario.getText()),
                cpfUsuario.getText(),acessoUsuario.getText(),senhaUsuario.getText(), cadastrarUsuario);

    }
}


