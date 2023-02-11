import java.util.*;

public class Biblioteca {
    ArrayList<Usuario> usuarios = new ArrayList<>();
    ArrayList<Livro> livros = new ArrayList<> ();

    public static void main(String[] args)
    {
        Biblioteca biblioteca = new Biblioteca();

        try (Scanner input = new Scanner(System.in)) {
            menuLoop: while(true)
            {
                System.out.println("--------------------------- SEJA BEM-VINDO---------------------------\n");
                System.out.println("                                MENU\n");
                System.out.println("1 - Criar Usuário");
                System.out.println("2 - Cadastrar Livros");
                System.out.println("3 - Listar Livros");
                System.out.println("4 - Listar Usuarios");
                System.out.println("5 - Alugar livro");
                System.out.println("6 - Devolver livro");
                System.out.println("7 - Buscar");
                System.out.println("8 - Listar Livros Alugados");
                System.out.println("9 - Sair");
                System.out.print("Digite o que deseja fazer: ");

                String opcao = input.nextLine();
                System.out.println();

                switch (opcao) {
                    case "1":
                        cadastrarUsuario(input, biblioteca);
                        break;
                    case "2":
                        cadastrarLivro(input, biblioteca);
                        break;
                    case "3":
                        listarLivros(biblioteca);
                        break;
                    case "4":
                        listarUsuarios(biblioteca);
                        break;
                    case "5":
                        alugarLivro(input, biblioteca);
                        break;
                    case "6":
                        devolverLivro(input, biblioteca);
                        break;
                    case "7":
                        buscarLivro(input,biblioteca);
                        break;
                    case "8":
                        listarLivrosAlugados(biblioteca);
                        break;
                    case "9":
                        break menuLoop;
                    default:
                        System.out.println("Opcao invalida!");
                        break;
                }
            }
        }

        System.out.println("\n                           ATÉ A PRÓXIMA!");
    }

    private static void listarLivros(Biblioteca biblioteca) {
        biblioteca.listarLivros();
    }

    private static void listarUsuarios(Biblioteca biblioteca) {
        biblioteca.listarUsuarios();
    }

    private static void listarLivrosAlugados(Biblioteca biblioteca)
    {
        biblioteca.listarLivrosAlugados();
    }

    private static void devolverLivro(Scanner input, Biblioteca biblioteca) {
        System.out.print("Digite o seu usuario: ");
        String username = input.nextLine();
        Usuario usuario = null;

        for (Usuario u : biblioteca.usuarios) {
            if (u.username.equals(username))
                usuario = u;
        }

        if (usuario == null) {
            System.out.println("Usuario nao encontrado!");
            return;
        }

        System.out.print("Digite o titulo do livro: ");
        String titulo = input.nextLine();
        Livro livro = null;

        for (Livro l : biblioteca.livros) {
            if (l.titulo.equals(titulo))
                livro = l;
        }

        if (livro == null) {
            System.out.println("Livro nao encontrado!");
            return;
        }

        usuario.devolverLivro(livro);
        System.out.println("Livro devolvido!");
    }

    private static void alugarLivro(Scanner input, Biblioteca biblioteca) {
        System.out.print("Digite o seu usuario: ");

        String username = input.nextLine();
        Usuario usuario = null;

        for (Usuario u : biblioteca.usuarios) {
            if (u.username.equals(username))
                usuario = u;
        }

        if (usuario == null) {
            System.out.println("Usuario nao encontrado!");
            return;
        }

        System.out.print("Digite o titulo do livro: ");
        String titulo = input.nextLine();
        Livro livro = null;

        for (Livro l : biblioteca.livros) {
            if (l.titulo.equals(titulo))
                livro = l;
        }

        if (livro == null) {
            System.out.println("Livro nao encontrado!");
            return;
        }

        usuario.alugarLivro(livro);
    }

    private static void cadastrarUsuario(Scanner input, Biblioteca biblioteca) {
        System.out.print("Digite o Username: ");
        String username = input.nextLine();

        System.out.print("Digite sua senha: ");
        String senha = input.nextLine();

        biblioteca.criarUsuario(username, senha);
    }

    private static void cadastrarLivro(Scanner input, Biblioteca biblioteca) {
        System.out.println("Nome do Livro:");
        String titulo = input.nextLine();

        System.out.println("Autor do Livro:");
        String autor = input.nextLine();

        System.out.println("Editora do Livro:");
        String editora = input.nextLine();

        System.out.println("Ano de lançamento do Livro:");
        int anoLancamento = input.nextInt();
        
        biblioteca.cadastrarLivro(titulo, autor, editora, anoLancamento);
    }

    private static void buscarLivro(Scanner input, Biblioteca biblioteca)
    {
        System.out.print("Digite o titulo do livro: ");
        String titulo = input.nextLine();
        Livro livro = null;

        for(Livro l : biblioteca.livros)
        {
            if(l.titulo.equals(titulo))
            livro = l;
        }

        if(livro == null)
        {
            System.out.println("Livro nao encontrado!");
            return;
        }
        else
        {
            System.out.print(" Titulo: " + livro.titulo);
            System.out.print(" Autor: " + livro.autor);
            System.out.print(" Editora: " + livro.editora);
            System.out.print(" Ano de lançamento: " + livro.ano);

            if (livro.pegarDisponibilidade() == true) {
                System.out.println(" Livro disponivel");
                    return;
                }
            else
            {
                System.out.println(" Livro indisponivel");
                return;
            }
        }
    }

    public void criarUsuario(String username, String senha) {
        Usuario novoUsuario = new Usuario(username, senha);
        this.usuarios.add(novoUsuario);
    }

    public void cadastrarLivro(String titulo, String autor, String editora, int anoLancamento) {
        this.livros.add(new Livro(titulo, autor, editora, anoLancamento));
    }

    public void listarLivros() {
        for (Livro livro: livros) {
            System.out.print(" Titulo: " + livro.pegarTitulo());
            System.out.print(" Autor: " + livro.pegarAutor());
            System.out.print(" Editora: " + livro.pegarEditora());
            System.out.print(" Ano de lançamento: " + livro.pegarAno());

            if (livro.pegarDisponibilidade()) {
                System.out.println(" Livro disponivel");
            }
            else {
                System.out.println(" Livro indisponivel");
            }
        }
    }

    public void listarUsuarios() {
        for (Usuario usuario : this.usuarios)
            System.out.println(usuario.username);
    }

    public void listarLivrosAlugados()
    {
        Usuario usuario = null;
        int count = 0;

        for (Usuario u : usuarios) {
            usuario = u;
            if(usuario.livrosAlugados.size() != 0)
            {
                System.out.print(usuario.username);
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
}
