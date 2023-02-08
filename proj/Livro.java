public class Livro{

    public String titulo;
    public String autor;
    public String editora;
    public int ano;
    public boolean disponibilidade;


    public Livro (String a, String b, String c, int x) {
        titulo = a;
        autor = b;
        editora = c;
        ano = x;
        disponibilidade = true;
    }

    void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    String pegarTitulo () {return titulo;}

    void setAutor(String autor) {
        this.autor = autor;
    } 

    String pegarAutor () {return autor;}

    void setEditora(String editora) {
        this.editora = editora;
    }

    String pegarEditora () {return editora;}

    void setAno(int ano) {
        this.ano = ano;
    }

    int pegarAno () {return ano;}

    void mudarDisponibilidade() {
        if(this.disponibilidade == true) {
            this.disponibilidade = false;
        } else {
            this.disponibilidade = true;
        }
    }

    boolean pegarDisponibilidade () {return disponibilidade;}
}