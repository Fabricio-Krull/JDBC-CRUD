import java.io.Console;
import java.util.*;

public class Main {
    @SuppressWarnings("ConvertToTryWithResources")
    static boolean boolRead(String boolLine){


        boolLine = boolLine.toLowerCase();
        boolean ativo = false;
        if (boolLine.equals("0") || boolLine.equals("falso") || boolLine.equals("false") || boolLine.equals("f") || boolLine.equals("!true") || boolLine.equals("untrue") || boolLine.equals("no") || boolLine.equals("não") || boolLine.equals("nao") || boolLine.equals("indisponível") || boolLine.equals("indisponivel") || boolLine.equals("n") || boolLine.equals("np") || boolLine.equals("-")) {
            ativo = false;
        }
        else if (boolLine.equals("1") || boolLine.equals("true") || boolLine.equals("verdadeiro") || boolLine.equals("t") || boolLine.equals("v") || boolLine.equals("yes") || boolLine.equals("sim") || boolLine.equals("ta") || boolLine.equals("tá") || boolLine.equals("disponível") || boolLine.equals("disponivel") || boolLine.equals("s") || boolLine.equals("ok") || boolLine.equals("+")) {
            ativo = true;
        }
        return ativo;
    }
    public static void main (String[] args){

        
        ProdutoDAO proDao = new ProdutoDAO();
        CategoriaDAO catDao = new CategoriaDAO();
        boolean first = true;
        Scanner sc = new Scanner(System.in);
        boolean continuousRunning = false;

        System.out.println("Gostaria de rodar o programa continuamente?\n" + 
            "(Note que, caso escolha não, o programa irá fechar assim que concluir a operação!)"
        );
        String opcao0 = sc.nextLine();

        continuousRunning = boolRead(opcao0);

        do{
            
            if(continuousRunning && !first){
                System.out.println("Gostaria de encerrar as operações por aqui?");
                String respCont = sc.nextLine();
                if(boolRead(respCont)){
                    continuousRunning = false;
                }
            }
            if(!continuousRunning && !first) break;
            if (first) first = !first; // Confuso, não é?

            System.out.println("\n\n\nCom qual tabela gostaria de trabalhar?");
            System.out.println("1 - Produtos | 2 - Categorias\n\n");
            int opcao1 = sc.nextInt();
            sc.nextLine();

            switch (opcao1) {

            // ================================================ Produtos ================================================ \\

                case 1:{
                System.out.println(
                "1 - Inserir | 2 - Listar | 3 - Atualizar | 4 - Deletar");
                int opcao2 = sc.nextInt();
                sc.nextLine(); // Limpa buffer
                switch (opcao2) {
                    case 1:
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();
                        System.out.print("Preço: ");
                        double preco = sc.nextDouble();
                        Produto p = new Produto (nome, preco);
                        proDao.inserir(p);
                        break;
                    case 2:
                        for(Produto prod : proDao.listar()){
                            System.out.println(
                                prod.getId() + " - " + prod.getNome() + " - R$" + prod.getPreco()
                            );
                        }
                        break;
                    case 3:

                        System.out.println("ID do produto: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Novo nome: ");
                        nome = sc.nextLine();
                        System.out.println("Novo preco: ");
                        preco = sc.nextDouble();
                        p = new Produto(nome, preco);
                        p.setId(id);
                        proDao.atualizar(p);
                        break;
                    case 4:
                        System.out.println("ID do produto a deletar: ");
                        id = sc.nextInt();
                        proDao.deletar(id);
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
                        break;
                }
                case 2: {

                // ================================================ Categorias ================================================ \\

                    System.out.println(
                    "1 - Inserir | 2 - Listar | 3 - Atualizar | 4 - Deletar");
                    int opcao2 = sc.nextInt();
                    sc.nextLine(); // Limpa buffer
                    switch (opcao2) {
                        case 1:{
                        System.out.print("Nome: ");
                            String nome = sc.nextLine(); System.out.print("Ativo: ");
                            String boolLine = sc.nextLine();
                            Categoria p = new Categoria (nome, boolRead(boolLine));
                            catDao.inserir(p);
                            break;
                        }
                        case 2:
                            for(Categoria cat : catDao.listar()){
                                System.out.println(
                                    cat.getId() + " - " + cat.getNome() + " - Ativo: " + cat.getAtivo()
                                );
                            }
                            break;
                            
                        case 3:{
                            System.out.println("ID da categoria: ");
                            int id = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Novo nome: ");
                            String nome = sc.nextLine();
                            System.out.println("Nova disponibilidade: ");
                            String boolLine = sc.nextLine();
                            Categoria p = new Categoria (nome, boolRead(boolLine));
                            p.setId(id);
                            catDao.atualizar(p);
                            break;
                        }
                        case 4:
                            System.out.println("ID da categoria a deletar: ");
                            int id = sc.nextInt();
                            catDao.deletar(id);
                            break;
                            

                        default:
                            System.out.println("Opção inválida.");
                    }
                            break;
                    }

                case 2008:{
                    System.out.println(
                    "1 - Listar todos os registros | 2 - Limpar banco de dados");
                    
                    int opcao = sc.nextInt();
                    sc.nextLine();
                    switch (opcao) {
                        case 1:{
                            if(Queries.contar("categorias") > 0) System.out.println("Categorias");
                                for(Categoria cat : catDao.listar()){

                                        System.out.println(
                                            "ID: " + cat.getId() + " - Nome: " + cat.getNome() + " - Ativo: " + cat.getAtivo()
                                        );
                                }
                            
                            if(Queries.contar("produtos") > 0) System.out.println("Produtos");
                                for(Produto prod : proDao.listar()){
                                    System.out.println(
                                        "ID: " + prod.getId() + " - Nome: " + prod.getNome() + "- Preço: R$" + prod.getPreco()
                                    );
                                }
                            
                            
                                break;
                        }

                        case 2:{
                            Queries.limparBanco();
                        }
                        
                    }
            
                }
                
                
            } // switch (opcao1)

            if(continuousRunning){
                System.out.println("press anything to proceed...");
                sc.nextLine();
                // System.out.print("\033[H\033[2J"); // Isso é GPT. COMO NÃO EXISTE UM COMANDO NATIVO DE LIMPAR O CONSOLE????
                // System.out.flush();
            }
        } while(continuousRunning == true);
        sc.close();
    } // public static void main
} // Main class