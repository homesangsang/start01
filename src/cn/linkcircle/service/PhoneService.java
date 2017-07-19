package cn.linkcircle.service;

import java.util.List;

import cn.linkcircle.domain.Phone;

public interface PhoneService {
	/**
	 * 查看号码是否被使用
	 * @param hma
	 * @return
	 */
	boolean ifHmaUsed(Long hma);
	/**
	 * 获取所有列表
	 * @return 如果不为空，返回true；如果为空，返回false
	 */
	List<Phone> getAll();
	/**
	 * 修改一条记录
	 * @param phone
	 * @return
	 */
	boolean modify(Phone phone);
	/**
	 * 获得一个可以使用的号码
	 * @return
	 */
	Long recommand();
	/**
	 * 添加一条记录
	 * @param phone
	 * @return
	 */
	boolean add(Phone phone);
	/**
	 * 删除一条记录
	 * @param phone
	 * @return
	 */
	boolean delete(Phone phone);
	/**
	 * 下载数据库中的数据
	 */
	void download();
}
