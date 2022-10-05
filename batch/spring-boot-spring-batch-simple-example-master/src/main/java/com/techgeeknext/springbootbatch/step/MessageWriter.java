package com.techgeeknext.springbootbatch.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * ItemWriter
 */
public class MessageWriter implements ItemWriter<String> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * ItemWriter writes received data to destination.
     *
     * @param inputMessage
     * @throws Exception
     */
    @Override
    public void write(List<? extends String> inputMessage) throws Exception {
        //write data to console
        for (String outputMsg : inputMessage) {
            System.out.println("Received input data from Step:- " + outputMsg);
        }
    }

}