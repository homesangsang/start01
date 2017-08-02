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
import org.springframework.web.bind.annotation.RequestParam;

import cn.linkcircle.domain.Message;
import cn.linkcircle.domain.Phone;
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
	
	@RequestMapping("/ifHmaUsed.do")
	public void ifHmaUsed(@RequestBody Message message, HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("text/html;charset=UTF-8");
		Phone phone = phone1Service.getStatus(message.getHma());
		if(phone!=null){
			message.setPhone(phone);
			message.setStatus("当前号码已经被使用");
		}else{
			message.setPhone(null);
			message.setStatus("号码没有使用");
		}
		
		response.getWriter().println(mapper.writeValueAsString(message));
	}
	@RequestMapping("/getAll.do")
	public void getAll(
			@RequestParam("pageNumber")int pageNumber,
			@RequestParam("pageSize")int pageSize,
			HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("text/html;charset=UTF-8");
		List<Phone> list = phone1Service.listByPage(pageNumber, pageSize);
		System.out.println("pageNumber:"+pageNumber+"  pageSize:"+pageSize+mapper.writeValueAsString(list));
		response.getWriter().println(mapper.writeValueAsString(list));
	}
	
	@RequestMapping("/modify.do")
	public String modify(
			@RequestParam("hma")Long hma,
			@RequestParam("syqye")String syqye,
			@RequestParam("sfzyong")String sfzyong,
			Model model
			){
		Phone phone = new Phone(hma,syqye,sfzyong);
		if(phoneService.modify(phone)){
			model.addAttribute("phone",phone);
		}else{
			model.addAttribute("message","更改失败");
		}
		return "modify";
	}
	@RequestMapping("/recommand.do")
	public void recommand(HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(mapper.writeValueAsString(phoneService.recommand()));
	}
	@RequestMapping("/add.do")
	public void add(@RequestBody Phone phone,HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new  ObjectMapper();
		System.out.println(mapper.writeValueAsString(phone));
		Message message = new Message();
		if(phoneService.add(phone)){
			message.setHma(phone.getHma());
			message.setStatus("添加成功");
			message.setPhone(phone);
		}else{
			message.setStatus("添加失败");
			message.setHma(phone.getHma());
			message.setPhone(null);
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(mapper.writeValueAsString(message));
	}
	@RequestMapping("/delete.do")
	public String delete(
			@RequestParam("hma")Long hma,
			@RequestParam("syqye")String syqye,
			@RequestParam("sfzyong")String sfzyong,
			Model model
			){
		Phone phone = new Phone(hma,syqye,sfzyong);
		if(phoneService.delete(phone)){
			model.addAttribute("message", "删除成功");
		}else{
			model.addAttribute("message", "删除失败");
		}
		return "status";
	}
	@RequestMapping(value = "/download.do")
	public String excel(Model model) throws Exception {
		      StringBuffer excelBuf = new StringBuffer();
		      List<Phone> list = phoneService.getAll();
		      if(list!=null){
		    	  excelBuf.append("号码").append("\t").append("使用企业").append("\t").append("详情").append("\n");
		          for (int i = 0; i < list.size(); i++) {
			    	excelBuf.append(list.get(i).getHma()).append("\t").append(list.get(i).getSyqye()
			    			).append("\t").append(list.get(i).getSfzyong()).append("\n");
		          }
		      }
		      model.addAttribute("excel",excelBuf.toString());
		      System.out.println(excelBuf.toString());
		     return "excel";
	}
	@RequestMapping(value = "/count.do")
	public void count(HttpServletResponse response) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("text/html;charset=UTF-8");
		Integer count = phone1Service.count();
		System.out.println("数据库总记录数："+count);
		response.getWriter().println(mapper.writeValueAsString(count));
	}
}
