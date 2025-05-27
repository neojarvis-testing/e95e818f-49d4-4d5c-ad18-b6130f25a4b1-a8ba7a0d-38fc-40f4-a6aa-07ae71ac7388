package com.examly.springappfeedback.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    
    private int feedbackId;
    private User userId;
    private String message;
    private LocalDateTime dateCreated;
    private int rating;
    

}
