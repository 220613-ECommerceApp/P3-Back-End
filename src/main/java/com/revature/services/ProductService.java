package com.revature.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dtos.ProductInfo;
import com.revature.models.Product;
import com.revature.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository pRepo;

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Optional<Product> findById(int id) {
		return productRepository.findById(id);
	}

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public List<Product> saveAll(List<Product> productList, List<ProductInfo> metadata) {
		return productRepository.saveAll(productList);
	}

	public void delete(int id) {
		productRepository.deleteById(id);
	}

	
	
	// fuzzy String search
	public List<Product> findBySimilarName(String input) {

		List<Product> allProd = new ArrayList<Product>(pRepo.findAll());
		List<Product> filteredNames = new LinkedList<Product>();

		for (Product p : allProd) {
			
			int[][] dist = new int[p.getName().length()][input.length()];
			for (int i = 0; i < p.getName().length(); i++) {
				for (int j = 0; j < input.length(); j++) {
					if (i == 0 && i != j) {
						dist[i][j] = j;
					}
					if (j == 0) {
						dist[i][j] = i;
					} else if (i > 0 && j > 0) {
						dist[i][j] = Math
								.min(Math.min(dist[i - 1][j - 1] + ((p.getName().charAt(j - 1) == input.charAt(j - 1)) ? 0 : 1),
										dist[i - 1][j] + 1), dist[i][j - 1] + 1);
					}
				}
			}
			if(dist[p.getName().length() - 1][input.length() - 1] <= (p.getName().length()/2)
					|| p.getName().contains(input)) {
				filteredNames.add(p);
			}
		}
		return filteredNames;
	}
}
