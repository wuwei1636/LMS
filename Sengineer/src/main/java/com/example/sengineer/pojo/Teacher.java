package com.example.sengineer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    int id ;
    String teacher_id;
    String name;
    String password;
    String id_card;
    //院系
    String faculty;
    // 职称
    String professional_title;
    String email;
    String phone;

}
