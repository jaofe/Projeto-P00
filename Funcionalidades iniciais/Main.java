import java.util.Scanner;

public class Main {
    static Biblioteca biblioteca;
    static Scanner input;

    public static void main(String[] args) {
        biblioteca = new Biblioteca();
        input = new Scanner(System.in);

        menuLoop: 
        while(true) {
            System.out.println("--------------------------- SEJA BEM-VINDO ---------------------------\n");
            System.out.println("                                MENU\n");
            System.out.println("1 - Criar Usuario");
            System.out.println("2 - Cadastrar Livros");
            System.out.println("3 - Listar Livros");
            System.out.println("4 - Listar Usuarios");
            System.out.println("5 - Alugar Livro");
            System.out.println("6 - Devolver Livro");
            System.out.println("7 - Buscar");
            System.out.println("8 - Listar Livros Alugados");
            System.out.println("9 - Reservar Livro");
            System.out.println("10 - Sair");
            System.out.print("Digite o que deseja fazer: ");

            String opcao = input.nextLine();
            System.out.println();

            switch (opcao) {
                case "1":
                    cadastrarUsuario();
                    break;
                case "2":
                    cadastrarLivro();
                    break;
                case "3":
                    listarLivros();
                    break;
                case "4":
                    listarUsuarios();
                    break;
                case "5":
                    alugarLivro();
                    break;
                case "6":
                    devolverLivro();
                    break;
                case "7":
                    buscarLivro();
                    break;
                case "8":
                    listarLivrosAlugados();
                    break;
                case "9":
                    reservar();
                    break;    
                case "10":
                    break menuLoop;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }

        System.out.println("\n                           ATÉ A PRÓXIMA!");
        input.close();
    }

    private static void listarLivros() {
        biblioteca.listarLivros();
    }

    private static void listarUsuarios() {
        biblioteca.listarUsuarios();
    }

    private static void listarLivrosAlugados()
    {
        biblioteca.listarLivrosAlugados();
    }

    private static void devolverLivro() {
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

        usuario.devolverLivro(livro,biblioteca,username);
    }

    private static void alugarLivro() {
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

    private static void cadastrarUsuario() {
        System.out.print("Digite o Username: ");
        String username = input.nextLine();

        for (Usuario u : biblioteca.usuarios) {
            if (u.username.equals(username)){
                System.out.println("Usuario já existente!");
                return;
            }
        }

        System.out.print("Digite sua senha: ");
        String senha = input.nextLine();

        biblioteca.criarUsuario(username, senha);
    }

    private static void cadastrarLivro() {
        System.out.println("Nome do Livro:");
        String titulo = input.nextLine();

        System.out.println("Autor do Livro:");
        String autor = input.nextLine();

        System.out.println("Editora do Livro:");
        String editora = input.nextLine();

        System.out.println("Ano de lançamento do Livro:");
        String anoLancamento = input.nextLine();

        Livro livro = new Livro(titulo, autor, editora, anoLancamento);
        
        biblioteca.cadastrarLivro(livro);
    }

    private static void buscarLivro()
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
            System.out.print("Titulo: " + livro.titulo);
            System.out.print(" Autor: " + livro.autor);
            System.out.print(" Editora: " + livro.editora);
            System.out.print(" Ano de lançamento: " + livro.ano);

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

    private static void reservar()
    {
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

        usuario.reservar(livro);
    }
}
