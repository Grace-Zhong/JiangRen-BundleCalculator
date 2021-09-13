package pers.grace.calculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    public ArrayList<Item> orderList = new ArrayList<>();

    public void add(Item item) {
        orderList.add(item);
    }
}
