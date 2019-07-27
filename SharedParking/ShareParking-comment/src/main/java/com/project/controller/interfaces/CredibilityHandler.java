package com.project.controller.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@FeignClient(name="park-complaint",fallbackFactory=TestHandlerFallbackfactory.class)
public interface CredibilityHandler {
	@PutMapping("/complaint/{id}")
	public int updateCreOrder(@PathVariable("id")int id);

}
