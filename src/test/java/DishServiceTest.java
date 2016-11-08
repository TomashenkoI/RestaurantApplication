import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.model.Dish;
import ua.goit.java.service.DishService;

import java.util.List;

@ContextConfiguration(locations = "classpath:application-context.test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class DishServiceTest {

    private DishService dishService;

    @Before
    public void setUp(){
        List<Dish> all = dishService.getDishes();
        all.stream().forEach(e -> dishService.delete(e.getID()));

        Dish dish = new Dish();
        dish.setName("test dish");
        dishService.save(dish);

        Dish dish2 = new Dish();
        dish2.setName("test dish 2");
        dishService.save(dish2);
    }

    @Test
    public void save() throws Exception {
        Dish dish = new Dish();
        dish.setName("test dish 3");
        dishService.save(dish);

        List<Dish> all = dishService.getDishes();
        Assert.assertEquals(all.size(), 3);
    }

    @Test
    public void delete() throws Exception {
        dishService.delete(2);

        List<Dish> all = dishService.getDishes();
        Assert.assertEquals(all.size(), 1);
    }

    @Test
    public void getByName() throws Exception {
        List<Dish> all = dishService.getDishByName("test dish");
        Assert.assertEquals(all.size(), 2);
    }

    @Test
    public void getById() throws Exception {
        List<Dish> all = dishService.getDishes();
        Dish dish = all.get(0);
        String expectedName = dish.getName();

        dish = dishService.getDishByID(dish.getID());

        Assert.assertEquals(dish.getName(), expectedName);
    }

    @Test
    public void getAll() throws Exception {
        List<Dish> all = dishService.getDishes();
        Assert.assertEquals(all.size(), 2);
    }

}