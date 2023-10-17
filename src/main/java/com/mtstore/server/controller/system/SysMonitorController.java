package com.mtstore.server.controller.system;

import com.aizuda.monitor.DiskInfo;
import com.aizuda.monitor.OshiMonitor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取系统信息
 */
@RequestMapping("/monitor")
@RestController
public class SysMonitorController {

    // 注入监控模块 Oshi 调用类
    @Resource
    private OshiMonitor oshiMonitor;

    @GetMapping("server")
    public Map<String, Object> monitor() {
        Map<String, Object> server = new HashMap<>(5);
        // 系统信息
        server.put("sysInfo", oshiMonitor.getSysInfo());
        // CPU 信息
        server.put("cupInfo", oshiMonitor.getCpuInfo());
        // 内存信息
        server.put("memoryInfo", oshiMonitor.getMemoryInfo());
        // Jvm 虚拟机信息
        server.put("jvmInfo", oshiMonitor.getJvmInfo());
        // 获取网络信息
//        server.put("netIoInfo", oshiMonitor.getNetIoInfo());
        // 磁盘信息
        List<DiskInfo> diskInfos = oshiMonitor.getDiskInfos();
        server.put("diskInfos", diskInfos);
        if (CollectionUtils.isNotEmpty(diskInfos)) {
            long usableSpace = 0;
            long totalSpace = 0;
            for (DiskInfo diskInfo : diskInfos) {
                usableSpace += diskInfo.getUsableSpace();
                totalSpace += diskInfo.getTotalSpace();
            }
            double usedSize = (totalSpace - usableSpace);
            // 统计所有磁盘的使用率
            server.put("diskUsePercent", oshiMonitor.formatDouble(usedSize / totalSpace * 100));
        }

        // 系统前 10 个进程
        List<OSProcess> processList = oshiMonitor.getOperatingSystem().getProcesses(null,
                OperatingSystem.ProcessSorting.CPU_DESC, 10);
        List<Map<String, Object>> processMapList = new ArrayList<>();
        for (OSProcess process : processList) {
            Map<String, Object> processMap = new HashMap<>(5);
            processMap.put("name", process.getName());
            processMap.put("pid", process.getProcessID());
            processMap.put("cpu", oshiMonitor.formatDouble(process.getProcessCpuLoadCumulative()));
            processMapList.add(processMap);
        }
        server.put("processList", processMapList);

        return server;
    }
}
