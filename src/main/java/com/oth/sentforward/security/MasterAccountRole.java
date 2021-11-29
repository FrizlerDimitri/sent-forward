package com.oth.sentforward.security;

import com.oth.sentforward.persistence.AbstractEntity;
import com.oth.sentforward.security.MasterAccountPermission;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class MasterAccountRole extends AbstractEntity<Long> {


    private String roleName;

    @OneToMany(mappedBy = "role")
    private Set<MasterAccountPermission> permissions= new HashSet<>();

    public MasterAccountRole() {
    }

    public MasterAccountRole(String roleName, Set<MasterAccountPermission> permissions) {
        this.roleName = roleName;
        this.permissions = permissions;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<MasterAccountPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<MasterAccountPermission> permissions) {
        this.permissions = permissions;
    }
}
