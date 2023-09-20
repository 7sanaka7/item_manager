package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Entity.Item;
import com.example.form.ItemForm;
import com.example.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	private final ItemService itemService;
	
	@Autowired
	public ItemController(ItemService itemservice) {
		this.itemService = itemservice;
	}
	
	//syouhinn
	@GetMapping
	public String index(Model model) {
		
		List<Item> items = this.itemService.findAll();
		System.out.println(items.toString());
		return "item/index";
	}
	
	@GetMapping("toroku")
	public String torokuPage(@ModelAttribute("itemForm") ItemForm itemForm) {
		return "item/torokuPage";
	}
	
	//syouhin touroku page
	@PostMapping("toroku")
	public String toroku(ItemForm itemform) {
		return "redirect:/item";
	}
	
	@GetMapping("henshu/{id}")
	public String hensyuPage(@PathVariable("id") Integer id,Model model,
								@ModelAttribute("itemForm") ItemForm itemForm) {
		return "item/henshuPage";
	}
	
	//syouhinn hensyu page
	@PostMapping("henshu/{id}")
	public String henshu(@PathVariable("id") Integer id, @ModelAttribute("itemForm") ItemForm itemForm) {
	return "redirect:/item";
	
	}

	//syouhinn sakujo
	@PostMapping("sakujo/{id}")
	public String sakujo(@PathVariable("id") Integer id) {
		return "redirect:/item";
	}
	
}
