package com.story.noah.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.bridge.IMessage;


public class AlreadyExistException extends RuntimeException{
     private String message;

     public AlreadyExistException(){

     }
     public AlreadyExistException(String mess){

          super(mess);
          this.message = mess;
     }
}
