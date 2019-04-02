package com.moviecube.wishlist;

import java.util.List;
import java.util.Map;

public interface WishListService {

   List<Map<String, Object>> selectWishList(Map<String, Object> map) throws Exception;

   void insertWishList(Map<String, Object> map) throws Exception;
   
   void deleteWishList(Map<String, Object> map) throws Exception;

}