package com.project.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
public class LogBean {
	private int id;
	private String username;
	private String time;
	private String message;
}
