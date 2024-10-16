package Entidades;

public class tipoSeguros {
	private int idTipo;
	private String descripcion;
	
	public tipoSeguros(int idTipo, String descripcion) {
		this.idTipo = idTipo;
		this.descripcion = descripcion;
	}
	
	public tipoSeguros() {
		this.idTipo = -1;
		this.descripcion = "";
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
