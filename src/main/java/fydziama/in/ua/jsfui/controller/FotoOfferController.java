package fydziama.in.ua.jsfui.controller;

import fydziama.in.ua.dao.FotoOfferDao;
import fydziama.in.ua.entity.FotoOffer;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;
import java.io.*;
import java.util.logging.Level;



@ManagedBean
@ViewScoped
//@Component
@Getter
@Setter
@Log
//@MultipartConfig
@WebServlet
@MultipartConfig(location="/D", fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class FotoOfferController extends AbstractController<FotoOffer>{

//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //handle file upload
//        log.log(Level.WARNING,"kjh");
//    }

    @Autowired
    private FotoOfferDao fotoOfferDao;

    private FotoOffer selectedFoto;

//    private Part uploadedFile;

    private UploadedFile file1;

    private String text;

    private String destination="D:\\tmp\\";

    private File file3;

//    private Part file;
    private String fileContent;

//    public void upload() {
//        log.log(Level.WARNING, "hgf");
//        try {
//            fileContent = new Scanner(file.getInputStream())
//                    .useDelimiter("\\A").next();
//        } catch (IOException e) {
//            // Error handling
//        }
//    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public void upload() {
        log.log(Level.WARNING, "sdfgdfgdfgdf");
        log.log(Level.WARNING, text);
        if (null != uploadedFile) {log.log(Level.WARNING, "6666");
//            try {
//                InputStream is = uploadedFile.getInputStream();
//                text = new Scanner(is).useDelimiter("\\A").next();
//            } catch (IOException ex) {
//            }
        }
    }

    private byte[] uploadedImage;

    // при загрузке обложки - она будет сохраняться в переменную uploadedImage
    public void uploadImage(FileUploadEvent event) {
        if (event.getFile() != null) {
            log.log(Level.WARNING, "dfgdfgdf3333333333333333333");
            uploadedImage = event.getFile().getContents();
        }
    }

    public void myBeanMethod(){
        log.log(Level.WARNING, "sdfdssdf");

    }

    public void save() {
        log.log(Level.WARNING, "dfgdfgdf444444444444444444444444444444444");
        // если было выбрано новое изображение
        if (uploadedImage != null) {
            log.log(Level.WARNING, "dfgdfgdf455555555555555555555555555554");
//            selectedBook.setImage(uploadedImage);
        }



//        bookDao.save(selectedBook);

    }

    private Part file;
    private UploadedFile uploadedFile; // +getter+setter
    public void handleFileUpload(FileUploadEvent event) {
        log.log(Level.WARNING, "dfgsdfgfdsgfdg3456789");

        uploadedFile = event.getFile();
//application code
    }



private File file2;

//    public void upload() {
//        log.log(Level.WARNING, "456778890" + file2.getName());
//        String fileName = uploadedFile.getFileName();
//        String contentType = uploadedFile.getContentType();
//        byte[] contents = uploadedFile.getContents(); // Or getInputStream()
//        // ... Save it, now!
//    }

    public void upload(FileUploadEvent event) {
        log.log(Level.WARNING, "dfgsdfgfdsgfdg3456789");
        UploadedFile uploadedFile = event.getFile();
        String fileName = uploadedFile.getFileName();
        String contentType = uploadedFile.getContentType();
        byte[] contents = uploadedFile.getContents(); // Or getInputStream()
        // ... Save it, now!
    }


    public void copyFile(String fileName, InputStream in) {
        try {
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(destination + fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean vizibilityAction() {
        return false;
    }

    @Override
    public Page<FotoOffer> search(int first, int count, String sortField, Sort.Direction sortDirection) {
        return null;
    }

    @Override
    public void addAction() {

    }

    @Override
    public void editAction() {

    }

    @Override
    public void deleteAction() {

    }
}
