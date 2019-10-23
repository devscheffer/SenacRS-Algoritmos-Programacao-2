package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Cidade;

/**
 *
 * @author assparremberger
 */
public class CidadeDAO {
    
    public static void inserir(Cidade cid){
        String query = "INSERT INTO cidades "
                + " ( nome ) VALUES ( "
                + " '" + cid.getNome() + "'  ); ";
        Conexao.executar( query );
    }
    
    public static void editar(Cidade cid){
        String query = "UPDATE cidades SET "
                + " nome =  '" + cid.getNome() + "' "
                + " WHERE id = " + cid.getId();
        Conexao.executar( query );
    }
    
    public static void excluir(Cidade cid){
        String query = "DELETE FROM cidades "
                    + " WHERE id = " + cid.getId();
        Conexao.executar( query );
    }
    
    public static List<Cidade> getCidades(){
        List<Cidade> lista = new ArrayList<>();
        String query = "SELECT id, nome "
                     + " FROM cidades ORDER BY nome ";
        ResultSet rs = Conexao.consultar(query);
        if( rs != null ){
            try {
                while ( rs.next() ) {                    
                    Cidade cid = new Cidade();
                    cid.setId( rs.getInt( 1 ) );
                    cid.setNome( rs.getString( 2 ) );
                    lista.add( cid );
                }
            } catch (Exception e) {
            }
        }
        return lista;
    }
    
    public static Cidade getCidadeById( int idCidade ){
        String query = "SELECT id, nome "
                     + " FROM cidades "
                     + " WHERE id = " + idCidade;
        ResultSet rs = Conexao.consultar(query);
        if( rs != null ){
            try {
                rs.next();                  
                    Cidade cid = new Cidade();
                    cid.setId( rs.getInt( 1 ) );
                    cid.setNome( rs.getString( 2 ) );
                    return cid;
            } catch (Exception e) {
                return null;
            }
        }else{
            return null;
        }
    }
    
}
