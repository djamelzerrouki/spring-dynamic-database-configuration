package com.example.entites;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Dossier {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name="id")
private Long id ;
private String ch1 ;
public String getch1() { ;
 return ch1; 
}
private String ch2 ;
public String getch2() { ;
 return ch2; 
}
private String ch3 ;
public String getch3() { ;
 return ch3; 
}
private String ch4 ;
public String getch4() { ;
 return ch4; 
}
private String ch5 ;
public String getch5() { ;
 return ch5; 
}
public void setch1(String ch1) { ;
  this.ch1=ch1 ; 
}
public void setch2(String ch2) { ;
  this.ch2=ch2 ; 
}
public void setch3(String ch3) { ;
  this.ch3=ch3 ; 
}
public void setch4(String ch4) { ;
  this.ch4=ch4 ; 
}
public void setch5(String ch5) { ;
  this.ch5=ch5 ; 
}
}
