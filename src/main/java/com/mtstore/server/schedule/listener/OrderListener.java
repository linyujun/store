package com.mtstore.server.schedule.listener;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.mtstore.server.beans.entity.OrderEntity;
import com.mtstore.server.beans.entity.PrintTemplateEntity;
import com.mtstore.server.beans.entity.PrinterEntity;
import com.mtstore.server.beans.entity.StoreOrderEntity;
import com.mtstore.server.schedule.event.order.OrderPaidEvent;
import com.mtstore.server.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderListener {

    final StoreOrderService storeOrderService;

    final OrderService orderService;

    final SysPropertyService propertyService;

    final PrinterService printerService;

    final PrintTemplateService printTemplateService;

    final FeiEPrinterService feiEPrinterService;

    /**
     * 注册监听实现方法
     * @param event 支付成功事件
     */
    @EventListener
    public void invite(OrderPaidEvent event)
    {
        final String orderId = event.getOrderId();
        log.info("支付成功事件！{}", orderId);
        //获取打印配置信息
        Map<String, Object> printConfig = propertyService.getFormData("print");
        log.info("printConfig {}", printConfig);
        if (printConfig.get("printSwitch").equals(true)) {
            log.info("打印已开启~");
//            printerService.test((Integer) printConfig.get("printerId"));
            PrinterEntity printerEntity = printerService.getById((Integer) printConfig.get("printerId"));
            PrintTemplateEntity templateEntity = printTemplateService.getById((Integer) printConfig.get("templateId"));
            if (null != templateEntity && null != printerEntity) {
                OrderEntity orderEntity = orderService.findByOrderId(orderId);
                StoreOrderEntity storeOrderEntity = storeOrderService.getByOrderId(orderId);
                String result = feiEPrinterService.print(printerEntity.getSnId(),
                        printerEntity.getAppKey(),
                        printerEntity.getAppSecret(),
                        getTemplate(templateEntity.getTemplate(), storeOrderEntity));
                log.info("打印结果 {}", result);
            }
        };
    }

    /**
     * 编译打印信息
     * @param content
     * @return
     */
    private  String getTemplate(String content, StoreOrderEntity orderInfo) {
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig());
        Template template = engine.getTemplate(content);
        List<String> detailList = orderInfo.getDetails().stream().map(detail -> {
            List<String> lineList = cutTitle(detail.getProductName());
            lineList.set(0, lineList.get(0) + String.format(" %s%s  %s",
                    addSpace(detail.getPrice().toString(), 6),
                    addSpace(detail.getCartNum().toString(), 3),
                    addSpace(detail.getTotalPrice().toString(), 6)));

            return String.join("<BR>", lineList).concat("<BR>");
        }).collect(Collectors.toList());

        Map dataMap = BeanUtil.copyProperties(orderInfo, Map.class);
        BeanUtil.copyProperties(orderInfo.getAddressInfo(), dataMap);
        dataMap.put("details", String.join("", detailList));
        dataMap.put("storePhone", "0571-88888888");
        dataMap.put("storeAddress", "浙江省杭州市滨江区");
        String result = template.render(dataMap);

        return result;
    }

    /**
     * 此代表通过OPENCHAT生成
     * @param content
     * @return
     */
    private List<String> cutTitle(String content) {
        String input = content;
        int maxBytes = 14;
        List<String> substrings = new ArrayList<>();
        try {
            // Convert the string to bytes using a specific encoding (e.g., UTF-8)
            byte[] inputBytes = input.getBytes("gbk");
            // Cut the byte array into substrings
            int startIndex = 0;
            int endIndex;

            while (startIndex < inputBytes.length) {
                endIndex = startIndex + maxBytes;
                if (endIndex > inputBytes.length) {
                    endIndex = inputBytes.length;
                }
                byte[] substringBytes = new byte[endIndex - startIndex];
                System.arraycopy(inputBytes, startIndex, substringBytes, 0, endIndex - startIndex);
                String substring = new String(substringBytes, "gbk");

                substrings.add(substring);
                System.out.println(substring);

                startIndex = endIndex;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return substrings;
    }

    public static String addSpace(String str, int size) {
        int len = str.length();
        if (len < size) {
            for (int i = 0; i < size - len; i++) {
                str += " ";
            }
        }
        return str;
    }
}
