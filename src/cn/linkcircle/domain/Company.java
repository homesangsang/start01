package cn.linkcircle.domain;

import java.io.Serializable;
import java.util.List;

public class Company implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3713993478794622036L;
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}
	public Company(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
