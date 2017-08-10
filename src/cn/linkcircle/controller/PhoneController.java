package cn.linkcircle.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.linkcircle.domain.Company;
import cn.linkcircle.domain.InputPhone;
import cn.linkcircle.domain.Message;
import cn.linkcircle.domain.Phone;
import cn.linkcircle.domain.Phone2;
import cn.linkcircle.service.CompanyPhone1Service;
import cn.linkcircle.service.Phone1Service;
import cn.linkcircle.service.PhoneService;

@Controller
@RequestMapping("/phone")
public class PhoneController {
	private static final Log logger = LogFactory.getLog(PhoneController.class);
	@Autowired
	@Qualifier("phoneService")
	private PhoneService phoneService;

	@Autowired
	@Qualifier("phone1Service")
	private Phone1Service phone1Service;

	@Autowired
	@Qualifier("CompanyPhone1Service")
	private CompanyPhone1Service companyPhone1Service;

	@RequestMapping("/ifHmaUsed.do")
	public void ifHmaUsed(@RequestBody Message message, HttpServletResponse response)
			throws JsonGenerationException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("text/html;charset=UTF-8");
		Phone phone = phone1Service.getStatus(message.getHma());
		if (phone != null) {
			Company company = companyPhone1Service.selectCompanyById(phone.getSyqye());
			if (company != null) {
				phone.setCompany(company);
			}
			message.setPhone(phone);
			message.setStatus("当前号码已经被使用");
			System.out.println(mapper.writeValueAsString(message));
		} else {
			message.setPhone(null);
			message.setStatus("号码没有使用");
		}

