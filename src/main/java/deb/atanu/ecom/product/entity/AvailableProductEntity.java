/**
 * 
 */
package deb.atanu.ecom.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @see ProductEntity
 * @see BrandEntity
 * @see CategoryEntity
 * @see ColourEntity
 * 
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "AVAILABLE_PRODUCT")
public class AvailableProductEntity extends BaseEntity {

	private static final long serialVersionUID = 7347088879213843668L;

	@Id
	@Column(name = "PRODUCT_ID")
	private Long productId;

	@Column(name = "PRODUCT_COUNT")
	private Long productCount;
}
