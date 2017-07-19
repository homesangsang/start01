package cn.linkcircle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.linkcircle.domain.Phone;
import cn.linkcircle.mapper.PhoneMapper;
import cn.linkcircle.service.Phone1Service;

@Service(value="phone1Service")
public class Phone1ServieImpl implements Phone1Service {
	
	@Autowired
	private PhoneMapper phoneMapper;
	
	@Override
	public Phone getStatus(Long hma) {
		return phoneMapper.selectHma(hma);
	}

}
