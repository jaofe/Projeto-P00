import java.util.*;

public class cadastroLivro {

    ArrayList<Livro> ListaDeLivros = new ArrayList<> ();

    

    public static void main (String[] args) {

        ArrayList<Livro> ListaDeLivros = new ArrayList<> ();
        Scanner sc = new Scanner(System.in);

        System.out.println("Nome do Livro:");       
        String titulo = sc.nextLine();

        System.out.println("Autor do Livro:");       
        String autor = sc.nextLine();

        System.out.println("Editora do Livro:");       
        String editora = sc.nextLine();

        System.out.println("Ano de lançamento do Livro:");       
        int anoLancamento = sc.nextInt();




        ListaDeLivros.add(new Livro(titulo, autor, editora, anoLancamento));

        for (Livro livro: ListaDeLivros) {
            System.out.println(livro.pegarTitulo());
            System.out.println(livro.pegarAutor());
            System.out.println(livro.pegarEditora());
            System.out.println(livro.pegarAno());

            if (livro.pegarDisponibilidade() == true) {
                System.out.println("Livro disponivel");
            }
            else {
                System.out.println("Livro indisponivel");
            }
        }        
    }
} 