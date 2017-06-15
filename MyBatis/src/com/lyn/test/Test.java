package com.lyn.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.lyn.mapper.CategoryMapper;
import com.lyn.pojo.Category;
import com.lyn.pojo.Order;
import com.lyn.pojo.OrderItem;
import com.lyn.pojo.Product;



public class Test {
	public static void main(String[] args) throws IOException {
		String resource = "com/lyn/config/mybatis-config.xml";
		InputStream inputSteam = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputSteam);
		SqlSession session = sqlSessionFactory.openSession();
//		CategoryMapper mapper = session.getMapper(CategoryMapper.class);
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("id", 2);
//		map.put("name", "≤‚ ‘");
//		session.insert("addCategory",c);
//		session.delete("deleteCategory",3);
//		session.update("updateCategory", c);
//		List<Category> cs = session.selectList("getCategory",5);
//		List<Category> cs = session.selectList("listCategory");
//		List<Order> orders = session.selectList("listOrder");
		List<Category> cs = session.selectList("listCategoryWithProduct");
		for (Category category : cs) {
			System.out.println(category.getName());
			for (Product p : category.getProducts()) {
				System.out.format("\t%s\n",p.getName());
			}
		}
		session.commit();
		session.close();
	}
	
	public static void listOrder(List<Order> orders){
		for (Order order : orders) {
			System.out.println(order.getCode());
			for (OrderItem oi : order.getOrderItems()) {
				String str = String.format("\t%s\t%f\t%d", oi.getProduct().getName(),oi.getProduct().getPrice(),oi.getNumber());
				System.out.println(str);
			}
		}
	}
}
