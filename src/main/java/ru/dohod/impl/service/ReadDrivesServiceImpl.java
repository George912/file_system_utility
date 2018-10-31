package ru.dohod.impl.service;

import org.springframework.stereotype.Service;
import ru.dohod.api.service.ReadDrivesService;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

@Service("readDrivesService")
public class ReadDrivesServiceImpl implements ReadDrivesService {
    private FileSystemView fsv = FileSystemView.getFileSystemView();
    private static final String NET_DISK_TYPE = "Сетевой диск";

    @Override
    public void readDrives() {
        File[] drives = File.listRoots();
        if (drives != null && drives.length > 0) {
            for (File aDrive : drives) {
                String driveType = fsv.getSystemTypeDescription(aDrive);
                if (!NET_DISK_TYPE.equals(driveType)) {
                    System.out.println(driveType + "\t " + aDrive);
                }
            }
        }
    }
}
