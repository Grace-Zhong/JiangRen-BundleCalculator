package pers.grace.calculator;

import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.TreeMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true, fluent = true)
public class Media {

    private String name;
    private TreeMap<Integer, BigDecimal> table;

}