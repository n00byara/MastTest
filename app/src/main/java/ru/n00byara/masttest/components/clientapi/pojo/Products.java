package ru.n00byara.masttest.components.clientapi.pojo;

import java.net.URL;
import java.util.List;

public class Products {
    public List<Product> products;

    public class Product{
        public int id;
        public String title;
        public String description;
        public int price;
        public double discountPercentage;
        public double rating;
        public int stock;
        public String brand;
        public String category;
        public String thumbnail;
        public List<String> images;
    }
}
