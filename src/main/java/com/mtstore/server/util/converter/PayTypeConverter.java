package com.mtstore.server.util.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.mtstore.server.beans.enums.PayTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class PayTypeConverter implements Converter<PayTypeEnum> {
    @Override
    public Class supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public PayTypeEnum convertToJavaData(ReadCellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String stringValue = cellData.getStringValue();

        return PayTypeEnum.valueOf(stringValue);
    }

    @Override
    public WriteCellData<?> convertToExcelData(PayTypeEnum value, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {

        return new WriteCellData(value.getDesc());
    }
}

