package com.sportyshoes.services;

import com.sportyshoes.exceptions.ProductNotFoundException;
import com.sportyshoes.models.Category;
import com.sportyshoes.models.Product;
import com.sportyshoes.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Category getCategoryById(long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElseThrow(() -> new ProductNotFoundException());
    }

    @Transactional
    public void updateCategory(Category categoryToUpdate, Category editedCategory) {
        categoryToUpdate.setName(editedCategory.getName());
        categoryRepository.save(categoryToUpdate);
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
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

//	 @Transactional
//	 public String getCategoriesDropDown(long id) {
//		 StringBuilder sb = new StringBuilder("");
//		 List<Category> purchaseList = categoryRepository.getAllCategories();
//		 for(Category cat: purchaseList) {
//			 if (cat.getID() == id)
//				 sb.append("<option value=" + String.valueOf(cat.getID()) + " selected>" + cat.getName() + "</option>");
//			 else
//				 sb.append("<option value=" + String.valueOf(cat.getID()) + ">" + cat.getName() + "</option>");
//
//		 }
//		 return sb.toString();
//		}
}
