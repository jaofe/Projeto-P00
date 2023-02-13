import java.util.Scanner;

public class Main {
    static Biblioteca biblioteca;
    static Scanner input;
    static Usuario usuario;
    static boolean isAdmin = false;

    public static void main(String[] args) {
        biblioteca = new Biblioteca();
        input = new Scanner(System.in);

        biblioteca.admins.add(new Usuario("admin", "admin"));

        do {
            loginMenu();

            if (usuario != null) {
                if (isAdmin) {
                    adminMenu();
                }
                else {
                    usuarioMenu();
                }
            }
        } while (usuario != null);

        System.out.println("\n                           ATÉ A PRÓXIMA!");
        input.close();
    }

    private static void loginMenu() {
        usuario = null;
        isAdmin = false;
        
        loginLoop:
        while (usuario == null) {
            System.out.println("--------------------------- SEJA BEM-VINDO ---------------------------\n");
            System.out.println("                                MENU\n");
            System.out.println("1 - Logar");
            System.out.println("2 - Cadastrar Usuario");
            System.out.println("3 - Sair");
            System.out.print("Digite o que deseja fazer: ");

            String opcao = input.nextLine();
            System.out.println();

            switch (opcao) {
                case "1":
                    usuario = tentarLogar();

                    if (usuario == null)
                        System.out.println("\n\nUsuario ou senha invalidos! Tente novamente");
                    else
                        System.out.println("Usuario " + usuario.username + " logado com sucesso!");
                    break;
                case "2":
                    cadastrarUsuario();
                    break;
                case "3":
                    break loginLoop;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
    }

    private static void adminMenu() {
        menu: while (true) {
            System.out.println("--------------------------- SEJA BEM-VINDO ---------------------------\n");
            System.out.println("                                MENU\n");
            System.out.println("1 - Criar Usuario Administrador");
            System.out.println("2 - Cadastrar Livros");
            System.out.println("3 - Listar Livros");
            System.out.println("4 - Listar Usuarios");
            System.out.println("5 - Listar Administradores");
            System.out.println("6 - Buscar");
            System.out.println("7 - Listar Livros Alugados");
            System.out.println("8 - Sair");
            System.out.print("Digite o que deseja fazer: ");

            String opcao = input.nextLine();
            System.out.println();

            switch (opcao) {
                case "1":
                    cadastrarAdmin();
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
                    listarAdmins();
                    break;
                case "6":
                    buscarLivro();
                    break;
                case "7":
                    listarLivrosAlugados();
                    break;
                case "8":
                    break menu;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
    }

    private static void usuarioMenu() {
        menu: while (true) {
            System.out.println("--------------------------- SEJA BEM-VINDO ---------------------------\n");
            System.out.println("                                MENU\n");
            System.out.println("1 - Buscar");
            System.out.println("2 - Listar Livros");
            System.out.println("3 - Alugar Livro");
            System.out.println("4 - Devolver Livro");
            System.out.println("5 - Listar Livros Alugados");
            System.out.println("6 - Reservar Livro");
            System.out.println("7 - Sair");
            System.out.print("Digite o que deseja fazer: ");

            String opcao = input.nextLine();
            System.out.println();

            switch (opcao) {
                case "1":
                    buscarLivro();
                    break;
                case "2":
                    listarLivros();
                    break;
                case "3":
                    alugarLivro();
                    break;
                case "4":
                    devolverLivro();
                    break;
                case "5":
                    listarLivrosAlugados(usuario);
                    break;
                case "6":
                    reservar();
                    break;
                case "7":
                    break menu;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
    }

    private static void cadastrarAdmin() {
        System.out.print("Digite o Username: ");
        String username = input.nextLine();

        for (Usuario u : biblioteca.admins) {
            if (u.username.equals(username)){
                System.out.println("Usuario já existente!");
                return;
            }
        }

        for (Usuario u : biblioteca.usuarios) {
            if (u.username.equals(username)){
                System.out.println("Usuario já existente!");
                return;
            }
        }

        System.out.print("Digite sua senha: ");
        String senha = input.nextLine();

        biblioteca.criarAdmin(username, senha);
    }

    private static Usuario tentarLogar() {
        System.out.print("Digite o seu usuario: ");
        String username = input.nextLine();

        System.out.print("Digite a sua senha: ");
        String senha = input.nextLine();

        for (Usuario usuario : biblioteca.admins) {
            if (usuario.username.equals(username)){
                if (usuario.senha.equals(senha)){
                    isAdmin = true;
                    return usuario;
                }
                return null;
            }
        }

        for (Usuario usuario : biblioteca.usuarios) {
            if (usuario.username.equals(username)){
                if (usuario.senha.equals(senha))
                    return usuario;
                    
                return null;
            }
        }

        return null;
    }

    private static void listarLivros() {
        biblioteca.listarLivros();
    }

    private static void listarUsuarios() {
        biblioteca.listarUsuarios();
    }

    private static void listarAdmins() {
        biblioteca.listarAdmins();
    }

    private static void listarLivrosAlugados()
    {
        biblioteca.listarLivrosAlugados();
    }

    private static void listarLivrosAlugados(Usuario usuario)
    {
        usuario.listarLivrosAlugados();
        System.out.println();
    }

    private static void devolverLivro() {
        System.out.print("Digite o titulo do livro: ");
        String titulo = input.nextLine();

        Livro livro = biblioteca.buscarLivro(titulo);

        if (livro == null) {
            System.out.println("Livro nao encontrado!");
            return;
        }

        usuario.devolverLivro(livro,biblioteca,usuario.username);
    }

    private static void alugarLivro() {
        System.out.print("Digite o titulo do livro: ");
        String titulo = input.nextLine();
        
        Livro livro = biblioteca.buscarLivro(titulo);

        if (livro == null) {
            System.out.println("Livro nao encontrado!");
            return;
        }

        usuario.alugarLivro(livro);
    }

    private static void cadastrarUsuario() {
        System.out.print("Digite o Username: ");
        String username = input.nextLine();

        for (Usuario u : biblioteca.admins) {
            if (u.username.equals(username)){
                System.out.println("Usuario já existente!");
                return;
            }
        }

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
        Livro livro = biblioteca.buscarLivro(titulo);

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
