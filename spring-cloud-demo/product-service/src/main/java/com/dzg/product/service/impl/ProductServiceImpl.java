package com.dzg.product.service.impl;


import com.dzg.product.dao.ProductDao;
import com.dzg.product.entity.Product;
import com.dzg.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public Product findById(Long id) {
		return productDao.findById(id).get();
	}

	@Override
	public void save(Product product) {
		productDao.save(product);
	}

	@Override
	public void update(Product product) {
		productDao.save(product);
	}

	@Override
	public void delete(Long id) {
		productDao.deleteById(id);
	}
}
