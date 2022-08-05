package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tag_junction")
@Data // generate getters/setter, toString, hashCode, and equals methods automatically
@NoArgsConstructor
@AllArgsConstructor
public class ProductTagJunction {

    @Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Product product;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "tag_name", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Tag tag;
	
	public ProductTagJunction(Product product, Tag tag) {
		super();
		this.product = product;
		this.tag = tag;
	}
	
}
