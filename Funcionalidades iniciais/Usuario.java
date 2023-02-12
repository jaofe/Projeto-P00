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
        return "{usuario: " + this.username + " senha:" + this.senha + " livros: " + livrosAlugados +" reservas: "+ livrosReservados +"}";
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

    public void devolverLivro(Livro livro, Biblioteca biblioteca, String user) {
        String nome = livro.titulo;

        for (int i = 0; i < livrosAlugados.size(); i++) {
            Livro atual = livrosAlugados.get(i);

            if (atual.titulo.equals(nome)) {
                atual.mudarDisponibilidade();
                livrosAlugados.remove(i);
                System.out.println("Livro devolvido com sucesso!");
                buscarReserva(livro,biblioteca, user);
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

    public void reservar(Livro livro)
    {
        if (livro.pegarReserva() && !livro.pegarDisponibilidade()) {
            livro.mudarReserva();
            this.livrosReservados.add(livro);
            System.out.println("Livro reservado com sucesso!");
        }
        else if (livro.pegarReserva() && livro.pegarDisponibilidade())
        {
            System.out.println("Opcao invalida, livro atualmente disponivel!");
        }
        else if (!livro.pegarDisponibilidade() && !livro.pegarReserva())
        {
            System.out.println("Livro já reservado!");
        }
    }
    
    public void buscarReserva(Livro livro, Biblioteca biblioteca, String user)
    {
        for(Usuario u : biblioteca.usuarios)
        {
            if(!u.username.equals(user))
            {
                removerReserva(livro, u);
            }
        }
    }

    public void removerReserva(Livro livro, Usuario u)
    {
        if (livro.pegarDisponibilidade()) {
            livro.mudarDisponibilidade();
            u.livrosAlugados.add(livro);
        }
       
        String n = livro.titulo;
       
        for(int j = 0;j < u.livrosReservados.size(); j++)
        {
            Livro aux = u.livrosReservados.get(j);
            if(aux.titulo.equals(n))
            {
                aux.mudarReserva();
                u.livrosReservados.remove(j);
                System.out.println("Livro reservado foi alugado!");
                break;
            }
        }
    }


}
