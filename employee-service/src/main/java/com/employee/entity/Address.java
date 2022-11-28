package com.employee.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

    @Column(name = "street")
    private String street;

    @Column(name = "landmark")
    private String landmark;

    @Column(name = "postalCode")
    private String postalCode;
}
