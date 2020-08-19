package com.prudential.demo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ExcelUtil<C> {
    public String createXlsx(List<C> dataList) throws IOException;
}
