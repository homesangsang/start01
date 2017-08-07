package cn.linkcircle.service;

import java.util.List;

import cn.linkcircle.domain.Company;
import cn.linkcircle.domain.Phone;

public interface CompanyPhone1Service {
	/**
	 * 通过企业id查询企业名称
	 * @param id
	 * @return
	 */
	Company selectCompanyById(Integer id);
	/**
	 * 查询企业列表
	 * @return
	 */
	List<Company> selectCompanyMenu();
	/**
	 * 新增一条企业记录
	 * @param hma
	 * @return boolean
	 */
	boolean addCompany(String hma);
	/**
	 * 通过企业id查询企业使用的号码
	 * 扩展：通过关联查询直接查出使用企业的记录
	 * @param syqye
	 * @return
	 */
	List<Phone> selectPhoneBySyqye(Integer syqye);
	Phone selectPhoneByHma(Long hma);
	Company selectCompanyByName(String name);
	
	
	
	
	
	
	
	
	
	
	
	
}
