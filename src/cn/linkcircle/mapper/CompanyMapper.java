package cn.linkcircle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.linkcircle.domain.Company;

public interface CompanyMapper {

	/**
	 * 根据id查看公司信息
	 */
	@Select("select * from company where id=#{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="name",property="name"),
		/*@Result(property="phone",column="syqye",
		many=@Many(select="cn.linkcircle.mapper.PhoneMapper.selectBySyqye",fetchType=FetchType.LAZY))*/
	})
	Company selectById(Integer id);
	/**
	 * 查询企业列表
	 * @return
	 */
	@Select("select * from company")
	List<Company> selectCompanys();
	/**
	 * 新增一条企业
	 * @param name
	 * @return
	 */
	@Insert("insert into company(name) values(#{name})")
	int insertCompany(String name);
	@Select("select * from company where name like #{name}")
	Company selectCompanyByName(String name);
}
