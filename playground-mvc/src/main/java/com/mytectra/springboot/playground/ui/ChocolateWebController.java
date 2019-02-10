package com.mytectra.springboot.playground.ui;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mytectra.springboot.playground.datastore.ItemStore;
import com.mytectra.springboot.playground.model.Chocolate;
import com.mytectra.springboot.playground.model.RequestScopeBean;
import com.mytectra.springboot.playground.model.SessionScopeBean;

@Controller
public class ChocolateWebController {
	
	@Autowired
	private ItemStore<Chocolate> chocolateStore;
	
	@Autowired
	private SessionScopeBean sessionBean;
	
	@Autowired
	private RequestScopeBean requestBean;
	
	
	@RequestMapping("/list.do")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView();
		List<Chocolate> items = chocolateStore.listItems();
		modelAndView.setViewName("chocolates");		
		modelAndView.addObject("chocolates", items);
		modelAndView.addObject("sessionChocolates", sessionBean.getChocolates());
		modelAndView.addObject("requestChocolates", requestBean.getChocolates());

		return modelAndView;
	}

	
	@RequestMapping("/buy.do")
	public ModelAndView buy(@RequestParam("name") String name, @CookieValue("JSESSIONID") String sessionId) {
		ModelAndView modelAndView = new ModelAndView();
		List<Chocolate> items = chocolateStore.listItems();
		Chocolate chocolate = items.stream().filter(chocloate -> chocloate.getName().equals(name)).findFirst().get();
		sessionBean.add(chocolate);
		requestBean.add(chocolate);
		
		modelAndView.setViewName("chocolates");		
		modelAndView.addObject("chocolates", items);
		modelAndView.addObject("sessionChocolates", sessionBean.getChocolates());
		modelAndView.addObject("requestChocolates", requestBean.getChocolates());
		
		System.out.println("Session Id = "+ sessionId);
		return modelAndView;
	}
	
	@RequestMapping(path="/add.do" , method = RequestMethod.POST )
	public String add(@Validated Chocolate chocloate) {
		chocloate.setExpiryDate(new Date());
		chocolateStore.loadItem(chocloate);
		return "redirect:list.do";
		
	}
	
	@RequestMapping(path="/addDisplay.do")
	public ModelAndView addDisplay() {
		ModelAndView modelAndView = new ModelAndView("chocolatesAdd");
		modelAndView.addObject("chocolate", new Chocolate());
		return modelAndView;
		
	}
	
	@RequestMapping(path = "/update.do", method = RequestMethod.POST)
	public String update(@ModelAttribute Chocolate chocolate) {
		System.out.println(chocolate);
		chocolateStore.updateChocolate(chocolate);
		return "redirect:list.do";
		
	}
	
	@RequestMapping(path= "/updateDisplay.do" , method = RequestMethod.GET)
	public ModelAndView updateDisplay(@RequestParam(value="name") String chocolateName) {
		Chocolate chocolate = chocolateStore.getItemByName(chocolateName);
		ModelAndView modelAndView = new ModelAndView("updateChocolates");
		modelAndView.addObject("updateChocolate", chocolate);
		return modelAndView;		
	}
	
	@RequestMapping("/download.do" )
	public @ResponseBody String download(HttpServletResponse response) {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"choco.csv\"");
		StringBuilder builder =  new StringBuilder("ChocolateName,Brand,Price\n");
		chocolateStore.listItems().stream().forEach(chocolate -> builder.append(chocolate.toCSV()));
		return builder.toString();
	}
	
}
