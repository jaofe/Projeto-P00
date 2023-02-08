import java.util.*;

public class Biblioteca
{
    ArrayList<Usuario> usuarios = new ArrayList<>();
    ArrayList<Livro> ListaDeLivros = new ArrayList<> ();


    public static void main(String args[])
    {
        Biblioteca biblioteca = new Biblioteca();

        
        try (Scanner input = new Scanner(System.in)) {
            String x;
            String Username;
            String Senha; 
            
            while(true)
            {
                System.out.println("--------------------------- SEJA BEM-VINDO---------------------------\n");
                System.out.println("                                MENU\n");
                System.out.println("1 - Criar Usuário");
                System.out.println("2 - Cadastrar Livros");
                System.out.println("3 - Listar Livros");
                System.out.println("4 - Listar Usuarios");
                System.out.println("5 - Alugar livro");
                System.out.println("6 - Devolver livro");
                System.out.println("7 - Sair");
                System.out.print("Digite o que deseja fazer: ");
                
                x = input.nextLine();
                System.out.print("\n");
                
                
                if(x.equals("1"))
                {
                    System.out.print("Digite o Username: ");
                    Username = input.nextLine();

                    System.out.print("Digite sua senha: ");
                    Senha = input.nextLine();
                   
                    biblioteca.criarUsuario(Username, Senha);
                }
                else if (x.equals("2")) {
                    biblioteca.cadastrarLivro();
                }
                else if (x.equals("3")) {
                    biblioteca.listarLivros();
                }
                else if (x.equals("4")) {
                    biblioteca.listarUsuarios();
                }
                else if (x.equals("5")) {
                    System.out.print("Digite o seu usuario: ");

                    String username = input.nextLine();
                    Usuario usuario = null;

                    for (Usuario u : biblioteca.usuarios) {
                        if (u.username.equals(username))
                            usuario = u;
                    }

                    if (usuario == null) {
                        System.out.println("Usuario nao encontrado!");
                        continue;
                    }

                    System.out.print("Digite o titulo do livro: ");
                    String titulo = input.nextLine();
                    Livro livro = null;

                    for (Livro l : biblioteca.ListaDeLivros) {
                        if (l.titulo.equals(titulo))
                            livro = l;
                    }

                    if (livro == null) {
                        System.out.println("Livro nao encontrado!");
                        continue;
                    }

                    usuario.alugarLivro(livro);

                }
                else if (x.equals("6")) {
                    System.out.print("Digite o seu usuario: ");
                    String username = input.nextLine();
                    Usuario usuario = null;

                    for (Usuario u : biblioteca.usuarios) {
                        if (u.username.equals(username))
                            usuario = u;
                    }

                    if (usuario == null) {
                        System.out.println("Usuario nao encontrado!");
                        continue;
                    }

                    System.out.print("Digite o titulo do livro: ");
                    String titulo = input.nextLine();
                    Livro livro = null;

                    for (Livro l : biblioteca.ListaDeLivros) {
                        if (l.titulo.equals(titulo))
                            livro = l;
                    }

                    if (livro == null) {
                        System.out.println("Livro nao encontrado!");
                        continue;
                    }

                    usuario.devolverLivro(livro);
                    System.out.println("Livro devolvido!");

                }
                else if (x.equals("7"))
                {
                    break;
                }
                else {
                    System.out.println("Opcao invalida!");
                }
            }
        }
        
        System.out.println("\n                           ATÉ A PRÓXIMA!");

    }

  
   public void criarUsuario(String username, String senha)
   {
      Usuario novoUsuario = new Usuario(username, senha);
      this.usuarios.add(novoUsuario);
   }

   public void cadastrarLivro() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Nome do Livro:");       
        String titulo = sc.nextLine();

        System.out.println("Autor do Livro:");       
        String autor = sc.nextLine();

        System.out.println("Editora do Livro:");       
        String editora = sc.nextLine();

        System.out.println("Ano de lançamento do Livro:");       
        int anoLancamento = sc.nextInt();

        this.ListaDeLivros.add(new Livro(titulo, autor, editora, anoLancamento));
   }

   public void listarLivros() {
    for (Livro livro: ListaDeLivros) {
        System.out.print(" Titulo: " + livro.pegarTitulo());
        System.out.print(" Autor: " + livro.pegarAutor());
        System.out.print(" Editora: " + livro.pegarEditora());
        System.out.print(" Ano de lançamento: " + livro.pegarAno());

        if (livro.pegarDisponibilidade() == true) {
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
}