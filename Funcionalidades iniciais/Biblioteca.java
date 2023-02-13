import java.util.*;

public class Biblioteca {
    ArrayList<Usuario> admins = new ArrayList<>();
    ArrayList<Usuario> usuarios = new ArrayList<>();
    ArrayList<Livro> livros = new ArrayList<> ();

    public void criarUsuario(String username, String senha) {
        Usuario novoUsuario = new Usuario(username, senha);
        this.usuarios.add(novoUsuario);
    }

    public void cadastrarLivro(Livro livro) {
        this.livros.add(livro);
    }

    public void listarLivros() {
        for (Livro livro: livros) {
            System.out.print("Titulo: " + livro.pegarTitulo());
            System.out.print(" Autor: " + livro.pegarAutor());
            System.out.print(" Editora: " + livro.pegarEditora());
            System.out.print(" Ano de lançamento: " + livro.pegarAno());

            if (livro.pegarDisponibilidade() && livro.pegarReserva()) {
                System.out.println(" Livro disponivel sem possibilidade de reserva");
            }
            else if (!livro.pegarDisponibilidade() && livro.pegarReserva()){
                System.out.println(" Livro indisponivel com possibilidade de reserva");
            }
            else if (!livro.pegarDisponibilidade() && !livro.pegarReserva())
            {
                System.out.println(" Livro indisponivel sem possibilidade de reserva");
            
            }
        }
    } 

    public void listarUsuarios() {
        for (Usuario usuario : this.usuarios) {
            usuario.listarLivrosAlugados();
            System.out.println();
        }
    }

    public void listarLivrosAlugados()
    {
        Usuario usuario = null;
        int count = 0;

        for (Usuario u : usuarios) {
            usuario = u;
            if(usuario.livrosAlugados.size() != 0)
            {
                usuario.listarLivrosAlugados();
                System.out.println("\n");
                count ++;
            }
        }
        if(count == 0)
        {
            System.out.println("Nenhum Livro Alugado!");
        }
    }

    public void criarAdmin(String username, String senha) {
        Usuario novoUsuario = new Usuario(username, senha);
        this.admins.add(novoUsuario);
    }

    public void listarAdmins() {
        for (Usuario usuario : this.admins)
            System.out.println(usuario.username);
    }

    Livro buscarLivro(String titulo) {
        Livro livro = null;
    
        for (Livro l : this.livros) {
            if (l.titulo.equals(titulo))
                livro = l;
        }

        return livro;
    }
}
