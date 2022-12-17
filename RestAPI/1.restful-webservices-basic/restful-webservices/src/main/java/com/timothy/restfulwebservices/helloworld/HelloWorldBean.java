package com.timothy.restfulwebservices.helloworld;

public class HelloWorldBean {
    private String message;

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "message='" + message + '\'' +
                '}';
    }

    public void setMessage(){
        this.message=message;
    }
    public HelloWorldBean(String message) {
        this.message= message;
    }
}
