package com.sportyshoes.services;

import com.sportyshoes.exceptions.ProductNotFoundException;
import com.sportyshoes.models.Category;
import com.sportyshoes.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Category getCategoryById(long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElseThrow(() -> new ProductNotFoundException());
    }

    @Transactional
    public void updateCategory(Category category) {
        categoryRepository.updateCategory(category);
    }

    @Transactional
    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }

    @Transactional
    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Transactional
    public Category addCategory(String name) {
        Category c = new Category();
        c.setName(name);
        return categoryRepository.save(c);
    }

//	 @Transactional
//	 public String getCategoriesDropDown(long id) {
//		 StringBuilder sb = new StringBuilder("");
//		 List<Category> list = categoryRepository.getAllCategories();
//		 for(Category cat: list) {
//			 if (cat.getID() == id)
//				 sb.append("<option value=" + String.valueOf(cat.getID()) + " selected>" + cat.getName() + "</option>");
//			 else
//				 sb.append("<option value=" + String.valueOf(cat.getID()) + ">" + cat.getName() + "</option>");
//
//		 }
//		 return sb.toString();
//		}
}
