package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;

/**
 *
 * @author assparremberger
 */
public class CategoriaDAO {
    
    public static void inserir(Categoria cat){
        String query = "INSERT INTO categorias "
                + " ( nome ) VALUES ( "
                + " '" + cat.getNome() + "'  ); ";
        Conexao.executar( query );
    }
    
    public static void editar(Categoria cat){
        String query = "UPDATE categorias SET "
                + " nome =  '" + cat.getNome() + "' "
                + " WHERE id = " + cat.getId();
        Conexao.executar( query );
    }
    
    public static void excluir(Categoria cat){
        String query = "DELETE FROM categorias "
                    + " WHERE id = " + cat.getId();
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
