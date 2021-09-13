package pers.grace.calculator;

import lombok.extern.slf4j.Slf4j;
import pers.grace.calculator.model.OrderProcessor;

import java.util.*;

@Slf4j
public class Main {
    public static void main(String[] args) {
        // read order
        OrderReader test = new OrderReader();
        HashMap<Integer, String> order = test.read();
        log.info(order.toString());

        // process order
        OrderProcessor processor = new OrderProcessor();
        processor.process(order);
        log.info(processor.mediaCodeList.toString());
        log.info(processor.bundleResList.toString());
        log.info(processor.priceResList.toString());
        log.info(processor.totalPriceList.toString());

        // print order result
        OrderPrinter printer = new OrderPrinter();
        printer.print(order, processor.mediaCodeList, processor.bundleResList,
                processor.priceResList, processor.totalPriceList);
    }
}
