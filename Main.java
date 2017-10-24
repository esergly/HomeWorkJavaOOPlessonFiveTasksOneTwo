import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        MyFileFilter myFileFilter = new MyFileFilter("doc");
        File folderFrom = new File("folderOUT");
        File folderTo = new File("folderIN");
        try {
            FileAndFolderCopy.copyFolder(folderFrom, folderTo, myFileFilter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TwoToOneTextFile.sameWordsFilter(new File("fileOne.txt"), new File("fileTwo.txt"), new File("fileResult.txt"));
    }
}
