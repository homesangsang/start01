package cn.linkcircle.mapper;


import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.mapping.FetchType;

import cn.linkcircle.domain.Phone;

public interface PhoneMapper {
	@Select("select * from phone where hma=#{hma}")
	Phone selectHma(@Param("hma")Long hma);
	@InsertProvider(method="insertPhone" ,type=cn.linkcircle.mapper.dynaprovider.PhoneDynaSqlProvider.class)
	int insertPhone(Phone phone);
	@UpdateProvider(type=cn.linkcircle.mapper.dynaprovider.PhoneDynaSqlProvider.class, method="updatePhone")
	int updatePhone(Phone phone);
	@DeleteProvider(method="deletePhone",type=cn.linkcircle.mapper.dynaprovider.PhoneDynaSqlProvider.class)
	int deletePhone(Phone phone);
	@Select("select * from phone")
	List<Phone> list();
	@Select("select * from phone limit #{pageNumber},#{pageSize}")
	List<Phone> listByPage(@Param("pageNumber")int pageNumber,@Param("pageSize")int pageSize);
	/**
	 * 获取数据库总行数
	 */
	@Select("select count(*) from phone")
	Integer count();
	
	/**
	 * 根据企业号查询所有号码
	 */
	@Select("select * from phone where syqye=#{id}")
	@Results({
		@Result(id=true,column="hma",property="hma"),
		@Result(column="syqye",property="syqye"),
		@Result(column="sfzyong",property="sfzyong"),
		@Result(column="id",property="company",
		one=@One(select="cn.linkcircle.mapper.CompanyMapper.selectById",fetchType=FetchType.EAGER))
	})
	List<Phone> selectBySyqye(Integer syqye);
}
