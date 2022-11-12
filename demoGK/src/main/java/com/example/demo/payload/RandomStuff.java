package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 5/1/2019
 * Github: https://github.com/loda-kun
 */
@Data

public class RandomStuff {
    private String message;

	public RandomStuff(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "RandomStuff [message=" + message + "]";
	}
    
    
}