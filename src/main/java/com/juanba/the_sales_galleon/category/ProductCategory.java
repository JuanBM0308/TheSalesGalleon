package com.juanba.the_sales_galleon.category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Entity
@Table(name = "tb_product_category")
public enum ProductCategory {
    ELECTRONICS(1, "A wide range of devices and equipment that operate on electricity, from computers and smartphones to appliances and entertainment systems."),
    FASHION(2, "Includes clothing, footwear, accessories, and everything related to apparel and fashion trends for various occasions and personal styles."),
    HOME(3, "Encompasses products for the household, such as furniture, decor, kitchenware, textiles, and essential items for daily life at home."),
    GARDENING(4, "Includes tools, plants, seeds, fertilizers, garden decorations, and everything needed for the care and beautification of outdoor spaces."),
    HEALTH(5, "Covers products related to physical and mental well-being, such as vitamin supplements, exercise equipment, personal care products, and health monitoring devices."),
    BEAUTY(6, "Includes cosmetics, skincare and hair care products, fragrances, and beauty tools to enhance personal appearance."),
    SPORTS(7, "Encompasses equipment, sportswear, specialized footwear, and accessories for various physical activities and sports."),
    BOOKS(8, "Covers a variety of reading formats, including physical books, e-books, and audiobooks, spanning diverse genres, topics, and purposes."),
    TOYS(9, "Includes games, dolls, action figures, puzzles, and other items designed for the entertainment and development of children and adults."),
    BABIES(10, "Encompasses essential products for infants and toddlers, such as clothing, diapers, food, strollers, toys, and childcare items."),
    FOOD(11, "Covers a wide selection of fresh, canned, frozen, snacks, and other edible products for human consumption."),
    DRINKS(12, "Includes beverages of all kinds, such as water, juices, soft drinks, coffee, tea, alcoholic beverages, and other options for hydration and enjoyment."),
    OFFICE(13, "Encompasses office supplies, desk equipment, ergonomic furniture, and technology for work environments."),
    AUTOMOTIVE(14, "Includes spare parts, accessories, tools, cleaning products, and other items related to motor vehicles.");

    @Id
    @Column(name = "id_ca")
    private final Integer id;

    @Column(name = "description_ca")
    private final String description;
}
