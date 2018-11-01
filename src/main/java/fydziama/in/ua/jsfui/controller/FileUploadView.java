package fydziama.in.ua.jsfui.controller;

import lombok.extern.java.Log;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.logging.Level;

@Log
@ManagedBean
public class FileUploadView {

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        log.log(Level.WARNING, "xcxgfdg");
        if(file != null) {
            log.log(Level.WARNING, "xcxgf444dg" +file.getFileName());
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}