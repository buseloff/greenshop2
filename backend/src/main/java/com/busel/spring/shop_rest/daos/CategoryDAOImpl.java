package com.busel.spring.shop_rest.daos;

import com.busel.spring.shop_rest.entities.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> getAllCategories() {
        Session session = sessionFactory.getCurrentSession();
        List<Category> allCategories = session.createQuery("SELECT new Category (id ,title) from Category"
                , Category.class).getResultList();
        allCategories.forEach(category ->  category.setProducts(getCategory(category.getId()).getProducts()));
        return allCategories;
    }

    @Override
    public void saveCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(category);
    }

    @Override
    public Category getCategory(int id) {
        Session session = sessionFactory.getCurrentSession();
        Category category = session.get(Category.class, id);
        return category;
    }

    @Override
    public void deleteCategory(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Category> query = session.createQuery("delete from Category " +
                "where id =:categoryId");
        query.setParameter("categoryId", id);
        query.executeUpdate();
    }
}
