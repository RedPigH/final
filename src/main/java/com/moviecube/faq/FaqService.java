package com.moviecube.faq;

import java.util.List;
import java.util.Map;

public interface FaqService {

	List<Map<String, Object>> selectFaqList(Map<String, Object> map) throws Exception;

	List<Map<String, Object>> selectFaqList1(Map<String, Object> map) throws Exception;

	List<Map<String, Object>> selectFaqList2(Map<String, Object> map) throws Exception;

	List<Map<String, Object>> selectFaqList3(Map<String, Object> map) throws Exception;

	List<Map<String, Object>> selectFaqList4(Map<String, Object> map) throws Exception;

	List<Map<String, Object>> selectFaqList5(Map<String, Object> map) throws Exception;

	void insertFaq(Map<String, Object> map) throws Exception;

	Map<String, Object> selectFaqDetail(Map<String, Object> map) throws Exception;

	void updateFaq(Map<String, Object> map) throws Exception;

	void deleteFaq(Map<String, Object> map) throws Exception;

	List<Map<String, Object>> faqSearch(Map<String, Object> map) throws Exception;

	List<Map<String, Object>> selectFaqType(Map<String, Object> map) throws Exception;

}
