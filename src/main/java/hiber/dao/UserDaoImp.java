package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getOwner(String car_name, String car_series) {
      TypedQuery<User> findUserQuery = sessionFactory.getCurrentSession().createQuery("from User where car.name= :car_name and car.series = :car_series"/*"from Car where name = :car_name and series = :car_series"*/)
              .setParameter("car_name", car_name)
              .setParameter("car_series", car_series);
      List<User> findUser = findUserQuery.getResultList();
      if (!findUser.isEmpty()) {
         return findUser.get(0);
      } else {
         return null;
      }

//      if (!findUser.isEmpty()) {
//         Car findCar = findUser.get(0);
//         List<User> ListUser = listUsers();
//         User FindUser = ListUser.stream()
//                 .filter(user -> user.getCar().equals(findCar))
//                 .findAny()
//                 .orElse(null);
//         return FindUser;
//      }
//      return null;
   }


   @Override
   public void deleteAllUsers() {
      List<User> users = listUsers();
      for(User user:users){
         sessionFactory.getCurrentSession().delete(user);
      }
   }

}
