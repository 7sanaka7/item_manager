package com.example.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Item;
import com.example.form.ItemForm;
import com.example.repository.ItemRepository;

@Service
public class ItemService {

	private final ItemRepository itemRepository;
	
	@Autowired
	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
	public List<Item> findAll(){
		return this.itemRepository.findAll();
	}
	
	public Item save(ItemForm itemForm) {
		//Entityクラスのインスタンス生成
		Item item = new Item();
		//フィールドセット
		item.setName(itemForm.getName());
		item.setPrice(itemForm.getPrice());
		//カテゴリIDをセット
		item.setCategoryId(itemForm.getCategoryId());
		//repository.savaメソッドを利用してデータの保存
		return this.itemRepository.save(item);
		
		
	}
	
	public Item findById(Integer id) {
		Optional<Item> optionalItem = this.itemRepository.findById(id);
		Item item = optionalItem.get();
		return item;
	}
	//データ更新用メソッド
	public Item update(Integer id, ItemForm itemForm) {
		//データ１件分のEntityクラス取得
		Item item = this.findById(id);
		//Formクラスのフィールドセット
		item.setName(itemForm.getName());
		item.setPrice(itemForm.getPrice());
		//カテゴリIDをセット
		item.setCategoryId(itemForm.getCategoryId());
		//repository.saveメソッドを利用してデータの保存
		return this.itemRepository.save(item);
	}
	
	//データ削除用のメソッドです
	public Item delete(Integer id) {
		//idから該当のEntityクラス取得
		Item item = this.findById(id);
		//EntityクラスのdeletedAtフィールドを現在日時で上書き
		item.setDeletedAt(LocalDateTime.now());
		//更新処理
		return this.itemRepository.save(item);
	}
	
	public List<Item> findByDeletedAtIsNull(){
		return this.itemRepository.findByDeletedAtIsNull();
	}
	
	
		
	}
	
