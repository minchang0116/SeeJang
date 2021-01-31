package com.springboot.pjt1.repository.mapper;

import java.util.List;

import com.springboot.pjt1.repository.dto.ShoppingList;

public interface ShoppingListMapper {
	public List<ShoppingList> selectShoppingListByMemberId(String MemeberId);
	public void insertShoppingList(ShoppingList shoppingList);
	public void updateShoppingList(ShoppingList shoppingList);
	public void deleteShoppingListByShoppingListNo(int shoppingListNo);
}
