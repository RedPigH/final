package com.moviecube.wishlist;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;


@Service("wishlistService")
public class WishListServiceImpl implements WishListService{
	
	@Resource(name = "wishlistDAO")
	private WishListDAO wishlistDAO;

	@Override
	public List<Map<String, Object>> selectWishList(Map<String, Object> map) throws Exception {
		
		return wishlistDAO.selectWishList(map);
	}

	@Override
	public void insertWishList(Map<String, Object> map) throws Exception {
		
		wishlistDAO.insertWishList(map);
	}

	@Override
	public void deleteWishList(Map<String, Object> map) throws Exception {
		
		wishlistDAO.deleteWishList(map);
	}

}
