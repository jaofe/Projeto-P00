import java.util.ArrayList;

public class Usuario {
    public String username;
    public String senha;
  
    public ArrayList<Livro> livrosAlugados = new ArrayList<>();
    public ArrayList<Livro> livrosReservados = new ArrayList<>();

    public Usuario(String username, String senha) {
        this.username = username;
        this.senha = senha;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        
        Usuario other = (Usuario) obj;
        return this.username.equals(other.username);
    }

    @Override
    public String toString() {
        return "{usuario: " + this.username + " senha:" + this.senha + " livros: " + livrosAlugados + "}";
    }

    public void alugarLivro(Livro livro) {
        if (livro.pegarDisponibilidade()) {
            livro.mudarDisponibilidade();
            this.livrosAlugados.add(livro);
            System.out.println("Livro alugado com sucesso!");
        }
        else {
            System.out.println("Livro indisponivel!");
        }
    }

    public void devolverLivro(Livro livro) {
        String nome = livro.titulo;

        for (int i = 0; i < livrosAlugados.size(); i++) {
            Livro atual = livrosAlugados.get(i);

            if (atual.titulo.equals(nome)) {
                atual.mudarDisponibilidade();
                livrosAlugados.remove(i);
                System.out.println("Livro devolvido com sucesso!");
                break;
            }
        }
    }

    public void listarLivrosAlugados() {
        
        System.out.print(" ->" + " Titulo:");
        
        for (Livro livro: livrosAlugados) {
    
           System.out.print(" "+ livro.pegarTitulo() + " /");
        
        }      
    }
}
