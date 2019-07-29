package xyz.frt.serverauth.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * @author phw 937855602@qq.com
 * create on 19-7-26 上午9:43
 */
@Entity
public class Role implements GrantedAuthority {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
