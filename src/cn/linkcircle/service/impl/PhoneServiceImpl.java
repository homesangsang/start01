package cn.linkcircle.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.linkcircle.domain.Phone;
import cn.linkcircle.mapper.PhoneMapper;
import cn.linkcircle.service.PhoneService;

@Service(value="phoneService")
public class PhoneServiceImpl implements PhoneService {
	@Autowired
	private PhoneMapper phoneMapper;
	private static final Log logger = LogFactory.getLog(PhoneServiceImpl.class);

	@Override
	public boolean ifHmaUsed(Long hma) {
		if(phoneMapper.selectHma(hma)!=null){
			return true;
		}
		return false;
	}

	@Override
	public List<Phone> getAll() {
		return phoneMapper.list();
	}

	@Override
	public boolean modify(Phone phone) {
		if(phoneMapper.updatePhone(phone)>0) return true;
		return false;
	}
	/**
	 * 此处可以改成多线程
	 */
	@Override
	public Long recommand() {
		Long start = 95078000000l;
		for(int i=0;i < 95078999999l;i++,start++){
			if(!ifHmaUsed(start)) return start;
		}
		return null;
	}

	@Override
	public boolean add(Phone phone) {
		if(phoneMapper.insertPhone(phone)>0) return true;
		return false;
	}

	@Override
	public boolean delete(Phone phone) {
		if(phoneMapper.deletePhone(phone)>0) return true;
		return false;
	}

	@Override
	public void download() {
		// TODO Auto-generated method stub
		
	}

	
}
