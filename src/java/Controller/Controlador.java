package Controller;


//Importamos las librerias
import Config.Conexion;
import Entidad.Persona;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**-----------------------------Notas----------------------------------------**/

//Bean -------> Es un objeto que crea una instancia , se ensambla y de lo contrario se administra
//              mediante un contenedor Spring loC (Inyeccion de dependencias)


//Definimos que esta clase sera un controlador
@Controller

public class Controlador {
    //Instanciamos la clase conexion para acceder a la base de datos
    Conexion conexion= new Conexion();
    
    //Ejecuta el flujo de trabajo JDBC central, dejando el codigo
    //de la aplicacion para proporcionar SQL y extraer
    //resultados, esta clase ejecuta consultas o actualizaciones
    //SQL, inicia la iteracion sobre los ResulSets y detecta
    //excepciones JDBC
    JdbcTemplate jdbcTemplate= new JdbcTemplate(conexion.conectar());
    
    //Sirve para devolver el modelo y la vista en un solo valor de retorno
    //Rellena propiedades de bean en lugar de pasar argumentos de constructor
    ModelAndView mav= new ModelAndView();
    
    int id;
    List datos;
    
    //Anotacion que asigna solicitudes web a metodos
    //en clases de manejo de solicitudes con firmas
    //de metodos flexibles
    @RequestMapping("index.htm")
    public ModelAndView ToList(){
        String sql="select * from persona";
        datos=this.jdbcTemplate.queryForList(sql);
        mav.addObject("lista",datos);
        mav.setViewName("index");
        return mav;
    }
    
    @RequestMapping(value="add.htm", method = RequestMethod.GET)
    public ModelAndView Add(){
        //Agregamos al mav las propiedades que tiene la clase Persona
        mav.addObject(new Persona());
        mav.setViewName("add");
        return mav;
    }
    
    @RequestMapping(value="add.htm", method = RequestMethod.POST)
    public ModelAndView Add(Persona p){
        String sql="insert into persona (pat_persona,mat_persona,nom_persona,nac_persona) values (?,?,?,?)";
        this.jdbcTemplate.update(sql,p.getPaterno(),p.getMaterno(),p.getNombre(),p.getNacionalidad());
        return new ModelAndView("redirect:/index.htm");
    }
    
    @RequestMapping(value="edit.htm", method = RequestMethod.GET)
    public ModelAndView Edit(HttpServletRequest request){
        id=Integer.parseInt(request.getParameter("id"));
        String sql ="select * from persona where cod_persona="+id;
        datos=this.jdbcTemplate.queryForList(sql);
        mav.addObject("lista",datos);
        mav.setViewName("edit");
        return mav;
    }
    
    @RequestMapping(value="edit.htm", method = RequestMethod.POST)
    public ModelAndView Edit(Persona p){
        String sql="update persona set pat_persona=?, mat_persona=?, nom_persona=?, nac_persona=? where cod_persona=?";
        this.jdbcTemplate.update(sql,p.getPaterno(),p.getMaterno(),p.getNombre(),p.getNacionalidad(),id);
        return new ModelAndView("redirect:/index.htm");
    }
    
    @RequestMapping("delete.htm")
    public ModelAndView Delete(HttpServletRequest request){
        id=Integer.parseInt(request.getParameter("id"));
        String sql="Delete from persona where cod_persona="+id;
        this.jdbcTemplate.update(sql);
        return new ModelAndView("redirect:/index.htm");
    }
}
