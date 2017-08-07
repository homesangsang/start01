package cn.linkcircle.mapper.dynaprovider;

import org.apache.ibatis.jdbc.SQL;

import cn.linkcircle.domain.Phone;
import net.sf.jsqlparser.statement.select.Limit;

public class PhoneDynaSqlProvider {
	/**
	 * updatePhone
	 * @param phone
	 * @return
	 */
	public String updatePhone(Phone phone){
		return new SQL(){
			{
				UPDATE("phone");
				if(phone.getSyqye()>=0){
					SET("syqye=#{syqye}");
				}
				if(phone.getSfzyong()!=null){
					SET("sfzyong=#{sfzyong}");
				}
				WHERE("hma=#{hma}");
			}
		}.toString();
	}
	/**
	 * 删除一条记录
	 * @param phone
	 * @return
	 */
	public String deletePhone(Phone phone){
		return new SQL(){
			{
				DELETE_FROM("phone");
				if(phone.getHma()!=null && !phone.getHma().equals(0l)){
					WHERE("hma=#{hma}");
				}
			}
		}.toString();
	}
	/**
	 * 更改一条记录
	 * @param phone
	 * @return
	 */
	public String insertPhone(Phone phone){
		return new SQL(){{
			INSERT_INTO("phone");
			if(phone.getHma()!=null && !phone.getHma().equals(0l)){
				VALUES("hma", "#{hma}");
			}
			if(phone.getSyqye()>=0){
				VALUES("syqye", "#{syqye}");
			}
			if(phone.getSfzyong()!=null && !phone.getSfzyong().equals("")){
				VALUES("sfzyong", "#{sfzyong}");
			}
		}}.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
