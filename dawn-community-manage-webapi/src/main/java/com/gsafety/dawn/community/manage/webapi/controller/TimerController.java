package com.gsafety.dawn.community.manage.webapi.controller;

import com.gsafety.dawn.community.manage.contract.model.SystemSetModel;
import com.gsafety.dawn.community.manage.contract.service.TimerService;
import com.gsafety.java.common.exception.HttpError;
import com.gsafety.springboot.common.annotation.LimitIPRequestAnnotation;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api", tags = "Timer Api")
public class TimerController {

    @Autowired
    private TimerService timerService;

    @GetMapping(value = "/timer/{villageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "刷新数据", notes = "flushData(villageId)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Boolean.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<Boolean> flushData(@PathVariable @ApiParam(value = "社区id", required = true)String villageId) {
        Boolean result = timerService.flushData(villageId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
