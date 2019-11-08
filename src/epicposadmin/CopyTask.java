/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epicposadmin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.concurrent.Task;

/**
 *
 * @author KwabenaEpic
 */
public class CopyTask extends Task<List<File>> {

    @Override
    protected List<File> call() throws Exception {

        File dir = new File("C:/Windows");
        File[] files = dir.listFiles();
        int count = files.length;

        List<File> copied = new ArrayList<File>();
        int i = 0;
        for (File file : files) {
            if (file.isFile()) {
                this.copy(file);
                copied.add(file);
            }
            i++;
            this.updateProgress(i, count);
        }
        return copied;
    }

    private void copy(File file) throws Exception {
        this.updateMessage("Copying: " + file.getAbsolutePath());
        Thread.sleep(500);
    }

}
