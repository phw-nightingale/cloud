package xyz.frt.servercommon.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(64) COMMENT '用户名'")
    private String username;

    @Column(nullable = false, columnDefinition = "VARCHAR(64) NOT NULL COMMENT '密码'")
    private String password;

    @Column(columnDefinition = "TEXT COMMENT '个性化存档'")
    private String save;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> authorities;

    @Column(nullable = false, columnDefinition = "TINYINT COMMENT '客户账户类型'")
    private Integer clientType;

    @Column(nullable = false, columnDefinition = "BIGINT COMMENT '客户账户ID'")
    private Long userDetailId;

    @Column(nullable = false, insertable = false, columnDefinition = "BIT DEFAULT 1 COMMENT '账户过期标记'")
    private boolean accountNonExpired;

    @Column(nullable = false,  insertable = false, columnDefinition = "BIT DEFAULT 1 COMMENT '账户锁定标记'")
    private boolean accountNonLocked;

    @Column(nullable = false,  insertable = false, columnDefinition = "BIT DEFAULT 1 COMMENT '账户授权过期标记'")
    private boolean credentialsNonExpired;

    @Column(nullable = false,  insertable = false, columnDefinition = "BIT DEFAULT 1 COMMENT '账户启用标记")
    private boolean enabled;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setUserDetailId(Long userDetailId) {
        this.userDetailId = userDetailId;
    }

    public Long getUserDetailId() {
        return userDetailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSave(String save) {
        this.save = save;
    }

    public String getSave() {
        return save;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Integer getClientType() {
        return clientType;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
