package generateCode;

import generateCode.DicStockType;

public interface DicStockTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_stock_type
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_stock_type
     *
     * @mbggenerated
     */
    int insert(DicStockType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_stock_type
     *
     * @mbggenerated
     */
    int insertSelective(DicStockType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_stock_type
     *
     * @mbggenerated
     */
    DicStockType selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_stock_type
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(DicStockType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_stock_type
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(DicStockType record);
}