		response.getWriter().println(mapper.writeValueAsString(message));
	}

	@RequestMapping("/getAll.do")
	public void getAll(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
			HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("text/html;charset=UTF-8");
		List<Phone> phones = phone1Service.listByPage(pageNumber, pageSize);
		List<Company> companys = companyPhone1Service.selectCompanyMenu();
		if(companys!=null && phones!=null){
			for (Phone phone : phones) {
				for(int i=0;i<companys.size();i++){
					if(phone.getSyqye()==companys.get(i).getId()){
						phone.setCompany(companys.get(i));
						break;
					}
				}
			}
		}
		System.out.println("pageNumber:" + pageNumber + "  pageSize:" + pageSize + mapper.writeValueAsString(phones));
		response.getWriter().println(mapper.writeValueAsString(phones));
	}

	@RequestMapping("/modify.do")
	public String modify(@RequestParam("hma") Long hma, @RequestParam("syqye") String syqye,
			@RequestParam("sfzyong") String sfzyong, Model model) {
		Company company = companyPhone1Service.selectCompanyByName(syqye);
		if(company!=null){
			Phone phone = new Phone(hma,company.getId(),sfzyong);
			if (phoneService.modify(phone)) {
				model.addAttribute("phone", phone);
			} else {
				model.addAttribute("message", "更改失败");
			}
		}else{
			if(companyPhone1Service.addCompany(syqye)){
				company = companyPhone1Service.selectCompanyByName(syqye);
				if(company!=null){
					Phone phone = new Phone(hma,company.getId(),sfzyong);
					if (phoneService.modify(phone)) {
						model.addAttribute("phone", phone);
					} else {
						model.addAttribute("message", "更改失败");
					}
				}
			}
		}
		/*Phone phone = new Phone(hma, syqye, sfzyong);
		if (phoneService.modify(phone)) {
			model.addAttribute("phone", phone);
		} else {
			model.addAttribute("message", "更改失败");
		}*/
		return "modify";
	}

	@RequestMapping(value="/update.do",method=RequestMethod.POST)
	public void update(@RequestBody Phone2 phone, HttpServletResponse response)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("text/html;charset=UTF-8");
		System.out.println(mapper.writeValueAsString(phone));
		Company company = companyPhone1Service.selectCompanyByName(phone.getSyqye());
		Message message = new Message();
		if(company!=null){
			Phone phone1 = new Phone(phone.getHma(),company.getId(),phone.getSfzyong());
			if (phoneService.modify(phone1)) {
				message.setHma(phone.getHma());
				phone1.setCompany(company);
				message.setPhone(phone1);
				message.setStatus("修改成功");
				response.getWriter().println(mapper.writeValueAsString(message));
			} else {
				message.setHma(phone.getHma());
				message.setPhone(null);
				message.setStatus("修改失败");
				response.getWriter().println(mapper.writeValueAsString(message));
			}
		}else{
			if(companyPhone1Service.addCompany(phone.getSyqye())){
				company = companyPhone1Service.selectCompanyByName(phone.getSyqye());
				if(company!=null){
					Phone phone1 = new Phone(phone.getHma(),company.getId(),phone.getSfzyong());
					if (phoneService.modify(phone1)) {
						message.setHma(phone.getHma());
						phone1.setCompany(company);
						message.setPhone(phone1);
						message.setStatus("修改成功");
						response.getWriter().println(mapper.writeValueAsString(message));
					} else {
						message.setHma(phone.getHma());
						message.setPhone(null);
						message.setStatus("修改失败");
						response.getWriter().println(mapper.writeValueAsString(message));
					}
				}
			}
		}
	}

	@RequestMapping("/recommand.do")
	public void recommand(HttpServletResponse response)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(mapper.writeValueAsString(phoneService.recommand()));
	}

	@RequestMapping("/add.do")
	public void add(@RequestBody Phone phone, HttpServletResponse response)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(phone));
		Message message = new Message();
		if (phoneService.add(phone)) {
			message.setHma(phone.getHma());
			message.setStatus("添加成功");
			message.setPhone(phone);
		} else {
			message.setStatus("添加失败");
			message.setHma(phone.getHma());
			message.setPhone(null);
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(mapper.writeValueAsString(message));
	}

	@RequestMapping("/delete.do")
	public String delete(@RequestParam("hma") Long hma, @RequestParam("syqye") Integer syqye,
			@RequestParam("sfzyong") String sfzyong, Model model) {
		Phone phone = new Phone(hma, syqye, sfzyong);
		if (phoneService.delete(phone)) {
			model.addAttribute("message", "删除成功");
		} else {
			model.addAttribute("message", "删除失败");
		}
		return "status";
	}
	@RequestMapping("deleteByHma.do")
	public void deleteByHma(@RequestBody Phone2 phone,HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("text/html;charset=UTF-8");
		Message message = new Message();
		Company company = companyPhone1Service.selectCompanyByName(phone.getSyqye());
		if(company!=null){
			Phone phone1 = new Phone(phone.getHma(),company.getId(),phone.getSfzyong());
			if(phoneService.delete(phone1)){
				message.setHma(phone.getHma());
				message.setStatus("删除成功");
			}else{
				message.setHma(phone.getHma());
				message.setStatus("删除失败");
			}
		}
		response.getWriter().println(mapper.writeValueAsString(message));
	}

	@RequestMapping(value = "/download.do")
	public String excel(Model model) throws Exception {
		StringBuffer excelBuf = new StringBuffer();
		List<Phone> phones = phoneService.getAll();
		List<Company> companys = companyPhone1Service.selectCompanyMenu();
		if(companys!=null && phones!=null){
			for (Phone phone : phones) {
				for(int i=0;i<companys.size();i++){
					if(phone.getSyqye()==companys.get(i).getId()){
						phone.setCompany(companys.get(i));
						break;
					}
				}
			}
			excelBuf.append("号码").append("\t").append("使用企业").append("\t").append("详情").append("\n");
			for (int i = 0; i < phones.size(); i++) {
				excelBuf.append(phones.get(i).getHma()).append("\t").append(phones.get(i).getCompany().getName()).append("\t")
						.append(phones.get(i).getSfzyong()).append("\n");
			}
		}
		model.addAttribute("excel", excelBuf.toString());
		System.out.println(excelBuf.toString());
		return "excel";
	}

	@RequestMapping(value = "/count.do")
	public void count(HttpServletResponse response) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("text/html;charset=UTF-8");
		Integer count = phone1Service.count();
		System.out.println("数据库总记录数：" + count);
		response.getWriter().println(mapper.writeValueAsString(count));
	}

	@RequestMapping(value = "/selectPhoneBySyqye.do")
	public void selectPhoneSyqye(@RequestParam("syqye") Integer syqye, HttpServletResponse response)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("text/html;charset=UTF-8");
		List<Phone> phones = companyPhone1Service.selectPhoneBySyqye(syqye);
		Company company = companyPhone1Service.selectCompanyById(syqye);
		if (phones != null && company != null) {
			for (Phone phone : phones) {
				phone.setCompany(company);
			}
		}
		System.out.println(phones);
		response.getWriter().println(mapper.writeValueAsString(phones));
	}

	@RequestMapping(value = "/companyList.do")
	public void companyList(HttpServletResponse response)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("text/html;charset=UTF-8");
		List<Company> companys = companyPhone1Service.selectCompanyMenu();
		if (companys != null) {
			System.out.println(companys);
			response.getWriter().println(mapper.writeValueAsString(companys));
		}
	}

	@RequestMapping(value = "/add1.do")
	public void add1(@RequestBody InputPhone iphone, HttpServletResponse response)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Message message = new Message();
		Company company = companyPhone1Service.selectCompanyByName(iphone.getSyqye());
		if (company != null) {
			Phone phone = new Phone(iphone.getHma(), company.getId(), iphone.getSfzyong());
			if (phoneService.add(phone)) {
				phone.setCompany(company);
				message.setHma(phone.getHma());
				message.setStatus("添加成功");
				message.setPhone(phone);
			} else {
				message.setStatus("添加失败");
				message.setHma(phone.getHma());
				message.setPhone(null);
			}
		} else {
			System.out.println("company=null:"+company);
			if (companyPhone1Service.addCompany(iphone.getSyqye())) {
				company = companyPhone1Service.selectCompanyByName(iphone.getSyqye());
				System.out.println(company);
				if (company != null) {
					System.out.println("新增公司："+company.toString());
					Phone phone = new Phone(iphone.getHma(), company.getId(), iphone.getSfzyong());
					if (phoneService.add(phone)) {
						phone.setCompany(company);
						message.setHma(phone.getHma());
						message.setStatus("添加成功");
						message.setPhone(phone);
						System.out.println("公司不存在，创建并插入"+message.toString());
					} else {
						message.setStatus("添加失败");
						message.setHma(phone.getHma());
						message.setPhone(null);
					}
				}
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		System.out.println(mapper.writeValueAsString(message));
		response.getWriter().println(mapper.writeValueAsString(message));
	}
}
