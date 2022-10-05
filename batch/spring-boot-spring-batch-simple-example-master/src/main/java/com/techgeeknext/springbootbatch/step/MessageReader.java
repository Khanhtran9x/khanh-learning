package com.techgeeknext.springbootbatch.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;

/**
 * ItemReader
 */
public class MessageReader implements ItemReader<String> {

    private String[] welcomeMessage = {"Hello World!", "Welcome to TechGeekNext Spring Batch Example!"};

    private int msgIndex = 0;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * It read the data from the given source
     *
     * @return String
     * @throws Exception
     */
    @Override
    public String read() throws Exception {

        //read and pass message to processor to process the message
        if (msgIndex < welcomeMessage.length) {
            //welcomeMessage[0],welcomeMessage[1]
            return welcomeMessage[msgIndex++];
        } else {
            msgIndex = 0;
        }
        return null;
    }

}