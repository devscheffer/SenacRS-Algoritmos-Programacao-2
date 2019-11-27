package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Produto;
import model.Categoria;

/**
 *
 * @author assparremberger
 */
public class ProdutoDAO {
    
    public static void inserir(Produto prod){
        String query = "INSERT INTO produtos "
                + " ( nome,preco,quantidade,codCategoria ) VALUES ( "
                + " '" + prod.getNome() + "'  , "
                + " " + prod.getPreco()+ " , "
                + " " + prod.getQtd()+ "  , "
                + " " + prod.getCategoria().getId()+ "  ); ";
        Conexao.executar( query );
    }
    
    public static void editar(Produto prod){
        String query = "UPDATE produtos SET "
                + " nome =  '" + prod.getNome() + "' "
                + " ,preco =  " + prod.getPreco()+ " "
                + " ,quantidade =  " + prod.getQtd()+ " "
                + " ,codCategoria =  " + prod.getCategoria().getId()+ " "
                + " WHERE id = " + prod.getId();
        Conexao.executar( query );
    }
    
    public static void excluir(Produto prod){
        String query = "DELETE FROM produtos "
                    + " WHERE id = " + prod.getId();
        Conexao.executar( query );
    }
    /*Arrumar para produto Inicio*/
    public static List<Produto> getProdutos(){
        List<Produto> lista = new ArrayList<>();
        String query = "SELECT p.id, p.nome, p.preco, p.quantidade, c.id, c.nome"
                     + " FROM produto p inner join categoria c"
                     + " on p.codCategoria = c.id ORDER BY p.nome ";
        ResultSet rs = Conexao.consultar(query);
        if( rs != null ){
            try {
                while ( rs.next() ) {                    
                    Categoria cat = new Categoria();
                    cat.setId( rs.getInt( 5 ) );
                    cat.setNome( rs.getString( 6 ) );
                    
                    Produto prod = new Produto();
                    prod.setCategoria(cat);
                    prod.setNome(rs.getString(2)); /*usar esse de exemplo para todos os set*/
                    prod.setPreco(rs.getDouble(3));
                    prod.setQtd(rs.getInt(4));                 
                    lista.add( prod );
                }
            } catch (Exception e) {
            }
        }
        return lista;
    }
    
    public static Produto getProdutoById( int idProduto ){
        String query = "SELECT id, produto.nome, preco, quantidade, categoria.id, categoria.nome"
                     + " FROM produto left join categoria"
                     + " on produto.codCategoria = categoria.id WHERE produto.id = "+idProduto;

        ResultSet rs = Conexao.consultar(query);
        if( rs != null ){
            try {
                rs.next();                  
                    Categoria cat = new Categoria();
                    cat.setId( rs.getInt( 5 ) );
                    cat.setNome( rs.getString( 6 ) );
                    
                    Produto prod = new Produto();
                    prod.setCategoria(cat);
                    prod.setNome(rs.getString(1));
                    prod.setPreco(rs.getDouble(3));
                    prod.setQtd(rs.getInt(4));     
           
                    return prod;
            } catch (Exception e) {
                return null;
            }
        }else{
            return null;
        }
    }
}
