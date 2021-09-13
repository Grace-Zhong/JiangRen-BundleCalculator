package pers.grace.calculator;

import lombok.extern.slf4j.Slf4j;
import pers.grace.calculator.model.Order;
import pers.grace.calculator.model.OrderProcessor;

import java.util.*;

@Slf4j
public class Main {
    public static void main(String[] args) {
        // read order
        OrderReader reader = new OrderReader();
        Order order = reader.read();
        log.info(order.toString());

        // process order
        OrderProcessor processor = new OrderProcessor();
        processor.process(order);

        // print order result
        OrderPrinter printer = new OrderPrinter();
        printer.print(order, processor.mediaCodeList, processor.bundleResList,
                processor.priceResList, processor.totalPriceList);
    }
}
