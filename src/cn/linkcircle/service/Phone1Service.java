package cn.linkcircle.service;

import java.util.List;

import cn.linkcircle.domain.Phone;

public interface Phone1Service {
	// 查询某一个号码的详细状态
	public Phone getStatus(Long hma);
	//分页查询
	public List<Phone> listByPage(int pageNumber,int pageSize);
}
