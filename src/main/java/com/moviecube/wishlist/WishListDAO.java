package com.moviecube.wishlist;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.moviecube.dao.AbstractDAO;

@Repository("wishlistDAO")
public class WishListDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectWishList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectList("wishlist.selectWishList", map);
	}
	
	public void insertWishList(Map<String, Object> map) throws Exception{
		insert("wishlist.insertWishList", map);
	}
	
	public void deleteWishList(Map<String, Object> map) throws Exception{
		delete("wishlist.deleteWishList", map);
	}

}
