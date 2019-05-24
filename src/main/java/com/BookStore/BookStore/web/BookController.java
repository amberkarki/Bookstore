package com.BookStore.BookStore.web;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.BookStore.BookStore.domain.Book;
import com.BookStore.BookStore.domain.BookFile;
import com.BookStore.BookStore.domain.BookFileRepository;
import com.BookStore.BookStore.domain.BookRepository;
import com.BookStore.BookStore.domain.CategoryRepository;


@Controller
public class BookController {

@Autowired
private BookRepository bookrepository;
@Autowired
private CategoryRepository categoryrepository;

//Show all books
@RequestMapping(value="/login")
public String login() {	
    return "login";
}

// Add new book
//@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "/add")
public String addBook(Model model){
	model.addAttribute("book", new Book());
	model.addAttribute("category", categoryrepository.findAll());
    return "addBook";
}
  

@RequestMapping(value ="/booklist")
	public String bookStore(Model model) {
	model.addAttribute("books", bookrepository.findAll());
		return "booklist";
	}
//For Restful Api creation:
@RequestMapping(value="/books", method = RequestMethod.GET)
public @ResponseBody List<Book> bookListRest(){
	return (List<Book>) bookrepository.findAll();
}
//For Restful Api creation to get books by ids
@RequestMapping(value="/book/{id}", method = RequestMethod.GET)
public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId){
	return bookrepository.findById(bookId);
}

@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(Book book){
	bookrepository.save(book);
    return "redirect:/booklist";
}

@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
public String deleteBook(@PathVariable("id") Long bookId, Model model) {
	bookrepository.deleteById(bookId);
    return "redirect:../booklist";
}
// updating books

@RequestMapping(value = "/edit/{id}")
public String updateBook(@PathVariable("id") Long bookId, Model model){
model.addAttribute("book", bookrepository.findById(bookId));
model.addAttribute("category", categoryrepository.findAll());

return "editBook";
}


//BookFileController
/*I am not implementing upload features now..............
@Autowired
private BookFileRepository repository; 	

@Value("${upload.path}")
private String uploadFolder;

@GetMapping("/")
public String index() {
    return "upload";
}


@PostMapping("/upload")
public String fileUpload(@RequestParam("file") MultipartFile file, Model model) {
	// Image Base64.getEncoder().encodeToString(file.file)
	// <img  th:src="@{'data:image/jpeg;base64,'+${file.file}}" />
    if (file.isEmpty()) {
    	model.addAttribute("msg", "Upload failed");
        return "uploadstatus";
    }

    try {
        BookFile fileModel = new BookFile(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        repository.save(fileModel);

        return "redirect:/files";
    } catch (IOException e) {
        e.printStackTrace();
    }

    return "uploadstatus";
}

@GetMapping("/files")
public String fileList(Model model) {
	model.addAttribute("files", repository.findAll());  	
	return "filelist";
}

@GetMapping("/file/{id}")
public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
	Optional<BookFile> fileOptional = repository.findById(id);
	
	if(fileOptional.isPresent()) {
		BookFile file = fileOptional.get();
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
				.body(file.getFile());	
	}
	
	return ResponseEntity.status(404).body(null);
}    
*/

}
