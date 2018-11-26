package com.dcssn.tree;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MAIN
 *
 * @author lihy
 */
@SpringBootApplication
@Log
public class TreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TreeApplication.class, args);
        log.info("http://localhost");
    }
}
