package com.examly.springappfeedback.model;

import java.time.LocalDateTime;

@Entity
public class Feedback {
    
    private int feedbackId;
    private User userId;
    private String message;
    private LocalDateTime dateCreated;
    private int rating;
    

}
