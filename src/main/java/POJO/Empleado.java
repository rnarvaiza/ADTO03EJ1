package POJO;

import POJO.Departamento;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table (name = "EMPLEADOS")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column (name="ID", nullable = false)
    private BigInteger id;

    @Column (name="NOMBRE")
    private String nombre;

    @Column (name="APELLIDOS")
    private String apellidos;

    @Column (name="PUESTO")
    private String puesto;

    @Column (name="FECHA_ALTA")
    private Date fechaAlta;

    @Column (name="SALARIO")
    private Float salario;

    @Column (name="COMISION")
    private Float comision;

    @ManyToOne
    @JoinColumn(name = "ID_DEPARTAMENTO", nullable = false)
    private Departamento departamento;

    public Empleado(){

    }

    public Empleado(String nombre, String apellidos, String puesto, Date fechaAlta, Float salario, Float comision, Departamento departamento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.puesto = puesto;
        this.fechaAlta = fechaAlta;
        this.salario = salario;
        this.comision = comision;
        this.departamento = departamento;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public Float getComision() {
        return comision;
    }

    public void setComision(Float comision) {
        this.comision = comision;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", puesto='" + puesto + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", salario=" + salario +
                ", comision=" + comision +
                ", departamento=" + departamento +
                '}';
    }
}
