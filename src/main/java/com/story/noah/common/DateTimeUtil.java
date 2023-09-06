package com.story.noah.common;

import com.story.noah.constants.PatternConstant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
	public static String getNowForFileName(){
	    return LocalDateTime.now().format(DateTimeFormatter.ofPattern(PatternConstant.DATE_TIME_FILE_NAME_PT));
    }
}
