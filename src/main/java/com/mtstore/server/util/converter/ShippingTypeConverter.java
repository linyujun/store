package com.mtstore.server.util.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.mtstore.server.beans.enums.ShippingTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class ShippingTypeConverter implements Converter<ShippingTypeEnum> {
    @Override
    public Class supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public ShippingTypeEnum convertToJavaData(ReadCellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String stringValue = cellData.getStringValue();

        return ShippingTypeEnum.valueOf(stringValue);
    }

    @Override
    public WriteCellData<?> convertToExcelData(ShippingTypeEnum value, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {

        return new WriteCellData(value.getDesc());
    }
}

