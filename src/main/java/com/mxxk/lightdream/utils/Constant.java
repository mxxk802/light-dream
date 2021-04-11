package com.mxxk.lightdream.utils;

/**
 * Constant
 *
 * @auther zhang
 * @date 2020/7/23
 **/
public class Constant {

    private Constant(){};

    public enum Language{
        CHINESE("CHINESE", 1), ENGLISH("ENGLISH", 2), JAPANESE("JAPANESE", 3), KOREAN("KOREAN", 4);
        // 成员变量
        private String name;
        private int index;
        // 构造方法
        private Language(String name, int index) {
            this.name = name;
            this.index = index;
        }
        // 普通方法
        public static String getName(int index) {
            for (Language l : Language.values()) {
                if (l.getIndex() == index) {
                    return l.name;
                }
            }
            return null;
        }
        // get set 方法
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getIndex() {
            return index;
        }
        public void setIndex(int index) {
            this.index = index;
        }
    }
}
