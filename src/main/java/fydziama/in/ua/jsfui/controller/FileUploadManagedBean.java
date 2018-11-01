package fydziama.in.ua.jsfui.controller;
import lombok.extern.java.Log;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.logging.Level;

@Log
@ManagedBean
@SessionScoped
public class FileUploadManagedBean {
    UploadedFile file;

    private byte[] uploadedImage;

    // при загрузке обложки - она будет сохраняться в переменную uploadedImage
    public void uploadImage(FileUploadEvent event) {
        if (event.getFile() != null) {
            uploadedImage = event.getFile().getContents();
        }
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String dummyAction(){
        log.log(Level.WARNING, "dfdfdf");
        System.out.println("Uploaded File Name Is :: "+file.getFileName()+" :: Uploaded File Size :: "+file.getSize());
        return "";
    }
}