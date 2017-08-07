package cn.linkcircle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.linkcircle.domain.Company;
import cn.linkcircle.domain.Phone;
import cn.linkcircle.mapper.CompanyMapper;
import cn.linkcircle.mapper.PhoneMapper;
import cn.linkcircle.service.CompanyPhone1Service;
@Service(value="CompanyPhone1Service")
public class CompanyPhone1ServiceImpl implements CompanyPhone1Service {

	
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private PhoneMapper phoneMapper;
	
	@Override
	public Company selectCompanyById(Integer id) {
		return companyMapper.selectById(id);
	}
	
	@Override
	public boolean addCompany(String hma) {
		if(companyMapper.insertCompany(hma)>0){
			return true;
		}
		return false;
	}
	/**
	 * 通过企业id查询企业使用的号码
	 */
	@Override
	public List<Phone> selectPhoneBySyqye(Integer syqye) {
		List<Phone> phones = phoneMapper.selectBySyqye(syqye);
		Company company = companyMapper.selectById(syqye);
		if(phones!=null && company!=null){
			for (Phone phone : phones) {
				phone.setCompany(company);
			}
			return phones;
		}
		return null;
	}
	@Override
	public Phone selectPhoneByHma(Long hma) {
		return null;
	}
	/**
	 * 企业列表
	 */
	@Override
	public List<Company> selectCompanyMenu() {
		return companyMapper.selectCompanys();
	}
	@Override
	public Company selectCompanyByName(String name) {
		return companyMapper.selectCompanyByName(name);
	}

}
