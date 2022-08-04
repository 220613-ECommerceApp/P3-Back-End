package com.revature.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dtos.ProductInfo;
import com.revature.models.Product;
import com.revature.models.ProductTagJunction;
import com.revature.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository pRepo;

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> findByDescription(String description) {
		return productRepository.findByDescriptionContainingIgnoreCase(description);
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

	public Set<Product> findBySimilarNameDescription(String input) {

		List<Product> allProd = new ArrayList<Product>(pRepo.findAll());

		Set<Product> filteredProds = new HashSet<Product>();

		filteredProds.addAll(pRepo.findBySimilarName(input));
		filteredProds.addAll(pRepo.findByDescriptionContainingIgnoreCase(input));

		for (Product p : allProd) {
			String pName = p.getName();
			int[][] dist = new int[pName.length()][input.length()];

			for (int i = 0; i < pName.length(); i++) {
				for (int j = 0; j < input.length(); j++) {
					if (i * j == 0) {
						dist[i][j] = (i == 0 ? j : i);
					} else {
						dist[i][j] = Math.min(Math.min(
								dist[i - 1][j - 1] + ((pName.charAt(i - 1) == input.charAt(j - 1)) ? 0 : 1),
								dist[i - 1][j] + 1), dist[i][j - 1] + 1);
					}
				}
			}
			if (dist[pName.length() - 1][input.length() - 1] <= pName.length() / 2) {
				filteredProds.add(p);
			}
		}
		return filteredProds;

	}

	public List<Product> searchByPriceRange(double startPrice, double endPrice) {
		return productRepository.priceRangeSearch(startPrice, endPrice);
	}
}