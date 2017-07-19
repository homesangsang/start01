package cn.linkcircle.domain;

import java.io.Serializable;

public class Phone implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6227973744514206129L;
	private Long hma;
	private String syqye;
	private String sfzyong;
	public Long getHma() {
		return hma;
	}
	public void setHma(Long hma) {
		this.hma = hma;
	}
	public String getSyqye() {
		return syqye;
	}
	public void setSyqye(String syqye) {
		this.syqye = syqye;
	}
	public String getSfzyong() {
		return sfzyong;
	}
	public void setSfzyong(String sfzyong) {
		this.sfzyong = sfzyong;
	}
	@Override
	public String toString() {
		return "Phone [hma=" + hma + ", syqye=" + syqye + ", sfzyong=" + sfzyong + "]";
	}
	public Phone(Long hma, String syqye, String sfzyong) {
		super();
		this.hma = hma;
		this.syqye = syqye;
		this.sfzyong = sfzyong;
	}
	public Phone() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
