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
            livro.printLivro();
        }
    } 

    public void listarUsuarios() {
        for (Usuario usuario : this.usuarios) {
            usuario.listarLivrosAlugados();
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
                System.out.println();
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

    public Usuario buscarUsuario(String username) {
        for (Usuario usuario : this.admins) {
            if (usuario.username.equals(username)) {
                return usuario;
            }
        }
    
        for (Usuario usuario : this.usuarios) {
            if (usuario.username.equals(username)) {
                return usuario;
            }
        }

        return null;
    }
}
