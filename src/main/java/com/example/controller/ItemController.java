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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Entity.Category;
import com.example.Entity.Item;
import com.example.form.ItemForm;
import com.example.service.CategoryService;
import com.example.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	private final ItemService itemService;
	//CategoryServiceをコンストラクタインジェクションする
		private final CategoryService categoryService;
		
		@Autowired
		public ItemController(ItemService itemService, CategoryService categoryService) {
			this.itemService = itemService;
			this.categoryService = categoryService; //追加
		}
	
	
	//syouhinn
	@GetMapping
	public String index(Model model) {
		
		List<Item> items = this.itemService.findByDeletedAtIsNull();
		model.addAttribute("items",items);
		
		return "item/index";
	}
	
	@GetMapping("toroku")
	public String torokuPage(@ModelAttribute("itemForm") ItemForm itemForm, Model model) {
		//Categoryモデルから一覧を取得する
		List<Category> categories = this.categoryService.findAll();
		
		//viewにカテゴリを渡す
		model.addAttribute("categories", categories);
		return "item/torokuPage";
	}
	
	//syouhin touroku page
	@PostMapping("toroku")
	public String toroku(ItemForm itemForm) {
		this.itemService.save(itemForm); 
		return "redirect:/item";
	}
	
	@GetMapping("henshu/{id}")
	public String hensyuPage(@PathVariable("id") Integer id,Model model,
								@ModelAttribute("itemForm") ItemForm itemForm) {
		//Entityクラスのインスタンスをidから検索し取得
		Item item = this.itemService.findById(id);
		//フィールドセット
		itemForm.setName(item.getName());
		itemForm.setPrice(item.getPrice());
		//カテゴリIDformセット
		itemForm.setcategoryId(item.getCategoryId());
		
		//Categoryモデルから一覧取得
		List<Category> categories = this.categoryService.findAll();
		//idセット
		model.addAttribute("id",id);
		
		//viweにカテゴリをわたす
		model.addAttribute("categories",categories);
		return "item/henshuPage";
	}
	
	//syouhinn hensyu page
	@PostMapping("henshu/{id}")
	public String henshu(@PathVariable("id") Integer id, @ModelAttribute("itemForm") ItemForm itemForm) {
		this.itemService.update(id, itemForm);	
		return "redirect:/item";
	
	}

	//syouhinn sakujo
	@PostMapping("sakujo/{id}")
	public String sakujo(@PathVariable("id") Integer id) {
		this.itemService.delete(id);
		return "redirect:/item";
	}
	
	//送信ボタンのname属性がinの場合は入荷処理の実行
	@PostMapping(path = "stock/{id}", params = "in")
	public String nyuka(@PathVariable("id") Integer id,@RequestParam("stock") Integer inputValue) {
		//入荷処理
		this.itemService.nyuka(id, inputValue);
		return "redirect:/item";
	}
	//送信ボタンのname属性がoutの場合は出荷処理の実行
	@PostMapping(path = "stock/{id}",params = "out")
	public String shukka(@PathVariable("id") Integer id, @RequestParam("stock") Integer inputValue){
		//出荷処理
		this.itemService.shukka(id,inputValue);
		
		return "redirect:/item";
	}
	
}
