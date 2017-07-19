package cn.linkcircle.domain;

import java.io.Serializable;

/**
 * Message类不是对应数据库的实体类
 * Message是后台传递给前台和接受前台ajax的数据的封装类
 * @author homesangsang
 *
 */
public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8557128305641795134L;
	private Long hma;
	private String status;
	private Phone phone;
	public Long getHma() {
		return hma;
	}
	public void setHma(Long hma) {
		this.hma = hma;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Phone getPhone() {
		return phone;
	}
	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Message [hma=" + hma + ", status=" + status + ", phone=" + phone + "]";
	}
	public Message(Long hma, String status, Phone phone) {
		super();
		this.hma = hma;
		this.status = status;
		this.phone = phone;
	}
	public Message() {
		super();
	}
	
	
}
