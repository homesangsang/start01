package cn.linkcircle.domain;

public class InputPhone {
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
		return "InputPhone [hma=" + hma + ", syqye=" + syqye + ", sfzyong=" + sfzyong + "]";
	}
	public InputPhone(Long hma, String syqye, String sfzyong) {
		super();
		this.hma = hma;
		this.syqye = syqye;
		this.sfzyong = sfzyong;
	}
	public InputPhone() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
