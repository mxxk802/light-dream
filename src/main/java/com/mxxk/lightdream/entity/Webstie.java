package com.mxxk.lightdream.entity;

import java.io.Serializable;

public class Webstie implements Serializable{
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Video.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Video.address
     *
     * @mbggenerated
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Video.introduce
     *
     * @mbggenerated
     */
    private String introduce;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Video.key_word
     *
     * @mbggenerated
     */
    private String keyWord;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Video.video_type
     *
     * @mbggenerated
     */
    private String websiteType;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Video.id
     *
     * @return the value of Video.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Video.id
     *
     * @param id the value for Video.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Video.address
     *
     * @return the value of Video.address
     *
     * @mbggenerated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Video.address
     *
     * @param address the value for Video.address
     *
     * @mbggenerated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Video.introduce
     *
     * @return the value of Video.introduce
     *
     * @mbggenerated
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Video.introduce
     *
     * @param introduce the value for Video.introduce
     *
     * @mbggenerated
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Video.key_word
     *
     * @return the value of Video.key_word
     *
     * @mbggenerated
     */
    public String getKeyWord() {
        return keyWord;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Video.key_word
     *
     * @param keyWord the value for Video.key_word
     *
     * @mbggenerated
     */
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord == null ? null : keyWord.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Video.video_type
     *
     * @return the value of Video.video_type
     *
     * @mbggenerated
     */
    public String getWebsiteType() {
        return websiteType;
    }

    public void setWebsiteType(String websiteType) {
        this.websiteType = websiteType;
    }
}