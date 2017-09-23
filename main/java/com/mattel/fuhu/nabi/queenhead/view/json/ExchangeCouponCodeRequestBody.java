package com.mattel.fuhu.nabi.queenhead.view.json;

import com.mattel.fuhu.nabi.queenhead.entity.Customer;
import com.mattel.fuhu.playform.common.auth.entity.BaseRequestBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ExchangeCouponCodeRequestBody extends BaseRequestBody {

    @ApiModelProperty(value = "Customer's email", required = true)
    private String email;

    @ApiModelProperty(value = "Customer's firstname", required = true)
    private String firstName;

    @ApiModelProperty(value = "Customer's lastname", required = true)
    private String lastName;

    @ApiModelProperty(value = "Customer's nabi userkey", required = true)
    private String nabiUserKey;

    @ApiModelProperty(value = "Coupon Code", required = true)
    private String couponCode;

    public Customer toCustomer(String email, String firstName, String lastName, String nabiUserKey){
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setNabiUserKey(nabiUserKey);
        return customer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNabiUserKey() {
        return nabiUserKey;
    }

    public void setNabiUserKey(String nabiUserKey) {
        this.nabiUserKey = nabiUserKey;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
