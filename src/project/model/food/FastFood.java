package project.model.food;

import project.model.User;
import project.model.category.Category;
import project.model.commentfood.CommentFood;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FastFood implements Serializable {
    private int id;
    private String foodName;
    private int price;
    private int quantity;
    private Date expirationDate;  // ngày hết hạn
    private Category category;
    private List<User> likeUser = new ArrayList<>();  // like
    private List<CommentFood> commentFoodList = new ArrayList<>(); // comment

    public FastFood() {
    }

    public FastFood(int id, String foodName) {
        this.id = id;
        this.foodName = foodName;
    }

    public FastFood(int id, String foodName, int price, int quantity) {
        this.id = id;
        this.foodName = foodName;
        this.price = price;
        this.quantity = quantity;
    }

    public FastFood(int id, String foodName, int price, int quantity, Date expirationDate, Category category) {
        this.id = id;
        this.foodName = foodName;
        this.price = price;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.category = category;
    }

    public FastFood(int id, String foodName, int price, int quantity, Date expirationDate, Category category, List<User> likeUser, List<CommentFood> commentFoodList) {
        this.id = id;
        this.foodName = foodName;
        this.price = price;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.category = category;
        this.likeUser = likeUser;
        this.commentFoodList = commentFoodList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        this.expirationDate = calendar.getTime();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<CommentFood> getCommentFoodList() {
        return commentFoodList;
    }

    public void setCommentFoodList(List<CommentFood> commentFoodList) {
        this.commentFoodList = commentFoodList;
    }

    public List<User> getLikeUser() {
        return likeUser;
    }

    public void setLikeUser(List<User> likeUser) {
        this.likeUser = likeUser;
    }

    public String editExpirationDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String st1 = simpleDateFormat.format(expirationDate);
        return st1;
    }

    public boolean checkDay() {
        Date date = new Date();
        date.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String st = simpleDateFormat.format(date);
        if (this.expirationDate.compareTo(date) < 0) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public String toString() {
        return "\nFastFood{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", expirationDate=" + expirationDate +
                ", category=" + category +
                ", likeFastFoodList=" + likeUser +
                ", commentFoodList=" + commentFoodList +
                '}';
    }
}
