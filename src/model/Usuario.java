package model;

public class Usuario extends Pessoa{

    private int idade;
    private String user;
    private String senha;

    public Usuario(String nome, String telefone, String cpf, int idade, String user, String senha) {
        super(nome, telefone, cpf);
        this.idade = idade;
        this.user = user;
        this.senha = senha;
    }

    public Usuario() {
        super();
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    }

