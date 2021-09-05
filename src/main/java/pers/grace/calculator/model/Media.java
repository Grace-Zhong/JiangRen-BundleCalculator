package pers.grace.calculator.model;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.TreeMap;

/**
 * A single media
 * name - name code
 * table - information table
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true, fluent = true)
public class Media {

    private String name;
    private TreeMap<Integer, BigDecimal> table;

}