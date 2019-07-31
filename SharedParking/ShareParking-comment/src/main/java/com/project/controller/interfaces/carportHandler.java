package com.project.controller.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="ShareParkingcarport",fallbackFactory = TestHandlerFallbackfactory.class)
public interface carportHandler {
	/***
	 * 修改车位为可租状态
	 * @param cid
	 * @return
	 */
	@PutMapping(value="/carport/yes/{cid}")
	public boolean updatestatusy(@PathVariable("cid")int cid);
	/**
	 * 修改车位为不可租状态
	 * @param cid
	 * @return
	 */
	@PutMapping(value="/carport/false/{cid}")
	public boolean updatestatusf(@PathVariable("cid")int cid);
}
