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
    
    public static List<Categoria> getCategoria(){
        List<Categoria> lista = new ArrayList<>();
        String query = "SELECT id, nome "
                     + " FROM categoria ORDER BY nome ";
        ResultSet rs = Conexao.consultar(query);
        if( rs != null ){
            try {
                while ( rs.next() ) {                    
                    Categoria cat = new Categoria();
                    cat.setId( rs.getInt( 1 ) );
                    cat.setNome( rs.getString( 2 ) );
                    lista.add( cat );
                }
            } catch (Exception e) {
            }
        }
        return lista;
    }
    
    public static Categoria getCategoriaById( int idCategoria ){
        String query = "SELECT id, nome "
                     + " FROM categoria "
                     + " WHERE id = " + idCategoria;
        ResultSet rs = Conexao.consultar(query);
        if( rs != null ){
            try {
                rs.next();                  
                    Categoria cat = new Categoria();
                    cat.setId( rs.getInt( 1 ) );
                    cat.setNome( rs.getString( 2 ) );
                    return cat;
            } catch (Exception e) {
                return null;
            }
        }else{
            return null;
        }
    }
       
}
