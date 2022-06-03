package SPRING.Entity;


import javax.persistence.*;

@Entity
@Table(name = "test")
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int no;

    @Column(name = "content")
    public String content;


}
