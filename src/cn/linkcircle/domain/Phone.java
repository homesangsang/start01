package cn.linkcircle.domain;

import java.io.Serializable;

public class Phone implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3394051451997450200L;
	private Long hma;
	private int syqye;
	private String sfzyong;
	
	private Company company;

	public Long getHma() {
		return hma;
	}

	public void setHma(Long hma) {
		this.hma = hma;
	}

	public int getSyqye() {
		return syqye;
	}

	public void setSyqye(int syqye) {
		this.syqye = syqye;
	}

	public String getSfzyong() {
		return sfzyong;
	}

	public void setSfzyong(String sfzyong) {
		this.sfzyong = sfzyong;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Phone [hma=" + hma + ", syqye=" + syqye + ", sfzyong=" + sfzyong + ", company=" + company + "]";
	}

	public Phone(Long hma, int syqye, String sfzyong, Company company) {
		super();
		this.hma = hma;
		this.syqye = syqye;
		this.sfzyong = sfzyong;
		this.company = company;
	}

	public Phone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Phone(Long hma, int syqye, String sfzyong) {
		super();
		this.hma = hma;
		this.syqye = syqye;
		this.sfzyong = sfzyong;
	}
	
}
