package com.craft.demo.utils.fileReaders;

import com.craft.demo.model.PlayerData;

import java.io.IOException;
import java.util.List;

public interface FileReader {

    List<PlayerData> readFile(String fileLocation) throws IOException;
}
