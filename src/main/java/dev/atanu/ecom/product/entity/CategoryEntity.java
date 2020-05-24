/**
 * 
 */
package dev.atanu.ecom.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This entity class is mapped with the table CATEGORY_DETAILS in database
 * 
 * @see ProductEntity
 * @see BrandEntity
 * @see ColourEntity
 * @see AvailableProductEntity
 * 
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "CATEGORY_DETAILS", uniqueConstraints = {
		@UniqueConstraint(name = "UNIQUE_CATEGORY_NAME", columnNames = "CATEGORY_NAME") })
public class CategoryEntity extends BaseEntity {

	private static final long serialVersionUID = -4723499017508681913L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_ID")
	private Long categoryId;

	@Column(name = "CATEGORY_NAME")
	private String categoryName;
}
