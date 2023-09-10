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
		
		List<Item> item = this.itemService.findAll();
		System.out.println(items.toString());
		return "item/index";
	}
	
	//syouhin touroku page
	@GetMapping("toroku")
	public String toroku(ItemForm itemform) {
		return "redirect:/item";
	}
	
	//syouhinn hensyu page
	@GetMapping("henshu/{id}")
	public String henshu(@PathVariable("id") Integer id, @ModelAttribute("itemForm") ItemForm itemform) {
	return "redirect:/item";	
	}

	//syouhinn sakujo
	public string sakujo(@PathVariable("id") Integerid) {
		return "redirect:/item";
	}
}
