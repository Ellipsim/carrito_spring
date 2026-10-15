package com.carrito.services;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Service;

import com.carrito.models.Product;
import com.carrito.repositories.ProductRepo;

@Service
public class DatabaseExportService {
	
	private final ProductRepo productRepo;

	public DatabaseExportService(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}
	
	public byte[] exportDatabaseToSql() {

        List<Product> products = productRepo.findAll();
        StringBuilder sql = new StringBuilder();

        sql.append("DELETE FROM product;\n");

        for (Product product : products) {

            String name = product.getName().replace("'", "''");

            sql.append("INSERT INTO product (id, name, price) VALUES (")
               .append(product.getId()).append(", '")
               .append(name).append("', ")
               .append(product.getPrice())
               .append(");\n");
        }

        return sql.toString().getBytes(StandardCharsets.UTF_8);
    }
}