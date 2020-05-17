package com.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.demo.entities.Billing;
import com.demo.entities.Product;
import com.demo.services.BillingService;
import com.demo.services.ProductService;



@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private BillingService billingService;

	@RequestMapping("/")
	public String viewHomePage(Model model) {
	    List<Product> listProducts = productService.listAll();
	    model.addAttribute("listProducts", listProducts);
	     
	    return "index";
	}
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
	    Product product = new Product();
	    model.addAttribute("product", product);
	     
	    return "new_product";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
	    productService.save(product);
	     
	    return "redirect:/";
	}
	@RequestMapping("/edit/{code}")
	public ModelAndView showEditProductPage(@PathVariable(name = "code") int code) {
	    ModelAndView mav = new ModelAndView("edit_product");
	    Product product = productService.get(code);
	    mav.addObject("product", product);
	     
	    return mav;
	}
	@RequestMapping("/delete/{code}")
	public String deleteProduct(@PathVariable(name = "code") int code) {
	    productService.delete(code);
	    return "redirect:/";       
	}
	
//	@RequestMapping(value = "/searchproduct", method = RequestMethod.GET)
//	public ModelAndView search(@RequestParam(value = "search", required = false) Long q, Model model) 
//	{
//		ModelAndView mav = new ModelAndView("billing");
//		Product product=productService.find(q);
//		if(product!=null)
//		{
//			Billing billing=new Billing(product.getCode(),product.getName(),
//					product.getGst(),product.getPrice(),1);
//			mav.addObject("billing", billing);
//			model.addAttribute("search", billing);
//			List<Billing> listBilling = billingService.listAll();
//			model.addAttribute("listBillings", listBilling);
//			float total=billingService.calculate();
//			model.addAttribute("total",total);
//		}
//		else
//		{
//			Billing billing=null;
//			mav.addObject("billing", billing);
//			model.addAttribute("search", billing);
//			List<Billing> listBilling = billingService.listAll();
//			model.addAttribute("listBillings", listBilling);
//			float total=billingService.calculate();
//			model.addAttribute("total",total);
//		}
//		return mav;
//	}
	
	
	@RequestMapping("/indextosearch")
	public String showSearchPage(Model model) {
		List<Billing> listBilling = billingService.listAll();
		model.addAttribute("listBillings", listBilling);
		float total=billingService.calculate();
		model.addAttribute("total",total);
		return "billing";
	}
	
	@RequestMapping(value="/addbilling", method = RequestMethod.POST)
	public String addBilling(@ModelAttribute("billing") Billing billing) {
		Billing b=billingService.findCode(billing.getBcode());
		int q=0;
		if(b!=null)
		{
			q=b.getBquantity();
			q+=billing.getBquantity();
			billing.setBquantity(q);
		}
		billingService.save(billing);
		return "redirect:/indextosearch";
	}
	
	@RequestMapping("/editbilling/{code}")
	public ModelAndView showEditBillingPage(@PathVariable(name = "code") Long code) {
		ModelAndView mav = new ModelAndView("edit_billing");
		Billing billing = billingService.get(code);
		mav.addObject("billing", billing);
		
		return mav;
	}
	
	@RequestMapping("/deletebilling/{code}")
	public String deleteBilling(@PathVariable(name = "code") Long code) {
		billingService.delete(code);
		return "redirect:/indextosearch";		
	}
}
