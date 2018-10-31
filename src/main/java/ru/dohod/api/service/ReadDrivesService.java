package ru.dohod.api.service;

/**
 * Reading drives API
 */
public interface ReadDrivesService {
    /**
     * Reading the list of local drives and
     * displaying them on the screen,
     * without network ones,
     * but including flash drives and CD / DVD.
     */
    void readDrives();
}
