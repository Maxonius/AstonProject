package filler;

import collection.CustomArrayList;
import model.Car;

import java.io.File;

public class FileFiller implements DataFiller{
    private final File file;

    public FileFiller(File file) {
        this.file = file;
    }

    @Override
    public CustomArrayList<Car> fill(int count){
        // TODO: заполнение из файла. Файлы хранятся в папке data
        return new CustomArrayList<>();
    }
}
