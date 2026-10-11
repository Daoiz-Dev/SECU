import Datos.Postulante;
import Persistencia.PostulanteDAO;

public class PruebaPostulanteDAO {
    public static void main(String[] args) throws Exception {
        PostulanteDAO dao = new PostulanteDAO();
        for (Postulante p : dao.listar()) {
            System.out.println(p.getNombre() + " " + p.getApellido());
        }
        System.out.println("Consulta OK");
    }
}